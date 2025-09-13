FROM tomcat:9.0-jdk13-openjdk-slim

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY SNS.war /usr/local/tomcat/webapps/ROOT.war

ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
