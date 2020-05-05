package edu.miu.cs545.group01.online.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogingController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
