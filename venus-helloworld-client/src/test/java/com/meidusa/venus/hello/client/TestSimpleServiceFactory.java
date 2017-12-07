package com.meidusa.venus.hello.client;

import java.util.List;

import com.meidusa.venus.client.ServiceFactory;
import com.meidusa.venus.client.simple.SimpleServiceFactory;
import com.meidusa.venus.hello.api.HelloService;
import com.meidusa.venus.hello.entity.BasicEntity;
import com.meidusa.venus.hello.entity.BasicEntityA;

public class TestSimpleServiceFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceFactory factory = new SimpleServiceFactory("127.0.0.1",16800);
		HelloService helloService = factory.getService(HelloService.class);
		//Hello hello = helloService.getHello("Simple Service Factory test");
		//PaginationResult<Boolean> pag = helloService.testBoolean();
		//System.out.println(pag);
		
		Object entities = helloService.testList();
		/*for(BasicEntity e: entities){
			System.out.println(e);
		}*/
		System.out.println(entities);
		//assert("Simple Service Factory test".equals(hello.getName()));
	}

}
