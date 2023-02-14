
package org.openjdk.jmh.it.ccontrol;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.CompilerControl.Mode;
import org.openjdk.jmh.runner.RunnerException;

@CompilerControl(Mode.EXCLUDE)
public class CompilerControlExcludeClassTest {

    @Benchmark
    public void compilerControlSpecimen() {}

    @Test
    public void test() throws RunnerException {
        String className = this.getClass().getName().replace(".", "/");

        Assert.assertTrue("Class hint should exist",
                CompilerControlUtils.hasHint(Mode.EXCLUDE.command(), className)
        );

        Assert.assertFalse("Method hint should not exist",
                CompilerControlUtils.hasHint("", className, "compilerControlSpecimen")
        );
    }

}
