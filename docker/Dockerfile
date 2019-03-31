FROM alpine:3.8


# RUN add-apt-repository ppa:gophers/archive
RUN apt-get update

# set timezone to UTC
RUN apt-get install -y tzdata locales
RUN ln -fs /usr/share/zoneinfo/UTC /etc/localtime && dpkg-reconfigure -f noninteractive tzdata
RUN apt-get install -y libmysqlclient-dev mysql-client s3cmd python python-setuptools python-dev python-pip rsync
RUN pip install --upgrade pip
RUN pip install -r /app/pip-dependencies.txt
RUN echo "${CLOUDERA_SSH_KEY}" > ~/.ssh/cloudera_cluster_rsa
RUN chmod 0600 ~/.ssh/cloudera_cluster_rsa
RUN touch ~/.ssh/known_hosts

RUN echo "StrictHostKeyChecking no
UserKnownHostsFile /dev/null
LogLevel VERBOSE" > ~/.ssh/config

COPY ./wordpress  /app/scripts

ENTRYPOINT []