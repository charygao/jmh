
package org.openjdk.jmh.runner.format;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.IterationResult;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.io.PrintStream;
import java.util.Collection;

/**
 * Silent format, does nothing.
 */
class SilentFormat extends AbstractOutputFormat {

    public SilentFormat(PrintStream out, VerboseMode verbose) {
        super(out, verbose);
    }

    @Override
    public void startRun() {
    }

    @Override
    public void endRun(Collection<RunResult> results) {
    }

    @Override
    public void startBenchmark(BenchmarkParams benchmarkParams) {

    }

    @Override
    public void endBenchmark(BenchmarkResult result) {

    }

    @Override
    public void iteration(BenchmarkParams benchmarkParams, IterationParams params, int iteration) {

    }

    @Override
    public void iterationResult(BenchmarkParams benchmarkParams, IterationParams params, int iteration, IterationResult data) {

    }

    @Override
    public void print(String s) {

    }

    @Override
    public void println(String s) {

    }

    @Override
    public void verbosePrintln(String s) {

    }
}
