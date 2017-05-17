#!/usr/bin/env bash

# Launches the docker images without deploying anything.
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

# Make sure we wrote the frontend/srvdist and labCon/srvdeploy files
# So we can handily use them for deployement as a security airlock between
# the host system and the docker image.
mkdir frontend/srvdist 2>/dev/null
mkdir labCon/srvdeploy 2>/dev/null

cd docker-setup

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

cd ..
