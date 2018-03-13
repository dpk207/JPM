package com.jpm.sale.main;

import com.jpm.sale.exception.SalesProcessorException;

public class SalesMain {

	public static void main(String[] args) throws SalesProcessorException 
	{
		SalesProcessor salesProcessor = new SalesProcessor();
		salesProcessor.sales();
	}
	
	

}
