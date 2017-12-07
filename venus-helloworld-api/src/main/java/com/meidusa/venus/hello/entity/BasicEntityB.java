package com.meidusa.venus.hello.entity;

import com.meidusa.fastmark.TypeHandleValue;

@TypeHandleValue("B")
public class BasicEntityB extends BasicEntity {

	public  BasicEntityB(){
		this.realType="B";
	}
	
	private String anotherB;

	public String getAnotherB() {
		return anotherB;
	}

	public void setAnotherB(String anotherB) {
		this.anotherB = anotherB;
	}

}
