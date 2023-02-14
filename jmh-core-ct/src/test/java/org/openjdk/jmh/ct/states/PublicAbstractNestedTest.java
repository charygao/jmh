
package org.openjdk.jmh.ct.states;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class PublicAbstractNestedTest {

    @State(Scope.Benchmark)
    public abstract static class S {

    }

    @Benchmark
    public void test(S s) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass());
    }

}
