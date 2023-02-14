
package org.openjdk.jmh.runner.options;

public enum VerboseMode {

    /**
     * Be completely silent.
     */
    SILENT(0),

    /**
     * Output normally.
     */
    NORMAL(1),

    /**
     * Output extra info.
     */
    EXTRA(2),

    ;

    private final int level;

    VerboseMode(int level) {
        this.level = level;
    }

    public boolean equalsOrHigherThan(VerboseMode other) {
        return level >= other.level;
    }

}
