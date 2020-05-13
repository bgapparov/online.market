
package edu.miu.cs545.group01.online.market.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PointValidator.class)
public @interface PointValidation {
    String message() default "You do not have so many points";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

}
