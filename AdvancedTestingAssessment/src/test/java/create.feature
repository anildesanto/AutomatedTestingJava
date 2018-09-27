Feature: Testing Create

	Scenario Outline: Create owner
    Given an owner with firstName "<firstName>", lastName "<lastName>", address "<address>", city "<city>" and telephone "<phone>" is added
	When the admin selects the owner by "<firstName>"
    Then the owner "<firstName>" along with other details are displayed
    
     Examples:
    |firstName|lastName|address|city|phone|
    |Anita|Thomson|150 hudson rd|London|99999999|