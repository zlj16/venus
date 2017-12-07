package com.meidusa.venus.hello.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.meidusa.venus.annotations.Endpoint;
import com.meidusa.venus.annotations.Param;
import com.meidusa.venus.annotations.PerformanceLevel;
import com.meidusa.venus.annotations.Service;
import com.meidusa.venus.hello.entity.BasicEntity;
import com.meidusa.venus.hello.entity.Hello;
import com.meidusa.venus.hello.entity.PaginationResult;
import com.meidusa.venus.hello.entity.Store;
import com.meidusa.venus.hello.entity.Type;
import com.meidusa.venus.hello.exception.HelloNotFoundException;
import com.meidusa.venus.notify.InvocationListener;

/**
 * Service framework的 HelloService 接口例子.</p> 支持3种调用方式：</p> <li>
 * 请求应答模式：普通的request、response，一般用于接口有返回值</li> <li>
 * 异步请求模式：通常用于接口无返回值，客户端并不关心服务器的处理结果，也不用关心服务器处理多少时间</li> <li>
 * 异步回调模式：接口无返回值，处理通常消化大量时间，需要服务端通知处理结果的业务接口</li>
 * 
 * @author Struct
 */
@Service(name = "HelloService", version = 1, description = "HelloService 服务")
public interface HelloService {
	/**
	 * 无返回结果的服务调用，支持回调方式，该服务在通讯层面上为异步调用
	 * 
	 * @param name
	 * @param invocationListener客户端的回调接口
	 */
	@Endpoint(name = "sayHelloCallback", async = true)
	public abstract void sayHelloCallback(
			@Param(name = "name") String name,
			@Param(name = "callback") InvocationListener<ArrayList<Hello>> invocationListener);

	/**
	 * 无返回结果的服务调用，支持同步或者异步调用, 该接口申明：同步，并且接口申明异常
	 * 
	 * @param name
	 */
	@Endpoint(name = "sayHello", async = false)
	public abstract void sayHello(@Param(name = "name") String name)
			throws HelloNotFoundException;

	/**
	 * 无返回结果的服务调用，支持同步或者异步调用，无异常申明
	 * 
	 * @param name
	 */
	@Endpoint(name = "sayAsyncHello", async = true)
	public abstract void sayAsyncHello(@Param(name = "name") String name);

	/**
	 * 有返回结果的服务调用，该接口只能支持同步调用
	 * 
	 * @param name
	 * @return Hello
	 */
	@Endpoint(name = "getHello", timeWait = 5000)
	public abstract Hello getHello(@Param(name = "name") String name);

	@Endpoint(name = "testHello", timeWait = 5000)
	public abstract Hello testHello();

	@Endpoint(name = "testLong", timeWait = 5000)
	public abstract List<Long> testLong();

	@Endpoint(name = "getHttpHello")
	@PerformanceLevel(error = 5000, warn = 3000, info = 1000, printParams = true, printResult = true)
	public abstract Hello getHttpHello(@Param(name = "name") String name,
			@Param(name = "age") int age);

	/**
	 * 有返回结果的服务调用，该接口只能支持同步调用
	 * 
	 * @param name
	 * @return boolean
	 */
	@Endpoint(name = "checkHello")
	public abstract boolean checkHello(@Param(name = "name") String name);

	/**
	 * @param hello
	 * @return <code>Hello</code>对象
	 */
	@Endpoint(name = "test")
	public abstract Hello testPerformace(@Param(name = "hello") Hello hello);

	@Endpoint(name = "testList")
	public PaginationResult<List<Hello>> testList();
	
	@Endpoint(name = "testBoolean")
	public PaginationResult<Boolean> testBoolean();

	/**
	 * @param hello
	 * @return <code>Hello</code>对象
	 */
	@Endpoint(name = "testChild", loadbalancingKey = "hello.name")
	public abstract Hello testChild(@Param(name = "hello") Hello hello);

	/**
	 * @param hellos
	 */
	@Endpoint(name = "testSet")
	public abstract void testSet(@Param(name = "hello") List<Hello> hellos);

	/**
	 * @param hellos
	 */
	@Endpoint(name = "testMap")
	public abstract Map<String, Store> testMap(
			@Param(name = "map") Map<String, Store> hellos);

	/**
	 * @param input
	 * @return <code>Type</code>
	 */
	@Endpoint(name = "testEnum")
	public abstract Type testEnum(@Param(name = "input") Type input);
	
	@Endpoint()
	public abstract List<BasicEntity> testTypeHandler();
}
