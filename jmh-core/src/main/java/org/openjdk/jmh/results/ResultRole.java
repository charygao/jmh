
package org.openjdk.jmh.results;

public enum ResultRole {

    /**
     * Participates in primary metric calculation.
     */
    PRIMARY,

    /**
     * Participates in secondary metric calculations.
     */
    SECONDARY,

    /**
     * Same as {@link #SECONDARY}, but always recomputed.
     */
    SECONDARY_DERIVATIVE,

    /**
     * Does not participate in any metric, garbage result.
     */
    OMITTED,
    ;

    public boolean isPrimary() {
        return this == PRIMARY;
    }

    public boolean isSecondary() {
        return this == SECONDARY || this == SECONDARY_DERIVATIVE;
    }

    public boolean isDerivative() {
        return this == SECONDARY_DERIVATIVE;
    }

}
