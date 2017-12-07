package com.meidusa.venus.hello.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meidusa.venus.hello.api.ParameterizedService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:./conf/applicationContext-helloworld-client.xml")
public class TestParameterizedService {
	
	@Autowired
	ParameterizedService parameterizedService;
	
	@Test
	public void testArray(){
		System.out.println(parameterizedService.arrayLong(new Long[]{1L,2L,3L,4L,54L}));
		
		System.out.println(parameterizedService.arraylong(new long[]{1,2,3,4,54}));
	}
	
	@Test
	public void testSet(){
		List<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		Set<String> result = parameterizedService.testSet(test);
		
		Assert.assertEquals(test.contains("1"), result.contains("1"));
	}
}
