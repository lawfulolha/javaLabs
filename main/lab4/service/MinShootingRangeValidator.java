package service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinShootingRangeValidator implements ConstraintValidator<MinShootingRange, Integer> {

    private double annotationMin;

    @Override
    public void initialize(MinShootingRange shootingRange) {
        this.annotationMin = shootingRange.value();
    }


    @Override
    public boolean isValid(Integer aInt, ConstraintValidatorContext constraintValidatorContext) throws IllegalStateException{
        if(aInt>=annotationMin)
            return true;
        else {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("must be more than " + annotationMin)
                    .addConstraintViolation();
            return false;
        }
    }
}