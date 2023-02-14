
package org.openjdk.jmh.runner;

import org.openjdk.jmh.infra.BenchmarkParams;

import java.io.Serializable;

class Action implements Serializable {
    private static final long serialVersionUID = -7315320958163363586L;

    private final BenchmarkParams params;
    private final ActionMode mode;

    public Action(BenchmarkParams params, ActionMode mode) {
        this.params = params;
        this.mode = mode;
    }

    public ActionMode getMode() {
        return mode;
    }

    public BenchmarkParams getParams() {
        return params;
    }
}
