
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
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
public class ForkedErrorsTest {

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

    @Benchmark
    public void test03_exit() throws InterruptedException {
        System.exit(1);
    }

    @Benchmark
    public void test04_normal() throws InterruptedException {
        Thread.sleep(1);
    }

    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    @Benchmark
    public void test05_crash() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        // SIGSEGV in JVM
        getUnsafe().getInt(0);
    }

    @Benchmark
    public void test06_normal() throws InterruptedException {
        Thread.sleep(1);
    }

    @Benchmark
    public void test07_runtimeExit() throws InterruptedException {
        Runtime.getRuntime().exit(1);
    }

    @Benchmark
    public void test08_normal() throws InterruptedException {
        Thread.sleep(1);
    }

    @Benchmark
    public void test09_runtimeHalt() throws InterruptedException {
        Runtime.getRuntime().halt(1);
    }

    @Benchmark
    public void test10_normal() throws InterruptedException {
        Thread.sleep(1);
    }

    @Test
    public void test_FOE_false() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .forks(1)
                .shouldFailOnError(false)
                .build();
        Collection<RunResult> results = new Runner(opt).run();

        Assert.assertEquals(6, results.size());
    }

    @Test
    public void test_FOE_true() throws RunnerException {
        try {
            Options opt = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .forks(1)
                    .shouldFailOnError(true)
                    .build();
            new Runner(opt).run();

            Assert.fail("Should have thrown the exception");
        } catch (RunnerException e) {
            // expected
        }
    }

}
