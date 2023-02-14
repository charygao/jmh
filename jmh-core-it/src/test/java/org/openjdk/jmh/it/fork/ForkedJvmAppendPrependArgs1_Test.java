
package org.openjdk.jmh.it.fork;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
public class ForkedJvmAppendPrependArgs1_Test {

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(jvmArgs = "-Dmiddle", jvmArgsAppend = "-Dappended", jvmArgsPrepend = "-Dprepended")
    public void test1() {
        Fixtures.work();
        Assert.assertNotNull(System.getProperty("middle"));
        Assert.assertNotNull(System.getProperty("appended"));
        Assert.assertNotNull(System.getProperty("prepended"));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(jvmArgsAppend = "-Dappended", jvmArgsPrepend = "-Dprepended")
    public void test2() {
        Fixtures.work();
        Assert.assertNull(System.getProperty("middle"));
        Assert.assertNotNull(System.getProperty("appended"));
        Assert.assertNotNull(System.getProperty("prepended"));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(jvmArgsPrepend = "-Dprepended")
    public void test3() {
        Fixtures.work();
        Assert.assertNull(System.getProperty("middle"));
        Assert.assertNull(System.getProperty("appended"));
        Assert.assertNotNull(System.getProperty("prepended"));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(jvmArgsAppend = "-Dappended")
    public void test4() {
        Fixtures.work();
        Assert.assertNull(System.getProperty("middle"));
        Assert.assertNotNull(System.getProperty("appended"));
        Assert.assertNull(System.getProperty("prepended"));
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
