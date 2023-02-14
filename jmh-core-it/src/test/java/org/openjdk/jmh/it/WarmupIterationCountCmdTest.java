
package org.openjdk.jmh.it;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tests if harness honors warmup command line settings.
 */
@State(Scope.Thread)
public class WarmupIterationCountCmdTest {

    private final AtomicInteger count = new AtomicInteger();

    @Setup(Level.Iteration)
    public void setup() {
        count.incrementAndGet();
    }

    @TearDown
    public void tearDown() {
        Assert.assertEquals("Four iterations expected", 4, count.get());
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Fork(1)
    public void test() {
        Fixtures.work();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .warmupTime(TimeValue.milliseconds(100))
                .measurementTime(TimeValue.milliseconds(100))
                .measurementIterations(1)
                .warmupIterations(3)
                .build();
        new Runner(opt).run();
    }


}
