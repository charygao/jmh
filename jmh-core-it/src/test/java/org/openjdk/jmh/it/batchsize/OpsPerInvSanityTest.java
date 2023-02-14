
package org.openjdk.jmh.it.batchsize;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class OpsPerInvSanityTest {

    private static final int SLEEP_TIME_MS = 50;

    @AuxCounters(AuxCounters.Type.EVENTS)
    @State(Scope.Thread)
    public static class TimeCounter {
        public long time;
    }

    @AuxCounters(AuxCounters.Type.EVENTS)
    @State(Scope.Thread)
    public static class OpsCounter {
        public int ops;
    }

    private long startTime;

    @Setup(Level.Iteration)
    public void setup() {
        startTime = System.nanoTime();
    }

    @TearDown(Level.Iteration)
    public void tearDown(TimeCounter cnt) {
        cnt.time = System.nanoTime() - startTime;
    }

    @Benchmark
    public void test(OpsCounter cnt) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(SLEEP_TIME_MS);
        cnt.ops++;
    }

    @Test
    public void invokeAPI() throws RunnerException {
        for (int opsPerInv : new int[] {1, 10, 100}) {
            for (Mode m : Mode.values()) {
                if (m == Mode.All) continue;
                doWith(m, opsPerInv);
            }
        }
    }

    private void doWith(Mode mode, int opsPerInv) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(Fixtures.getTestMask(this.getClass()))
            .shouldFailOnError(true)
            .warmupIterations(0)
            .measurementTime(TimeValue.seconds(1))
            .measurementIterations(5)
            .forks(1)
            .timeUnit(TimeUnit.MILLISECONDS)
            .syncIterations(false)
            .operationsPerInvocation(opsPerInv)
            .mode(mode)
            .build();
        RunResult run = new Runner(opt).runSingle();

        final double TOLERANCE = 0.30;

        Map<String, Result> srs = run.getSecondaryResults();

        Assert.assertNotNull("Ops counter is available for " + mode, srs.get("ops"));
        Assert.assertNotNull("Time counter is available for " + mode, srs.get("time"));

        double realOps = srs.get("ops").getScore();
        double realTime = srs.get("time").getScore() / 1_000_000;

        double actualScore = run.getPrimaryResult().getStatistics().getMean();
        double expectedScore;

        switch (mode) {
            case Throughput:
                expectedScore = 1.0 * (realOps * opsPerInv) / realTime;
                break;
            case AverageTime:
            case SampleTime:
            case SingleShotTime:
                expectedScore = 1.0 * realTime / (realOps * opsPerInv);
                break;
            default:
                expectedScore = Double.NaN;
                actualScore   = Double.NaN;
                Assert.fail("Unhandled mode: " + mode);
        }

        Assert.assertTrue(
                String.format("mode = %s, ops per inv = %d, expected score = %e, actual score = %e; real time = %.5f, real ops = %.5f",
                        mode, opsPerInv, expectedScore, actualScore,
                        realTime, realOps),
                Math.abs(1 - actualScore / expectedScore) < TOLERANCE);
    }

}
