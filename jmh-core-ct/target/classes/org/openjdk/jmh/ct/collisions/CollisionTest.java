
package org.openjdk.jmh.ct.collisions;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.ct.CompileTest;

@BenchmarkMode(Mode.All)
public class CollisionTest {

    @Benchmark
    @Group("same")
    @Warmup(iterations = 1)
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
