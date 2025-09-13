FROM tomcat:9.0-jdk17-temurin


RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY SNS.war /tmp/SNS.war
RUN mkdir /usr/local/tomcat/webapps/ROOT
RUN cd /usr/local/tomcat/webapps/ROOT && jar -xvf /tmp/SNS.war

ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]


