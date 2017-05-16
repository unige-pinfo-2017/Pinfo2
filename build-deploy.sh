#!/usr/bin/env bash

# Automatic build and deploy of everything :O
cd $(dirname $0)
if (( $# > 0 )) ; then
    if [[ $1 =~ clea[rn] ]] ; then
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
    elif [[ $1 == down ]]
        cd docker-setup
        docker-compose down
        cd ..
    fi
fi
./launch-servers.sh
echo "==== Deploying... ===="
./hot-deploy.sh back
./hot-deploy.sh front
