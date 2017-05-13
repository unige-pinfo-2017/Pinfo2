#!/usr/bin/env bash

# Automatic build and deploy of everything :O
cd $(dirname $0)
source ./launch-servers.sh
echo "==== Deploying... ===="
source ./hot-deploy.sh back
source ./hot-deploy.sh front
