#!/usr/bin/env bash

testid=$RANDOM

tag="clicktripz:test-"${testid}
echo ${tag}

HOSTIP=$(ipconfig getifaddr en0)
# add condition for linux

echo ${HOSTIP}

{
    docker network create grid
    docker run -d -p 4444:4444 --net grid --name selenium-hub selenium/hub:latest
    docker run -d --net grid -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-chrome:latest

# 192.168.99.1

    docker build --build-arg SELENIUM_HUB=${HOSTIP} -t ${tag} .
    docker run -d ${tag}
    CID=$(docker ps -alq)
    echo ${CID}
    sleep 5s
    docker cp ${CID}:/testrun/target/surefire-reports ./results-${testid}/
    open results-${testid}/emailable-report.html
}
docker stop $(docker ps -q)
docker rm $(docker ps -qa)
docker network prune

# This will remove all networks not used by at least one container.
# Are you sure you want to continue? [y/N]
#yes y

#remove untagged images
#docker rmi $(docker images | grep "^<none>")
#docker rmi $(docker images | grep "^<0.0.5>")
