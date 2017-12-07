package com.meidusa.venus.hello.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.FileSystemResource;

import com.meidusa.toolkit.common.bean.config.ConfigUtil;
import com.meidusa.toolkit.common.heartbeat.HeartbeatManager;
import com.meidusa.venus.client.ServiceFactory;
import com.meidusa.venus.client.VenusServiceFactory;
import com.meidusa.venus.hello.api.HelloService;
import com.meidusa.venus.hello.api.ParameterizedService;
import com.meidusa.venus.hello.entity.DaisyHello;
import com.meidusa.venus.hello.entity.EnumType;
import com.meidusa.venus.hello.entity.Hello;
import com.meidusa.venus.hello.entity.Type;
import com.meidusa.venus.hello.exception.HelloNotFoundException;

public class HelloWorldClient {
	
	private ServiceFactory serviceFactory;
	
	public HelloWorldClient(){
		VenusServiceFactory serviceFactory = new VenusServiceFactory();
		serviceFactory.setConfigFiles(new FileSystemResource("file:./conf/VenusClient-simple.xml"));
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("./application.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ConfigUtil.addProperties(properties);
		try {
			serviceFactory.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.serviceFactory = serviceFactory;
	}
	
	public void invokService() throws SocketTimeoutException{
		HelloService helloService = serviceFactory.getService(HelloService.class);
		Hello tmp = helloService.getHello("Jack");	
		try {
				helloService.sayHello("Jack");
			} catch (HelloNotFoundException e) {
				System.out.println("message="+e.getMessage() +", HelloNotFoundException is threw From server");
			}
		DaisyHello hello = new DaisyHello();
		hello.setAge(1);
		hello.setCost(1.35);
		hello.setGreeting("lalala");
		hello.setName("daisy");
		hello.setDaisySpecify("lalalalalalala");
		hello.type = EnumType.HELLO;
		Hello result = helloService.testChild(hello);
		assert(result.getClass() == DaisyHello.class);
		assert(((DaisyHello)result).getDaisySpecify().endsWith("lalalalalalala"));
		
		assert(helloService.testEnum(Type.A) == Type.B);
	}
	
	
	public void invokParameterizedService(){
		ParameterizedService parameterizedService = serviceFactory.getService(ParameterizedService.class);
		Long[] result = parameterizedService.arraylong(new long[]{1L,2L});
		
		for(Long l : result){
			System.out.println(l);
		}
		
		System.out.println("getMap result:");
		Map<String,Hello> map = parameterizedService.getMap();
		
		for(Map.Entry<String, Hello> entry: map.entrySet()){
			System.out.println(entry.getValue().getGreeting());
		}
		
		System.out.println("getList result:");
		List<Hello> list =	parameterizedService.getList();
		
		for(Hello entry: list){
			System.out.println(entry.getGreeting());
		}
	}
	
	public static void main(String[] args) throws Exception{
		HelloWorldClient client = new HelloWorldClient();
		client.invokService();
		client.invokParameterizedService();
		//Thread.sleep(10000000L);
		client.serviceFactory.destroy();
		HeartbeatManager.shutdown();
		System.exit(0);
	}
}
