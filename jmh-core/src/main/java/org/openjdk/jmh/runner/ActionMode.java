
package org.openjdk.jmh.runner;

enum ActionMode {

    /**
     * No action.
     */
    UNDEF(false, false),

    /**
     * Do warmup only.
     */
    WARMUP(true, false),

    /**
     * Do measurement only.
     */
    MEASUREMENT(false, true),

    /**
     * Do both warmup and measurement
     */
    WARMUP_MEASUREMENT(true, true),
    ;

    private final boolean doWarmup;
    private final boolean doMeasurement;

    ActionMode(boolean doWarmup, boolean doMeasurement) {
        this.doWarmup = doWarmup;
        this.doMeasurement = doMeasurement;
    }

    public boolean doWarmup() {
        return doWarmup;
    }

    public boolean doMeasurement() {
        return doMeasurement;
    }
}
