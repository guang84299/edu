服务器启动流程
1：启动redis   bin/redis-server ./etc/redis.conf
2：启动zookeeper  	bin/zkServer.sh start ./conf/zoo.cfg
3：启动dubbo  当前直接启动tomcat
4：启动Solr 当期直接启动tomcat
5：启动nginx图片服务器    ./nginx -c /usr/local/nginx/conf/nginx-fdfs.conf
6：启动edu-service
7：启动edu-laboratory
8：启动edu-teacher
9：启动edu-student