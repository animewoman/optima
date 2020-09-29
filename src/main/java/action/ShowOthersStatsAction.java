package action;

import context.Context;
import robot.Robot;

class ShowOthersStatsAction extends Action {

    public ShowOthersStatsAction() {
        super("Check other's stats", Integer.MAX_VALUE, true);
    }

    @Override
    protected ActionStatus run(String userId, Context cnxt) {

        Robot robot = cnxt.getOtherRobot(userId);

        StringBuilder builder = new StringBuilder();

        builder
                .append(robot.getUsedId()).append("'s stats : {")
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
