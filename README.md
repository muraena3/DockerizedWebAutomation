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
        
            $ git clone git@github.com:muraena3/DockerizedWebAutomation.git             
   
   4. On your terminal, go to the project downloaded directory, then enter the following command
                  
            $ cd DockerizedWebAutomation/
            $ ./run.sh
   
   5. Once Test Run finishes successfully, HTML report should open automatically. 
        Or else:
        -   go to project folder and under it look for results-XXXX folder
        -   right click and open emailable-reports.html
        -   and all the screenshots are found under screenshots folder
   
   6.  Screen-captured [demo video](https://youtu.be/ztDWifaInCI) of the project running on a Mac Device
                          

#### Features of the framework
    1. Runs completely with just shell script and Docker
    2. Can be integrated with CI/CD tools like Jenkins
    3. Uses Selenium Grid, tests can be run on parallel; Scalable
    4. Generates HTML report with logs and Screenshot embedded
    5. parametrized test input
    6. Implements PageObject Model for scripting tests for code reuse and DRY


#### Test Requirements
Test Page : https://www.clicktripz.com/test.php

##### Test Steps:

###### For Hotel Citywide:
    1. Open a new Chrome browser window and navigate to the Test Page.
    2. Change the location in the City field.
    3. Change the date in the Check-in field to next week’s Friday date.
    4. Change the date in the Check-out field to next week’s Sunday date.
    5. Change the number in the Guests drop-down to 2.
    6. Keep the Rooms drop-down set to 1.
    7. Select the Search Hotels button.
    8. Minimize the main browser window.
    9. Change focus to the Exit Unit window. (capture a screenshot)
    10. Select the ‘Show me the first deal!’ button on the welcome modal to dismiss it and expand the window. (capture a screenshot)
    11. Click through each tab. (capturing screenshots of each tab after they load)
    12. Capture the Exit Unit URL.
    13. Close both browser windows.

###### For Flights:
    1. Open a new Chrome browser window and navigate to the Test Page.
    2. Select the Flights form.
    3. Change the airport in the From field.
    4. Change the airport in the To field.
    5. Change the date in the Departing field to next week’s Friday date.
    6. Change the date in the Returning field to next week’s Sunday date.
    7. Change the number in the Travelers drop-down to 2.
    8. Select the Search button.
    9. Minimize the main browser window.
    10. Change focus to the Exit Unit window. (capture a screenshot)
    11. Select the ‘Show me the first deal!’ button on the welcome modal to dismiss it and expand the window. (capture a screenshot)
    12. Click through each tab. (capturing screenshots of each tab after they load)
    13. Capture the Exit Unit URL.
    14. Close both browser windows.
        
