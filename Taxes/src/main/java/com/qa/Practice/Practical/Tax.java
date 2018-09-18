package com.qa.Practice.Practical;

public class Tax 
{
	public int percentage(double salary)
	{
		return taxPercentage(salary);
	}
	public double taxAmount(double salary)
	{
		return salary*taxPercentage(salary)/100;	
	}
	
	public int taxPercentage(double salary)
	{
		if(salary < 0)
		{
			return 0; //INVALID!
		}
		else if(salary >= 0 && salary < 15000)
		{
			return 0;
		}
		else if (salary >= 15000 && salary < 20000)
		{
			return 10;
		}
		else if (salary >= 20000 && salary < 30000)
		{
			return 15;
		}
		else if (salary >= 30000 && salary < 45000)
		{
			return 20;
		}
		else
		{
			return 25;
		}
	}
}
