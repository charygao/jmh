
package org.openjdk.jmh.it.ccontrol;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.CompilerControl.Mode;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.RunnerException;

public class CompilerControlNestedTest {

    @CompilerControl(Mode.INLINE)
    public static class SpecimenClass {
        void sampleMethod() {}
    }


    public static class SpecimenMethod {
        @CompilerControl(Mode.INLINE)
        void sampleMethod() {}
    }

    @Benchmark
    public void dummyTest() {
        Fixtures.work();
    }

    @Test
    public void testClass() throws RunnerException {
        String className = this.getClass().getName().replace(".", "/");
        Assert.assertTrue("Has hint on nested class",
                CompilerControlUtils.hasHint(Mode.INLINE.command(), className, "$SpecimenClass.*"));
    }

    @Test
    public void testMethod() throws RunnerException {
        String className = this.getClass().getName().replace(".", "/");
        Assert.assertTrue("Has hint on nested class",
                CompilerControlUtils.hasHint(Mode.INLINE.command(), className, "$SpecimenMethod", "sampleMethod"));
    }

}
