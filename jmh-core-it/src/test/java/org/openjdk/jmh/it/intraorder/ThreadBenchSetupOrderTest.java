
package org.openjdk.jmh.it.intraorder;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Benchmark;
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

/**
 * Tests if harness executes setup (instance) methods in lexicographical order.
 */
@State(Scope.Thread)
public class ThreadBenchSetupOrderTest {

    private final AtomicInteger seq = new AtomicInteger();
    private int run1, run2, run3, runD;
    private int iter1, iter2, iter3, iterD;
    private int invoc1, invoc2, invoc3, invocD;

    @Setup(Level.Trial)
    public void run1() {
        run1 = seq.incrementAndGet();
    }

    @Setup(Level.Trial)
    public void run3() {
        run3 = seq.incrementAndGet();
    }

    @Setup(Level.Trial)
    public void run2() {
        run2 = seq.incrementAndGet();
    }

    @Setup(Level.Trial)
    public void run() {
        runD = seq.incrementAndGet();
    }

    @Setup(Level.Iteration)
    public void iteration1() {
        iter1 = seq.incrementAndGet();
    }

    @Setup(Level.Iteration)
    public void iteration3() {
        iter3 = seq.incrementAndGet();
    }

    @Setup(Level.Iteration)
    public void iteration2() {
        iter2 = seq.incrementAndGet();
    }

    @Setup(Level.Iteration)
    public void iteration() {
        iterD = seq.incrementAndGet();
    }

    @Setup(Level.Invocation)
    public void invocation1() {
        invoc1 = seq.incrementAndGet();
    }

    @Setup(Level.Invocation)
    public void invocation3() {
        invoc3 = seq.incrementAndGet();
    }

    @Setup(Level.Invocation)
    public void invocation2() {
        invoc2 = seq.incrementAndGet();
    }

    @Setup(Level.Invocation)
    public void invocation() {
        invocD = seq.incrementAndGet();
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        Assert.assertTrue("Trial(D) < Trial(1)", runD < run1);
        Assert.assertTrue("Trial(1) < Trial(2)", run1 < run2);
        Assert.assertTrue("Trial(2) < Trial(3)", run2 < run3);

        Assert.assertTrue("Iter(D) < Iter(1)", iterD < iter1);
        Assert.assertTrue("Iter(1) < Iter(2)", iter1 < iter2);
        Assert.assertTrue("Iter(2) < Iter(3)", iter2 < iter3);

        Assert.assertTrue("Invoc(D) < Invoc(1)", invocD < invoc1);
        Assert.assertTrue("Invoc(1) < Invoc(2)", invoc1 < invoc2);
        Assert.assertTrue("Invoc(2) < Invoc(3)", invoc2 < invoc3);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(2)
    public void test() {
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
