package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.repository.UserRepository;
import edu.miu.cs545.group01.online.market.service.IAuthenticationFacade;
import edu.miu.cs545.group01.online.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {
    @Autowired
    IAuthenticationFacade authenticationFacade;

    @Autowired
    UserService userService;

    protected String getCurrentUserEmail(){
        return authenticationFacade.getAuthentication().getName();
    }

    protected User getCurrentUser(){
        String email = getCurrentUserEmail();
        if(!isNullOrEmpty(email)&& !"anonymousUser".equalsIgnoreCase(email)) {
            return userService.getUserByEmail(email);
        }
        return null;
    }

    protected boolean isNullOrEmpty(String str){
        return str == null || "".equalsIgnoreCase(str);
    }
    @ModelAttribute("currentUser")
    public User currentUser(){
        return getCurrentUser();
    }
}
