
package org.openjdk.jmh.it.threads;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
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
@State(Scope.Benchmark)
public class TwoThreadCountTest {

    private final Set<Thread> threads = Collections.synchronizedSet(new HashSet<>());

    @TearDown(Level.Iteration)
    public void tearDown() {
        Assert.assertEquals("amount of threads should be 2", threads.size(), 2);
    }

    @Benchmark
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 0)
    @Fork(1)
    @Threads(2)
    public void test1() {
        threads.add(Thread.currentThread());
        Fixtures.work();
    }

    @Benchmark
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 0)
    @Fork(1)
    public void test2() {
        threads.add(Thread.currentThread());
        Fixtures.work();
    }

    @Test
    public void invokeAPI_1() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()) + ".*test1")
                    .shouldFailOnError(true)
                    .build();
            new Runner(opt).run();
        }
    }

    @Test
    public void invokeAPI_2() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()) + ".*test2")
                    .shouldFailOnError(true)
                    .threads(2)
                    .build();
            new Runner(opt).run();
        }
    }

}
