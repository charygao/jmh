
package org.openjdk.jmh.ct.states.helpers.explicit.iterparams;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.IterationParams;

public class IterationIterParams2Test {

    @State(Scope.Benchmark)
    public static class S {
        @Setup(Level.Iteration)
        public void setup(IterationParams bh) {}
    }

    @Benchmark
    public void test(S s, IterationParams bh) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
