
package org.openjdk.jmh.it.result;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Tests if harness favors the iteration count annotations.
 */
@State(Scope.Thread)
public class ResultFileNameTest {

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    public void test() {
        Fixtures.work();
    }

    @Test
    public void testLower() throws RunnerException, IOException {
        doWith("jmh" + this.getClass().getSimpleName().toLowerCase() + ".result");
    }

    @Test
    public void testUpper() throws RunnerException, IOException {
        doWith("JMH" + this.getClass().getSimpleName().toUpperCase() + ".RESULT");
    }

    @Test
    public void testMixed() throws RunnerException, IOException {
        doWith("jmh" + this.getClass().getSimpleName() + ".result");
    }

    public void doWith(String name) throws RunnerException, IOException {
        name = System.getProperty("java.io.tmpdir") + File.separator + name;
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .result(name)
                .build();
        new Runner(opts).run();

        File file = new File(name);
        Assert.assertTrue(file.exists());
        file.delete();
    }

}
