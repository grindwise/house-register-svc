#!/bin/bash

readonly program="register-house-service"
readonly invalid_args=100
readonly success=0
returnCode=$success

if [ "$#" -ne 2 ]; then
    echo "the address authenticator id and token must be provided"
    returnCode=$invalid_args
    exit $returnCode
else
    readonly address_authenticator_auth_id=$1
    readonly address_authenticator_auth_token=$2
fi

readonly root=/opt/propertymgmt/register-house-service
readonly bin=$root/bin
readonly lib=$root/lib
readonly etc=$root/etc

# source environment
[ -f "${etc}/environment" ] && source ${etc}/environment

jarName=propertymgmt.house.register-${version}.jar

java -Ddw.addressAuthenticatorID=${address_authenticator_auth_id} -Ddw.addressAuthenticatorToken=${address_authenticator_auth_token} -jar ${lib}/${jarName} server ${etc}/RegisterHouseService.yml
returnCode=$?

exit $returnCode
