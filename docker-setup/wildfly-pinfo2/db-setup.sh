#!/bin/bash

# Some bash security measures:
shopt -s failglob
set -o errexit
set -o nounset

declare -r MARIADB_RELEASE=2.0.0-RC

curl "https://downloads.mariadb.com/Connectors/java/connector-java-${MARIADB_RELEASE}/mariadb-java-client-${MARIADB_RELEASE}.jar" > mariadb-java-client-${MARIADB_RELEASE}.jar

# Start the jboss-cli script using given environement variables.
JDB_CONNECTOR_PATH="$(dirname $0)/mariadb-java-client-${MARIADB_RELEASE}.jar"
DB_TABLE_NAME=meskyne
DB_URL=jdbc:mariadb://172.18.0.3:3306
DB_USER=root
DB_PASS=admin

# Replaces braced expressions in install-db-driver.sh (since it is not
# interpreted, yet I wrote  it in bash style) and route it through the
# jboss cli.
sed -e "s#\${JDB_CONNECTOR_PATH}#${JDB_CONNECTOR_PATH}#g
s#\${DB_TABLE_NAME}#${DB_TABLE_NAME}#g
s#\${DB_URL}#${DB_URL}#g
s#\${DB_USER}#${DB_USER}#g
s#\${DB_PASS}#${DB_PASS}#g" install-db-driver.sh |
    ./bin/jboss-cli.sh -c
