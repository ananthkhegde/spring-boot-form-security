# spring-boot-form-security
spring boot form security with role based authorization

## This is simple spring boot form based authentication to secure REST Api's in web appication.

### Instruction guide set up and run the application
* download the project and import into any popular IDE (eclipse,sts,intellij)
* SecurityConfig present under ananth.spring.security is spring security configuration file
* This method below under SecurityConfig class adds in memory users
```
  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.inMemoryAuthentication()
                    .withUser("adminuser").password("{noop}" + "password").roles("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}" + "password").roles("USER");

    }
                   
````
* Below method in SecurityConfig class configures the url's required for authentication.
  except login,and other url for resource loding all the requests has to be authenticated.
```
 @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/lib/**",
                        "/favicon.ico",
                        "/**/*.css",
                        "/**/*.js",
                        "/logout**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/index.html");
    }

```

### Demo
* There are 3 GET api's wirrten in SecurityController class to verify authentication and authorization.
  ** /user 
     Returns authticated user(called as principal in spring security context)
  ** /securedapi
     This api can be accessed only by authenticated user.
     On directly accessing this api without authenticating user will be redirected to spring default login page.
  ** /adminapi
     This api can be accessed by the user with admin role
     If a user without admin role tries to access this url, 403 Unauthorized code will be returned.
  ** /userapi
     This api can be accessed by the user with user role
     If a user without user role tries to access this url, 403 Unauthorized code will be returned.
     
