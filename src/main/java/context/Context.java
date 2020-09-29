package context;

import action.Action;
import action.ActionsFactory;
import action.ActionsFactoryImpl;
import robot.Robot;

import java.util.HashMap;
import java.util.Map;

public class Context {

    public enum State {
        PLAYER_ONE_WON,
        PLAYER_TWO_WON,
        DRAW
    }

    public static final int ROUNDS = 2;
    public static final int ACTIONS_AVAIL = 2; // actions available for users per round

    private static Context context;

    private static Map<Robot, Action[]> robotActionsMap = new HashMap<>();

    public synchronized static Context get(Robot robotOne, Robot robotTwo) {

        if(context == null) {
            context = new Context(robotOne, robotTwo);
        }
        return context;
    }

    private ActionsFactory actionsFactory = new ActionsFactoryImpl();
    private Robot robotOne;
    private Robot robotTwo;

    private State gameState;

    public Context(Robot robotOne, Robot robotTwo) {
        this.robotOne = robotOne;
        this.robotTwo = robotTwo;

        robotActionsMap.put(robotOne, actionsFactory.actions(robotOne.getType()));
        robotActionsMap.put(robotTwo, actionsFactory.actions(robotTwo.getType()));
    }

    public Robot getRobot(String userId) {
        return robotOne.getUsedId().equals(userId) ? robotOne : robotTwo;
    }

    public Robot getOtherRobot(String userId) {
        return !robotOne.getUsedId().equals(userId) ? robotOne : robotTwo;
    }

    public Action[] getActions(Robot robot) {
        return robotActionsMap.get(robot);
    }

    public State getState() { return gameState; }

    public State evalState() {

             if(robotOne.getHealth() == 0) { gameState = State.PLAYER_TWO_WON; }
        else if(robotTwo.getHealth() == 0) { gameState = State.PLAYER_ONE_WON; }
        else { gameState = State.DRAW; }

        return gameState;
    }
}
