package ananth.spring.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlled class to demonstrate Spring form security and Role based authorization
 */
@RestController
public class SecurityController {

    /**
     * To get the authenticated principal name
     * @return {@link String} : returns currently loggedin principal
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome " + authentication.getName();
    }

    /**
     * This API can only be accessed by logged in user
     * @return {@link String} : Sample text
     */
    @RequestMapping(value = "/securedapi", method = RequestMethod.GET)
    @ResponseBody
    public String getSecuredApi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "you are viewing this api because You are authenticated as  " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * This API can only be accessed by authenticated user with ADMIN ROLE
     * @return {@link String} : Sample text response
     */
    @RequestMapping(value = "/adminapi", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminApi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "You are authenticated as  " + SecurityContextHolder.getContext().getAuthentication().getName() + " with role Admin.";
    }

    /**
     * This API can only be accessed by authenticated user with USER ROLE.
     * @return {@link String}: Sample text.
     */
    @RequestMapping(value = "/userapi", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUserApi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "You are authenticated as  " + SecurityContextHolder.getContext().getAuthentication().getName() + " with role USER.";
    }

}
