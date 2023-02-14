
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.ct.CompileTest;

@BenchmarkMode(Mode.All)
public class OperationsPerInvocationTest {

    @Benchmark
    @OperationsPerInvocation(1)
    public void test1() {

    }

    @Benchmark
    @OperationsPerInvocation(10)
    public void test2() {

    }

    @Benchmark
    @OperationsPerInvocation(Integer.MAX_VALUE)
    public void test3() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
