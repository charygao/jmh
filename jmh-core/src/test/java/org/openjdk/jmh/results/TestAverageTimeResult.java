
package org.openjdk.jmh.results;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestAverageTimeResult {

    private static final double ASSERT_ACCURACY = 0.0000001;

    @Test
    public void testIterationAggregator1() {
        AverageTimeResult r1 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MICROSECONDS);
        AverageTimeResult r2 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 2_000_000L, TimeUnit.MICROSECONDS);
        Result result = r1.getIterationAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(1.5, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }

    @Test
    public void testIterationAggregator2() {
        AverageTimeResult r1 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MICROSECONDS);
        AverageTimeResult r2 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MICROSECONDS);
        Result result = r1.getIterationAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(1.0, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }

    @Test
    public void testThreadAggregator1() {
        AverageTimeResult r1 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MICROSECONDS);
        AverageTimeResult r2 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MICROSECONDS);
        Result result = r1.getThreadAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(1.0, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }

    @Test
    public void testThreadAggregator2() {
        AverageTimeResult r1 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 1_000_000L, TimeUnit.MICROSECONDS);
        AverageTimeResult r2 = new AverageTimeResult(ResultRole.PRIMARY, "test1", 1_000L, 2_000_000L, TimeUnit.MICROSECONDS);
        Result result = r1.getThreadAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(1.5, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }
}
