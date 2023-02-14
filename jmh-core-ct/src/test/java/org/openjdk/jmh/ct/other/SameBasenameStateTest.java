
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

public class SameBasenameStateTest {

    public static class Enclosure1 {
        @State(Scope.Thread)
        public static class S {

        }
    }

    public static class Enclosure2 {
        @State(Scope.Thread)
        public static class S {

        }
    }

    @Benchmark
    public void work(Enclosure1.S s1, Enclosure2.S s2) {
        // this method was intentionally left blank
    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
