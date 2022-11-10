# Dockerfile
FROM hub.npro.bitbank.com/sys/java8:1.0
#RUN useradd --create-home --no-log-init --shell /bin/bash dubbo
RUN mkdir -p /app/lib
RUN mkdir -p /app/config
#RUN chown dubbo:dubbo -R /app
#RUN chmod 700 -R /app
RUN ls -lka /app
#USER dubbo
# 设置工作目录
WORKDIR /app
# 将jar文件拷贝到镜像中。注：docker-maven-plugin 会将jar文件拷贝到构建目录target/docker/中
COPY lib /app/lib
COPY config /app/config
COPY *.jar /app
RUN ls -lka /app

EXPOSE 8060
ENV JAVA_HOME /usr/local/java8
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/:$JRE_HOME/lib/
ENV PATH $PATH:$JAVA_HOME/bin
ENV LANG C.UTF-8
ENV CONFIG_DECRYPT_PWD 123qwe
CMD ["java","-jar", "/app/bit_ex_simple-1.0-SNAPSHOT.jar"]
