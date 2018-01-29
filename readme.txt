服务器启动流程
1：启动redis   bin/redis-server ./etc/redis.conf
2：启动zookeeper  	bin/zkServer.sh start ./conf/zoo.cfg
3：启动dubbo  直接启动tomcat
4：启动Solr 直接启动tomcat
5：启动nginx图片服务器    ./nginx -c /usr/local/nginx/conf/nginx-fdfs.conf