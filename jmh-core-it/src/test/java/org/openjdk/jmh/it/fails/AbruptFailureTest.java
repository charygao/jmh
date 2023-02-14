
package org.openjdk.jmh.it.fails;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.ThreadParams;
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
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 60, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class AbruptFailureTest {

    @Benchmark
    @Threads(4)
    public void test(ThreadParams tp) {
        if (tp.getThreadIndex() == 0) {
            throw new IllegalStateException(); // fail one thread
        }
        Fixtures.work();
    }

    @Test
    public void invokeAPI() {
        long time1 = System.nanoTime();
        try {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .shouldFailOnError(true)
                    .build();
            new Runner(opt).run();

            Assert.fail("Should have failed");
        } catch (RunnerException e) {
            // expected
            long time2 = System.nanoTime();
            long delay = TimeUnit.NANOSECONDS.toSeconds(time2 - time1);
            Assert.assertTrue("Delayed for too long: " + delay + "s", delay < 10);
        }
    }

}
