package com.meidusa.venus.hello.client;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import com.meidusa.venus.client.ServiceFactory;
import com.meidusa.venus.hello.api.HelloService;
import com.meidusa.venus.hello.entity.Hello;
import com.meidusa.venus.notify.InvocationListener;
import com.meidusa.toolkit.common.runtime.Application;
import com.meidusa.toolkit.common.runtime.ApplicationConfig;

/**
 * only for test
 *
 */
public class HelloworldClientPerformance extends Application<ApplicationConfig> {
	final AtomicLong count = new AtomicLong(0);
	final AtomicLong responseCounter = new AtomicLong(0);
	final int threadSize = Integer.getInteger("thread",100);
	final int total = Integer.getInteger("total",10*100000000);
	final boolean async = Boolean.getBoolean("async");
	final boolean isDebug = Boolean.getBoolean("debug");
	final boolean isList = Boolean.getBoolean("list");
	final int searchResultSize = Integer.getInteger("result", 10);
	final int delay = Integer.getInteger("delay", 0);
	final CountDownLatch latch = new CountDownLatch(threadSize);
	final CountDownLatch request = new CountDownLatch(total);
	InvocationListener<ArrayList<Hello>> listener = new InvocationListener<ArrayList<Hello>>() {
		public void callback(ArrayList<Hello> myobject) {
			long current = responseCounter.incrementAndGet();
			if(isDebug){
				System.out.println("<--- hello="+myobject);
			}
			if(current % 10000 == 0){
				System.out.println("<--totle="+ current);
			}
			if(current == total){
				System.out.println("<--totle="+ current);
				System.out.flush();
			}
		}

		@Override
		public void onException(Exception e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Autowired
	private HelloService helloService;
	private ServiceFactory serviceFactory;
	
	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	@Override
	public void doRun() {
		final StringBuffer buffer = new StringBuffer();
		for(int i=0;i<10;i++){
			buffer.append("hello0");
		}
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					helloService.getHello(buffer.toString());
					
				}
			}
		}.start();
		
		long start = System.currentTimeMillis();
		for(int j=0;j<threadSize;j++){
			new Thread(){
				public void run(){
					long current = 0;
					while(current <= total){
						if(async){
							helloService.sayHelloCallback("jack", listener);
						}else{
							Object object = null;
							try{
								//object = helloService.sayAsyncHello("android");
								 helloService.getHello("abcde");
							}catch(Exception e){
								return;
							}
							if(isDebug){
								System.out.println("--> object="+object);
							}
						}
						
						if(current % 20000 == 0){
							System.out.println("-->totle="+ current);
						}
						
						current = count.incrementAndGet();
					}
					latch.countDown();
				}
			}.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
		}
		System.out.println("time="+(System.currentTimeMillis() - start));
		System.exit(0);
	}

	@Override
	public ApplicationConfig getApplicationConfig() {
		return null;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"file:${project.home:.}/conf/applicationContext-helloworld-client.xml"};
	}

	public static void main(String[] args){
		System.setProperty(ApplicationConfig.PROJECT_MAINCLASS,HelloworldClientPerformance.class.getName());
		Application.main(args);
	}
	
}
