
package org.openjdk.jmh.ct.states;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class PublicNestedNoConstrTest {

    @State(Scope.Benchmark)
    public static class S {
        public S(int value) {
            // d'uh
        }
    }

    @Benchmark
    public void test(S s) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass());
    }

}
