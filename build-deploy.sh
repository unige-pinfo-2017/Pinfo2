#!/usr/bin/env bash

# Automatic build and deploy of everything :O
# Note: if you don't want to relaunch the wildfly docker, directly
# mvn install in the labCon folder

cd $(find ../.. -name docker-setup)

docker-compose down

dockerlogfile="/tmp/docker-log-$(date +%a-%H-%M)"
docker-compose build
docker-compose up 2>&1 1>$dockerlogfile &

# Reads log file until both servers are ready.
declare -i ready_count=0
tail -f $dockerlogfile | while read line ; do
    if [[ $line =~ 'mysqld: ready for connections' ]] ; then
        echo "mariadb server ready"
        ready_count=$((ready_count + 1))
    elif [[ $line =~ 'Admin console listening' ]] ; then
        echo "wildfly server ready"
        ready_count=$((ready_count + 1))
    fi
    if (( ready_count == 2 )) ; then
        break
    fi
done
echo $(jobs)
echo "Both servers are ready, deploying..."
sleep 1

cd ../labCon
mvn install
if [[ $(tail -1 $dockerlogfile) =~ 'Deployed "labCon.war"' ]] ; then
    echo "Wildfly log repports a successful deployement"
else
    echo "Hmm..."
fi

cd ../frontend

echo "starting web client server... Kill me if you want to kill it."
npm run-script ng serve

echo "Shutting down... Docker network might still be alive"
cd ..
