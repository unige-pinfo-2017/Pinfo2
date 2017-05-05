#!/usr/bin/env bash

# Automatic build and deploy of everything :O
# Note: if you don't want to relaunch the wildfly docker, directly
# mvn install in the labCon folder
rm /tmp/docker-log-*
cd $(find ../.. -name docker-setup); docker-compose down ; cd ..

cd docker-setup
dockerlogfile=$(mktemp /tmp/docker-log-XXXXXX)
docker-compose up 2>&1 1>$dockerlogfile &
tail -f $dockerlogfile | while read line ; do
    if [[ $line =~ 'mysqld: ready for connections' ]] ; then
        break
    fi
done
cd ..
sleep 3
cd labCon; mvn install; cd ..
