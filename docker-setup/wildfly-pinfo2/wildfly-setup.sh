#!/bin/bash

shopt -s failglob
set -o nounset

cd $(dirname $0)

tempdir=$(mktemp /tmp/wildfly-log-XXXXX.log)
./bin/standalone.sh -b 0.0.0.0 -c standalone.xml > $tempdir &

# Waits until wildfly repports that it is running
while true ; do
    serverstate=$(./bin/jboss-cli.sh -c ":read-attribute(name=server-state)")
    if [[ $serverstate =~ running ]] ; then
        break
    fi
    sleep 1
done

source db-setup.sh
