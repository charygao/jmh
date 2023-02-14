
package org.openjdk.jmh.runner.options;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.profile.Profiler;
import org.openjdk.jmh.results.format.ResultFormatType;

import java.util.concurrent.TimeUnit;

public interface ChainedOptionsBuilder {

    /**
     * Produce the final Options
     * @return options object.
     */
    Options build();

    /**
     * Override the defaults from the given option.
     * You may use this only once.
     *
     * @param other options to base on
     * @return builder
     */
    ChainedOptionsBuilder parent(Options other);

    /**
     * Include benchmark in the run
     * (Can be used multiple times)
     *
     * @param regexp to match benchmarks against
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#INCLUDE_BENCHMARKS
     */
    ChainedOptionsBuilder include(String regexp);

    /**
     * Exclude benchmarks from the run
     * (Can be used multiple times)
     *
     * @param regexp to match benchmark against
     * @return builder
     */
    ChainedOptionsBuilder exclude(String regexp);

    /**
     * ResultFormatType to use in the run
     * @param type resultformat type
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#RESULT_FORMAT
     */
    ChainedOptionsBuilder resultFormat(ResultFormatType type);

    /**
     * Output filename to write the run log to
     * @param filename file name
     * @return builder
     */
    ChainedOptionsBuilder output(String filename);

    /**
     * Output filename to write the result to
     * @param filename file name
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#RESULT_FILE_PREFIX
     */
    ChainedOptionsBuilder result(String filename);

    /**
     * Should do GC between measurementIterations?
     * @param value flag
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#DO_GC
     */
    ChainedOptionsBuilder shouldDoGC(boolean value);

    /**
     * Add the profiler in the run
     * @param profiler profiler class
     * @return builder
     */
    ChainedOptionsBuilder addProfiler(Class<? extends Profiler> profiler);

    /**
     * Add the profiler in the run
     * @param profiler profiler class
     * @param initLine profiler options initialization line
     * @return builder
     */
    ChainedOptionsBuilder addProfiler(Class<? extends Profiler> profiler, String initLine);

    /**
     * Add the profiler in the run
     * @param profiler profiler class name, or profiler alias
     * @return builder
     */
    ChainedOptionsBuilder addProfiler(String profiler);

    /**
     * Add the profiler in the run
     * @param profiler profiler class name, or profiler alias
     * @param initLine profiler options initialization line
     * @return builder
     */
    ChainedOptionsBuilder addProfiler(String profiler, String initLine);

    /**
     * Control verbosity level.
     * @param mode flag
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#VERBOSITY
     */
    ChainedOptionsBuilder verbosity(VerboseMode mode);

    /**
     * Should fail on first benchmark error?
     * @param value flag
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#FAIL_ON_ERROR
     */
    ChainedOptionsBuilder shouldFailOnError(boolean value);

    /**
     * Number of threads to run the benchmark in
     * @param count number of threads
     * @return builder
     * @see org.openjdk.jmh.annotations.Threads
     * @see org.openjdk.jmh.runner.Defaults#THREADS
     */
    ChainedOptionsBuilder threads(int count);

    /**
     * Subgroups thread distribution.
     * @param groups thread distribution
     * @return builder
     * @see org.openjdk.jmh.annotations.Group
     * @see org.openjdk.jmh.annotations.GroupThreads
     */
    ChainedOptionsBuilder threadGroups(int... groups);

    /**
     * Should synchronize measurementIterations?
     * @param value flag
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#SYNC_ITERATIONS
     */
    ChainedOptionsBuilder syncIterations(boolean value);

    /**
     * How many warmup iterations to do?
     * @param value flag
     * @return builder
     * @see org.openjdk.jmh.annotations.Warmup
     * @see org.openjdk.jmh.runner.Defaults#WARMUP_ITERATIONS
     * @see org.openjdk.jmh.runner.Defaults#WARMUP_ITERATIONS_SINGLESHOT
     */
    ChainedOptionsBuilder warmupIterations(int value);

    /**
     * How large warmup batchSize should be?
     * @param value batch size
     * @return builder
     * @see org.openjdk.jmh.annotations.Warmup
     * @see org.openjdk.jmh.runner.Defaults#WARMUP_BATCHSIZE
     */
    ChainedOptionsBuilder warmupBatchSize(int value);

    /**
     * How long each warmup iteration should take?
     * @param value time
     * @return builder
     * @see org.openjdk.jmh.annotations.Warmup
     * @see org.openjdk.jmh.runner.Defaults#WARMUP_TIME
     */
    ChainedOptionsBuilder warmupTime(TimeValue value);

