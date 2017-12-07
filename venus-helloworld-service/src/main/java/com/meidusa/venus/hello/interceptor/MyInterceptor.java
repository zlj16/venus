package com.meidusa.venus.hello.interceptor;

import com.meidusa.venus.backend.EndpointInvocation;
import com.meidusa.venus.backend.interceptor.AbstractInterceptor;
import com.meidusa.toolkit.common.bean.util.Initialisable;
import com.meidusa.toolkit.common.bean.util.InitialisationException;

public class MyInterceptor extends AbstractInterceptor implements Initialisable {
	private String name;
	private boolean first;
	private TestBean testBean;
	
	public TestBean getTestBean() {
		return testBean;
	}
	public void setTestBean(TestBean testBean) {
		this.testBean = testBean;
	}
	@Override
	public Object intercept(EndpointInvocation invocation) {
		System.out.println(this.getClass().getSimpleName()+" invoked");
		return invocation.invoke();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}

	public void init() throws InitialisationException{
		System.out.println("MyInterceptor inited");
	}
}
