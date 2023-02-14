
package org.openjdk.jmh.results;

import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.util.ListStatistics;
import org.openjdk.jmh.util.Statistics;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Result class that stores average operation time.
 */
public class AverageTimeResult extends Result<AverageTimeResult> {
    private static final long serialVersionUID = 6937689337229703312L;

    public AverageTimeResult(ResultRole mode, String label, double operations, long durationNs, TimeUnit tu) {
        this(mode, label,
                of(durationNs / (operations * TimeUnit.NANOSECONDS.convert(1, tu))),
                TimeValue.tuToString(tu) + "/op");
    }

    AverageTimeResult(ResultRole mode, String label, Statistics value, String unit) {
        super(mode, label, value, unit, AggregationPolicy.AVG);
    }

    @Override
    protected Aggregator<AverageTimeResult> getThreadAggregator() {
        return new ResultAggregator();
    }

    @Override
    protected Aggregator<AverageTimeResult> getIterationAggregator() {
        return new ResultAggregator();
    }

    static class ResultAggregator implements Aggregator<AverageTimeResult> {
        @Override
        public AverageTimeResult aggregate(Collection<AverageTimeResult> results) {
            ListStatistics stat = new ListStatistics();
            for (AverageTimeResult r : results) {
                stat.addValue(r.getScore());
            }
            return new AverageTimeResult(
                    AggregatorUtils.aggregateRoles(results),
                    AggregatorUtils.aggregateLabels(results),
                    stat,
                    AggregatorUtils.aggregateUnits(results)
            );
        }
    }

}
