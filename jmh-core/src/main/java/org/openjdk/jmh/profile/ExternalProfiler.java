
package org.openjdk.jmh.profile;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.Result;

import java.io.File;
import java.util.Collection;

/**
 * External profiler: profilers to be run outside of JVM.
 *
 * <p>External profilers usually call external tools to get the performance data.
 * It is futile to query any internal JVM facilities in external profiler
 * Java code, because it may not be executed in the benchmarked VM at all.</p>
 */
public interface ExternalProfiler extends Profiler {

    /**
     * Prepend JVM invocation with these commands.
     *
     * @param params benchmark parameters used for current launch
     * @return commands to prepend for JVM launch
     */
    Collection<String> addJVMInvokeOptions(BenchmarkParams params);

    /**
     * Add JVM these options to the run.
     *
     * @param params benchmark parameters used for current launch
     * @return options to add to JVM launch
     */
    Collection<String> addJVMOptions(BenchmarkParams params);

    /**
     * Run this code before starting the trial. This method will execute
     * before starting the benchmark JVM.
     *
     * @param benchmarkParams benchmark parameters used for current launch
     */
    void beforeTrial(BenchmarkParams benchmarkParams);

    /**
     * Run this code after the trial is done. This method will execute
     * after benchmark JVM had stopped.
     *
     * @param br benchmark result that was the result of the trial
     * @param pid pid that the forked JVM had
     * @param stdOut file containing the standard output from the benchmark JVM
     * @param stdErr file containing the standard error from the benchmark JVM
     * @return profiler results
     */
    Collection<? extends Result> afterTrial(BenchmarkResult br, long pid, File stdOut, File stdErr);

    /**
     * If target VM communicates with profiler with standard output, this method
     * can be used to shun the output to console. Profiler is responsible for consuming
     * the standard output and printing the relevant data from there.
     *
     * @return returns true, if profiler allows harness to print out the standard output
     */
    boolean allowPrintOut();

    /**
     * If target VM communicates with profiler with standard error, this method
     * can be used to shun the output to console. Profiler is responsible for consuming
     * the standard error and printing the relevant data from there.
     *
     * @return returns true, if profiler allows harness to print out the standard errpr
     */
    boolean allowPrintErr();
}
