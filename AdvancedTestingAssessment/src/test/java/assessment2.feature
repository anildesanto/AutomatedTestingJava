Feature: Testing Pet Clinic
	Scenario Outline: U1
	    Given an admin using page "<page>"
	    When I add new owner with firstName "<firstName>", lastName "<lastName>", address "<address>", city "<city>" and telephone "<phone>"
		And I go to the owners page "<website>"
	    Then I find the owner with firstName "<firstName>" and lastName "<lastName>"
	    
	     Examples:
	     |page|firstName|lastName|address|city|phone|website|
	     |http://10.0.10.10:4200/petclinic/owners/add|Anita|Thomson|150 hudson rd|London|7784977560|http://10.0.10.10:4200/petclinic/owners|

	Scenario Outline: U2
	    Given an admin using page "<page>"
	    When I find the owner with firstName "<ownerFullName>" and click on it
	    And update its details with lastName firstName "<firstName>" "<lastName>", address "<address>", city "<city>" and telephone "<phone>"
	    Then the correct details are now shown
	     
	     Examples:
	    |page|ownerFullName |firstName|lastName|address|city|phone|
	    |http://10.0.10.10:4200/petclinic/owners|George Franklin|George|Franklin|200 hudson rd|Manchester|7784998450|
	     
	Scenario Outline: U3
	    Given an admin using page "<page>"
	    When I find the owner with firstName "<ownerFullName>" and click on it
	    And add a new pet with name "<name>", birthday "<birth>" and  type"<type>" and save it and 
	    Then the correct details are now shown in the page
	     
	     Examples:
	    |page|ownerFullName |name|birth|type|
	    |http://10.0.10.10:4200/petclinic/owners|George Franklin|GG|21/05/2001|dog|
	


 