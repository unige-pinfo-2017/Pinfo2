#!/usr/bin/env bash

# Launches the docker images without deploying anything.
# if launched with the argument "soft", it will only relaunch the necessary
# images
shopt -s failglob
set -o nounset

# Reads log file until the mariadb and wildfly servers are ready.
# Note: cannot wait for the nginx proxy, because no verbosity set
function wait_ready {
	local mariaready=0
	local wildflyready=0
	while read line ; do
		if [[ $line =~ 'mysqld: ready for connections' ]] ; then
			echo "==== mariadb server ready ===="
			mariaready=1
		elif [[ $line =~ 'Admin console listening' ]] ; then
			echo "==== wildfly server ready ===="
			wildflyready=1
		fi
		if (( mariaready && wildflyready )) ; then
			break
		fi
	done
}

cd $(dirname $0)

# Make sure the masterproxy network exists
if ! docker network ls | grep masterproxy -q ; then
	echo ==== Création du réseau docker masterproxy pour compatibilité ====
	docker network create --driver bridge masterproxy
fi

# XXX HACK If we are on production, we stealthly modify the docker-compose file
if [ $HOSTNAME = pinfo2 ] ; then # if we are on the server, we
	# Remove the exposed wildfly administration port.
	sed -i -e '/ports:/{$!{N;/9990/d}}' docker-setup/docker-compose.yml
	# Replace `ports:` binding with a simple expose command.
	sed -i -e '/ports:/{$!{N;s/80:80/80/}};/ports:/s//expose:/' \
		docker-setup/docker-compose.yml
fi

cd docker-setup

# If we want to only update the images without messing up the config, we do a
# "soft launch"
if [[ $# != 0 ]] ; then
	if [[ $1 == soft ]] ; then
		echo "Rechargement des images..."
		docker-compose build
		docker-compose up -d
	elif [[ $1 == down ]] ; then
		echo "Extinction des images docker..."
		docker-compose down
	else
		echo "Erreure, $1 n'est pas un argument pour ce script
les arguments sont:
	soft: relance seulement les images modifiée
	down: termine toutes les images sans les relancer
		le comportement par défaut est de terminer les images et puis
		de les relancer"
	fi
else
	echo ==== Lancement des images docker ====
	docker-compose down
	docker-compose build
	dockerlogfile="/tmp/docker-log-$(date +%a-%H-%M)" # Create the log file

	# Create a fifo for the loop
	fifo=/tmp/build-deploy.fifo.$$
	mkfifo $fifo
	tail -F $dockerlogfile >$fifo & # Redirect tail output to the fifo
	tailpid=$!

	docker-compose up 2>&1 1>$dockerlogfile & # Start docker servers images
	wait_ready < $fifo
	kill $tailpid
	rm $fifo
	echo "==== All servers are ready... ===="
fi

# XXX HACK If we are on production, we stealthly modify the docker-compose file
if [ $HOSTNAME = pinfo2 ] ; then
	# We revert de change like we did, so we don't bother version control
	git checkout -- docker-setup/docker-compose.yml
fi

cd ..
