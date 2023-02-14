
package org.openjdk.jmh.results;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestSingleShotResult {

    private static final double ASSERT_ACCURACY = 0.0000001;

    @Test
    public void testIterationAggregator1() {
        SingleShotResult r1 = new SingleShotResult(ResultRole.PRIMARY, "Test1", 1000L, 1, TimeUnit.MICROSECONDS);
        SingleShotResult r2 = new SingleShotResult(ResultRole.PRIMARY, "Test1", 2000L, 1, TimeUnit.MICROSECONDS);
        Result result = r1.getIterationAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(1.5, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }

    @Test
    public void testThreadAggregator1() {
        SingleShotResult r1 = new SingleShotResult(ResultRole.PRIMARY, "Test1", 1000L, 1, TimeUnit.MICROSECONDS);
        SingleShotResult r2 = new SingleShotResult(ResultRole.PRIMARY, "Test1", 2000L, 1, TimeUnit.MICROSECONDS);
        Result result = r1.getThreadAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(1.5, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }

    @Test
    public void testMultiops() {
        SingleShotResult r1 = new SingleShotResult(ResultRole.PRIMARY, "Test1", 1000L, 1, TimeUnit.MICROSECONDS);
        SingleShotResult r2 = new SingleShotResult(ResultRole.PRIMARY, "Test1", 1000L, 2, TimeUnit.MICROSECONDS);

        assertEquals(1, r1.getScore(), ASSERT_ACCURACY);
        assertEquals(0.5, r2.getScore(), ASSERT_ACCURACY);
    }

}
