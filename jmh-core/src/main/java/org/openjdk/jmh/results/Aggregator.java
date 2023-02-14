
package org.openjdk.jmh.results;

import java.util.Collection;

/**
 * Aggregator composes multiple results into one.
 *
 * It is assumed the collection has the results of specified type.
 * This class is generic to save some of the unchecked casts in the code.
 *
 * @param <R> accepted result type
 */
public interface Aggregator<R extends Result> {

    /**
     * Aggregate the results.
     * @param results results to aggregate
     * @return aggregated result; may throw exceptions on validation errors
     */
    R aggregate(Collection<R> results);
}
