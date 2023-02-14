
package org.openjdk.jmh.it.fork;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
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
 * tests multiply spaces in jvmArgs annotations.
 */
@BenchmarkMode(Mode.All)
public class ForkedJvmArgs3_Test {

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(jvmArgs = {"-Dtest1", "-Dtest3"})
    public void test1() {
        Fixtures.work();
        Assert.assertNotNull(System.getProperty("test1"));
        Assert.assertNull(System.getProperty("test2"));
        Assert.assertNotNull(System.getProperty("test3"));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(jvmArgs = {"-Dtest2", "-Dtest3"})
    public void test2() {
        Fixtures.work();
        Assert.assertNull(System.getProperty("test1"));
        Assert.assertNotNull(System.getProperty("test2"));
        Assert.assertNotNull(System.getProperty("test3"));
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

}
