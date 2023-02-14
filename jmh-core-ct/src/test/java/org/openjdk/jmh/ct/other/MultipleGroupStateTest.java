
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@BenchmarkMode(Mode.All)
public class MultipleGroupStateTest {

    @State(Scope.Group)
    public static class G1 {}

    @State(Scope.Group)
    public static class G2 {}

    @State(Scope.Group)
    public static class G3 {}

    @Benchmark
    @Group("T")
    public void test1(G1 g1) {

    }

    @Benchmark
    @Group("T")
    public void test2(G1 g1, G2 g2) {

    }

    @Benchmark
    @Group("T")
    public void test3(G1 g1, G2 g2, G3 g3) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
