
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MICROSECONDS)
@Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MICROSECONDS)
@Fork(1)
@State(Scope.Thread)
public class EnumParamToStringOverridingTest {

    @Param({"VALUE_A", "VALUE_B", "VALUE_C"})
    public SampleEnumA a;

    @Benchmark
    public void test() {
        Fixtures.work();
    }

    @Test
    public void normal() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .build();

        Assert.assertEquals(3, new Runner(opts).run().size());
    }

    public enum SampleEnumA {
        VALUE_A, VALUE_B, VALUE_C;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
