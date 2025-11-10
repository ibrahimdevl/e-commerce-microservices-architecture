package com.myshop.users.config;

import com.myshop.users.entities.User;
import com.myshop.users.services.LoginAttemptService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {


    private LoginAttemptService loginAttemptService;


    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthSuccess(AuthenticationSuccessEvent event){
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof User){
            User user = (User) event.getAuthentication().getPrincipal();
            loginAttemptService.evicUserFromLoginAttemptCache(user.getUsername());
        }

    }
}
