
package org.openjdk.jmh.ct.states.helpers.implicit;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

import java.util.concurrent.atomic.AtomicInteger;

@State(Scope.Benchmark)
public class PublicArgsTest {

    @Setup(Level.Trial)
    public void setup(AtomicInteger v) {}

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass());
    }

}
