
package org.openjdk.jmh.ct.states.helpers.explicit.iterparams;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.IterationParams;

public class TrialIterParams3Test {

    @State(Scope.Benchmark)
    public static class S {
        @Setup(Level.Trial)
        public void setup(IterationParams bh) {}

        @TearDown(Level.Trial)
        public void trial(IterationParams bh) {}
    }

    @Benchmark
    public void test(S s, IterationParams bh) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
