package com.meidusa.venus.hello.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PaginationResult<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
    
    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }
    
}
