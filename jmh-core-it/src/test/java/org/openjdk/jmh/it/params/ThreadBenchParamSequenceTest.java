
package org.openjdk.jmh.it.params;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Thread)
public class ThreadBenchParamSequenceTest {

    @Param({"1", "2", "3"})
    public int x;

    @Param({"a", "b", "c"})
    public String y;

    @Benchmark
    public void test() {
        Fixtures.work();
    }

    @Test
    public void full() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .build();

        Shared.compare(new Runner(opts).run(), new int[] {1, 2, 3}, new String[] { "a", "b", "c"});
    }

    @Test
    public void constrainedX() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .param("x", "2", "3")
                .build();

        Shared.compare(new Runner(opts).run(), new int[] {2, 3}, new String[] { "a", "b", "c"});
    }

    @Test
    public void constrainedY() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .param("y", "b", "c")
                .build();

        Shared.compare(new Runner(opts).run(), new int[] {1, 2, 3}, new String[] { "b", "c"});
    }

}
