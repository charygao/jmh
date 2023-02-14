
package org.openjdk.jmh.ct.benchmark.args;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.BenchmarkParams;

public class BenchmarkParamsTest {

    @Benchmark
    public void test(BenchmarkParams benchmarkParams) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
