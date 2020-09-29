package robot;

public interface RobotFactory {

    Robot createRobot(String userId, Robot.Type type);
}
