# spring-boot-form-security
spring boot form security with role based authorization

## This is simple spring boot form based authentication to secure REST Api's in web appication.

### Instruction guide set up and run the application
* download the project and import into any popular IDE (eclipse,sts,intellij)
* SecurityConfig present under ananth.spring.security is spring security configuration file
* This code snippet adds in memory users
```
 auth.inMemoryAuthentication()
                    .withUser("adminuser").password("{noop}" + "password").roles("ADMIN");
                   
````
