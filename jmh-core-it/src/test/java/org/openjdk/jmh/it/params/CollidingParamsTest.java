
package org.openjdk.jmh.it.params;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class CollidingParamsTest {

    @State(Scope.Benchmark)
    public static class Benchmark1 {
        @Param("1")
        private int x;

        @Benchmark
        public void test() {

        }
    }

    @State(Scope.Benchmark)
    public static class Benchmark2 {
        @Param("2")
        private int x;

        @Benchmark
        public void test() {

        }
    }

    @Test
    public void constrainedX() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .warmupIterations(1)
                .warmupTime(TimeValue.milliseconds(100))
                .measurementIterations(1)
                .measurementTime(TimeValue.milliseconds(100))
                .forks(1)
                .shouldFailOnError(true)
                .param("x", "2", "3")
                .build();

        Assert.assertEquals(4, new Runner(opts).run().size());
    }

}
