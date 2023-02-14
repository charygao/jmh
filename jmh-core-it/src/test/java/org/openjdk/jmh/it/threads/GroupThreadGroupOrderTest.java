
package org.openjdk.jmh.it.threads;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
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
@BenchmarkMode(Mode.All)
@Warmup(iterations = 0)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Group)
public class GroupThreadGroupOrderTest {

    private final Set<Thread> abc = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> def = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> ghi = Collections.synchronizedSet(new HashSet<>());

    @Setup(Level.Iteration)
    public void prepare() {
        abc.clear();
        def.clear();
        ghi.clear();
    }

    @TearDown(Level.Iteration)
    public void verify() {
        Assert.assertEquals("Test abc", 3, abc.size());
        Assert.assertEquals("Test def", 1, def.size());
        Assert.assertEquals("Test ghi", 2, ghi.size());
    }

    @Benchmark
    @Group("T")
    public void abc() {
        abc.add(Thread.currentThread());
        Fixtures.work();
    }

    @Benchmark
    @Group("T")
    public void ghi() {
        ghi.add(Thread.currentThread());
        Fixtures.work();
    }

    @Benchmark
    @Group("T")
    public void def() {
        def.add(Thread.currentThread());
        Fixtures.work();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .threadGroups(3, 1, 2)
                    .shouldFailOnError(true)
                    .build();
            new Runner(opt).run();
        }
    }

}
