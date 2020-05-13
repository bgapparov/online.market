package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.UserRegistrationModel;
import edu.miu.cs545.group01.online.market.domain.enums.UserType;
import edu.miu.cs545.group01.online.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthorizationController extends BaseController {

    @GetMapping("/login")
    public String login(){
        return "ar/login";
    }

    @GetMapping("/signup/select-user-type")
    public String selectUserType(){
        return "ar/select-user-type";
    }

    @GetMapping("/signup/{userType}")
    public String signup(@ModelAttribute("user") UserRegistrationModel user, @PathVariable("userType") UserType userType){
        if(userType == UserType.SELLER) {
            return "ar/signup-seller";
        }
        return "ar/signup";
    }
    @PostMapping("/signup/{userType}")
    public String signup(HttpServletRequest request, @PathVariable("userType") UserType userType, @Valid @ModelAttribute("user") UserRegistrationModel user, BindingResult result) throws ServletException {
        if (result.hasErrors()) {
            if(userType == UserType.SELLER) {
                return "ar/signup-seller";
            }
            return "ar/signup";
        }
        if(userType == UserType.SELLER) {
            userService.createNewSeller(user);
        }else{
            userService.createNewBuyer(user);
        }
        request.login(user.getEmail(), user.getPassword());
        return "redirect:/";
    }
}
