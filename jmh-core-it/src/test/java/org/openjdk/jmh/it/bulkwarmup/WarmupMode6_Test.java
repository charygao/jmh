
package org.openjdk.jmh.it.bulkwarmup;


import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.WarmupMode;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertEquals;

/**
 * Tests if harness honors warmup command line settings like:
 * -wmb
 * -wm
 * -frw
 * ....
 */
@State(Scope.Thread)
public class WarmupMode6_Test {

    private static final Queue<String> testSequence = new ConcurrentLinkedQueue<>();

    boolean recorded;

    @Setup(Level.Iteration)
    public void oneShot() {
        recorded = false;
    }

    @Benchmark
    public void testBig(IterationParams params) {
        if (!recorded) {
            recorded = true;
            switch (params.getType()) {
                case WARMUP:
                    testSequence.add("W");
                    break;
                case MEASUREMENT:
                    testSequence.add("I");
                    break;
                default:
                    throw new IllegalStateException(params.getType().toString());
            }
        }
        Fixtures.work();
    }

    @Benchmark
    public void testSmall(IterationParams params) {
        if (!recorded) {
            recorded = true;
            switch (params.getType()) {
                case WARMUP:
                    testSequence.add("w");
                    break;
                case MEASUREMENT:
                    testSequence.add("i");
                    break;
                default:
                    throw new IllegalStateException(params.getType().toString());
            }
        }
        Fixtures.work();
    }

    private static String getSequence() {
        StringBuilder sb = new StringBuilder();
        for (String s : testSequence) {
            sb.append(s);
        }
        return sb.toString();
    }

    @Test
    public void invokeAPI() throws RunnerException {
        testSequence.clear();

        Options opt = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .warmupIterations(2)
                .warmupTime(TimeValue.milliseconds(100))
                .measurementIterations(1)
                .measurementTime(TimeValue.milliseconds(200))
                .threads(1)
                .forks(0)
                .syncIterations(false)
                .warmupMode(WarmupMode.BULK)
                .build();
        new Runner(opt).run();

        assertEquals("WWwwIi", getSequence());
    }

}
