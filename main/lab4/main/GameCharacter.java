package main;
import service.MinShootingRange;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.*;
public class GameCharacter implements Comparable<GameCharacter>{

    @NotNull(message = "Field can`t be null")
    @Size(min = 1, max=20)
    private String name;

    @Max(value = 200, message = "Max health value is 200")
    @PositiveOrZero( message = "Min health value is 0")
    private Integer health;

    @Min(value = 0, message = "Min armor value is 0")
    @Max(value = 200, message = "Max armor value is 200")
    private Integer armor;

    @PositiveOrZero
    @NotNull(message = "Damage cannot be NULL")
    private Integer damage;


    @MinShootingRange(value = 10, message = "not less than 10")
    private Integer shootingRange;

    @Max(value = 600, message = "Player must be inside of the screen")
    @Min(value = 0, message =  "Player must be inside of the screen")
    private Integer x;

    @Max(value = 300, message = "Player must be inside of the screen")
    @Min(value = 0, message =  "Player must be inside of the screen")
    private Integer y;

    private GameCharacter() {
    }


    private GameCharacter(String name, int health, int armor, int damage, int shootingRange) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.damage = damage;
        this.shootingRange = shootingRange;
       // this.position = new Position();
        this.x = 0;
        this.y = 0;
    }

    private GameCharacter(GameCharacter player) {
        this.name = player.getName();
        this.health = player.getHealth();
        this.armor = player.getArmor();
        this.damage = player.getDamage();
        this.shootingRange = player.getShootingRange();
        this.x = player.x;
        this.y = player.y;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public int getShootingRange() {
        return shootingRange;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void moveTo(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void getInjury(int delta){
        this.health-=delta;
        if(this.health <= 0) this.health = 0;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameCharacter gamer = (GameCharacter) o;

        return name.equals(gamer.name) && damage==gamer.damage && shootingRange==gamer.shootingRange;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (damage != 0 ? damage.hashCode() : 0);
        result = 31 * result + (shootingRange != 0 ? shootingRange.hashCode() : 0);
        return result;
    }

    public static Builder newBuilder() {
        return new GameCharacter().new Builder();
    }

    @Override
    public int compareTo(GameCharacter o) {
        return name.compareTo(o.name);
    }

    public class Builder {


        private Builder() { }


        public Builder setName(String name) {
            GameCharacter.this.name = name;
            return this;
        }
        public Builder setHealth(int health){
            GameCharacter.this.health = health;
            return this;
        }
        public Builder setArmor(int armor) {
            GameCharacter.this.armor = armor;
            return this;
        }
        public Builder setDamage(int damage) {
            GameCharacter.this.damage = damage;
            return this;
        }
        public Builder setShootingRange(int shootingRange){

            GameCharacter.this.shootingRange = shootingRange;
            return this;
        }

        public Builder setPosition(int x, int y) {
         //   GameCharacter.this.position = new Position(x,y);
            GameCharacter.this.x = x;
            GameCharacter.this.y = y;
            return this;
        }

        public GameCharacter build() throws IllegalStateException {

                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                Set<ConstraintViolation<Builder>> constraintViolations = validator.validate(this);
                if (constraintViolations.isEmpty())
                    return GameCharacter.this;
                else {
                    Set<String> exceptions = new HashSet<>();
                    for (ConstraintViolation constraintViolation : constraintViolations) {
                        String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                        exceptions.add(fieldName + " " + constraintViolation.getMessage());
                    }
                    exceptions.forEach(System.out::println);
                    throw new IllegalStateException(exceptions.toString());
                }
        }
    }

    @Override
    public String toString() {
        return   name+' '+health+' '
                +armor+' '+damage+' '
                +shootingRange+' '+x+' '+y;
    }

}
