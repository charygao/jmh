
package org.openjdk.jmh.it.parameters.threads;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.it.parameters.Parameters;

@Threads(10)
public class BenchmarkGroupThreadsTest {

    @Group("a")
    @Benchmark
    public void bench1() {
        // do nothing
    }

    @Group("a")
    @Benchmark
    public void bench2() {
        // do nothing
    }

    @Test
    public void test() {
        Assert.assertEquals(Integer.valueOf(10), Parameters.get(this.getClass()).getThreads().get());
    }

}
