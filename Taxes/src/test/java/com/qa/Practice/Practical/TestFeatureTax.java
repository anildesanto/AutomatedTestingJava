package com.qa.Practice.Practical;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestFeatureTax 
{

	private int percentageExpected;
	private double salary;
	private double taxAmountExpected;
	private Tax taxes = new Tax();
	
	@Parameters
	public static Collection <Object[]> data()
	{
		return Arrays.asList(new Object[][]
		{
			//percentageExpected, taxAmoutExpected, salary
			{0,0,-1},
			{0,0,0},
			{0,0,14999},
			//
			{10,1500,15000},
			{10,1500.1,15001},
			{10,1999.9,19999},
			//
			{15,3000,20000},
			{15,3000.15,20001},
			{15,4499.85,29999},
			//
			{20,6000,30000},
			{20,6000.2,30001},
			{25,11250,45000},
			//
			{25,11250.25,45001},
		}
		);
	}
	
	@Before
	public void initiate()
	{
		Tax taxes = new Tax();
	}

	
	public TestFeatureTax(int perEx, double taxEx, double s)
	{
		percentageExpected = perEx;
		taxAmountExpected = taxEx;
		salary = s;
	}
	
	@Test
	public void check()
	{
		assertEquals(taxAmountExpected,taxes.taxAmount(salary),0);
		assertEquals(percentageExpected,taxes.percentage(salary),0);
	}
	
}
