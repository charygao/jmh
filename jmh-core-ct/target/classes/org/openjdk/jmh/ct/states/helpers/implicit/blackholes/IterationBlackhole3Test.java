
package org.openjdk.jmh.ct.states.helpers.implicit.blackholes;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class IterationBlackhole3Test {

    @Setup(Level.Iteration)
    public void setup(Blackhole bh) {}

    @TearDown(Level.Iteration)
    public void tearDown(Blackhole bh) {}

    @Benchmark
    public void test(Blackhole bh) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
