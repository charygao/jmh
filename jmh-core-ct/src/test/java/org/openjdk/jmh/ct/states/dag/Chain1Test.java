
package org.openjdk.jmh.ct.states.dag;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class Chain1Test {

    @State(Scope.Benchmark)
    public static class B1 {

    }

    @Benchmark
    public void test(B1 b) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
