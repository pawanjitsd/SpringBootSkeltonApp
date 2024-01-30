#!/bin/sh

cd /home/app

APP_JAR="SpringBootSkeltonApp.jar"

APP_HOME="/home/app"

APP_NAME="SpringBootSkeltonApp"

LOG_DIR=/home/app

CONSOLE_LOG="$LOG_DIR/console.log"

SYS_JAVA_OPTS="-XX:+UseCompressedOops -Djava.net.preferIPv4Stack=true -Djava.awt.headless=true -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOG_DIR -XX:ErrorFile=$LOG_DIR/hs_err_pid_%p.log"

APP_JAVA_OPTS="-Xms500m -Xmx500m -Djdk.tls.client.protocols=TLSv1.2"

APP_GARBAGE_OPTS="-XX:+UseStringDeduplication -XX:NativeMemoryTracking=detail -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics -verbose:gc -Xloggc:$LOG_DIR/app_gc.log.$(date +%Y%m%d%H%M%S) -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOG_DIR"

echo "APP_HOME: $APP_HOME "

echo "Starting App: $APP_NAME "
#echo "nohup java $SYS_JAVA_OPTS $APP_JAVA_OPTS $DSERVERNICK $APP_GARBAGE_OPTS $APP_CONFIG_FILE -jar $APP_HOME >& $CONSOLE_LOG &"
echo "nohup java $JAVA_OPTS $APP_JAVA_OPTS $APP_CONFIG_FILE -jar $APP_HOME/$APP_WAR >& $CONSOLE_LOG &"

nohup java $SYS_JAVA_OPTS $APP_JAVA_OPTS $DSERVERNICK $APP_GARBAGE_OPTS -jar $APP_HOME/$APP_JAR >& $CONSOLE_LOG &


