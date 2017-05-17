#!/usr/bin/env bash

# Automatic build and deploy of everything :O
cd $(dirname $0)
./launch-servers.sh
echo "==== Deploying... ===="
./hot-deploy.sh back
./hot-deploy.sh front
