package robot;

public class RobotFactoryImpl implements RobotFactory{

    public Robot createRobot(String userId, Robot.Type type) {
        return new Robot(userId, type, healthStrategy(type));
    }

    // At this moment all the robots have 10 health units
    private int healthStrategy(Robot.Type type) {
        return 10;
    }
}
