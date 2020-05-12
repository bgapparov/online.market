package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.enums.Role;
import edu.miu.cs545.group01.online.market.repository.BuyerRepository;
import edu.miu.cs545.group01.online.market.repository.UserRepository;
import edu.miu.cs545.group01.online.market.service.BuyerService;
import edu.miu.cs545.group01.online.market.service.IAuthenticationFacade;
import edu.miu.cs545.group01.online.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseController {
    @Autowired
    IAuthenticationFacade authenticationFacade;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    UserService userService;
    @Autowired
    BuyerService buyerService;

    protected String getCurrentUserEmail() {
        return authenticationFacade.getAuthentication().getName();
    }

    protected User getCurrentUser() {
        String email = getCurrentUserEmail();
        if (!isNullOrEmpty(email) && !"anonymousUser".equalsIgnoreCase(email)) {
            return userService.getUserByEmail(email);
        }
        return null;
    }

    protected Seller getCurrentSeller() {
        User curUser = getCurrentUser();
        if (curUser == null) {
            return null;
        }
        return (Seller) curUser;
    }

    protected Buyer getCurrentBuyer() {
        String email = getCurrentUserEmail();
        if (!isNullOrEmpty(email) && !"anonymousUser".equalsIgnoreCase(email)) {
            return buyerService.getBuyerByEmail(email);
        }
        return null;
    }

    protected boolean isNullOrEmpty(String str) {
        return str == null || "".equalsIgnoreCase(str);
    }

    @ModelAttribute("currentUser")
    public User currentUser() {
        return getCurrentUser();
    }


    static Map<String, Long> updatedLastRuntime = new HashMap<>();

    protected void resetAuthentication() {
        User curUser = getCurrentUser();
        if(curUser == null){
            return;
        }
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        if (updatedLastRuntime.containsKey(curUser.getEmail()) &&
                updatedLastRuntime.get(curUser.getEmail())+(10 * 1000)>t
        ) {
            return;
        }

        Authentication auth = authenticationFacade.getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        curUser.getRoles().stream().forEach(role -> updatedAuthorities.add(new SimpleGrantedAuthority(role.toRoleString())));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        updatedLastRuntime.put(curUser.getEmail(), t);
    }
}
