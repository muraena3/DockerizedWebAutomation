
FROM openjdk:8-jre-alpine
WORKDIR /testrun

# Install maven
FROM maven:3.5-jdk-8-alpine
WORKDIR /testrun

# Prepare by downloading dependencies
ADD pom.xml /testrun/pom.xml
ADD src /testrun/src
ADD config.properties /testrun/config.properties

ARG SELENIUM_HUB

RUN mvn clean test \
-DseleniumHubHost=$SELENIUM_HUB \
-DsuiteXmlFile=src/test/java/_resources/testNGSuites/clicktripz-tests-suites.xml




