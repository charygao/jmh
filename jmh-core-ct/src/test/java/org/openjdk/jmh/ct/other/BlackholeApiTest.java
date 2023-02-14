
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Tests basic blackholing API.
 */
@BenchmarkMode(Mode.All)
public class BlackholeApiTest {

    @Benchmark
    public void testNothing() {
    }

    @Benchmark
    public Object testReturnObject() {
        return null;
    }

    @Benchmark
    public int testReturnInt() {
        return 0;
    }

    @Benchmark
    public void testBH(Blackhole bh) {
    }

    @Benchmark
    public Object testBH_ReturnObject(Blackhole bh) {
        return null;
    }

    @Benchmark
    public int testBH_ReturnInt(Blackhole bh) {
        return 0;
    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
