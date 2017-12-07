package com.meidusa.venus.hello.client;

import java.net.SocketTimeoutException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meidusa.venus.exception.CodedException;
import com.meidusa.venus.hello.api.HelloService;
import com.meidusa.venus.hello.entity.Hello;
import com.meidusa.venus.hello.entity.PaginationResult;
import com.meidusa.venus.hello.entity.Store;
import com.meidusa.venus.hello.exception.HelloNotFoundException;
import com.meidusa.venus.notify.InvocationListener;
import com.meidusa.venus.util.ThreadLocalMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:./conf/applicationContext-helloworld-client.xml")
public class TestHelloService {

    @Autowired
    private HelloService helloService;
    
    @Test
    public void testHello() {
    	for(int j=0;j<2;j++){
    		final int k = j;
	    	Thread t = new Thread(){
	    		public void run(){
	    			try {
	    				for(int i=0;i<3;i++){
	    					helloService.sayHello("test");
	    					System.out.println("say hello~~~"+k+","+i);
	    				}
					} catch (HelloNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	};
	    	t.start();
	    	try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	for(int j=0;j<2;j++){
    		Thread t = new Thread(){
    			public void run(){
    				for(int i=0;i<1;i++){
    		    		try{
    		    			System.out.println(helloService.testHello());
    		    		}catch(Exception e){
    		    			e.printStackTrace();
    		    		}
    		    	}
    			}
    		};
    		t.start();
	    	try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    }
    
    @Test
    public void getHello() throws SocketTimeoutException {
        System.out.println(helloService.getHello("asdfasdf"));
    }
    
    @Test
    public void testLong() {
        System.out.println(helloService.testLong());
    }
    
    @Test
    public void testMap() {
        Store store = new Store();
        store.setBrandId(1L);
        store.setBrandName("test");
        store.setClosingDate(new Timestamp(System.currentTimeMillis()));
        store.setStoreDesc("asdfasdfasdfqwer");
       /* Hello hello = new Hello();
        hello.setName("test");
        hello.setDate(new Date());*/
        Map<String,Store> map = new HashMap<String,Store>();
        map.put("test", store);
        
       System.out.println(helloService.testMap(map));
    }
    
    @Test
    public void tesHello() {
        Hello hello = new Hello();
        hello.setName("test");
        hello.setDate(new Date());
        Hello result = helloService.testPerformace(hello);
        Assert.assertEquals(hello.getDate(), result.getDate());
    }

    @Test
    public void tesList(){
        
	    PaginationResult<List<Hello>> result = helloService.testList();
	    List<Hello> list = result.getT();
        Assert.assertEquals(list.get(0).getClass(),Hello.class);
    }

    @Test
    public void saySync() throws SocketTimeoutException {
        System.out.println(helloService.getHello("venus~~~"));
    }

    @Test
    public void testSyncWithException() {
        try {
            helloService.sayHello("jack");
        } catch (HelloNotFoundException e) {
            System.out.println("throw an user defined HelloNotFoundException");
        }
    }

    @After
    public void afterTest() {
        ThreadLocalMap.reset();
    }

    @Test
    public void testAsync() {
        helloService.sayAsyncHello("jack");
    }
    
    @Test
    public void testCallback() throws Exception {
        System.out.println("start testCallback....");
        // 为了让回调完成，采用countDownLatch计数器方式，避免testcase主线程运行完成而回调未结束的问题
        final CountDownLatch latch = new CountDownLatch(1);

        // 在正常的使用的代码中这个类需要单实例，避免过多的callback listener导致内存问题
        InvocationListener<ArrayList<Hello>> listener = new InvocationListener<ArrayList<Hello>>() {
            public void callback(ArrayList<Hello> myobject) {
                System.out.println(" async call back ,class=" + myobject.getClass() + ", result=" + myobject);
                latch.countDown();
            }

            @Override
            public void onException(Exception e) {
                if (e instanceof CodedException) {
                    CodedException exception = (CodedException) e;
                    System.out.println(" async call back error:" + exception.getErrorCode() + ",message=" + exception.getMessage());
                } else {
                    System.out.println(" async call back message=" + e.getMessage());
                }

                if (e instanceof HelloNotFoundException) {
                    System.out.println(e.getMessage());
                }
                latch.countDown();

            }
        };

        helloService.sayHelloCallback("jack", listener);
        latch.await(2L, TimeUnit.SECONDS);
        Assert.assertEquals(latch.getCount(), 0);
    }
 
}
