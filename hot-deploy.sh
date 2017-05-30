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
	echo -e "\e[32m==== Deploiement du frontend ====\e[0m"
	cd frontend
	if [[ node_modules -ot package.json ]] ; then # only update if modules older than source
		npm install
	fi
	npm run-script ng build
	docker cp dist/ dockersetup_proxy_1:/usr/share/nginx/dist/
	if (( $? != 0 )) ; then
		echo  -e "\e[31m==== ERROR couldn't move the webapp package to the airlock ABORTING ====\e[0m"
		exit 1
	fi
	docker exec dockersetup_proxy_1 sh \
		-c 'rm -rf /usr/share/nginx/html/*
			mv /usr/share/nginx/dist/* /usr/share/nginx/html/
			rm -rf /usr/share/nginx/dist
			chown -R nginx /usr/share/nginx/html'
	if (( $? != 0 )) ; then
		echo  -e "\e[31m==== ERROR couldn't manipulate the dist files inside the docker image ====\e[0m"
		exit 1
	else
		echo  -e "\e[32m==== SUCCESS deploying frontend (probably) ====\e[0m"
	fi
	cd ..
}

function backdeploy {
	echo -e "\e[32m==== Deploiement du backend ====\e[0m"
	cd labCon
	rm -rf target bin
	mvn clean install
	docker cp target/restapi.war dockersetup_appserver_1:/opt/newwarstash/restapi.war
	if (( $? != 0 )) ; then
		echo  -e "\e[31m==== ERROR couldn't move the .war to the airlock ABORTING ====\e[0m"
		exit 1
	fi
	docker exec dockersetup_appserver_1 sh \
		-c 'mv /opt/newwarstash/* /opt/jboss/wildfly/standalone/deployments
			chown -R jboss /opt/jboss/wildfly/standalone/deployments'
	if (( $? != 0 )) ; then
		echo  -e "\e[31m==== ERROR couldn't manipulate the .war inside the docker image ====\e[0m"
		exit 1
	else
		echo  -e "\e[32m==== SUCCESS deploying backend (probably) ====\e[0m"
	fi
	cd ..
}

oldoldwd="$PWD"
cd $(dirname $0)
if (( $# >= 1 )) ; then
	if [ $1 = front ] ; then
		frontdeploy
	elif [ $1 = back ] ; then
		backdeploy
	else
		echo "Les arguments sont:"
		echo "  front: deploie le front end"
		echo "  back: deploie la backend"
		echo "  Aucun argument deploie les deux"
		echo "  Ce programe n'accepte qu'un argument à la fois"
	fi
else
	echo ==== Deploiement des deux applications.. ====
	frontdeploy
	backdeploy
fi
cd "$oldoldwd"

