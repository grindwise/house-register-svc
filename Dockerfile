FROM grindwiseinc/bbi:1.0

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

COPY etc/environment /opt/propertymgmt/register-house-service/etc/
COPY etc/RegisterHouseService.yml /opt/propertymgmt/register-house-service/etc/
COPY build/libs/propertymgmt.house.registration-*.jar /opt/propertymgmt/register-house-service/lib/
COPY scripts/register-house-service.sh /opt/propertymgmt/register-house-service/bin/
COPY scripts/service-start.sh /service-start.sh

EXPOSE  7085 7086 27017

VOLUME /data/db

ENTRYPOINT [ "/service-start.sh" ]
