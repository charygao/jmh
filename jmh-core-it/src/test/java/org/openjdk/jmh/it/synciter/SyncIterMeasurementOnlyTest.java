
package org.openjdk.jmh.it.synciter;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class SyncIterMeasurementOnlyTest {

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(2)
    public void test() {
        Assert.assertTrue(isInMeasurementLoop());
        Fixtures.work();
    }

    private boolean isInMeasurementLoop() {
        boolean inMeasurementLoop = false;
        for (StackTraceElement element : new Exception().getStackTrace()) {
            inMeasurementLoop |= element.getMethodName().contains("jmhStub");
        }
        return inMeasurementLoop;
    }

    @Test
    public void invokeAPI() throws RunnerException {
        for (int c = 0; c < Fixtures.repetitionCount(); c++) {
            Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .syncIterations(false)
                .build();
            new Runner(opt).run();
        }
    }

}
