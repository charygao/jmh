
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.ct.CompileTest;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
public class MeasureTest {

    @Benchmark
    @Measurement(iterations = 2)
    public void test1() {

    }

    @Benchmark
    @Measurement(time = 3)
    public void test2() {

    }

    @Benchmark
    @Measurement(timeUnit = TimeUnit.MICROSECONDS)
    public void test3() {

    }

    @Benchmark
    @Measurement(batchSize = 7)
    public void test4() {

    }

    @Benchmark
    @Measurement(iterations = 2, time = 3)
    public void test5() {

    }

    @Benchmark
    @Measurement(iterations = 2, time = 3, timeUnit = TimeUnit.MICROSECONDS)
    public void test6() {

    }

    @Benchmark
    @Measurement(iterations = 2, batchSize = 7)
    public void test7() {

    }

    @Benchmark
    @Measurement(iterations = 2, time = 3, timeUnit = TimeUnit.MICROSECONDS, batchSize = 7)
    public void test8() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
