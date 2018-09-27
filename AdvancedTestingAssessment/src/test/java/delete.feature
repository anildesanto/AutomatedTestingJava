Feature: Testing delete

	Scenario Outline: Delete owner
    Given an owner with id "<id>" 
	When the owner is deleted
    Then the owner is not found
    
     Examples:
    |id|
    |28|