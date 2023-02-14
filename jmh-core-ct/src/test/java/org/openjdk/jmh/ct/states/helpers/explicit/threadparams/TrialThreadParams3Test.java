
package org.openjdk.jmh.ct.states.helpers.explicit.threadparams;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.ThreadParams;

public class TrialThreadParams3Test {

    @State(Scope.Benchmark)
    public static class S {
        @Setup(Level.Trial)
        public void setup(ThreadParams bh) {}

        @TearDown(Level.Trial)
        public void trial(ThreadParams bh) {}
    }

    @Benchmark
    public void test(S s, ThreadParams bh) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
