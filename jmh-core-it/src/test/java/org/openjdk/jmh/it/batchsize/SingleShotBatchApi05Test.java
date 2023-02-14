
package org.openjdk.jmh.it.batchsize;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tests if harness honors batch size annotation settings.
 */
@State(Scope.Thread)
public class SingleShotBatchApi05Test {

    private static final int WARMUP_ITERATIONS = 2;
    private static final int MEASUREMENT_ITERATIONS = 1;
    private static final int WARMUP_BATCH = 1;
    private static final int MEASUREMENT_BATCH = 5;

    private final AtomicInteger iterationCount = new AtomicInteger();
    private final AtomicInteger batchCount = new AtomicInteger();

    @Setup(Level.Iteration)
    public void setup() {
        iterationCount.incrementAndGet();
        batchCount.set(0);
    }

    private boolean isWarmup() {
        return iterationCount.get() <= WARMUP_ITERATIONS;
    }

    @TearDown(Level.Iteration)
    public void tearDownIter() {
        if(isWarmup()) {
            Assert.assertEquals(WARMUP_BATCH + " batch size expected", WARMUP_BATCH, batchCount.get());
        } else {
            Assert.assertEquals(MEASUREMENT_BATCH + " batch size expected", MEASUREMENT_BATCH, batchCount.get());
        }
    }

    @TearDown
    public void tearDownTrial() {
        Assert.assertEquals((MEASUREMENT_ITERATIONS+WARMUP_ITERATIONS)+" iterations expected", (MEASUREMENT_ITERATIONS+WARMUP_ITERATIONS), iterationCount.get());
    }

    @Benchmark
    @Fork(1)
    public void test() {
        Fixtures.work();
        batchCount.incrementAndGet();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .warmupIterations(WARMUP_ITERATIONS)
                .warmupBatchSize(WARMUP_BATCH)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .measurementBatchSize(MEASUREMENT_BATCH)
                .mode(Mode.SingleShotTime)
                .build();
        new Runner(opt).run();
    }

}
