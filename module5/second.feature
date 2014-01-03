#encoding: utf-8
Feature: Showcase the simplest possible Cucumber scenario
  In order to verify that cucumber is installed and configured correctly
  As an aspiring BDD fanatic 
  I should be able to run this scenario and see that the steps pass (green like a cuke)
 
  Scenario: Login verification 
#    Given a book store web application  
    When A new user wants to create a an account 
    And fill in "member_login" as "newuser1"
    And fill in "member_password" as "newuser1"
    And fill in "member_password2" as "newuser1"
    And fill in "first_name" as "srikanth"
    And fill in "last_name" as "Gandupalli"
    And fill in "email" as "gandupal@usc.edu"
    And click the button "Register"
    And load  page "/bookstore/Login.jsp" 
    And fill in "Login" as "newuser1"
    And fill in "Password" as "newuser1"
    And click the button "Login"
    And verify content "srikanth"
    And verify content "newuser1"
    And load  page "/bookstore/Default.jsp"
    And click the link  "MySQL & PHP From Scratch" 
    And click the button "Add to Shopping Cart" 
    And load  page "/bookstore/Default.jsp"
    And click the link  "Beginning ASP Databases"
    And click the button "Add to Shopping Cart"
    And verify content "MySQL & PHP From Scratch"
    And verify content "Beginning ASP Databases"
    And delete the MySQL & PHP book from shoppingcart
    #And click the link by xpath "//tr[3]/td/a/font"
    And click the delte "Delete"
    And verify content not present "MySQL & PHP From Scratch"
    And load  page "/bookstore/Default.jsp"
    And fill in "name" as "programming"
    And click the button "Search"
    And verify content "programming"
    And click on the logout page
    # And click the link by xpath "//td[6]/a/font/img"
    And verify content "Logout"
     
