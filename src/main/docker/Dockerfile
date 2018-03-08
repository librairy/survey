FROM anapsix/alpine-java:8u162b12_server-jre
VOLUME /tmp
ADD survey-exec.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -server -jar /app.jar