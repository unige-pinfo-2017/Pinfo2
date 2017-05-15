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
    npm run-script ng build ${1:-}
    if [[ -n "$(ls srvdist)" ]] ; then # Remove content of srvdist
        rm -rf srvdist/*
    fi
    mv -f dist/* srvdist
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
    mvn install
    local currentlog=$(ls /tmp/docker-log-* | sort --reverse | head -1)
    if [[ $(tail -1 $currentlog) =~ 'Deployed "labCon.war"' ]] ; then
        echo "==== Wildfly log repports a successful deployement ===="
    else
        echo "==== Hmm... ===="
    fi
    cd ..
}

oldoldwd="$PWD"
cd $(dirname $0)
if (( $# >= 1 )) ; then
    if [ $1 = front ] ; then
        frontdeploy ${2:-}
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

