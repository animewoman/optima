import action.Action;
import action.ActionStatus;
import context.Context;
import robot.Robot;
import robot.RobotFactory;
import robot.RobotFactoryImpl;

import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    static RobotFactory robotFactory = new RobotFactoryImpl();

    public static void main(String[] args) {

        printRules();

        Robot robot1 = initUser();
        Robot robot2 = initUser();

        Context ctx = Context.get(robot1, robot2);

        int totalActions = Context.ROUNDS * Context.ACTIONS_AVAIL;
        for (int action = 0; action < totalActions; action++) {

            if(action % Context.ROUNDS == 0) {
                int roundNumber = action / Context.ROUNDS + 1;
                System.out.println("\nROUND " + roundNumber + " STARTED : ");
            }

            performAction(robot1, ctx);

            if(ctx.evalState() != Context.State.DRAW) break;

            performAction(robot2, ctx);

            if(ctx.evalState() != Context.State.DRAW) break;
        }

        switch (ctx.getState()) {
            case PLAYER_ONE_WON: {
                System.out.println("\nPLAYER " + robot1.getUsedId() + " HAVE WON!!!");
                break;
            }
            case PLAYER_TWO_WON: {
                System.out.println("\nPLAYER " + robot2.getUsedId() + " HAVE WON!!!");
                break;
            }
            default: {
                System.out.println("\nNO ONE HAVE WON!!!");
            }
        }
    }

    static void printRules() {

        StringBuilder builder = new StringBuilder();

        builder.append("++++++++++++++++WELCOME TO ROBOT DEATHMATCH++++++++++++++++\n")
                .append("Rules are simple : \n")
                .append("You got " + Context.ROUNDS + " rounds\n")
                .append("Within each round both of you can do " + Context.ACTIONS_AVAIL + " actions\n")
                .append("Good luck!!!\n");

        System.out.println(builder.toString());
    }

    static Robot initUser() {

        System.out.println("\nUSER INITIALIZATION");
        System.out.print("Type name : ");
        String userId = in.next();

        System.out.println("Choose robot :");
        Robot.Type[] types = Robot.Type.values();
        for(int i=0; i<types.length; i++) {
            System.out.println(i + ". " + types[i].name());
        }
        System.out.print("&> ");
        int typeChoice = in.nextInt();

        Robot.Type type = types[typeChoice];

        return robotFactory.createRobot(userId, type);
    }

    static void performAction(Robot robot, Context cxt) {

        System.out.println("\n" + robot.getUsedId() + "'s turn : ");
        Action[] actions = cxt.getActions(robot);

        System.out.println("Choose action : ");
        for(int i=0; i<actions.length; i++)
            System.out.println(i + ". " + actions[i].description());

        Action choosenAction = null;
        do {
            System.out.print("&> ");
            int choice = in.nextInt();

            choosenAction = actions[choice];
            ActionStatus status = choosenAction.start(robot.getUsedId(), cxt);

            System.out.println(status.getMsg());

        } while (choosenAction.isRepeatable());

    }
}
