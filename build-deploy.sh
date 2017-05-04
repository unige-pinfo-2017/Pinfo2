#!/usr/bin/env bash

# Automatic deploy of everything :O
cd $(find ../.. -name docker-setup); docker-compose down ; cd ..

cd docker-setup
dockerlogfile=$(mktemp /tmp/docker-logXXXX.log)
docker-compose up 2>&1 1>$dockerlogfile &
tail -f $dockerlogfile | while read line ; do
    if [[ $line =~ 'mysqld: ready for connections' ]] ; then
        break
    fi
done
cd ..
cd labCon; mvn clean install; cd ..
