FROM grindwiseinc/pipeline:1.0

MAINTAINER Jim Fiolek jim.fiolek@grindwise.com

# install mongodb
RUN \
  echo '@testing http://dl-4.alpinelinux.org/alpine/edge/testing' >> /etc/apk/repositories && \
  apk add --no-cache mongodb@testing 
#&& 
#\
#  rm /usr/bin/mongoperf

VOLUME ["/data/db"]
CMD [ "mongod" ]
