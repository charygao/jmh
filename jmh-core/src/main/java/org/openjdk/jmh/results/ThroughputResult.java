
package org.openjdk.jmh.results;

import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.util.ListStatistics;
import org.openjdk.jmh.util.Statistics;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Result class that counts the number of operations performed during a specified unit of time.
 */
public class ThroughputResult extends Result<ThroughputResult> {
    private static final long serialVersionUID = 7269598073169413322L;

    public ThroughputResult(ResultRole role, String label, double operations, long durationNs, TimeUnit outputTimeUnit) {
        this(role, label,
                of(operations * TimeUnit.NANOSECONDS.convert(1, outputTimeUnit) / durationNs),
                "ops/" + TimeValue.tuToString(outputTimeUnit),
                AggregationPolicy.SUM);
    }

    ThroughputResult(ResultRole role, String label, Statistics s, String unit, AggregationPolicy policy) {
        super(role, label, s, unit, policy);
    }

    @Override
    protected Aggregator<ThroughputResult> getThreadAggregator() {
        return new ThroughputAggregator(AggregationPolicy.SUM);
    }

    @Override
    protected Aggregator<ThroughputResult> getIterationAggregator() {
        return new ThroughputAggregator(AggregationPolicy.AVG);
    }

    static class ThroughputAggregator implements Aggregator<ThroughputResult> {
        private final AggregationPolicy policy;

        ThroughputAggregator(AggregationPolicy policy) {
            this.policy = policy;
        }

        @Override
        public ThroughputResult aggregate(Collection<ThroughputResult> results) {
            ListStatistics stat = new ListStatistics();
            for (ThroughputResult r : results) {
                stat.addValue(r.getScore());
            }
            return new ThroughputResult(
                    AggregatorUtils.aggregateRoles(results),
                    AggregatorUtils.aggregateLabels(results),
                    stat,
                    AggregatorUtils.aggregateUnits(results),
                    policy
            );
        }
    }

}
