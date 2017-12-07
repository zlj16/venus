package com.meidusa.venus.hello.exception;
import com.meidusa.venus.annotations.RemoteException;

@RemoteException(errorCode=19111001)
public class HelloNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	public HelloNotFoundException(String message){
		super(message);
	}
}
