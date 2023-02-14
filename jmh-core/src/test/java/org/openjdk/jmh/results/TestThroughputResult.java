
package org.openjdk.jmh.results;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestThroughputResult {

    private static final double ASSERT_ACCURACY = 0.0000001;

    /**
     * Test of getScore method, of class ThroughputResult.
     */
    @Test
    public void testGetScore() {
        ThroughputResult instance = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MILLISECONDS);
        assertEquals(1_000, instance.getScore(), 0.0);
        ThroughputResult instance2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.SECONDS);
        assertEquals(1_000_000, instance2.getScore(), 0.0);
        ThroughputResult instance3 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000L, TimeUnit.MILLISECONDS);
        assertEquals(1_000 / (1_000 / (double) 1_000_000), instance3.getScore(), 0.0);
    }

    @Test
    public void testTimeUnits() {
        ThroughputResult instanced = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.DAYS);
        assertEquals(86_400_000_000D, instanced.getScore(), 0.0);
        assertEquals("ops/day", instanced.getScoreUnit());

        ThroughputResult instanceh = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.HOURS);
        assertEquals(3_600_000_000D, instanceh.getScore(), 0.0);
        assertEquals("ops/hr", instanceh.getScoreUnit());

        ThroughputResult instancem = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MINUTES);
        assertEquals(60_000_000, instancem.getScore(), 0.0);
        assertEquals("ops/min", instancem.getScoreUnit());

        ThroughputResult instance = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.SECONDS);
        assertEquals(1_000_000, instance.getScore(), 0.0);
        assertEquals("ops/s", instance.getScoreUnit());

        ThroughputResult instance2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MILLISECONDS);
        assertEquals(1_000, instance2.getScore(), 0.0);
        assertEquals("ops/ms", instance2.getScoreUnit());

        ThroughputResult instance3 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MICROSECONDS);
        assertEquals(1, instance3.getScore(), 0.0);
        assertEquals("ops/us", instance3.getScoreUnit());

        ThroughputResult instance4 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.NANOSECONDS);
        assertEquals(0.001, instance4.getScore(), 0.0);
        assertEquals("ops/ns", instance4.getScoreUnit());
    }

    @Test
    public void testRunAggregator1() {
        ThroughputResult r1 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        ThroughputResult r2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 2_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        Result result = r1.getIterationAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(150.0, result.getScore(), ASSERT_ACCURACY);
        assertEquals("ops/ms", result.getScoreUnit());
    }

    @Test
    public void testRunAggregator2() {
        ThroughputResult r1 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        ThroughputResult r2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 2_000L, 20_000_000L, TimeUnit.MILLISECONDS);
        Result result = r1.getIterationAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(100.0, result.getScore(), ASSERT_ACCURACY);
        assertEquals("ops/ms", result.getScoreUnit());
    }

    @Test // regression test, check for overflow
    public void testIterationAggregator3() {
        ThroughputResult r1 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000_000_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        ThroughputResult r2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 2_000_000_000L, 20_000_000L, TimeUnit.MILLISECONDS);
        Result result = r1.getIterationAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(100_000_000.0, result.getScore(), ASSERT_ACCURACY);
        assertEquals("ops/ms", result.getScoreUnit());
    }

    @Test
    public void testThreadAggregator1() {
        ThroughputResult r1 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        ThroughputResult r2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 2_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        Result result = r1.getThreadAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals("ops/ms", result.getScoreUnit());
        assertEquals(300.0, result.getScore(), ASSERT_ACCURACY);
    }

    @Test
    public void testThreadAggregator2() {
        ThroughputResult r1 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        ThroughputResult r2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 2_000L, 20_000_000L, TimeUnit.MILLISECONDS);
        Result result = r1.getThreadAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals("ops/ms", result.getScoreUnit());
        assertEquals(200.0, result.getScore(), ASSERT_ACCURACY);
    }

    @Test  // regression test, check for overflow
    public void testThreadAggregator3() {
        ThroughputResult r1 = new ThroughputResult(ResultRole.PRIMARY, "test1", 1_000_000_000L, 10_000_000L, TimeUnit.MILLISECONDS);
        ThroughputResult r2 = new ThroughputResult(ResultRole.PRIMARY, "test1", 2_000_000_000L, 20_000_000L, TimeUnit.MILLISECONDS);
        Result result = r1.getThreadAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals("ops/ms", result.getScoreUnit());
        assertEquals(200_000_000.0, result.getScore(), ASSERT_ACCURACY);
    }
}
