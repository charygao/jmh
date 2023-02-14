
package org.openjdk.jmh.it;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tests if harness had indeed executed different tests in different JVMs.
 */
@BenchmarkMode(Mode.SingleShotTime)
public class SingleShotTest {

    private static final AtomicInteger test1executed = new AtomicInteger();
    private static final AtomicInteger test2executed = new AtomicInteger();

    @Benchmark
    @Fork(1)
    public void test1() {
        Fixtures.work();
        Assert.assertEquals(1, test1executed.incrementAndGet());
        Assert.assertEquals(0, test2executed.get());
    }

    @Benchmark
    @Fork(1)
    public void test2() {
        Fixtures.work();
        Assert.assertEquals(1, test2executed.incrementAndGet());
        Assert.assertEquals(0, test1executed.get());
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
                .forks(2)
                .build();
        new Runner(opt).run();
    }

}
