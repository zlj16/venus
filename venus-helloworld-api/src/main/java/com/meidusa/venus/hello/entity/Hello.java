package com.meidusa.venus.hello.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Hello implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String name;
	private String greeting;
	private int age;
	private byte flag;
	private double cost;
	public EnumType type;
	private Map<String,EnumType> map;
	private Date date;
	private BigDecimal bigDecimal;
	private Timestamp time;
	
	
	public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public Map<String, EnumType> getMap() {
		return map;
	}

	public void setMap(Map<String, EnumType> map) {
		this.map = map;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getCost() {
		return cost;
	}

	public EnumType getType() {
		return type;
	}

	public void setType(EnumType type) {
		this.type = type;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}
	
	public int hashCode(){
	    return HashCodeBuilder.reflectionHashCode(this, false) ;
	}
	
}
