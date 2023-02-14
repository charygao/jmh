
package org.openjdk.jmh.it.parameters.threads;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.it.parameters.Parameters;

public class MethodThreadsTest {

    @Threads(10)
    @Benchmark
    public void bench() {
        // do nothing
    }

    @Test
    public void test() {
        Assert.assertEquals(Integer.valueOf(10), Parameters.get(this.getClass()).getThreads().get());
    }

}
