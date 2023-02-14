
package org.openjdk.jmh.it.ccontrol;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Collection;

public class CompilerControlInlineActualTest {

    @CompilerControl(CompilerControl.Mode.INLINE)
    public void strawMethod() {

    }

    public void trampoline() {
        strawMethod();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.INLINE)
    public void compilerControlSpecimen() {
        trampoline();
    }

    @Test
    public void testBenchmark() throws RunnerException {
        for (Mode mode : Mode.values()) {
            if (mode == Mode.All) continue;

            Options opts = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .mode(mode)
                    .shouldFailOnError(true)
                    .addProfiler(LogConsumeProfiler.class)
                    .measurementIterations(mode == Mode.SingleShotTime ? 200_000 : 1)
                    .measurementTime(TimeValue.seconds(5))
                    .warmupIterations(0)
                    .forks(1)
                    .jvmArgsPrepend("-XX:CICompilerCount=2") // need to serialize the output properly
                    .build();
            RunResult runResult = new Runner(opts).runSingle();

            Collection<String> log = CompilerControlUtils.getLog(runResult);

            if (CompilerControlUtils.check(log, "@", "callee")) { // Poor man's check -XX:+PrintInlining works
                Assert.assertTrue("Failed with " + mode,
                        CompilerControlUtils.check(log, this.getClass().getName() + "::compilerControlSpecimen", "force inline by"));
                Assert.assertTrue("Failed with " + mode,
                        CompilerControlUtils.check(log, this.getClass().getName() + "::strawMethod", "force inline by"));
            }
        }
    }



}
