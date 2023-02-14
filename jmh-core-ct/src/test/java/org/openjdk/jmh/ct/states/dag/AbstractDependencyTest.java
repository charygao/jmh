
package org.openjdk.jmh.ct.states.dag;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.ct.CompileTest;

public class AbstractDependencyTest {

    @State(Scope.Benchmark)
    public abstract static class A {
        @Setup(Level.Trial)
        public void setup() {}

        protected abstract void helper();
    }

    public static class CA extends A {
        @Override
        protected void helper() {
            // do nothing
        }
    }

    @State(Scope.Thread)
    public static class B {
        @Setup(Level.Trial)
        public void setup(A b2) {}

    }

    @Benchmark
    public void test(CA ca, B b) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "abstract");
    }

}
