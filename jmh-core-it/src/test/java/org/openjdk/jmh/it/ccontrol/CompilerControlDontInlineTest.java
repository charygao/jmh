
package org.openjdk.jmh.it.ccontrol;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.CompilerControl.Mode;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.RunnerException;

public class CompilerControlDontInlineTest {

    @CompilerControl(Mode.DONT_INLINE)
    void sampleMethod() {}

    @Benchmark
    @CompilerControl(Mode.DONT_INLINE)
    public void dummyTest() {
        Fixtures.work();
    }

    @Test
    public void testBenchmark() throws RunnerException {
        String className = this.getClass().getName().replace(".", "/");
        Assert.assertTrue("Has hint on benchmark method",
                CompilerControlUtils.hasHint(Mode.DONT_INLINE.command(), className, "dummyTest"));
    }

    @Test
    public void testStandalone() throws RunnerException {
        String className = this.getClass().getName().replace(".", "/");
        Assert.assertTrue("Has hint on standalone method",
                CompilerControlUtils.hasHint(Mode.DONT_INLINE.command(), className, "sampleMethod"));
    }

}
