
package org.openjdk.jmh.results;

import org.junit.Test;
import org.openjdk.jmh.util.SampleBuffer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestSampleTimeResult {

    private static final double ASSERT_ACCURACY = 0.0000001;

    @Test
    public void testIterationAggregator1() {
        SampleBuffer b1 = new SampleBuffer();
        b1.add(1000);
        b1.add(2000);

        SampleBuffer b2 = new SampleBuffer();
        b2.add(3000);
        b2.add(4000);

        SampleTimeResult r1 = new SampleTimeResult(ResultRole.PRIMARY, "Test1", b1, TimeUnit.MICROSECONDS);
        SampleTimeResult r2 = new SampleTimeResult(ResultRole.PRIMARY, "Test1", b2, TimeUnit.MICROSECONDS);
        Result result = r1.getIterationAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(2.5, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }

    @Test
    public void testThreadAggregator1() {
        SampleBuffer b1 = new SampleBuffer();
        b1.add(1000);
        b1.add(2000);

        SampleBuffer b2 = new SampleBuffer();
        b2.add(3000);
        b2.add(4000);

        SampleTimeResult r1 = new SampleTimeResult(ResultRole.PRIMARY, "Test1", b1, TimeUnit.MICROSECONDS);
        SampleTimeResult r2 = new SampleTimeResult(ResultRole.PRIMARY, "Test1", b2, TimeUnit.MICROSECONDS);
        Result result = r1.getThreadAggregator().aggregate(Arrays.asList(r1, r2));

        assertEquals(2.5, result.getScore(), ASSERT_ACCURACY);
        assertEquals("us/op", result.getScoreUnit());
    }

}
