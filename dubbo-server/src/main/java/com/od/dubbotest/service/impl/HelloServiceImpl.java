package com.od.dubbotest.service.impl;

import java.util.logging.*;
import com.alibaba.dubbo.config.annotation.Service;
import com.od.dubbotest.api.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	public String hello(String name) {
		Logger log = Logger.getLogger("com"); 
		String a = "Logger";
		log.info("HelloService接收到消息:"+name);
		return "hello " + a + name;
	}
}
