#!/usr/bin/env bash

# Assuming that the docker containers are already running, redeploys
# the wildfly .war and the web app.
# Unless specified otherwise, will redeploy both the frontend and the backend
# Arguments:
#   front: only deploy frontend
#   back: only deploy the backend
#   no arguments means: deploy both front and backend
#   Both arguments means: really, don't do that, only the first argument is
#   parsed..
shopt -s failglob
set -o nounset

function frontdeploy {
	echo ==== Deploiement du frontend ====
	cd frontend
	if [[ node_modules -ot package.json ]] ; then # only update if modules older than source
		npm install
	fi
	npm run-script ng build -prod
	if [[ -n "$(ls srvdist)" ]] ; then # Prevent conflicts when copying new files
		rm -rf srvdist/*
	fi
	mv -f dist/* srvdist
	# The file is moved to a special place in the file system where we take
	# care to "sanitize" it for use on the image (this means: make sure the
	# permissions and UIDs are set correctly)
	docker exec dockersetup_proxy_1 sh \
		-c 'rm -rf /usr/share/nginx/html/*
	mv /usr/share/nginx/srvdist/* /usr/share/nginx/html
	chown -R nginx /usr/share/nginx/html'
	cd ..
}

function backdeploy {
	echo ==== Deploiement du backend ====
	cd labCon
	rm -rf target bin
	mvn clean
	mvn install
	mv target/restapi.war srvdeploy
	docker exec dockersetup_appserver_1 sh \
		-c 'mv /opt/newwarstash/* /opt/jboss/wildfly/standalone/deployments
	chown -R jboss /opt/jboss/wildfly/standalone/deployments'
	cd ..
}

function proxydeploy {
	echo ==== Deploiement des clées ssl ====
	echo cp /etc/letsencrypt/live/pinfo2.unige.ch/fullchain.pem /opt/production/seckeys/
	sudo cp /etc/letsencrypt/live/pinfo2.unige.ch/fullchain.pem /opt/production/seckeys/
	echo cp /etc/letsencrypt/live/pinfo2.unige.ch/privkey.pem /opt/production/seckeys/
	sudo cp /etc/letsencrypt/live/pinfo2.unige.ch/privkey.pem /opt/production/seckeys/
}

oldoldwd="$PWD"
cd $(dirname $0)
if (( $# >= 1 )) ; then
	if [ $1 = front ] ; then
		frontdeploy
	elif [ $1 = back ] ; then
		backdeploy
	elif [ $1 = proxy ] ; then
		proxydeploy
	else
		echo "Les arguments sont:"
		echo "  front: deploie le front end"
		echo "  back: deploie la backend"
		echo "  proxy: deploie les clées et certificats ssl"
		echo "  Aucun argument deploie les deux"
		echo "  Ce programe n'accepte qu'un argument à la fois"
	fi
else
	echo ==== Deploiement des deux applications.. ====
	frontdeploy
	backdeploy
fi
cd "$oldoldwd"

