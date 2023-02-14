
package org.openjdk.jmh.ct.other.auxcounters.counterTypes.publicMethods;

import org.junit.Test;
import org.openjdk.jmh.annotations.AuxCounters;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class VoidTest {

    @AuxCounters
    @State(Scope.Thread)
    public static class S {
        public void cnt() { }
    }

    @Benchmark
    public void benchmark(S s) {
        // intentionally left blank
    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
