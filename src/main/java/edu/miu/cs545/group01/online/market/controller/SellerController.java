package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.User;
import org.springframework.stereotype.Controller;

@Controller
public class SellerController extends BaseController {
    void test(){
        User user = getCurrentUser();
    }
}
