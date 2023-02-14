
package org.openjdk.jmh.ct.benchmark;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Thread)
public class PublicSynchronizedStaticThreadStateBenchmarkTest {

    @Benchmark
    public static synchronized void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "@State(Scope.Benchmark)");
    }

}
