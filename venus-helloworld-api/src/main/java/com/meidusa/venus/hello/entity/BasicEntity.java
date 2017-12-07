package com.meidusa.venus.hello.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.meidusa.fastmark.TypeHandle;
import com.meidusa.fastmark.TypeHandleKey;


@TypeHandle(childClasses = { BasicEntityA.class, BasicEntityB.class })
public class BasicEntity {
	@TypeHandleKey
	protected String realType;
	private String a;
	private String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public String getRealType() {
		return realType;
	}

	public void setRealType(String realType) {
		this.realType = realType;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
