package action;

import context.Context;
import robot.Robot;

class DamageAction extends Action{

    private int damage;

    public DamageAction(String name, int timesLeft, int damage) {
        super(name, timesLeft, false);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    protected ActionStatus run(String userId, Context cnxt) {

        Robot enemy = cnxt.getOtherRobot(userId);

        enemy.takeDamage(damage);

        return new ActionStatus("Damage is done!!!");
    }

    @Override
    public String description() {
        return getName() + " { damage : " + getDamage() + ( isInfinite() ? "" : ", times : " + getTimesLeft()) + " }";
    }
}
