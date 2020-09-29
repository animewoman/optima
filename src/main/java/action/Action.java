package action;

import context.Context;

public abstract class Action {

    private int timesLeft;
    private String name;
    private boolean isRepeatable; // Can be repeated within one action

    public Action(String name, int timesLeft, boolean isRepeatable) {
        this.name = name;
        this.timesLeft = timesLeft;
        this.isRepeatable = isRepeatable;
    }

    public int getTimesLeft() {
        return timesLeft;
    }

    public void setTimesLeft(int timesLeft) {
        this.timesLeft = timesLeft;
    }

    public String getName() {
        return name;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public boolean isInfinite() {
        return timesLeft == Integer.MAX_VALUE;
    }

    public final ActionStatus start(String userId, Context cnxt) {

        if(timesLeft == 0)
            return new ActionStatus("The action cannot be done, no times left...");

        ActionStatus status = run(userId, cnxt);

        if(!isInfinite())
            timesLeft--;

        return status;
    }

    protected abstract ActionStatus run(String userId, Context cnxt);

    public abstract String description();
}
