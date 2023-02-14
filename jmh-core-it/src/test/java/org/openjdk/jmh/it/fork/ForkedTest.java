
package org.openjdk.jmh.it.fork;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Tests if harness had indeed executed different tests in different JVMs.
 */
@BenchmarkMode(Mode.All)
public class ForkedTest {

    private static volatile boolean test1executed;
    private static volatile boolean test2executed;

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    public void test1() {
        Fixtures.work();
        test1executed = true;
        Assert.assertFalse(test2executed);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    public void test2() {
        Fixtures.work();
        test2executed = true;
        Assert.assertFalse(test1executed);
    }

    @Test
    public void invokeAPI() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Test
    public void invokeAPI_1() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Test
    public void invokeAPI_WF() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .forks(1)
                .warmupForks(2)
                .build();
        new Runner(opt).run();
    }

}
