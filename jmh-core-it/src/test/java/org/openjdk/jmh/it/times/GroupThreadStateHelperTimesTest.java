
package org.openjdk.jmh.it.times;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tests if harness executes setup, run, and tearDown in the same workers.
 */
@BenchmarkMode(Mode.All)
@Warmup(iterations = 5,  time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class GroupThreadStateHelperTimesTest {

    @State(Scope.Thread)
    public static class State1 {
        static final AtomicInteger setupTimes = new AtomicInteger();
        static final AtomicInteger tearDownTimes = new AtomicInteger();

        @Setup
        public void setup() {
            setupTimes.incrementAndGet();
        }

        @Setup
        public void tearDown() {
            tearDownTimes.incrementAndGet();
        }
    }

    @State(Scope.Thread)
    public static class State2 {
        static final AtomicInteger setupTimes = new AtomicInteger();
        static final AtomicInteger tearDownTimes = new AtomicInteger();

        @Setup
        public void setup() {
            setupTimes.incrementAndGet();
        }

        @Setup
        public void tearDown() {
            tearDownTimes.incrementAndGet();
        }
    }

    @Setup
    public void reset() {
        State1.setupTimes.set(0);
        State1.tearDownTimes.set(0);
        State2.setupTimes.set(0);
        State2.tearDownTimes.set(0);
    }

    @TearDown
    public void verify() {
        Assert.assertEquals(1, State1.setupTimes.get());
        Assert.assertEquals(1, State1.tearDownTimes.get());
        Assert.assertEquals(1, State2.setupTimes.get());
        Assert.assertEquals(1, State2.tearDownTimes.get());
    }

    @Benchmark
    @Group("T")
    public void test1(State1 state1) {
        Fixtures.work();
    }

    @Benchmark
    @Group("T")
    public void test2(State2 state2) {
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
