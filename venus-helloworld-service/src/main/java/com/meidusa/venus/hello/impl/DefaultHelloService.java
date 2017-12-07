package com.meidusa.venus.hello.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.meidusa.toolkit.common.bean.util.Initialisable;
import com.meidusa.toolkit.common.bean.util.InitialisationException;
import com.meidusa.venus.hello.api.HelloService;
import com.meidusa.venus.hello.entity.BasicEntity;
import com.meidusa.venus.hello.entity.BasicEntityA;
import com.meidusa.venus.hello.entity.BasicEntityB;
import com.meidusa.venus.hello.entity.EnumType;
import com.meidusa.venus.hello.entity.Hello;
import com.meidusa.venus.hello.entity.PaginationResult;
import com.meidusa.venus.hello.entity.Store;
import com.meidusa.venus.hello.entity.Type;
import com.meidusa.venus.hello.exception.HelloNotFoundException;
import com.meidusa.venus.io.serializer.bson.FastBsonSerializerWrapper;
import com.meidusa.venus.io.serializer.json.JsonSerializer;
import com.meidusa.venus.notify.InvocationListener;

public class DefaultHelloService implements HelloService,Initialisable {
	private String greeting;
	
	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	
	public Hello getHello(String name) {
		Hello hello = new Hello();
		hello.setAge(1001);
		hello.setName(name);
		hello.setGreeting(greeting);
		Map<String,EnumType> map = new HashMap<String,EnumType>();
		hello.setMap(map);
		map.put("1", EnumType.ABCD);
		map.put("2", EnumType.HELLO);
		hello.setBigDecimal(new BigDecimal("1.341241233412"));
		/*for(ArrayList<Hello> list : lists){
			list.add(hello);
		}*/
		//throw new RemoteSocketIOException("sokcet error");
		
		return hello;
	}

	public void sayHello(String name)  throws HelloNotFoundException {
		Hello hello = new Hello();
		hello.setAge(1001);
		hello.setName(name);
		hello.setGreeting(greeting);
		Map<String,EnumType> map = new HashMap<String,EnumType>();
		hello.setMap(map);
		map.put("1", EnumType.ABCD);
		map.put("2", EnumType.HELLO);
		hello.setDate(new Date());
		hello.setTime(new Timestamp(System.currentTimeMillis()));
		hello.setBigDecimal(new BigDecimal("1.341241233412"));
	}

	@Override
	public void sayAsyncHello(String name) {
		System.out.println("method sayAsyncHello invoked");
	}
	
	public void sayHelloCallback(String name,
			InvocationListener<ArrayList<Hello>> invocationListener) {
		Hello hello = new Hello();
		hello.setName(name);
		hello.setGreeting(greeting);
		Map<String,EnumType> map = new HashMap<String,EnumType>();
		hello.setMap(map);
		map.put("1", EnumType.ABCD);
		map.put("2", EnumType.HELLO);
		hello.setType(EnumType.TEST);
		hello.setBigDecimal(new BigDecimal("1223123.324"));
		if(invocationListener != null){
			ArrayList<Hello> list = new ArrayList<Hello>();
			list.add(hello);
			invocationListener.callback(list);
		}
		
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Hello Service Properties Setted!");
	}

	/**
	 * 如果在instance里面配置class name，venus将负责这个类的生命周期工作
	 * 那么初始化工作由venus框架负责调用
	 * 
	 * @see Initialisable
	 */
	@Override
	public void init() throws InitialisationException {
		System.out.println("HelloService init method invoked");
		System.out.println("property name greeting="+greeting);
	}

	@Override
	public Hello testPerformace(Hello hello) {
	    //throw new HelloRuntimeException("runtime exception throw");
		return hello;
	}
	
	public static void main(String[] args){
		Hello hello = new Hello();
		hello.setAge(1001);
		hello.setName("asdfqwerqwer");
		hello.setGreeting("venus服务框架欢迎您。。。");
		Map<String,EnumType> map = new HashMap<String,EnumType>();
		hello.setMap(map);
		hello.setType(EnumType.HELLO);
		map.put("1", EnumType.ABCD);
		map.put("2", EnumType.HELLO);
		hello.setBigDecimal(new BigDecimal("1.341241233412"));
		
		FastBsonSerializerWrapper serializer = new FastBsonSerializerWrapper();
		byte[] bts = serializer.encode(hello);
		Object obj = serializer.decode(bts, Hello.class);
		System.out.println("BSON result= "+obj);

		Object bmap = serializer.decode(bts, Map.class);
		System.out.println("BSON result= "+bmap);
		
		JsonSerializer jserializer = new JsonSerializer();
		byte[] jbts = jserializer.encode(hello);
		Hello jobj = (Hello)jserializer.decode(jbts, Hello.class);
		System.out.println("JSON result= "+jobj);
		
		Object mobj = jserializer.decode(jbts, Map.class);
		System.out.println("JSON result= "+mobj);
		
	}

	@Override
	public boolean checkHello(String name) {
		return true;
	}

	@Override
	public Hello testChild(Hello hello) {
		return hello;
	}

	@Override
	public Hello getHttpHello(String name, int age) {
		Hello hello = new Hello();
		hello.setAge(age);
		hello.setName(name);
		hello.setGreeting(greeting);
		hello.type = EnumType.ABCD;
		Map<String,EnumType> map = new HashMap<String,EnumType>();
		hello.setMap(map);
		map.put("1", EnumType.ABCD);
		map.put("2", EnumType.HELLO);
		hello.setBigDecimal(new BigDecimal("1.341241233412"));
		return hello;
	}

	@Override
	public void testSet(List<Hello> hellos) {
		System.out.println(hellos);
	}

	@Override
	public Type testEnum(Type input) {
		return Type.B;
	}

    @Override
    public PaginationResult<List<Hello>> testList() {
        PaginationResult<List<Hello>> result = new PaginationResult<List<Hello>>();
        List<Hello> list = new ArrayList<Hello>();
        Hello h = new Hello();
        h.setName("hello");
        list.add(h);
        list.add(h);
        
        result.setT(list);
        
        return result;
    }

    @Override
    public Map<String, Store> testMap(Map<String, Store> hellos) {
        return hellos;
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
        List<Long> list = new ArrayList<Long>();
        list.add(new Long(11));
        list.add(new Long(121));
        return list;
    }

	@Override
	public PaginationResult<Boolean> testBoolean() {
		 PaginationResult<Boolean> result = new PaginationResult<Boolean>();
		 result.setT(Boolean.TRUE);
		return result;
	}

	@Override
	public List<BasicEntity> testTypeHandler() {
		List<BasicEntity> list = Lists.newArrayList();
		BasicEntityA entity = new BasicEntityA();
		entity.setAnotherA("AAA");
		list.add(entity);
		
		BasicEntityB b = new BasicEntityB();
		b.setAnotherB("BBB");
		
		list.add(b);
		return list;
	}

}
