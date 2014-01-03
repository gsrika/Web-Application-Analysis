##Web Application Analysis##

This Project has various modules. Find the appliation of each module below

##Module 1 -Measure Coverage On Web Application -Cobertura ##
1. Use ant scripts to deploy a web applicaiton on tomcat 6
2. Generate coverage using wget 
3. Use cobertura to instrument web applicaiton and generate coverage 
4. Coverge should be generated in html-reports 
5. python scripts to automate complete process

##Module 2 - Automate Web Crawling -Crwl Jax##
1.Create a Maven Project to crawl a web application using Crawl Jax (Automated crawling tool)
2.Generate static report from each servlet on different types of tags ( <a> , <input> ,<form> )
3.Measure coverage using cobertura tool

##Module 3 - Black Box Testing  -Selenium##
1.Generate various black box test cases using selenium
2.Caputure and replay those test cases
3.Cover scenarios like adding item to shopcart , removing item from shop cart, adding a new user to store

##Module 4 - White Box Testing -WAM##
1.Generate white box test cases using WAM
2.Configure WAM to obtain detalis about various methods parameters in each servlet.
3.Use the information generated to generate white box test cases.

##Module 5 - Formal Specification Testing -Cucumber,Capybara##
1.Use Cucumber,Capybara tools to generate test cases
2.Define declaritve features , step definitions to generete test cases

##Module 6 - Formal Verification   - Apache bcel library##
1.Use apache bcel library to generate Control flow graph for servlets
2.Use the geneareted control flow graph to answer various questions about reachability information and verify properties

##Module 7 - Regression Testin Tool -Dejavu Algorithm##
1.Build Control flow graph of servlets before and after changes
2.Use Dejavul algorithm to find out which test suite to be re run


##Module 8 - Fault Localization  - Tarauntal Process##
1.Use cobertura generated reports to create test case, statement mapping 
2.Use the testcase,statement mapping in tarantula to find out suspicious staement in program


