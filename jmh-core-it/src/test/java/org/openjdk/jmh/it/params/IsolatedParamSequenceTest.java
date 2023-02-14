
package org.openjdk.jmh.it.params;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MICROSECONDS)
@Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MICROSECONDS)
@Fork(1)
public class IsolatedParamSequenceTest {

    @State(Scope.Benchmark)
    public static class MyState {
        @Param({"1", "2", "3"})
        public int x;
    }

    @Benchmark
    public void test1(MyState s) {
        Fixtures.work();
    }

    @Benchmark
    public void test2() {
        Fixtures.work();
    }

    @Test
    public void onlyTest1() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()) + ".*test1")
                .shouldFailOnError(true)
                .build();

        Collection<RunResult> run = new Runner(opts).run();
        Assert.assertEquals(3, run.size());
    }

    @Test
    public void onlyTest2() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()) + ".*test2")
                .shouldFailOnError(true)
                .build();

        Collection<RunResult> run = new Runner(opts).run();
        Assert.assertEquals(1, run.size());
    }

}
