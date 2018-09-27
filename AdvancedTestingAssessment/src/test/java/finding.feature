Feature: Testing Finding

    Scenario Outline: Find Owner by Name
    Given the owners list
	When the admin searches the owner by "<lastName>"
    Then the owner is found
    
     Examples:
    |lastName|
    |Estaban|
    |Franklin|
    |Davis|
    
    Scenario Outline: Find pet by ID
    Given the pets list
	When the admin searches the pet by "<id>"
    Then the pet is found
    
     Examples:
    |id|
    |2|
    |1|
    |5|