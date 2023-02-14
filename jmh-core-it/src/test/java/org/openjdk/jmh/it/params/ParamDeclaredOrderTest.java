
package org.openjdk.jmh.it.params;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(0)
@State(Scope.Thread)
public class ParamDeclaredOrderTest {

    static int prevV;

    @Param({"1", "2", "4", "8", "16", "32", "64"})
    public int v;

    @Setup(Level.Trial)
    public void setup() {
        Assert.assertTrue("Running in different VM", prevV != 0);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    public void test() {
        Fixtures.work();
        Assert.assertTrue(v + " > " + prevV, v > prevV);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        prevV = v;
    }

    @Test
    public void invoke() throws RunnerException {
        prevV = -1;

        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .build();
        new Runner(opt).run();
    }

    @Test
    public void invokeOverride() throws RunnerException {
        prevV = -1;

        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .param("128", "1024", "2048")
                .build();
        new Runner(opt).run();
    }

}
