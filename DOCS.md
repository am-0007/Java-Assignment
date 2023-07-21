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

 ## Demo
  1. Login Page:
       ![Screenshot from 2023-07-22 03-37-24](https://github.com/am-0007/Java-Assignment/assets/96876023/af8ef48c-c0fa-437d-a2dd-6342f6f81bff)

  2. Registration Page
      ![Screenshot from 2023-07-22 03-43-45](https://github.com/am-0007/Java-Assignment/assets/96876023/b0dfbb3b-93e1-4396-952c-69cd2936b4bc)

  3. Reset Password Page
      ![Screenshot from 2023-07-22 03-41-15](https://github.com/am-0007/Java-Assignment/assets/96876023/22362fd0-52fa-4369-84b3-06b0f064e69f)

  4. Otp submission Page
      ![Screenshot from 2023-07-22 03-50-45](https://github.com/am-0007/Java-Assignment/assets/96876023/fef805a5-c58c-4ea7-a4ae-7f90cffb5f68)

  5. Dashboard (Admin)
      ![Screenshot from 2023-07-22 03-41-01](https://github.com/am-0007/Java-Assignment/assets/96876023/a08098af-57f0-409f-8cdb-6881bbba1e55)

  6. Dashboard (User)
      ![Screenshot from 2023-07-22 03-52-43](https://github.com/am-0007/Java-Assignment/assets/96876023/7510822b-bbf5-4abd-b2e4-360ef06f6d0a)

  7. Reset Email
      ![Screenshot from 2023-07-22 03-42-59](https://github.com/am-0007/Java-Assignment/assets/96876023/8d9226ad-46e1-4730-b88e-6d3925a8cf28)
 
      
