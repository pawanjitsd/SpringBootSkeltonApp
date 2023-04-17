FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-al2-full
ENV USER=app
ENV UID=12345
ENV GID=23456
RUN addgroup -g 23456 app
RUN adduser -u 12345 -G app -h /home/app -D app
USER app
WORKDIR /home/app
ENV PATH="/home/app/.local/bin:${PATH}"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /home/app/SpringBootSkeltonApp.war
EXPOSE 8080
ENTRYPOINT ["java", "-Djdk.tls.client.protocols=TLSv1.2","-jar","/home/app/SpringBootSkeltonApp.jar"]