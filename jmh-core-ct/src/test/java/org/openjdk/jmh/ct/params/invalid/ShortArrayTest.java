
package org.openjdk.jmh.ct.params.invalid;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
public class ShortArrayTest {

    @Param("0")
    public short[] param;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "annotation-compatible types");
    }

}
