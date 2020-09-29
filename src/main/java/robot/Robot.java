package robot;

import java.util.Objects;

public class Robot {

    public enum Type { TANK, TRANSFORMER };

    private String usedId;
    private Type type;
    private int health;

    Robot(String usedId, Type type, int health) {
        this.usedId = usedId;
        this.type = type;
        this.health = health;
    }

    public String getUsedId() {
        return usedId;
    }

    public Type getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health < 0) this.health = 0;
        else this.health = health;
    }

    public void takeDamage(int damage) {

        if(damage > health) health = 0;
        else                health = health - damage;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;
        return usedId.equals(robot.usedId) && type == robot.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usedId, type);
    }
}
