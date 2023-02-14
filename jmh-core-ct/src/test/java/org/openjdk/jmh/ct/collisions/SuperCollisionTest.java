
package org.openjdk.jmh.ct.collisions;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.ct.CompileTest;

@Warmup(iterations = 1)
@BenchmarkMode(Mode.All)
public class SuperCollisionTest {

    @Benchmark
    @Group("same")
    // inherited @Warmup(iterations = 1)
    public void g1() {

    }

    @Benchmark
    @Group("same")
    @Warmup(iterations = 5)
    public void g2() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "Colliding annotations");
    }

}
