
package org.openjdk.jmh.ct.benchmark.args.prims;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;

public class PrimBooleanArrayTest {

    @Benchmark
    public void test(boolean[] v) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "Method parameters should be");
    }

}
