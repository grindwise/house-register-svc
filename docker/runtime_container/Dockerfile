FROM grindwiseinc/depbi:1.0

MAINTAINER Jim Fiolek jim.fiolek@grindwise.com

# install needed package for this behavior image 
RUN echo http://dl-4.alpinelinux.org/alpine/edge/testing >> /etc/apk/repositories && \
    apk add --update --no-cache \
    mongodb

RUN mkdir -p /opt/propertymgmt/register-house-service 
RUN mkdir /opt/propertymgmt/register-house-service/etc
RUN mkdir /opt/propertymgmt/register-house-service/lib
RUN mkdir /opt/propertymgmt/register-house-service/bin
RUN mkdir -p /data/db

COPY environment /opt/propertymgmt/register-house-service/etc/
COPY RegisterHouseService.yml /opt/propertymgmt/register-house-service/etc/
COPY propertymgmt.house.register-*.jar /opt/propertymgmt/register-house-service/lib/
COPY register-house-service.sh /opt/propertymgmt/register-house-service/bin/
COPY service-start.sh /service-start.sh

EXPOSE  7085 7086 27017

VOLUME /data/db

ENTRYPOINT [ "/service-start.sh" ]
