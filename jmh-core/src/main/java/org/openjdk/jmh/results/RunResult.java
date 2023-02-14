
package org.openjdk.jmh.results;

import org.openjdk.jmh.infra.BenchmarkParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

/**
 * Complete run result.
 * Contains the iteration results.
 */
public class RunResult implements Serializable {

    private static final long serialVersionUID = 6467912427356048369L;

    private final Collection<BenchmarkResult> benchmarkResults;
    private final BenchmarkParams params;

    public RunResult(BenchmarkParams params, Collection<BenchmarkResult> data) {
        this.benchmarkResults = data;
        this.params = params;
    }

    public Collection<BenchmarkResult> getBenchmarkResults() {
        return benchmarkResults;
    }

    public Result getPrimaryResult() {
         return getAggregatedResult().getPrimaryResult();
    }

    public Map<String, Result> getSecondaryResults() {
        return getAggregatedResult().getSecondaryResults();
    }

    /**
     * Return the benchmark result, as if all iterations from all sub-benchmark results
     * were merged in a single result.
     *
     * @return merged benchmark result
     */
    public BenchmarkResult getAggregatedResult() {
        if (benchmarkResults.isEmpty()) {
            return null;
        }

        Collection<IterationResult> results = new ArrayList<>();
        for (BenchmarkResult r : benchmarkResults) {
            results.addAll(r.getIterationResults());
        }
        BenchmarkResult result = new BenchmarkResult(params, results);
        for (BenchmarkResult br : benchmarkResults) {
            for (String k : br.getBenchmarkResults().keys()) {
                for (Result r : br.getBenchmarkResults().get(k)) {
                    result.addBenchmarkResult(r);
                }
            }
        }
        return result;
    }

    public BenchmarkParams getParams() {
        return params;
    }

    public static final Comparator<RunResult> DEFAULT_SORT_COMPARATOR = Comparator.comparing(o -> o.params);

}
