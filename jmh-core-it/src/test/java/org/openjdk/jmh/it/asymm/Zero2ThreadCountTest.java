
package org.openjdk.jmh.it.asymm;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
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

@BenchmarkMode(Mode.All)
@Warmup(iterations = 0)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(1)
public class Zero2ThreadCountTest {

    private final Set<Thread> test1threads = Collections.synchronizedSet(new HashSet<>());
    private final Set<Thread> test2threads = Collections.synchronizedSet(new HashSet<>());

    @TearDown
    public void check() {
        Assert.assertEquals(1, test1threads.size());
        Assert.assertEquals(0, test2threads.size());
    }

    @Benchmark
    @Group("test")
    @GroupThreads(1)
    public void test1() {
        test1threads.add(Thread.currentThread());
        Fixtures.work();
    }

    @Benchmark
    @Group("test")
    @GroupThreads(0)
    public void test2() {
        test2threads.add(Thread.currentThread());
        Fixtures.work();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()) + ".*test")
                    .shouldFailOnError(true)
                    .build();
            new Runner(opt).run();
        }
    }

}
