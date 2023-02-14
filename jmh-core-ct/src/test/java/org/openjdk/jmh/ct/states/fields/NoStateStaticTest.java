
package org.openjdk.jmh.ct.states.fields;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;

public class NoStateStaticTest {

    private static int x;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
