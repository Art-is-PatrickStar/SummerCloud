# 基于哪个镜像
FROM openjdk:8
# 将本地文件挂载到容器
VOLUME /tmp
# 拷贝文件到容器
ADD target/summercloud-task-service-1.0.0-SNAPSHOT.jar /usr/share/task-service.jar
ENV JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.rmi.port=10012 -Dcom.sun.management.jmxremote.port=10012 -Djava.rmi.server.hostname=192.168.223.100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Xmx1g -Xms1g -Xmn1g -XX:NewRatio=3 -XX:SurvivorRatio=3 -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./"
# 声明要暴露的端口
EXPOSE 8092 10012
# 配置容器启动后执行命令，并指定使用项目外部的配置文件等
CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -Duser.timezone=GMT+8 -jar /usr/share/task-service.jar
