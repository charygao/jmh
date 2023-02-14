
package org.openjdk.jmh.ct.params.valid.statics;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
public class IntTest {

    @Param("0")
    public static int param;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
