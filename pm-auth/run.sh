#!/bin/sh
mvn clean install -DskipTests
java -jar target/PropertyMonkey-0.0.1-SNAPSHOT.jar
