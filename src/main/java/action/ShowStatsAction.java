package action;

import context.Context;
import robot.Robot;

class ShowStatsAction extends Action{

    public ShowStatsAction() {
        super("Check stats", Integer.MAX_VALUE, true);
    }

    @Override
    protected ActionStatus run(String userId, Context cnxt) {

        Robot robot = cnxt.getRobot(userId);

        StringBuilder builder = new StringBuilder();

        builder
                .append(userId).append("'s stats : {")
                .append(" type : ").append(robot.getType()).append(", ")
                .append(" health : ").append(robot.getHealth())
                .append(" }\n");

        return new ActionStatus(builder.toString());
    }

    @Override
    public String description() {
        return getName();
    }
}
