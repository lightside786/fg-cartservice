#!/bin/bash

echo "Executing docker start.sh file"
if [ ! -z "${HEAP_JAVA_OPTS}" ]; then
	export JAVA_OPTS="${JAVA_OPTS} ${HEAP_JAVA_OPTS}"
fi

echo ${SPRING_PROFILE}
if [ ! -z "${SPRING_PROFILE}" ]; then
    export JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=${SPRING_PROFILE}"
    export MONITORING_JAVA_OPTS="${MONITORING_JAVA_OPTS} -Dnewrelic.environment=${SPRING_PROFILE}"
else
    echo ${SPRING_PROFILE}
    export JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=aws"
fi

if [ ! -z "${BOOTSTRAP_OPTS}" ]; then
    export JAVA_OPTS="${JAVA_OPTS} ${BOOTSTRAP_OPTS}"
fi

if [ ! -z "${MONITORING_JAVA_OPTS}" ]; then
	export JAVA_OPTS="${JAVA_OPTS} ${NEWRELIC_JAVA_OPTS} ${MONITORING_JAVA_OPTS}"
fi


exec java ${JAVA_OPTS} -DLOG_ROOT_LEVEL=${LOG_ROOT_LEVEL} -Djava.security.egd=file:/dev/./urandom -jar /opt/*.jar