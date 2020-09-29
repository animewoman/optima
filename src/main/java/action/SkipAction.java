package action;

import context.Context;

class SkipAction extends Action {

    public SkipAction() {
        super("skip", Integer.MAX_VALUE, false);
    }

    @Override
    protected ActionStatus run(String userId, Context cnxt) {

        //do nothing
        return new ActionStatus("action.Action has been skipped!!!");
    }

    @Override
    public String description() {
        return getName();
    }
}
