package com.od.dubbotest;

import java.io.IOException;
import java.util.logging.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main( String[] args) throws IOException {
        SpringApplication.run("classpath*:spring-config.xml",args);
        Logger log = Logger.getLogger("com"); 
        System.out.println("Dubbo server started");
        System.out.println("Dubbo 服务端已经启动");
        log.info("Dubbo 等待输出");
        while(true) {
            System.in.read();
        }
    }
}
