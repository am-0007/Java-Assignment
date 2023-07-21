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

  As this project utilizes SMTP mail sender functionality, it is essential to configure the `mail.username` and `mail.password` fields for sending emails to user email addresses. However, keeping security in mind, it is advisable not to store sensitive information like passwords directly in the application properties. Therefore, I have intentionally left these fields empty, as it is crucial to ensure the safety and privacy of my Google account. Please note that you will need to securely provide valid credentials for these fields to enable the SMTP mail functionality and ensure proper email communication with users. If this property is left empty, you will be kept quiet from resetting the user password. So I recommend adding configuration property in yaml file.
