package com.jpm.sale.exception;

@SuppressWarnings("serial")
public class SalesProcessorException extends Throwable {
	
	private String errMsg; 

	public SalesProcessorException( String errMsg, Throwable t )
	{
		this.errMsg = errMsg;
		printTrace(t);
	}
	
	public SalesProcessorException( String errMsg )
	{
		System.out.println(errMsg);
	}
	
	public void printTrace(Throwable t)
	{
		t.printStackTrace();
		System.out.println( this.errMsg );
	}
	
}
