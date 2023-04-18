FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-al2023-headful
ENV USER=app
ENV UID=12345
ENV GID=23456
RUN yum install -y shadow-utils
RUN groupadd -r app -g 23456 
RUN useradd -u 12345 -G app -h /home/app -D app
USER app
WORKDIR /home/app
ENV PATH="/home/app/.local/bin:${PATH}"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /home/app/SpringBootSkeltonApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djdk.tls.client.protocols=TLSv1.2","-jar","/home/app/SpringBootSkeltonApp.jar"]