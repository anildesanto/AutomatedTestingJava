Feature: Creating Account and Loging in
    As a customer
    I want to be able to create an Account 
    So that I can Login

    Scenario Outline: Creating Account
        Given "<username>" is entered as username And "<password>" is entered as password  And I click a button
        When I go to the website and try to Log in with "<username1>" as username and "<password1>" as password
        Then I log in and see account
        
    Examples:
    | username | password | username1 | password1 |
    | Anilde | lool | Anilde | lool |
    
    