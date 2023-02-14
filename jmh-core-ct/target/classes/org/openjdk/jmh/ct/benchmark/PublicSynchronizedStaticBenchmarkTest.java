
package org.openjdk.jmh.ct.benchmark;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;

public class PublicSynchronizedStaticBenchmarkTest {

    @Benchmark
    public static synchronized void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "enclosing class is annotated with @State");
    }

}