    /**
     * Warmup mode to use
     * @param mode to use
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#WARMUP_MODE
     */
    ChainedOptionsBuilder warmupMode(WarmupMode mode);

    /**
     * What other benchmarks to warmup along the way
     * @param regexp to match benchmarks against
     * @return builder
     */
    ChainedOptionsBuilder includeWarmup(String regexp);

    /**
     * How many measurement measurementIterations to do
     * @param count number of iterations
     * @return builder
     * @see org.openjdk.jmh.annotations.Measurement
     * @see org.openjdk.jmh.runner.Defaults#MEASUREMENT_ITERATIONS
     * @see org.openjdk.jmh.runner.Defaults#MEASUREMENT_ITERATIONS_SINGLESHOT
     */
    ChainedOptionsBuilder measurementIterations(int count);

    /**
     * How large measurement batchSize should be?
     * @param value batch size
     * @return builder
     * @see org.openjdk.jmh.annotations.Measurement
     * @see org.openjdk.jmh.runner.Defaults#MEASUREMENT_BATCHSIZE
     */
    ChainedOptionsBuilder measurementBatchSize(int value);

    /**
     * How long each measurement iteration should take?
     * @param value time
     * @return builder
     * @see org.openjdk.jmh.annotations.Measurement
     * @see org.openjdk.jmh.runner.Defaults#MEASUREMENT_TIME
     */
    ChainedOptionsBuilder measurementTime(TimeValue value);

    /**
     * Benchmark mode.
     * (Can be used multiple times)
     *
     * @param mode benchmark mode
     * @return builder
     * @see org.openjdk.jmh.annotations.BenchmarkMode
     * @see org.openjdk.jmh.runner.Defaults#BENCHMARK_MODE
     */
    ChainedOptionsBuilder mode(Mode mode);

    /**
     * Timeunit to use in results
     * @param tu time unit
     * @return builder
     * @see org.openjdk.jmh.annotations.OutputTimeUnit
     * @see org.openjdk.jmh.runner.Defaults#OUTPUT_TIMEUNIT
     */
    ChainedOptionsBuilder timeUnit(TimeUnit tu);

    /**
     * Operations per invocation.
     * @param value operations per invocation.
     * @return builder
     * @see org.openjdk.jmh.annotations.OperationsPerInvocation
     * @see org.openjdk.jmh.runner.Defaults#OPS_PER_INVOCATION
     */
    ChainedOptionsBuilder operationsPerInvocation(int value);

    /**
     * Number of forks to use in the run
     * @param value number of forks
     * @return builder
     * @see org.openjdk.jmh.annotations.Fork
     * @see org.openjdk.jmh.runner.Defaults#MEASUREMENT_FORKS
     */
    ChainedOptionsBuilder forks(int value);

    /**
     * Number of ignored forks
     * @param value number of ignored forks
     * @return builder
     * @see org.openjdk.jmh.annotations.Fork
     * @see org.openjdk.jmh.runner.Defaults#WARMUP_FORKS
     */
    ChainedOptionsBuilder warmupForks(int value);

    /**
     * Forked JVM to use.
     *
     * @param path path to /bin/java
     * @return builder
     */
    ChainedOptionsBuilder jvm(String path);

    /**
     * Forked JVM arguments.
     *
     * @param value arguments to add to the run
     * @return builder
     * @see org.openjdk.jmh.annotations.Fork
     */
    ChainedOptionsBuilder jvmArgs(String... value);

    /**
     * Append forked JVM arguments:
     * These options go after other options.
     *
     * @param value arguments to add to the run
     * @return builder
     * @see org.openjdk.jmh.annotations.Fork
     */
    ChainedOptionsBuilder jvmArgsAppend(String... value);

    /**
     * Prepend forked JVM arguments:
     * These options go before any other options.
     *
     * @param value arguments to add to the run
     * @return builder
     * @see org.openjdk.jmh.annotations.Fork
     */
    ChainedOptionsBuilder jvmArgsPrepend(String... value);

    /**
     * Autodetect forked JVM arguments from the parent VM.
     * Overrides the jvmArgs(...) value.
     *
     * @return builder
     */
    ChainedOptionsBuilder detectJvmArgs();

    /**
     * Set benchmark parameter values.
     * The parameter values would be taken in the order given by user.
     *
     * @param name parameter
     * @param values sequence of values to set
     * @return builder
     * @see org.openjdk.jmh.annotations.Param
     */
    ChainedOptionsBuilder param(String name, String... values);

    /**
     * How long to wait for iteration execution?
     * @param value time
     * @return builder
     * @see org.openjdk.jmh.runner.Defaults#TIMEOUT
     */
    ChainedOptionsBuilder timeout(TimeValue value);

}
