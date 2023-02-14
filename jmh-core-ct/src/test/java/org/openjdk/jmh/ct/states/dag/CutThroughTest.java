
package org.openjdk.jmh.ct.states.dag;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class CutThroughTest {

    @State(Scope.Benchmark)
    public static class B1 {

    }

    @State(Scope.Benchmark)
    public static class B2 {

    }

    @State(Scope.Thread)
    public static class L {
        @Setup
        public void setup(B1 b1, B2 b2) {

        }
    }

    @Benchmark
    public void test(L l, B1 b1) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
