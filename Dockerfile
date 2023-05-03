FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
ENV USER=app
ENV UID=12345
ENV GID=23456
RUN yum install -y shadow-utils
RUN groupadd -r app -g 23456 
RUN useradd -u 1000 -G app -h /home/app -D app
USER 1000
WORKDIR /home/app
ENV PATH="/home/app/.local/bin:${PATH}"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /home/app/SpringBootSkeltonApp.jar
SHELL ["/bin/sh", "-c"]
EXPOSE 8080
CMD ["java","-jar","/home/app/SpringBootSkeltonApp.jar"]

