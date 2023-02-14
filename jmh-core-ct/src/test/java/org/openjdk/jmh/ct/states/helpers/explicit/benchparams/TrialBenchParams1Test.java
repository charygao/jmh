
package org.openjdk.jmh.ct.states.helpers.explicit.benchparams;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.BenchmarkParams;

public class TrialBenchParams1Test {

    @State(Scope.Benchmark)
    public static class S {
        @Setup(Level.Trial)
        public void setup(BenchmarkParams bh) {}
    }

    @Benchmark
    public void test(S s) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
