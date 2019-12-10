package test;
import main.*;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationTest {
    @Test
    public void builderTest() {
        GameCharacter player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(69).setArmor(69).setDamage(69).setShootingRange(69)
                .setPosition(10, 10)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void builderNegativeTest() {
        GameCharacter player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(-69).setArmor(-69).setDamage(69).setShootingRange(69)
                .setPosition(10, 10)
                .build();

        ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validFactory.getValidator();

        Set<ConstraintViolation<GameCharacter>> violatons = validator.validate(player);

        Assert.assertFalse(violatons.isEmpty());
    }

    @Test
    public void minShootingRangeValidationTest() {
        GameCharacter player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(69).setArmor(69).setDamage(69).setShootingRange(69)
                .setPosition(10, 10)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void minShootingRangeValidationNegativeTest() {
        GameCharacter player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(69).setArmor(69).setDamage(69).setShootingRange(0)
                .setPosition(10, 10)
                .build();
    }
}