#!/usr/bin/env bash

# Automatic build and deploy of everything :O
cd $(dirname $0)
if [[ $1 == 'clean' ]] ; then
    if [[ $2 == 'db' ]] ; then
        echo sudo rm -rf docker-deploy/database
        sudo rm -rf docker-deploy/database
    elif [[ $2 == 'wildfly' ]] ; then
        echo sudo rm -rf docker-deploy/deployments
        sudo rm -rf docker-deploy/deployments
    else
        echo sudo rm -rf docker-deploy
        sudo rm -rf docker-deploy
    fi
    exit
fi
source ./launch-servers.sh
echo "==== Deploying... ===="
source ./hot-deploy.sh back
source ./hot-deploy.sh front
