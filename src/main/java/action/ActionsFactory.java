package action;

import robot.Robot;

public interface ActionsFactory {

    Action[] actions(Robot.Type type);

}
