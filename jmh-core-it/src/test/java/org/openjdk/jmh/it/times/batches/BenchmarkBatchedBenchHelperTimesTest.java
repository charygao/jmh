
package org.openjdk.jmh.it.times.batches;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@State(Scope.Benchmark)
public class BenchmarkBatchedBenchHelperTimesTest {

    private final AtomicInteger countInvocations = new AtomicInteger();
    private final AtomicInteger countSetupRun = new AtomicInteger();
    private final AtomicInteger countSetupIteration = new AtomicInteger();
    private final AtomicInteger countSetupInvocation = new AtomicInteger();
    private final AtomicInteger countTearDownRun = new AtomicInteger();
    private final AtomicInteger countTearDownIteration = new AtomicInteger();
    private final AtomicInteger countTearDownInvocation = new AtomicInteger();

    @Setup(Level.Trial)
    public void setup1() {
        countSetupRun.incrementAndGet();
    }

    @Setup(Level.Iteration)
    public void setup2() {
        countSetupIteration.incrementAndGet();
    }

    @Setup(Level.Invocation)
    public void setup3() {
        countSetupInvocation.incrementAndGet();
    }

    @TearDown(Level.Trial)
    public void tearDown1() {
        countTearDownRun.incrementAndGet();
    }

    @TearDown(Level.Iteration)
    public void tearDown2() {
        countTearDownIteration.incrementAndGet();
    }

    @TearDown(Level.Invocation)
    public void tearDown3() {
        countTearDownInvocation.incrementAndGet();
    }

    @TearDown(Level.Trial)
    public void tearDownLATEST() { // this name ensures this is the latest teardown to run
        Assert.assertEquals("Setup1 called once", 1, countSetupRun.get());
        Assert.assertEquals("Setup2 called twice", 2, countSetupIteration.get());

        Assert.assertEquals("TearDown1 called once", 1, countTearDownRun.get());
        Assert.assertEquals("TearDown2 called twice", 2, countTearDownIteration.get());

        Assert.assertEquals("Setup3 and TearDown3 agree", countSetupInvocation.get(), countTearDownInvocation.get());
        Assert.assertEquals("Test invocations and Setup3 agree", countInvocations.get(), countSetupInvocation.get());
        Assert.assertEquals("Test invocations and TearDown3 agree", countInvocations.get(), countTearDownInvocation.get());
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 0)
    @Measurement(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(2)
    public void test() {
        Fixtures.work();
        countInvocations.incrementAndGet();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .warmupBatchSize(10)
                    .measurementBatchSize(10)
                    .build();
            new Runner(opt).run();
        }
    }

}
