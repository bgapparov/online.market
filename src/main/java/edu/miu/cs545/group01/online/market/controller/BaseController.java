package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.repository.BuyerRepository;
import edu.miu.cs545.group01.online.market.repository.UserRepository;
import edu.miu.cs545.group01.online.market.service.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {
    @Autowired
    IAuthenticationFacade authenticationFacade;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BuyerRepository buyerRepository;

    protected String getCurrentUserEmail(){
        return authenticationFacade.getAuthentication().getName();
    }

    protected User getCurrentUser(){
        String email = getCurrentUserEmail();
        if(!isNullOrEmpty(email)&& !"anonymousUser".equalsIgnoreCase(email)) {
            return userRepository.findByEmail(email).orElse(null);
        }
        return null;
    }
    protected Buyer getCurrentBuyer(){
        String email = getCurrentUserEmail();
        if(!isNullOrEmpty(email)&& !"anonymousUser".equalsIgnoreCase(email)) {
            return buyerRepository.findByEmail(email).orElse(null);
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
