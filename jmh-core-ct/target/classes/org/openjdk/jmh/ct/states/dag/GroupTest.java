
package org.openjdk.jmh.ct.states.dag;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class GroupTest {

    @State(Scope.Benchmark)
    public static class B1 {

    }

    @State(Scope.Benchmark)
    public static class B2 {

    }

    @State(Scope.Group)
    public static class L {
        @Setup
        public void setup(B1 b1, B2 b2) {

        }
    }

    @Benchmark
    @Group("test")
    public void test1(L l) {

    }

    @Benchmark
    @Group("test")
    public void test2(L l) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
