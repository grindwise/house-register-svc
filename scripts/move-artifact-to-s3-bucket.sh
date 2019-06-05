#!/bin/bash

EXIT_CODE=0

AWS="/usr/bin/aws"

SERVICE_NAME="house-registration"

BUCKET_NAME="house-register-artifacts-east"
FULL_BUCKET_NAME="s3://${BUCKET_NAME}/binary-artifacts/"
BINARY_ARTIFACT=$1
DEPLOYER_ACCESS_ID=$2
DEPLOYER_SECRET_KEY=$3

export $DEPLOYER_ACCESS_ID
export $DEPLOYER_SECRET_KEY

export


echo "Full bucket name: ${FULL_BUCKET_NAME}"
echo "Binary artifact: ${BINARY_ARTIFACT}"

$AWS s3 cp ${BINARY_ARTIFACT} ${FULL_BUCKET_NAME}

if [ $? -eq 0 ]; then
    echo "binary artifact successfully copied to S3"
else
    echo "binary artifact copy failed to S3"
    EXIT_CODE=1
fi

exit $EXIT_CODE
