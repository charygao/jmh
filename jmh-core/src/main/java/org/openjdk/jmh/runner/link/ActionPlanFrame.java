
package org.openjdk.jmh.runner.link;

import org.openjdk.jmh.runner.ActionPlan;

import java.io.Serializable;

class ActionPlanFrame implements Serializable {
    private static final long serialVersionUID = 4652437412096164885L;

    private final ActionPlan actionPlan;

    public ActionPlanFrame(ActionPlan actionPlan) {
        this.actionPlan = actionPlan;
    }

    public ActionPlan getActionPlan() {
        return actionPlan;
    }
}
