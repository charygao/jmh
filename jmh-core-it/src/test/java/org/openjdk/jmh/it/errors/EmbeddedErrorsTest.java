
package org.openjdk.jmh.it.errors;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
public class EmbeddedErrorsTest {

    @Benchmark
    public void test00_normal() throws InterruptedException {
        Thread.sleep(1);
    }

    @Benchmark
    public void test01_exceptional() {
        throw new IllegalStateException();
    }

    @Benchmark
    public void test02_normal() throws InterruptedException {
        Thread.sleep(1);
    }

    // Embedded runs can not possibly survive either System.exit, or VM crash

    @Test
    public void test_FOE_false() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .forks(0)
                .shouldFailOnError(false)
                .build();
        Collection<RunResult> results = new Runner(opt).run();

        Assert.assertEquals(2, results.size());
    }

    @Test
    public void test_FOE_true() throws RunnerException {
        try {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .forks(0)
                    .shouldFailOnError(true)
                    .build();

            new Runner(opt).run();

            Assert.fail("Should have thrown the exception");
        } catch (RunnerException e) {
            // expected
        }
    }

}
