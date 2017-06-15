FROM docker.io/java:8

VOLUME /tmp

# Expose ports.

ADD start.sh /opt/start.sh
# Expose ports.
EXPOSE 8080 33066 5005 9004

STOPSIGNAL SIGTERM

ADD build/libs/fg-cartservice-0.0.1-SNAPSHOT.jar /opt/app.jar

ENV LOG_ROOT_LEVEL=WARN
ENV JAVA_OPTS="$JAVA_OPTS "

# do not put an array here as it will parse the * as a literal
ENTRYPOINT ["/opt/start.sh"]