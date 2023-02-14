
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.ct.CompileTest;

@BenchmarkMode(Mode.All)
public class StrictFPMethodTest {

    @Benchmark
    public strictfp void test() {
        // intentionally blank
    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
