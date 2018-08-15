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
