package com.meidusa.venus.hello;

import java.net.SocketTimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meidusa.venus.hello.api.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:./conf/applicationContext-helloworld-server.xml")
public class TestAutowire {
	
	@Autowired
	private HelloService helloService;
	
	@Test
	public void testGetHello() throws SocketTimeoutException{
		System.out.println(helloService.getHello("abc"));
	}
}
