package com.meidusa.venus.hello.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.meidusa.fastmark.TypeHandleValue;

@TypeHandleValue("A")
public class BasicEntityA extends BasicEntity {

	public BasicEntityA(){
		this.realType = "A";
	}
	private String anotherA;

	public String getAnotherA() {
		return anotherA;
	}

	public void setAnotherA(String anotherA) {
		this.anotherA = anotherA;
	}

}
