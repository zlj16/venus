package com.meidusa.venus.hello.exception;

import com.meidusa.venus.annotations.RemoteException;

@RemoteException(errorCode=1988000)
public class HelloRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public HelloRuntimeException(String msg){
        super(msg);
    }
}
