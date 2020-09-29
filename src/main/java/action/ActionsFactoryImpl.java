package action;

import robot.Robot;

import java.util.HashMap;
import java.util.Map;

public class ActionsFactoryImpl implements ActionsFactory {

    private static final Map<Robot.Type, Action[]> actionMap = new HashMap<>();

    private static final Action showStatsAction = new ShowStatsAction();
    private static final Action showOthersStatsAction = new ShowOthersStatsAction();
    private static final Action skipAction = new SkipAction();

    private static Action[] initTankActions() {
        Action[] actions = new Action[5];

        actions[0] = showStatsAction;
        actions[1] = showOthersStatsAction;
        actions[2] = skipAction;

        // Machine gun
        actions[3] = new DamageAction("Machine gun", 5, 2);

        // Tank gun (a.k.a. main gun)
        actions[4] = new DamageAction("Main gun", 3, 4);

        return actions;
    }

    private static Action[] initTransformerActions() {
        Action[] actions = new Action[6];

        actions[0] = showStatsAction;
        actions[1] = showOthersStatsAction;
        actions[2] = skipAction;

        // Cut
        actions[3] = new DamageAction("Cut", Integer.MAX_VALUE, 1);

        // Slice
        actions[4] = new DamageAction("Slice", Integer.MAX_VALUE, 2);

        // Gun
        actions[5] = new DamageAction("Gun", 3, 4);

        return actions;
    }

    public Action[] actions(Robot.Type type) {
        switch (type) {
            case TANK: return initTankActions();
            case TRANSFORMER: return initTransformerActions();
            default: return new Action[0];
        }
    }

}
