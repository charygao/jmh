
package org.openjdk.jmh.runner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActionPlan implements Serializable {
    private static final long serialVersionUID = 7250784375093638099L;

    private final List<Action> actions;
    private final ActionType type;

    public ActionPlan(ActionType type) {
        this.type = type;
        this.actions = new ArrayList<>();
    }

    public ActionType getType() {
        return type;
    }

    public void add(Action action) {
        actions.add(action);
    }

    public void mixIn(ActionPlan other) {
        actions.addAll(other.actions);
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Action> getMeasurementActions() {
        List<Action> result = new ArrayList<>();
        for (Action action : actions) {
            switch (action.getMode()) {
                case MEASUREMENT:
                case WARMUP_MEASUREMENT:
                    result.add(action);
                    break;

            }
        }
        return result;
    }

}
