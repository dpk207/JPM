package com.jpm.sale.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpm.sale.exception.SalesProcessorException;

public class SalesProcessor {

	private List<String> inputData;
	private Map<String, Sales> salesStore = new HashMap<String, Sales>();
	private Map<String, List<Adjustments>> productAdjmnts = new HashMap<String, List<Adjustments>>();
	LogPrinter logPrinter = new LogPrinter();
	
	public void sales() throws SalesProcessorException
	{
		setInputData();
		processSales();
	}
	
	private void setInputData() throws SalesProcessorException
	{
		String filePath = "sales.txt";
		InputReader reader = new InputReader(filePath);
		inputData = reader.readFile();
		if( inputData == null )
			throw new SalesProcessorException("The input data is NULL after reading the file'" + filePath + "'" );
	}
	
	private void processSales() throws SalesProcessorException
	{
		int count = 0;
		boolean isProper = false;
		for( String line : inputData )
		{
			if( line.contains( SalesEnums.Add.name() ) || line.contains( SalesEnums.Subtract.name() ) || line.contains( SalesEnums.Multiply.name() ) )
			{
				isProper = true;
				processType3Msg( line );
			}
			else if( line.matches("[0-9](.*)") )
			{
				isProper = true;
				processType2Msg( line );
			}
			else
			{
				isProper = true;
				processType1Msg( line );
			}
			
			if( isProper )
			{
				count++;
				isProper = false;
			}
			
			if( count % 10 == 0 )
			{
				if( count == 50 )
				{
					System.out.println( "50 Messages have been successfully processed");
					System.out.println( "Pausing the application.....");
					printLog( false );
					break;
				}
				else
				{
					System.out.println( "10 Messages have been successfully processed");
					printLog( true );
				}
			}
		}
	}
	
	private void processType3Msg(String line) throws SalesProcessorException
	{
		String[] str = line.split(" ");
		if( str.length < 3 )
			throw new SalesProcessorException("Invalid format for type 3 Message: " + line );
		
		//TODO Handle if Different Currency
		Adjustments adj = new Adjustments( str[2], Integer.parseInt( str[1].substring( 0, str[1].length() - 1 ) ), getAdj( str[0] ) );
		if( productAdjmnts.containsKey( str[2] ) )
		{
			productAdjmnts.get( str[2] ).add( adj );
		}
		else
		{
			productAdjmnts.put( str[2], new ArrayList<Adjustments>() );
			productAdjmnts.get( str[2] ).add( adj );
		}
		
		addAdjToSales( adj );
	}
	
	private void addAdjToSales( Adjustments adj ) throws SalesProcessorException
	{
		Sales sale = salesStore.get( adj.getPName() );
		if( sale == null )
			throw new SalesProcessorException( "No sales of product type '" + adj.getPName() + "' has happened to add adjustments" );
		
		sale.addAdj( adj );
	}
	
	private String getAdj( String str )
	{
		if( str.equalsIgnoreCase( SalesEnums.Add.name() ) )
			return SalesEnums.Add.name();
		else if( str.equalsIgnoreCase( SalesEnums.Subtract.name() ) )
			return SalesEnums.Subtract.name();
		else
			return SalesEnums.Multiply.name();
	}
	
	private void processType2Msg(String line) throws SalesProcessorException
	{
		String[] str = line.split(" ");
		if( str.length < 7 )
			throw new SalesProcessorException("Invalid format for type 2 Message: " + line );
		
		//TODO Handle if Different Currency 
		modifySales( str[3], Integer.parseInt( str[5].substring( 0, str[5].length() - 1 ) ), Integer.parseInt( str[0] ) );
	}
	
	private void processType1Msg(String line) throws SalesProcessorException
	{
		String[] str = line.split(" ");
		if( str.length < 3 )
			throw new SalesProcessorException("Invalid format for type 1 Message: " + line );
		
		//TODO Handle if Different Currency 
		modifySales( str[0] + "s", Integer.parseInt( str[2].substring( 0, str[2].length() - 1 ) ), 1 );
	}
	
	private void modifySales( String pName, int value, int multiplier )
	{
		Sales sale = null;
		if( salesStore.containsKey( pName ) )
		{
			sale = salesStore.get( pName );
		}
		else
		{
			sale = new Sales( pName, value );
		}
		
		sale.addValue( multiplier * value );
		sale.addSales( multiplier );
		salesStore.put( sale.getPName(), sale );
	}
	
	private void printLog( boolean print10Msg )
	{
		if( print10Msg )
			logPrinter.print10MsgLog( salesStore );
		else
			logPrinter.print50MsgLog( productAdjmnts );
	}
}
