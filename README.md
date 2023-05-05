# Introduction 
The KSU Department of Exercise Science and Sport Management wants to have a system that allows their patients to input their general information, such as age, sex, race, etc., and their medical info, such as body composition, weight, etc., and step count so that the system can display their target weight, current step count, and target step count.

# Contributors
* Kristie Boyd - Team Lead/Project Manager
* Alexandria Fuller - Frontend Developer/Supprt Manager
* Santhiya Subramanian - Frontend Developer/Quality & Process Manager
* Armando Negron Achecar - Backend Developer/Planning Manager
* David Sanford - Backend Developer/Development Manager

# Software Dependencies
* [Springboot Initializer](https://spring.io/)
  * _We are using Java version 17 for our project_
  * Includes the following dependencies:
    * Spring Boot DevTools
    * Spring Web
    * H2 Database
    * Thymeleaf
  * Visual Studio (for frontend)
* [Angular](https://angular.io/cli)

# Installation Process
1. Download the IntelliJ Ultimate version: https://www.jetbrains.com/idea/download/
   2. Get the student license to get all developer tools from Jetbrains: https://www.jetbrains.com/community/education/#students
2. Download Java: https://www.java.com/en/download/manual.jsp
3. Download Node.JS version 18.15.0: https://nodejs.org/en/#download
4. Download any version Visual Studio: https://visualstudio.microsoft.com/
5. Click the 'code' button on this repository and then click 'Download Zip'.
6. After downloading the ZIP file extract its contents to a desired folder.
7. Open the extracted folder in IntelliJ.
8. Navigate to src/main/java/com/ksupwlt/stepcounttracker/StepcounttrackerApplication.java
9. Once you have found this file right click it and select "Run 'StepcounttrackerApplication.main()'" to begin running the API.

# Configuration Changes
By default this api will use a local database utilizing H2, in order to change this along with other configurations do the following:
* Navigate to src/main/resources/application.properties
* In order to change the database source comment out lines 1, 2, and 4. Then add the following line to the file:
* `spring.datasource.url= *put database url here*`

* When setting up an email service simply edit the relevent information in the `# Email configuration` section below line 18.
