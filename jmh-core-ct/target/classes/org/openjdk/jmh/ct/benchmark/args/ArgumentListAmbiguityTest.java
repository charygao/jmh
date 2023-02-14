
package org.openjdk.jmh.ct.benchmark.args;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.Blackhole;

public class ArgumentListAmbiguityTest {

    @Benchmark
    public void test() {

    }

    @Benchmark
    public void test(Blackhole bh) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "uniquely named method");
    }

}
