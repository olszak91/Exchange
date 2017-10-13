package pl.coderslab;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = StartWithValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StartWith {
	int value();

	String message() default "{startWith.error.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}