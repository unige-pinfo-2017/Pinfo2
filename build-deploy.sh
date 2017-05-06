#!/usr/bin/env bash

# Automatic build and deploy of everything :O
# Note: if you don't want to relaunch the wildfly docker, directly
# mvn install in the labCon folder

cd $(find ../.. -name docker-setup)

docker-compose down # kill old network if needed
docker-compose build # Update images if needed

dockerlogfile="/tmp/docker-log-$(date +%a-%H-%M)" # Create the log file

# Create a fifo for the loop
fifo=/tmp/build-deploy.fifo.$$
mkfifo $fifo
tail -F $dockerlogfile >$fifo & # Redirect tail output to the fifo
tailpid=$!

docker-compose up 2>&1 1>$dockerlogfile & # Start docker servers images

# Reads log file until both servers are ready.
declare mariaready=0
declare wildflyready=0
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
done < $fifo

kill $tailpid
rm $fifo

echo "==== Both servers are ready, deploying... ===="

cd ../labCon
mvn install
if [[ $(tail -1 $dockerlogfile) =~ 'Deployed "labCon.war"' ]] ; then
    echo "==== Wildfly log repports a successful deployement ===="
else
    echo "==== Hmm... ===="
fi

cd ../frontend

echo "==== starting web client server... Kill me if you want to kill it. ===="
npm run-script ng serve

echo "====Shutting down... Docker network might still be alive===="
cd ..
