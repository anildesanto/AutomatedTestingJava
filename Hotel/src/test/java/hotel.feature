Feature: Testing Hotel

Scenario Outline: Testing creation
	Given a hotel with name "<name>", description "<description>", city "<city>" and "<rating>" 
  	When a user searches the hotel by the name "<name>"
  	Then the hotel "<name>" along with other details are displayed
  
  
 Examples:
    | name   | description | city | rating |
    | Tansilvania Hotel | Lovely place in darkness | Tansilvania | 5 |
    | Miami Hotel | Nice sunny beaches close by | Miami | 5 |
    | LangLane Hotel | Its okay | Los Angles | 2 |
    | The Mario Bros Hotel | We tried to hotel but we should have sticked with pasta | Mario World | 1 |
    
  