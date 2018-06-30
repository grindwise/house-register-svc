#!/bin/sh

service_script=$1
auth_id=$2
auth_token=$3

# make sure that database is owned by user mongodb
[ "$(stat -c %U /data/db)" = mongodb ] || chown -R mongodb /data/db

# drop root privilege (no way back), exec provided command as user mongodb
#cmd=exec; for i; do cmd="$cmd '$i'"; done

exec su -s /bin/sh -c mongod mongodb &

echo "service startup: $service_script"

. $service_script $auth_id $auth_token 
