
package org.openjdk.jmh.it.parameters.fork;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.it.parameters.Parameters;

@Fork(20)
public class BenchMethodForkTest {

    @Fork(10)
    @Benchmark
    public void bench() {
        // do nothing
    }

    @Test
    public void test() {
        Assert.assertEquals(Integer.valueOf(10), Parameters.get(this.getClass()).getForks().get());
    }

}
