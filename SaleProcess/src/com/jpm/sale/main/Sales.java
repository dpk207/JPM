package com.jpm.sale.main;

public class Sales {

	private String pName;
	private int pValue;
	private int totalSales;
	
	public Sales( String pName, int pValue )
	{
		this.pName = pName;
		this.pValue = pValue;
	}
	
	public void addValue( int value )
	{
		this.pValue += value;
	}
	
	public void addSales( int sales )
	{
		this.totalSales += sales;
	}
	
	public void addAdj( Adjustments adj )
	{
		if( adj.getAdj().equalsIgnoreCase( SalesEnums.Add.name() ) )
			this.pValue += this.totalSales * adj.getPAdjValue();
		else if( adj.getAdj().equalsIgnoreCase( SalesEnums.Subtract.name() ) )
			this.pValue -= this.totalSales * adj.getPAdjValue();
		else
			this.pValue *= this.totalSales * adj.getPAdjValue();
	}
	
	public String getPName()
	{
		return this.pName;
	}
	
	public int getPValue()
	{
		return this.pValue;
	}
	
	public int getTotalSales()
	{
		return this.totalSales;
	}
}
