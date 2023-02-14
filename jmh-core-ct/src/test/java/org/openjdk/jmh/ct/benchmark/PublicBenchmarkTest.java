
package org.openjdk.jmh.ct.benchmark;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;

public class PublicBenchmarkTest {

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
