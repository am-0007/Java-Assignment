# Java Web MVC Application

## Description

  This Spring Boot application leverages the latest Spring Security 6, JSON Web Token, and Thymeleaf dependencies. Built with an MVC architecture, the app provides a user-friendly authentication mechanism for seamless user interactions. This app offers a range of features, including Java SMTP mail for password reset, user registration, user login, user deletion, and log-out functionality. The app starts with 11 users: 1 admin, 10 users. Admin can delete users, while regular users have limited access to the dashboard. 

## Instruction

  ### Guide to run the project:
  
  - clone the repo

  - change directory to internSathi: `cd internSathi`
  
  - build all jar: `./gradlew build`
    
  - run the project with terminal command: `./gradlew bootrun`
     
  - open your browser and type: `http://localhost:7999/internsathi/user/login`

## Login with following credentials:

     Admin:
        username: am0007
        password: password

     User:
        username: am007
        password: password

## Point to consider

  This project uses SMTP mail sender functionality to send emails to user email addresses. To enable this feature, make sure to provide valid credentials for the `mail.username` and `mail.password` fields in the application properties. However, for security reasons, it is advisable not to store sensitive information like passwords directly in the properties file. Instead, securely provide the credentials to ensure the safety of your Google account. If the `mail.username` and `mail.password` fields are left empty, the app will not be able to reset user passwords. Please add the necessary configuration in the YAML file to enable this functionality.
