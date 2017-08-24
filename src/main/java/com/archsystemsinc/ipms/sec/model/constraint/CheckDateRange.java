package com.archsystemsinc.ipms.sec.model.constraint;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckDateRangeValidator.class)
@Documented
public @interface CheckDateRange {

    String message() default "{DateRange.failed}";

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    ObjectTypeEnum value();

}
