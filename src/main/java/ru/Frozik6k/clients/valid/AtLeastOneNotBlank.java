package ru.Frozik6k.clients.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = {
        ContactAtLeastOneNotBlankValidator.class,
        ClientAtLeastOneNotBlankValidator.class
})
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneNotBlank {
    String message() default "Необходимо указать либо имя, либо фамилию";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
