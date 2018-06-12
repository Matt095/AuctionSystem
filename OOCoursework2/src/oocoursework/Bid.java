package oocoursework;

import java.util.Date;

public class Bid 
{

	private double amount;
	private Buyer who;
	private Date when;
	
	public Bid(double amount, Buyer who, Date when)
	{
		this.amount = amount;
		this.who = who;
		this.when = when;
	}

	public double getAmount() 
	{
		return amount;
	}

	public void setAmount(double amount) 
	{
		this.amount = amount;
	}

	public Buyer getWho() 
	{
		return who;
	}

	public void setWho(Buyer who) 
	{
		this.who = who;
	}

	public Date getWhen() 
	{
		return when;
	}

	public void setWhen(Date when) 
	{
		this.when = when;
	}
}
