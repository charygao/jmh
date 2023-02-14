
package org.openjdk.jmh.results;

import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.util.ListStatistics;
import org.openjdk.jmh.util.Statistics;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Result class that stores once operation execution time.
 */
public class SingleShotResult extends Result<SingleShotResult> {
    private static final long serialVersionUID = -1251578870918524737L;

    public SingleShotResult(ResultRole role, String label, long duration, TimeUnit outputTimeUnit) {
        // TODO: Transition interface, should be removed when we decide it is OK to break the publicly leaked API.
        this(role, label, duration, 1, outputTimeUnit);
    }

    public SingleShotResult(ResultRole role, String label, long duration, long ops, TimeUnit outputTimeUnit) {
        this(role, label,
                of(1.0D * duration / ops / TimeUnit.NANOSECONDS.convert(1, outputTimeUnit)),
                TimeValue.tuToString(outputTimeUnit) + "/op");
    }

    SingleShotResult(ResultRole mode, String label, Statistics s, String unit) {
        super(mode, label, s, unit, AggregationPolicy.AVG);
    }

    @Override
    public String extendedInfo() {
        return distributionExtendedInfo();
    }

    @Override
    protected Aggregator<SingleShotResult> getThreadAggregator() {
        return new AveragingAggregator();
    }

    @Override
    protected Aggregator<SingleShotResult> getIterationAggregator() {
        return new AveragingAggregator();
    }

    /**
     * Averages the time on all levels.
     */
    static class AveragingAggregator implements Aggregator<SingleShotResult> {
        @Override
        public SingleShotResult aggregate(Collection<SingleShotResult> results) {
            ListStatistics stat = new ListStatistics();
            for (SingleShotResult r : results) {
                stat.addValue(r.getScore());
            }
            return new SingleShotResult(
                    AggregatorUtils.aggregateRoles(results),
                    AggregatorUtils.aggregateLabels(results),
                    stat,
                    AggregatorUtils.aggregateUnits(results)
            );
        }

    }

}
