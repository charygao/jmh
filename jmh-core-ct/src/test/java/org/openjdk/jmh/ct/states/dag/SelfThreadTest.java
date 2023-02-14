
package org.openjdk.jmh.ct.states.dag;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class SelfThreadTest {

    @State(Scope.Thread)
    public static class B1 {
        @Param
        private int x;

        @Setup
        public void setup(B1 b1) {

        }
    }

    @Benchmark
    public void test(B1 b1) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass());
    }

}
