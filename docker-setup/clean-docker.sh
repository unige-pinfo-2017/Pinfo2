#!/bin/sh

#!!! LAST RESSORT !!!#
# Deletes ALL docker images cached in docker.

# source is there:
#https://www.digitalocean.com/community/tutorials/how-to-remove-docker-images-containers-and-volumes
docker rmi --force $(docker images -a -q)
