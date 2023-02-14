
package org.openjdk.jmh.ct.states.helpers.explicit.benchparams;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.BenchmarkParams;

public class IterationBenchParams3Test {

    @State(Scope.Benchmark)
    public static class S {
        @Setup(Level.Iteration)
        public void setup(BenchmarkParams bh) {}

        @TearDown(Level.Iteration)
        public void trial(BenchmarkParams bh) {}
    }

    @Benchmark
    public void test(S s, BenchmarkParams bh) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
