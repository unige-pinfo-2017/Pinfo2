#!/bin/bash

shopt -s failglob
set -o nounset

cd $(dirname $0)


# Adds to wildfly the ability to answer to CORS requests, needed for
# using extraneous libraries in the webapp. More info on CORS:
# https://en.wikipedia.org/wiki/Cross-origin_resource_sharing 5 May 2017
sed --in-place \
    --file=enable-CORS.sed \
      standalone/configuration/standalone.xml

# Start up the wildfly server.
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

# Send to wildfly the instructions to setup the database
source db-setup.sh
