# base0image
FROM openjdk:11
ARG JAR_FILE=build.libs.*.jar
COPY ${JAR_FILE}vapp.jar
ENTRYPOINT ["java","jar","/app,js"]