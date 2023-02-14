
package org.openjdk.jmh.ct.exceptions;

import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
public class BenchmarkBenchExceptionSignaturesTest {

    @Setup(Level.Trial)
    public void setup1() throws Exception {}

    @Setup(Level.Iteration)
    public void setup2() throws Exception {}

    @Setup(Level.Invocation)
    public void setup3() throws Exception {}

    @TearDown(Level.Trial)
    public void tearDown1() throws Exception {}

    @TearDown(Level.Iteration)
    public void tearDown2() throws Exception {}

    @TearDown(Level.Invocation)
    public void tearDown3() throws Exception {}

    @Benchmark
    public void test() throws Exception {}

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
