# SummerCloud

#### 使用SpringBoot编写,不使用SpringCloud组件,利用k8s自身提供的能力构建的Java微服务

> java.version -> 17
> SpringBoot -> 3

> 项目结构:
>
> summercloud-api
>
> summercloud-common
>
> summercloud-service
>
> summercloud-archive-service 8091
>
> summercloud-task-service 8092
> 
> summercloud-user-service 8093

#### docker部署中间件
1. rabbitmq
   docker run -d -p 5672:5672 -p 15672:15672 --restart=always --name rabbitmq rabbitmq:management
2. redis
   docker run -d -p 6379:6379 --restart=always --name redis redis:7.0.5 --requirepass "root"
3. xxl-job
   docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai --spring.datasource.username=root --spring.datasource.password=root" 
   -p 8080:8080 -v /tmp:/data/applogs --name xxl-job-admin --restart=always -d xuxueli/xxl-job-admin:2.4.0

#### docker操作:

1. 通过dockerfile构建镜像

   docker build . -t summer15/archive-service:1.0.0

   docker build . -t summer15/task-service:1.0.0

   docker build . -t summer15/user-service:1.0.0

2. 运行容器

   docker run -p 8091:8091 -p 10011:10011 --name archive-service -e JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.rmi.port=10011 -Dcom.sun.management.jmxremote.port=10011 -Djava.rmi.server.hostname=192.168.223.100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./" -e TASK_SERVICE_ENDPOINT=http://192.168.223.100:8092 -d summer15/archive-service:1.0.0

   docker run -p 8092:8092 -p 10012:10012 --name task-service -e JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.rmi.port=10012 -Dcom.sun.management.jmxremote.port=10012 -Djava.rmi.server.hostname=192.168.223.100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./" -d summer15/task-service:1.0.0

   docker run -p 8093:8093 -p 10013:10013 --name user-service -e JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.rmi.port=10013 -Dcom.sun.management.jmxremote.port=10013 -Djava.rmi.server.hostname=192.168.223.100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./" -d summer15/user-service:1.0.0

   **http://192.168.223.100 为task服务部署机器的ip**

3. 推送镜像到远程仓库

   docker push summer15/archive-service:1.0.0

   docker push summer15/task-service:1.0.0

   docker push summer15/user-service:1.0.0

#### k8s操作:

    以我本地的k8s集群为例

    master节点(控制平面): 192.168.223.101

    node节点1(工作节点): 192.168.223.102

    node节点2(工作节点): 192.168.223.103

1. 切换命名空间namespace

   kubectl config set-context $(kubectl config current-context) --namespace=wsw

   **切换为自己定义的命名空间**

2. 部署deployment

   kubectl apply -f archive-deployment.yml

   kubectl apply -f task-deployment.yml

   kubectl apply -f user-deployment.yml

3. 部署service

   **通过service使用NodePort方式暴露服务给外部访问:**

   kubectl apply -f archive-service-nodeport.yml

   kubectl apply -f task-service-nodeport.yml

   kubectl apply -f user-service-nodeport.yml

   **通过service使用ClusterIP方式暴露服务,只能在集群内部访问:**

   kubectl apply -f archive-service-clusterip.yml

   kubectl apply -f task-service-clusterip.yml

   kubectl apply -f user-service-clusterip.yml

4. 部署ingress

   ingress相当于网关,通过域名映射和路径映射的方式使外部来访问集群中的服务,ingress需要ingress-controller来实现,
   ingress-controller可以是nginx,traefik等,这里使用nginx,安装完ingress-controller后映射的端口为30546

   kubectl apply -f ingress.yml

5. 查看pod

   kubectl get pod
   <img src="./doc/images/k8s-pod.png" alt=""/>

6. 查看deployment

   kubectl get deploy
   <img src="./doc/images/k8s-deploy.png" alt=""/>

7. 查看service

   kubectl get svc
   <img src="./doc/images/k8s-svc.png" alt=""/>

8. 查看ingress
   
   kubectl get ingress
   <img src="./doc/images/k8s-ingress.png" alt=""/>

**可以直接通过控制平面网关ingress访问服务:**

archive-service:

http://192.168.223.101:30546/archive/health

archive-service调用task-service:

http://192.168.223.101:30546/archive/getHealthFromTaskService

task-service:

http://192.168.223.101:30546/task/health

user-service:

http://192.168.223.101:30546/user/health