#! /bin/bash

rm filebeat.yml
rm environment
rm RegisterHouseService.yml
rm propertymgmt.portfoliomgmt.house.register-*.jar
rm register-house-service.sh
rm service-start.sh

cp ../../etc/filebeat.yml .
cp ../../etc/environment .
cp ../../etc/RegisterHouseService.yml . 
cp ../../build/libs/propertymgmt.portfoliomgmt.house.register-*.jar .
cp ../../scripts/register-house-service.sh . 
cp ../../scripts/service-start.sh . 
