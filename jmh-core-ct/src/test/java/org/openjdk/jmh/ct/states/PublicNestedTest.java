
package org.openjdk.jmh.ct.states;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class PublicNestedTest {

    @State(Scope.Benchmark)
    public static class S {

    }

    @Benchmark
    public void test(S s) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
