
package org.openjdk.jmh.it.threads;

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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Tests if harness executes setup, run, and tearDown in the same workers.
 */
@State(Scope.Benchmark)
public class BenchmarkBenchSameThreadTest {

    private final Set<Thread> setupRunThread = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> setupIterationThread = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> setupInvocationThread = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> teardownRunThread = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> teardownIterationThread = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> teardownInvocationThread = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> testInvocationThread = Collections.synchronizedSet(new HashSet<>());

    @Setup(Level.Trial)
    public void setupRun() {
        setupRunThread.add(Thread.currentThread());
    }

    @Setup(Level.Iteration)
    public void setupIteration() {
        setupIterationThread.add(Thread.currentThread());
    }

    @Setup(Level.Invocation)
    public void setupInvocation() {
        setupInvocationThread.add(Thread.currentThread());
    }

    @TearDown(Level.Trial)
    public void tearDownRun() {
        teardownRunThread.add(Thread.currentThread());
    }

    @TearDown(Level.Iteration)
    public void tearDownIteration() {
        teardownIterationThread.add(Thread.currentThread());
    }

    @TearDown(Level.Invocation)
    public void tearDownInvocation() {
        teardownInvocationThread.add(Thread.currentThread());
    }

    @TearDown(Level.Trial)
    public void teardownZZZ() { // should perform last
        Assert.assertFalse("Test sanity", testInvocationThread.isEmpty());
        Assert.assertTrue("test <: setupRun", testInvocationThread.containsAll(setupRunThread));
        Assert.assertTrue("test <: setupIteration", testInvocationThread.containsAll(setupIterationThread));
        Assert.assertTrue("test <: setupInvocation", testInvocationThread.containsAll(setupInvocationThread));
        Assert.assertTrue("test <: teardownRun", testInvocationThread.containsAll(teardownRunThread));
        Assert.assertTrue("test <: teardownIteration", testInvocationThread.containsAll(teardownIterationThread));
        Assert.assertTrue("test <: teardownInvocation", testInvocationThread.containsAll(teardownInvocationThread));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 0)
    @Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(4)
    public void test() {
        testInvocationThread.add(Thread.currentThread());
        Fixtures.work();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .shouldFailOnError(true)
                    .build();
            new Runner(opt).run();
        }
    }

}
