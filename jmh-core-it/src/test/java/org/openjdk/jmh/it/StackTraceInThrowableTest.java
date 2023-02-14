
package org.openjdk.jmh.it;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
public class StackTraceInThrowableTest {

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    public void test() {
        Fixtures.work();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .jvmArgs("-XX:-StackTraceInThrowable")
                .build();
        new Runner(opts).run();
    }

}
