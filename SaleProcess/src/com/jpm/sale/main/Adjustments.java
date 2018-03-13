package com.jpm.sale.main;

public class Adjustments {

	private String pName;
	private int pAdjValue;
	private String adj;
	
	public Adjustments( String pName, int pAdjValue, String adj )
	{
		this.pName = pName;
		this.pAdjValue = pAdjValue;
		this.adj = adj;
	}
	
	public String getPName()
	{
		return this.pName;
	}
	
	public int getPAdjValue()
	{
		return this.pAdjValue;
	}
	
	public String getAdj()
	{
		return this.adj;
	}
}
