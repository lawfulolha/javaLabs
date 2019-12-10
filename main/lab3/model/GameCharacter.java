package main;

import java.util.HashSet;
import java.util.Set;

public class GameCharacter implements Comparable<GameCharacter>{
    private static int MAX_HEALTH = 200;
    private static int MAX_ARMOR = 200;

    private String name;
    private Integer health;
    private Integer armor;
    private Integer damage;
    private  Integer shootingRange;
    private  Position position;

    private GameCharacter() {
        this.position = new Position();
    }


    private GameCharacter(String name, int health, int armor, int damage, int shootingRange) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.damage = damage;
        this.shootingRange = shootingRange;
        this.position = new Position();
    }

    private GameCharacter(GameCharacter player) {
        this.name = player.getName();
        this.health = player.getHealth();
        this.armor = player.getArmor();
        this.damage = player.getDamage();
        this.shootingRange = player.getShootingRange();
        this.position = new Position();
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

    public Position getPosition() {
        return position;
    }

    public int getPositionX() {
        return this.position.x;
    }

    public int getPositionY() {
        return position.getY();
    }

    public void moveTo(int x, int y){
        position.setPosition(x, y);
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


        public Builder setName(String name) throws IllegalArgumentException {
            if(name==null) throw new IllegalArgumentException("Name is required");
            GameCharacter.this.name = name;
            return this;
        }
        public Builder setHealth(int health) throws IllegalArgumentException {
            if(health <= 0 || health > MAX_HEALTH)
                throw new IllegalArgumentException("HEALTH must be greater than 0 and less than 200");
            GameCharacter.this.health = health;
            return this;
        }
        public Builder setArmor(int armor)  throws IllegalArgumentException {
            if(armor < 0 || armor > MAX_ARMOR)
                throw new IllegalArgumentException("Armor must be greater than 0 and less than 200");
            GameCharacter.this.armor = armor;
            return this;
        }
        public Builder setDamage(int damage) {
            GameCharacter.this.damage = damage;
            return this;
        }
        public Builder setShootingRange(int shootingRange) throws IllegalArgumentException {
            if(shootingRange < 0)
                throw new IllegalArgumentException("Shooting Range must be Positive");
            GameCharacter.this.shootingRange = shootingRange;
            return this;
        }

        public Builder setPosition(int x, int y) {
            GameCharacter.this.position.setPosition(x,y);
            return this;
        }

        public GameCharacter build() throws IllegalArgumentException {
            Set<String> emptyFields = new HashSet<String>();
            if(GameCharacter.this.name == null) emptyFields.add("name");
            if(GameCharacter.this.health == null) emptyFields.add("health");
            if(armor==null) emptyFields.add("armor");
            if(shootingRange==null) emptyFields.add("shooting Range");
            if(GameCharacter.this.position == null) emptyFields.add("position");
            if(emptyFields.size() > 0) {
                String exceptionMessage = "The following fields must be initialized: " + emptyFields.toString();
                throw new IllegalArgumentException(exceptionMessage);
            }
            return GameCharacter.this;
        }
        public GameCharacter build(GameCharacter player) {
            GameCharacter.this.name = player.name;
            GameCharacter.this.health = player.health;
            GameCharacter.this.armor = player.armor;
            GameCharacter.this.damage = player.damage;
            GameCharacter.this.shootingRange = player.shootingRange;
            GameCharacter.this.position = new Position();
            return GameCharacter.this;
        }
    }

    @Override
    public String toString() {
        return   name+' '+health+' '
                +armor+' '+damage+' '
                +shootingRange+' ';
    }

}
