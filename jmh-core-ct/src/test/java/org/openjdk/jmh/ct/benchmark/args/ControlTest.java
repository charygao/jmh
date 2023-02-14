
package org.openjdk.jmh.ct.benchmark.args;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.Control;

public class ControlTest {

    @Benchmark
    public void test(Control cnt) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
