
package org.openjdk.jmh.ct.benchmark.args;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.Blackhole;

public class BlackholeTest {

    @Benchmark
    public void test(Blackhole bh) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
