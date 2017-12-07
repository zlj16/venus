package com.meidusa.venus.hello.client;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.meidusa.fastjson.JSON;
import com.meidusa.fastjson.parser.DefaultJSONParser;
import com.meidusa.venus.hello.api.HelloService;
import com.meidusa.venus.hello.entity.Hello;
import com.meidusa.venus.hello.entity.PaginationResult;

public class TestPagination {

	public static void main(String[] args) throws Exception, SecurityException {
		PaginationResult<List<Hello>> result = new PaginationResult<List<Hello>>();
        List<Hello> list = new ArrayList<Hello>();
        Hello h = new Hello();
        h.setName("hello");
        list.add(h);
        list.add(h);
        
        result.setT(list);
        
        String s = JSON.toJSONString(result);
        System.out.println(s);
       
        Method method = HelloService.class.getDeclaredMethod("testList",null);
        Type clazz = method.getGenericReturnType();
        Object obj = JSON.parseObject(s, clazz);
        
       System.out.println(obj);
	}

}
