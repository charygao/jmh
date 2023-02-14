
package org.openjdk.jmh.runner.options;

/**
 * Warmup Mode enum
 */
public enum WarmupMode {

    /**
     * Do the individual warmup for every benchmark
     */
    INDI(false, true),

    /**
     * Do the bulk warmup before any benchmark starts.
     */
    BULK(true, false),

    /**
     * Do the bulk warmup before any benchmark starts,
     * and then also do individual warmups for every
     * benchmark.
     */
    BULK_INDI(true, true),

    ;
    private final boolean bulk;
    private final boolean indi;

    WarmupMode(boolean bulk, boolean indi) {
        this.bulk = bulk;
        this.indi = indi;
    }

    public boolean isBulk() {
        return bulk;
    }

    public boolean isIndi() {
        return indi;
    }

}
