FROM alpine:3.8


#### docker initilize
################################
WORKDIR /app/
ENV HOME /app/
COPY . ./

#### Install Build Dependencies
################################

RUN apk upgrade && apk update
RUN apk --no-cache --update add py-pip \
            python \
            python-dev \
            py-mysqldb \
            libffi-dev \
            openssl-dev \
            mysql-client \
            musl-dev \
            tzdata \
            gcc \
            openssh \
            bash \
            unzip \
            wget \
            rsync \
            openjdk7 \
            nodejs \
            nodejs-npm \
            curl

ENV JAVA_HOME /usr/lib/jvm/default-jvm

RUN pip install --upgrade  pip
RUN pip install Django==1.10.6 requests

RUN python --version


