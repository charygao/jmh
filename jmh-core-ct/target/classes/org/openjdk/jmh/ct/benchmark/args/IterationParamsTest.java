
package org.openjdk.jmh.ct.benchmark.args;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.IterationParams;

public class IterationParamsTest {

    @Benchmark
    public void test(IterationParams params) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
