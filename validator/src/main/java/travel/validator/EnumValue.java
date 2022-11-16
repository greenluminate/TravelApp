package travel.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValue {
    Class<? extends Enum<?>> enumClass();

    String message() default "Attraction class must be on of them: {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
