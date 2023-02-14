
package org.openjdk.jmh.ct.benchmark.args.prims;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;

public class PrimFloatTest {

    @Benchmark
    public void test(float v) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "Method parameters should be");
    }

}
