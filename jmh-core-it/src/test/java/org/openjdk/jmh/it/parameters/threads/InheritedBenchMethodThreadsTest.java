
package org.openjdk.jmh.it.parameters.threads;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.it.parameters.Parameters;

public class InheritedBenchMethodThreadsTest {

    @Threads(20)
    public static abstract class AbstractBenchmark {
        @Threads(10)
        @Benchmark
        public void bench() {
            // do nothing
        }
    }

    public static class ConcreteBenchmark extends AbstractBenchmark {}

    @Test
    public void test() {
        Assert.assertEquals(Integer.valueOf(10), Parameters.get(ConcreteBenchmark.class).getThreads().get());
    }

}
