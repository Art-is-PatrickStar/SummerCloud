# SummerCloud

#### 使用SpringBoot编写,利用k8s自身提供的能力构建的Java微服务

> java.version -> 8

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

   docker run -p 8091:8091 --name archive-service --env TASK_SERVICE_ENDPOINT=http://192.168.223.100:8092 -d summer15/archive-service:1.0.0

   docker run -p 8092:8092 --name task-service -d summer15/task-service:1.0.0

   docker run -p 8093:8093 --name user-service -d summer15/user-service:1.0.0

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

2. 部署deployment

   kubectl apply -f archive-deployment.yml

   kubectl apply -f task-deployment.yml

   kubectl apply -f user-deployment.yml

3. 部署service

   kubectl apply -f archive-service.yml

   kubectl apply -f task-service.yml

   kubectl apply -f user-service.yml

4. 查看pod

   kubectl get pod
   <img src="./doc/images/k8s-pod.png" alt=""/>

5. 查看deployment

   kubectl get deploy
   <img src="./doc/images/k8s-deploy.png" alt=""/>

6. 查看service

   kubectl get svc
   <img src="./doc/images/k8s-svc.png" alt=""/>

**可以直接通过控制平面访问服务:**

archive-service:

http://192.168.223.101:20601/archive/hello
<img src="./doc/images/archive-service-hello.png" alt=""/>

http://192.168.223.101:20601/archive/getHelloFromTaskService
<img src="./doc/images/archive-service-getHelloFromTaskService.png" alt=""/>

调用的task-service响应:

node1:
<img src="./doc/images/task-service-node1-hello.png" alt=""/>

node2:
<img src="./doc/images/task-service-node2-hello.png" alt=""/>