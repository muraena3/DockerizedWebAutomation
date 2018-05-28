# Dockerized Web Automation
Web Automation Framework using Docker, Java, TestNG, Selenium Webdriver Grid, Shell Script

*Note: Tested only on Mac Device*

#### Steps to run the tests
   1. Install latest version of Docker on your machine [download link](https://www.docker.com/docker-mac)
   - Verify Docker
         
         $ docker -v
   2. Install git [Tutorial on Installation](https://gist.github.com/derhuerst/1b15ff4652a867391f03)
           - Enter following command on your terminal
            
                  $ git --version
                   
       - If installed successful you should see something like this on your terminal
              
              git version 2.15.1 (Apple Git-101)
   3. Clone the project
        
            $ git clone https://github.com/tazpervr/AutomationFramework.git              

#### Features
    1. 
    2. parametrized test input
    3. can be executed from command prompt
    4. screenshots 
    5. failed test retry
    6. logging
    7. dashboard with historical data

#### Scenario Automated
    1. Go to yelp.com
    2. Select “Restaurants” in the dropdown
    box in Find
    3. Click Search button
    4. Append Pizza to Restaurants to make the search text as “Restaurants Pizza”
    5. Report total no. of Search results with no. of results in the current page
    6. Parameterize any 2 of the filtering parameters (Neighborhoods, Distance, Price,
    Features, etc.)
    7. Apply the filter
    8. Report total no. of Search results with no. of results in the current page
    9. Report the star rating of each of the results in the first result page
    10. Click and expand the first result from the search results
    11. Log all critical information of the selected restaurant details, for the reporting purpose
        ● Address, Phone No, web site details
        ● First 3 customer reviews
    12. Automate the scenario mentioned using the yelp APIs