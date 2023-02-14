
package org.openjdk.jmh.profile;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.results.*;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CompilerProfiler implements InternalProfiler {

    private long startCompTime;

    @Override
    public String getDescription() {
        return "JIT compiler profiling via standard MBeans";
    }

    public CompilerProfiler() throws ProfilerException {
        CompilationMXBean comp = ManagementFactory.getCompilationMXBean();
        if (!comp.isCompilationTimeMonitoringSupported()) {
            throw new ProfilerException("The MXBean is available, but compilation time monitoring is disabled.");
        }
    }

    @Override
    public void beforeIteration(BenchmarkParams benchmarkParams, IterationParams iterationParams) {
        CompilationMXBean comp = ManagementFactory.getCompilationMXBean();
        try {
            startCompTime = comp.getTotalCompilationTime();
        } catch (UnsupportedOperationException e) {
            // do nothing
        }
    }

    @Override
    public Collection<? extends Result> afterIteration(BenchmarkParams benchmarkParams, IterationParams iterationParams, IterationResult result) {
        CompilationMXBean comp = ManagementFactory.getCompilationMXBean();
        try {
            long curTime = comp.getTotalCompilationTime();
            return Arrays.asList(
                new ScalarResult(Defaults.PREFIX + "compiler.time.profiled", curTime - startCompTime, "ms", AggregationPolicy.SUM),
                new ScalarResult(Defaults.PREFIX + "compiler.time.total", curTime, "ms", AggregationPolicy.MAX)
            );
        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        }
    }

}
