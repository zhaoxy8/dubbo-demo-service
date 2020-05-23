### 构建dubbo的jre8基础镜像 <br>
### 包含promesute监控配置 <br>
- jmx_prometheus_javaagent-0.12.0.jar <br>
- https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.12.0/jmx_prometheus_javaagent-0.12.0.jar <br>
```
docker build -t registry.cn-hangzhou.aliyuncs.com/infra-xyzhao/jre8:8u251 .
docker push registry.cn-hangzhou.aliyuncs.com/infra-xyzhao/jre8:8u251 
```
