
package org.openjdk.jmh.runner.format;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.IterationResult;
import org.openjdk.jmh.results.RunResult;

import java.io.IOException;
import java.util.Collection;

/**
 * Internal interface for OutputFormat.
 */
public interface OutputFormat {

    /**
     * Format for iteration start.
     *
     * @param benchParams benchmark parameters
     * @param params iteration params in use
     * @param iteration iteration-number
     */
    void iteration(BenchmarkParams benchParams, IterationParams params, int iteration);

    /**
     * Format for end-of-iteration.
     *
     * @param benchParams      name of benchmark
     * @param params    iteration params in use
     * @param iteration iteration-number
     * @param data    result of iteration
     */
    void iterationResult(BenchmarkParams benchParams, IterationParams params, int iteration, IterationResult data);

    /**
     * Format for start-of-benchmark output.
     * @param benchParams benchmark params
     */
    void startBenchmark(BenchmarkParams benchParams);

    /**
     * Format for end-of-benchmark.
     *
     * @param result statistics of the run
     */
    void endBenchmark(BenchmarkResult result);

    /**
     * Format for start-of-benchmark output.
     */
    void startRun();

    /**
     * Format for end-of-benchmark.
     * @param result benchmark results
     */
    void endRun(Collection<RunResult> result);

    /* ------------- RAW OUTPUT METHODS ------------------- */

    void print(String s);

    void println(String s);

    void flush();

    void close();

    void verbosePrintln(String s);

    void write(int b);

    void write(byte[] b) throws IOException;

}
