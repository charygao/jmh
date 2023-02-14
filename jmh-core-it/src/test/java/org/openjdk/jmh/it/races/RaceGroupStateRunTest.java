
package org.openjdk.jmh.it.races;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Baseline test:
 * Checks if assertions are propagated back to integration tests.
 */
public class RaceGroupStateRunTest {

    @State(Scope.Group)
    public static class MyState {
        public int value = 2;

        @Setup(Level.Trial)
        public void setup() {
            Assert.assertEquals("Setup", 2, value);
            value = 1;
        }

        @TearDown(Level.Trial)
        public void tearDown() {
            Assert.assertEquals("TearDown", 1, value);
            value = 2;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 0)
    @Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Group("T")
    @GroupThreads(4)
    public void test(MyState state) {
        Assert.assertEquals("Run", 1, state.value);
        Fixtures.work();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .shouldFailOnError(true)
                    .forks(0)
                    .build();
            new Runner(opt).run();
        }
    }

}
