package edu.miu.cs545.group01.online.market.validator;

import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DuplicateEmailValidator implements ConstraintValidator<DuplicateEmail, String> {
    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.getUserByEmail(value);
        return user == null ? true : false;
    }


}
