FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
ENV USER=app
RUN yum install -y shadow-utils
RUN yum install -y procps
RUN groupadd -r -g 1000  app 
RUN useradd -r -u 1000 -g app app
WORKDIR /home/app
#RUN mkdir /home/app/.aws
#VOLUME ["/home/app/.aws"]
#RUN chown -R app:app /home/app/.aws
ENV PATH="/home/app/.local/bin:${PATH}"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /home/app/SpringBootSkeltonApp.jar
SHELL ["/bin/sh", "-c"]
EXPOSE 8080
USER app
CMD ["java", "-jar","/home/app/SpringBootSkeltonApp.jar"]

