FROM grindwiseinc/depbi:1.0

MAINTAINER Jim Fiolek jim.fiolek@grindwise.com

ENV FILEBEAT_VERSION=6.2.3 

COPY filebeat.yml /

RUN apk add --update-cache curl bash libc6-compat && \
    rm -rf /var/cache/apk/* && \
    curl https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-${FILEBEAT_VERSION}-linux-x86_64.tar.gz -o /filebeat.tar.gz && \
    tar xzvf filebeat.tar.gz && \
    rm filebeat.tar.gz && \
    mv filebeat-${FILEBEAT_VERSION}-linux-x86_64 filebeat && \
    cd filebeat && \
    cp filebeat /usr/bin && \
    rm -rf /filebeat/filebeat.yml && \
    cp /filebeat.yml /filebeat/ && \
    ls -ltr /filebeat && \
    cat /filebeat/filebeat.yml

# install needed package for this behavior image 
RUN echo http://dl-4.alpinelinux.org/alpine/edge/testing >> /etc/apk/repositories && \
    apk add --update --no-cache \
    mongodb

RUN mkdir -p /opt/propertymgmt/register-house-service 
RUN mkdir /opt/propertymgmt/register-house-service/etc
RUN mkdir /opt/propertymgmt/register-house-service/lib
RUN mkdir /opt/propertymgmt/register-house-service/bin
RUN mkdir /opt/propertymgmt/register-house-service/logs
RUN mkdir -p /data/db

COPY environment /opt/propertymgmt/register-house-service/etc/
COPY RegisterHouseService.yml /opt/propertymgmt/register-house-service/etc/
COPY propertymgmt.house.register-*.jar /opt/propertymgmt/register-house-service/lib/
COPY register-house-service.sh /opt/propertymgmt/register-house-service/bin/
COPY service-start.sh /service-start.sh

EXPOSE  7085 7086 27017

VOLUME /data/db

ENTRYPOINT [ "/service-start.sh" ]
