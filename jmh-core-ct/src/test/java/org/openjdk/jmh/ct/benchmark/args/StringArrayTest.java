
package org.openjdk.jmh.ct.benchmark.args;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;

public class StringArrayTest {

    @Benchmark
    public void test(String[] args) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass());
    }

}
