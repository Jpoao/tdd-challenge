package com.devsuperior.bds02.services.expetion;

public class DataViolationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataViolationException(String msg){
		super(msg);
	}
}
