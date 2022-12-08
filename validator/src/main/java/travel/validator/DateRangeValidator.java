package travel.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, LocalDate> {
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public void initialize(ValidDateRange annotation) {

        annotation.startDate().cast(startDate);
        annotation.endDate().cast(endDate);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value.isBefore(startDate) && value.isAfter(endDate);
    }
}
