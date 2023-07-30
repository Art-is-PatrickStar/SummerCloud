# SummerCloud

#### 一、使用SpringBoot编写,使用SpringCloud组件,构建的Java微服务

> java.version -> 17
> SpringBoot -> 3.1.2
> SpringCloud -> 2022.0.3
> SpringCloudAlibaba -> 2022.0.0.0

> 项目结构:
>
> summercloud-api
>
> summercloud-common
> 
> summercloud-gateway 4000
> 
> summercloud-service
>
> summercloud-archive-service 8091
>
> summercloud-task-service 8092
>
> summercloud-user-service 8093

nacos配置文件:

summercloud.yaml

```yaml
jwt:
  secret:
    key: summercloud
  token:
    expire:
      time: 43200

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

summercloud-gateway-dev.yaml

```yaml
auth:
  skip:
    urls: /summercloud-user-service/user/login,/summercloud-user-service/user/signup,/summercloud-user-service/swagger-ui/**,/summercloud-user-service/swagger-resources/**,/summercloud-user-service/v2/**
```

summercloud-user-service.yaml

```yaml
spring:
  data:
    redis:
      host: 192.168.223.100
      port: 6379
      database: 0
      password: root
      timeout: 5000
      lettuce:
        pool:
          enabled: true
          #连接池最大连接数(使用负值表示没有限制)
          max-active: 10
          # 连接池最大阻塞等待时间(使用负值表示没有限制)
          max-wait: 5000
          #连接池中的最小空闲连接
          min-idle: 5
          #连接池中的最大空闲连接
          max-idle: 10
          time-between-eviction-runs: 1000
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.223.100:3306/summercloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true
    username: 
    password: 
    hikari:
      #连接超时时间,小于250毫秒,否则被重置为默认值30秒
      connection-timeout: 60000
      #空闲连接超时时间,默认值600000单位毫秒,大于等于max-lifetime且max-lifetime>0,会被重置为0,不等于0且小于10秒,会被重置为10秒
      idle-timeout: 500000
      #连接最大存活时间,不等于0且小于30秒,会被重置为默认值30分钟,设置应该比mysql设置的超时时间短
      max-lifetime: 540000
      #最小空闲连接,默认值10,小于0或大于maximum-pool-size,都会重置为maximum-pool-size
      minimum-idle: 5
      #最大连接数,小于等于0会被重置为默认值10,大于零小于1会被重置为minimum-idle的值
      maximum-pool-size: 20
      pool-name: HikariPool-user
  jpa:
    database: mysql
    database-platform: org.hibernate.dialect.MySQLDialect
    show-sql: true
    open-in-view: false
    hibernate:
      ddl-auto: none
```

summercloud-archive-service.yaml

```yaml
spring:
  data:
    redis:
      host: 192.168.223.100
      port: 6379
      database: 0
      password: root
      timeout: 5000
      lettuce:
        pool:
          enabled: true
          max-active: 10
          max-wait: 5000
          min-idle: 5
          max-idle: 10
          time-between-eviction-runs: 1000
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.223.100:3306/summercloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true
    username: 
    password: 
    hikari:
      #连接超时时间,小于250毫秒,否则被重置为默认值30秒
      connection-timeout: 60000
      #空闲连接超时时间,默认值600000单位毫秒,大于等于max-lifetime且max-lifetime>0,会被重置为0,不等于0且小于10秒,会被重置为10秒
      idle-timeout: 500000
      #连接最大存活时间,不等于0且小于30秒,会被重置为默认值30分钟,设置应该比mysql设置的超时时间短
      max-lifetime: 540000
      #最小空闲连接,默认值10,小于0或大于maximum-pool-size,都会重置为maximum-pool-size
      minimum-idle: 5
      #最大连接数,小于等于0会被重置为默认值10,大于零小于1会被重置为minimum-idle的值
      maximum-pool-size: 20
      pool-name: HikariPool-archive
  rabbitmq:
    addresses: 192.168.223.100
    port: 5672
    connection-timeout: 30000
    username: guest
    password: guest
    virtual-host: /

mybatis-plus:
  mapper-locations: classpath*:mapper/*.xml
  configuration.log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

task:
  service:
    url: ${TASK_SERVICE_ENDPOINT:http://localhost:8092}

archive:
  realtime-exchange: resource_realtime_exchange
  realtime-queue: resource_realtime_queue
  realtime-routingKey: resource_realtime_routing_key
  realtime-dead-exchange: resource_realtime_dlq_exchange
  realtime-dead-queue: resource_realtime_dlq_queue
  realtime-dead-routingKey: resource_realtime_dlq_routing_key
  history-exchange: resource_history_exchange
  history-queue: resource_history_queue
  history-routingKey: resource_history_routing_key
  threadpool:
    config:
      corePoolSize: 5
      maximumPoolSize: 10
      keepAliveTime: 60
      queueCapacity: 1024

xxl:
  job:
    admin:
      addresses: http://192.168.223.100:8080/xxl-job-admin
    accessToken: default_token
    executor:
      appname: archive-service-executor
      address:
      ip:
      port: 9998
      logpath: ./logs/archive-service/xxl-job/jobhandler
      logretentiondays: 7
```

summercloud-task-service.yaml

```yaml
spring:
  elasticsearch:
    uris: 192.168.223.100:9200
  data:
    redis:
      host: 192.168.223.100
      port: 6379
      database: 0
      password: root
      timeout: 5000
      lettuce:
        pool:
          enabled: true
          max-active: 10
          max-wait: 5000
          min-idle: 5
          max-idle: 10
          time-between-eviction-runs: 1000
  datasource:
    driver-class-name: org.apache.shardingsphere.driver.ShardingSphereDriver
    url: jdbc:shardingsphere:classpath:sharding.yml
  mail:
    host: smtp.qq.com
    port: 465
    username: 
    password: 
    default-encoding: UTF-8
    properties:
      mail:
        debug: false
        smtp:
          ssl:
            enable: true
            required: false
          auth: true
          timeout: 25000

mybatis-plus:
  mapper-locations: classpath*:mapper/*.xml
  configuration.log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

xxl:
  job:
    admin:
      addresses: http://192.168.223.100:8080/xxl-job-admin
    accessToken: default_token
    executor:
      appname: task-service-executor
      address:
      ip:
      port: 9999
      logpath: ./logs/task-service/xxl-job/jobhandler
      logretentiondays: 7

management:
  health:
    db:
      enabled: false

mail:
  open:
    true
  from: 2544894086@qq.com
  to: 2544894086@qq.com
  cc: 2544894086@qq.com

task:
  threadpool:
    config:
      corePoolSize: 5
      maximumPoolSize: 10
      keepAliveTime: 60
      queueCapacity: 1024
  api-log:
    enabled: true
    log-persist-interval: 1
    log-cleanup-interval: 12
    expire-days: 7

archive:
  service:
    url: ${ARCHIVE_SERVICE_ENDPOINT:http://localhost:8091}

user:
  service:
    url: ${USER_SERVICE_ENDPOINT:http://localhost:8093}
```

#### 二、使用SpringBoot编写,不使用SpringCloud组件,利用k8s自身提供的能力构建的Java微服务

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

   docker run -p 8091:8091 -p 10011:10011 --name archive-service -e JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.rmi.port=10011 -Dcom.sun.management.jmxremote.port=10011 -Djava.rmi.server.hostname=192.168.223.100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:MaxRAMPercentage=25 -XX:MinRAMPercentage=25" -e TASK_SERVICE_ENDPOINT=http://192.168.223.100:8092 -d summer15/archive-service:1.0.0

   docker run -p 8092:8092 -p 10012:10012 --name task-service -e JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.rmi.port=10012 -Dcom.sun.management.jmxremote.port=10012 -Djava.rmi.server.hostname=192.168.223.100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:MaxRAMPercentage=25 -XX:MinRAMPercentage=25" -d summer15/task-service:1.0.0

   docker run -p 8093:8093 -p 10013:10013 --name user-service -e JAVA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.rmi.port=10013 -Dcom.sun.management.jmxremote.port=10013 -Djava.rmi.server.hostname=192.168.223.100 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:MaxRAMPercentage=25 -XX:MinRAMPercentage=25" -d summer15/user-service:1.0.0

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