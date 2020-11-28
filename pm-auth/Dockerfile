FROM amazonlinux:2
RUN yum -y update && yum -y install java-11-amazon-corretto-headless
COPY ./target/pm-auth-1.0-SNAPSHOT.jar /pm/
EXPOSE 8081
CMD ["java", "-jar", "/pm/pm-auth-1.0-SNAPSHOT.jar"]


