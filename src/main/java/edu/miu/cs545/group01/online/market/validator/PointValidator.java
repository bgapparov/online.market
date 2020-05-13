package edu.miu.cs545.group01.online.market.validator;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.helper.Helper;
import edu.miu.cs545.group01.online.market.service.IAuthenticationFacade;
import edu.miu.cs545.group01.online.market.service.GainPointService;
import edu.miu.cs545.group01.online.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PointValidator implements ConstraintValidator<PointValidation, Float> {
    @Autowired
    private GainPointService pointService;
    @Autowired
    UserService userService;
    @Autowired
    IAuthenticationFacade authenticationFacade;
    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {
        Buyer buyer = getCurrentBuyer();
        if(buyer==null) {
            return value==0;
        }
        return value>=0 && value <= pointService.getFreePoints(buyer);
    }
    public String getCurrentUserEmail() {
        return authenticationFacade.getAuthentication().getName();
    }
    public User getCurrentUser() {
        String email = getCurrentUserEmail();
        if (!Helper.isNullOrEmpty(email) && !"anonymousUser".equalsIgnoreCase(email)) {
            return userService.getUserByEmail(email);
        }
        return null;
    }
    public Buyer getCurrentBuyer() {
        User curUser = getCurrentUser();
        if (curUser == null) {
            return null;
        }
        return (Buyer) curUser;
    }
}
