package com.jpm.sale.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.jpm.sale.exception.SalesProcessorException;

public class InputReader {

	private String filePath;
	
	public InputReader( String filePath)
	{
		this.filePath = filePath;
	}
	
	public List<String> readFile() throws SalesProcessorException
	{
		try 
		{
			return Files.readAllLines(Paths.get(filePath));
		} 
		catch ( IOException e ) 
		{
			throw new SalesProcessorException("Error while reading the input file", e);
		}
	}
}
