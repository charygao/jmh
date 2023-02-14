
package org.openjdk.jmh.ct.states.dag;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class Chain4Test {

    @State(Scope.Benchmark)
    public static class B4 {

    }

    @State(Scope.Benchmark)
    public static class B3 {
        @Setup(Level.Trial)
        public void setup(B4 b2) {}
    }

    @State(Scope.Benchmark)
    public static class B2 {
        @Setup(Level.Trial)
        public void setup(B3 b2) {}
    }

    @State(Scope.Benchmark)
    public static class B1 {
        @Setup(Level.Trial)
        public void setup(B2 b2) {}
    }

    @Benchmark
    public void test(B1 b) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
