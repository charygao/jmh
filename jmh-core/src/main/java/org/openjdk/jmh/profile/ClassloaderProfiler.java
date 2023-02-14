
package org.openjdk.jmh.profile;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.results.*;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClassloaderProfiler implements InternalProfiler {

    private long loadedClasses;
    private long unloadedClasses;
    private long beforeTime;
    private long afterTime;

    @Override
    public String getDescription() {
        return "Classloader profiling via standard MBeans";
    }

    @Override
    public void beforeIteration(BenchmarkParams benchmarkParams, IterationParams iterationParams) {
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();

        this.beforeTime = System.nanoTime();

        try {
            loadedClasses = cl.getTotalLoadedClassCount();
        } catch (UnsupportedOperationException e) {
            // do nothing
        }
        try {
            unloadedClasses = cl.getUnloadedClassCount();
        } catch (UnsupportedOperationException e) {
            // do nothing
        }
    }

    @Override
    public Collection<? extends Result> afterIteration(BenchmarkParams benchmarkParams, IterationParams iterationParams, IterationResult result) {
        afterTime = System.nanoTime();

        List<Result> results = new ArrayList<>();

        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();

        long allOps = result.getMetadata().getAllOps();
        double time = 1.0 * TimeUnit.SECONDS.toNanos(1) / (afterTime - beforeTime);

        try {
            long loadedClassCount = cl.getTotalLoadedClassCount();
            long loaded = loadedClassCount - loadedClasses;
            results.add(new ScalarResult(Defaults.PREFIX + "class.load", loaded / time, "classes/sec", AggregationPolicy.AVG));
            results.add(new ScalarResult(Defaults.PREFIX + "class.load.norm", 1.0 * loaded / allOps, "classes/op", AggregationPolicy.AVG));
        } catch (UnsupportedOperationException e) {
            // do nothing
        }

        try {
            long unloadedClassCount = cl.getUnloadedClassCount();
            long unloaded = unloadedClassCount - unloadedClasses;
            results.add(new ScalarResult(Defaults.PREFIX + "class.unload", unloaded / time, "classes/sec", AggregationPolicy.AVG));
            results.add(new ScalarResult(Defaults.PREFIX + "class.unload.norm", 1.0 * unloaded / allOps, "classes/op", AggregationPolicy.AVG));

        } catch (UnsupportedOperationException e) {
            // do nothing
        }

        return results;
    }

}
