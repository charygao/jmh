
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

import java.util.concurrent.TimeUnit;

/**
 * Tests if harness executes setup, run, and tearDown in the same workers.
 */
@State(Scope.Thread)
public class ThreadBenchSameThreadTest {

    private Thread setupRunThread;
    private Thread setupIterationThread;
    private Thread setupInvocationThread;
    private Thread teardownRunThread;
    private Thread teardownIterationThread;
    private Thread tearDownInvocationThread;
    private Thread testInvocationThread;

    @Setup(Level.Trial)
    public void setupRun() {
        if (setupRunThread == null) {
            setupRunThread = Thread.currentThread();
        }
        Assert.assertEquals("setupRun", setupRunThread, Thread.currentThread());
    }

    @Setup(Level.Iteration)
    public void setupIteration() {
        if (setupIterationThread == null) {
            setupIterationThread = Thread.currentThread();
        }
        Assert.assertEquals("setupIteration", setupIterationThread, Thread.currentThread());
    }

    @Setup(Level.Invocation)
    public void setupInvocation() {
        if (setupInvocationThread == null) {
            setupInvocationThread = Thread.currentThread();
        }
        Assert.assertEquals("setupInvocation", setupInvocationThread, Thread.currentThread());
    }

    @TearDown(Level.Trial)
    public void tearDownRun() {
        if (teardownRunThread == null) {
            teardownRunThread = Thread.currentThread();
        }
        Assert.assertEquals("teardownRun", teardownRunThread, Thread.currentThread());
    }

    @TearDown(Level.Iteration)
    public void tearDownIteration() {
        if (teardownIterationThread == null) {
            teardownIterationThread = Thread.currentThread();
        }
        Assert.assertEquals("teardownIteration", teardownIterationThread, Thread.currentThread());
    }

    @TearDown(Level.Invocation)
    public void tearDownInvocation() {
        if (tearDownInvocationThread == null) {
            tearDownInvocationThread = Thread.currentThread();
        }
        Assert.assertEquals("tearDownInvocation", tearDownInvocationThread, Thread.currentThread());
    }

    @TearDown(Level.Trial)
    public void teardownZZZ() { // should perform last
        Assert.assertEquals("test != setupRun",           testInvocationThread, setupRunThread);
        Assert.assertEquals("test != setupIteration",     testInvocationThread, setupIterationThread);
        Assert.assertEquals("test != setupInvocation",    testInvocationThread, setupInvocationThread);
        Assert.assertEquals("test != teardownRun",        testInvocationThread, teardownRunThread);
        Assert.assertEquals("test != teardownIteration",  testInvocationThread, teardownIterationThread);
        Assert.assertEquals("test != teardownInvocation", testInvocationThread, tearDownInvocationThread);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 0)
    @Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(4)
    public void test() {
        if (testInvocationThread == null) {
            testInvocationThread = Thread.currentThread();
        }
        Assert.assertEquals("test", testInvocationThread, Thread.currentThread());
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
