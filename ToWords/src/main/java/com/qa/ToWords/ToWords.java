package com.qa.ToWords;

public class ToWords 
{
	private final int ten = 10;
    private final int hundred = 100;
    private final long maximumNumber = 100000000000000000l;
    //17 zeros for 999 999 999 999 999 999 quadrillion
    private final String[] names = 
    {
	    "quadrillion",
	    "trillion",
	    "billion",
	    "million",
	    "thousand",
	    "hundred"
    };
    private final String[] firstSelection =
    {
	    "",
	    "one",
	    "two",
	    "three",
	    "four",
	    "five",
	    "six",
	    "seven",
	    "eight",
	    "nine",
	    "ten",
	    "eleven",
	    "twelve",
	    "thirteen",
	    "fourteen",
	    "fifteen",
	    "sixteen",
	    "seventeen",
	    "eighteen",
	    "nineteen",
	    "",
	    "twenty",
	    "thirty",
	    "fourty",
	    "fifty",
	    "sixty",
	    "seventy",
	    "eighty",
	    "ninety"
    };
    public String numberToWord(long passedNumber)
    {
	    if(passedNumber == 0)
	    return result(firstSelection[0]);
	    else if(passedNumber < hundred)
	    {
	    	return result(dNumber(passedNumber));
	    }
	    long test = lastDigits(maximumNumber, passedNumber);
	    String wordsToPrint = "";
	    long number = passedNumber;
	    int i = 1;
	    for(long num =0, divider = 0 , numbToUse = maximumNumber, div = number/numbToUse; number > 0; numbToUse/=divider,num++)
	    {
		    int size = Long.toString(number).length();
		    System.out.println("Size: "+ size);

		    if(num % 2 == 0)
		    divider = hundred;
		    else
		    divider = ten;
		  
		    String name = names[(i-1)/2];
		    div = number/numbToUse; 
		    System.out.println("====================");
		    System.out.println("Div: "+div);
       
    
	       if(div > 0)
	       {
			    if(number < hundred)
			    {
				    if(wordsToPrint.endsWith(","))
				    {
					    wordsToPrint = wordsToPrint
					    .substring(0, wordsToPrint.length()-1);
					    wordsToPrint += " and";
				    }
				    wordsToPrint += dNumber(div);
			    }
			    else
			    {
				    Boolean even = (i % 2 == 0);
				    long check = numbToUse/hundred;
				    long calculate =(number/check)%hundred;
				    
				    String word ="";
				    wordsToPrint += dNumber(div);word += " "+(even ? (name) : (names[names.length-1]));
				     
				     Boolean same = !word.trim().equals(name.trim());
				     if((even && same) || (same && calculate == 0))
				     word += " "+ name;
				     
				     wordsToPrint += word;
			     
				     /*System.out.println("Even: "+even);
				     System.out.println("Check: "+check);
				     System.out.println("Number/Check: "+ number/check);
				     System.out.println("Calculate: "+calculate);
				     System.out.println("Remainder: "+remainder);
				     System.out.println("Outcome: "+n);*/
				     
				     if(!even && (calculate > 0))
				     wordsToPrint += " and";
				     else
				     wordsToPrint +=",";
			     
				     System.out.println("Words: "+ wordsToPrint);
			     }
		     }
		    number %= numbToUse;
		    i++;
	    }
	    System.out.println("===== Number: "+passedNumber+"  ======" );
	    return result(wordsToPrint);
    }
	public long lastDigits(long numbToUse, long number)
	{
	    long toUse = numbToUse/ten;
	    long remains = (number%toUse);
	    String idk = "€";
	    System.out.println("ToUse is: "+ toUse);
	    System.out.println("Returned Number is: "+ number);
	    System.out.println("Remains: "+ remains);
	    if(remains < 10)
	    {
		    System.out.println(idk);
		    return 0;
	    }
	    else
	    	return lastDigits(toUse, remains);
	    
	}
    public String dNumber(long number)
    {
	    for(int i = 0; i < firstSelection.length; i++)
	    {
		    int n = java.util.Arrays.asList(firstSelection)
		    .indexOf(firstSelection[i]);
		    if(i < 20)
		    {
			    if(number == n)
			    	return (n == 0 ? "" : " ")+ firstSelection[i];
		    }
		    else
		    {
			    int secondN = n-19;
			    if(secondN == -19)
			    continue;
			    long checkNum = (number/ten)*ten;
			    int actualNum = secondN*ten;
			    long remainder = number%ten; 
			    //System.out.println("SecondN: "+ secondN
			    //+ "\nActualNum: "+ actualNum);  
		    if(checkNum == actualNum)
			    return " "+firstSelection[i] + dNumber(remainder);
		    }
	    }
	    return " Oops!";
    }
    
    public String result(String wordsToPrint)
    {
	    wordsToPrint = wordsToPrint.trim();
	    if(wordsToPrint.endsWith(","))
	    wordsToPrint = wordsToPrint.substring(0, wordsToPrint.length()-1);
	    
	        return wordsToPrint.substring(0,1).toUpperCase() 
	       + wordsToPrint.substring(1, wordsToPrint.length()) +".";
    }
}
