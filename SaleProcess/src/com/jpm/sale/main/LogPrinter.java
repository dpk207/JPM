package com.jpm.sale.main;

import java.util.List;
import java.util.Map;

public class LogPrinter {

	public void print10MsgLog( Map<String, Sales> salesStore )
	{
		//TODO format if needed
		for( String key : salesStore.keySet() )
		{
			Sales sale = salesStore.get( key );
			System.out.println( "Product: " + sale.getPName() + ", Sales: " + sale.getTotalSales() + ", Value: " + sale.getPValue() + "p" );
		}
	}
	
	public void print50MsgLog( Map<String, List<Adjustments>> productAdjmnts )
	{
		//TODO format if needed
		for( String key : productAdjmnts.keySet() )
		{
			List<Adjustments> adjs = productAdjmnts.get( key );
			for( Adjustments adj : adjs )
			{
				System.out.println( "Product: " + adj.getPName() + ", Adjustment: " + adj.getPAdjValue() + "p" );
			}
		}
	}
}
