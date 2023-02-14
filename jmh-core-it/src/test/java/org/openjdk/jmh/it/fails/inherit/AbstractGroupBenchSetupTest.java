
package org.openjdk.jmh.it.fails.inherit;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Baseline test:
 * Checks if assertions are propagated back to integration tests.
 */
@BenchmarkMode(Mode.All)
@State(Scope.Group)
public class AbstractGroupBenchSetupTest extends AbstractSetupBase {

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    @Group("T")
    @GroupThreads(4)
    public void test() {
        Fixtures.work();
    }

    @Test
    public void invokeAPI() {
        try {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .shouldFailOnError(true)
                    .build();
            new Runner(opt).run();

            Assert.fail("Should have failed");
        } catch (RunnerException e) {
            // expected
        }
    }

}
