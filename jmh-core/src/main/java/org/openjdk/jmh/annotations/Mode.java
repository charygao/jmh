
package org.openjdk.jmh.annotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Benchmark mode.
 */
public enum Mode {

    /**
     * <p>Throughput: operations per unit of time.</p>
     *
     * <p>Runs by continuously calling {@link Benchmark} methods,
     * counting the total throughput over all worker threads. This mode is time-based, and it will
     * run until the iteration time expires.</p>
     */
    Throughput("thrpt", "Throughput, ops/time"),

    /**
     * <p>Average time: average time per per operation.</p>
     *
     * <p>Runs by continuously calling {@link Benchmark} methods,
     * counting the average time to call over all worker threads. This is the inverse of {@link Mode#Throughput},
     * but with different aggregation policy. This mode is time-based, and it will run until the iteration time
     * expires.</p>
     */
    AverageTime("avgt", "Average time, time/op"),

    /**
     * <p>Sample time: samples the time for each operation.</p>
     *
     * <p>Runs by continuously calling {@link Benchmark} methods,
     * and randomly samples the time needed for the call. This mode automatically adjusts the sampling
     * frequency, but may omit some pauses which missed the sampling measurement. This mode is time-based, and it will
     * run until the iteration time expires.</p>
     */
    SampleTime("sample", "Sampling time"),

    /**
     * <p>Single shot time: measures the time for a single operation.</p>
     *
     * <p>Runs by calling {@link Benchmark} once and measuring its time.
     * This mode is useful to estimate the "cold" performance when you don't want to hide the warmup invocations, or
     * if you want to see the progress from call to call, or you want to record every single sample. This mode is
     * work-based, and will run only for a single invocation of {@link Benchmark}
     * method.</p>
     *
     * Caveats for this mode include:
     * <ul>
     *  <li>More warmup/measurement iterations are generally required.</li>
     *  <li>Timers overhead might be significant if benchmarks are small; switch to {@link #SampleTime} mode if
     *  that is a problem.</li>
     * </ul>
     */
    SingleShotTime("ss", "Single shot invocation time"),

    /**
     * Meta-mode: all the benchmark modes.
     * This is mostly useful for internal JMH testing.
     */
    All("all", "All benchmark modes"),

    ;

    private final String shortLabel;
    private final String longLabel;

    Mode(String shortLabel, String longLabel) {
        this.shortLabel = shortLabel;
        this.longLabel = longLabel;
    }

    public String shortLabel() {
        return shortLabel;
    }

    public String longLabel() {
        return longLabel;
    }

    public static Mode deepValueOf(String name) {
        try {
            return Mode.valueOf(name);
        } catch (IllegalArgumentException iae) {
            Mode inferred = null;
            for (Mode type : values()) {
                if (type.shortLabel().startsWith(name)) {
                    if (inferred == null) {
                        inferred = type;
                    } else {
                        throw new IllegalStateException("Unable to parse benchmark mode, ambiguous prefix given: \"" + name + "\"\n" +
                                "Known values are " + getKnown());
                    }
                }
            }
            if (inferred != null) {
                return inferred;
            } else {
                throw new IllegalStateException("Unable to parse benchmark mode: \"" + name + "\"\n" +
                        "Known values are " + getKnown());
            }
        }
    }

    public static List<String> getKnown() {
        List<String> res = new ArrayList<>();
        for (Mode type : Mode.values()) {
            res.add(type.name() + "/" + type.shortLabel());
        }
        return res;
    }

}
