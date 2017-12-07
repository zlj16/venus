package com.meidusa.venus.hello.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.meidusa.venus.hello.api.HelloService;
import com.meidusa.venus.hello.entity.BasicEntity;
import com.meidusa.venus.hello.entity.Hello;
import com.meidusa.venus.hello.entity.PaginationResult;
import com.meidusa.venus.hello.entity.Store;
import com.meidusa.venus.hello.entity.Type;
import com.meidusa.venus.hello.exception.HelloNotFoundException;
import com.meidusa.venus.notify.InvocationListener;

public class LocalHelloService implements HelloService {

	@Override
	public void sayHelloCallback(String name,
			InvocationListener<ArrayList<Hello>> invocationListener) {
		
	}

	@Override
	public void sayHello(String name) throws HelloNotFoundException {
		System.out.println("Local helloService.sayHello:"+name);
	}

	@Override
	public void sayAsyncHello(String name) {
		
	}

	@Override
	public Hello getHello(String name) {
		Hello hello = new Hello();
		hello.setName("Local-"+name);
		return hello;
	}

	@Override
	public boolean checkHello(String name) {
		return false;
	}

	@Override
	public Hello testPerformace(Hello hello) {
		return null;
	}

	@Override
	public Hello testChild(Hello hello) {
		return hello;
	}

	@Override
	public Hello getHttpHello(String name, int age) {
		Hello hello = new Hello();
		hello.setName("Local-"+name);
		hello.setAge(age);
		return hello;
	}

	@Override
	public void testSet(List<Hello> hellos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type testEnum(Type input) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public PaginationResult<List<Hello>> testList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Store> testMap(Map<String, Store> hellos) {
        return null;
    }

    @Override
    public Hello testHello() {
        Hello hello = new Hello();
        hello.setName("Local-");
        hello.setAge(12);
        return hello;
    }

    @Override
    public List<Long> testLong() {
        return null;
    }

	@Override
	public PaginationResult<Boolean> testBoolean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BasicEntity> testTypeHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
