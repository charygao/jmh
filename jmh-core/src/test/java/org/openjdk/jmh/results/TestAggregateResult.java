
package org.openjdk.jmh.results;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.runner.IterationType;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.util.Utils;
import org.openjdk.jmh.util.Version;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Tests for AggregateResult
 */
public class TestAggregateResult {

    private static IterationResult result;
    private static final double[] values = {10.0, 20.0, 30.0, 40.0, 50.0};

    @BeforeClass
    public static void setupClass() {
        result = new IterationResult(
                new BenchmarkParams("blah", "blah", false,
                        1, new int[]{1}, Collections.<String>emptyList(),
                        1, 1,
                        new IterationParams(IterationType.WARMUP, 1, TimeValue.seconds(1), 1),
                        new IterationParams(IterationType.MEASUREMENT, 1, TimeValue.seconds(1), 1),
                        Mode.Throughput, null, TimeUnit.SECONDS, 1,
                        Utils.getCurrentJvm(), Collections.<String>emptyList(),
                        System.getProperty("java.version"), System.getProperty("java.vm.name"), System.getProperty("java.vm.version"), Version.getPlainVersion(),
                        TimeValue.days(1)),
                new IterationParams(IterationType.MEASUREMENT, 1, TimeValue.days(1), 1),
                null
        );
        for (double d : values) {
            result.addResult(new ThroughputResult(ResultRole.PRIMARY, "test1", (long) d, 10 * 1000 * 1000, TimeUnit.MILLISECONDS));
        }
    }

    @Test
    public void testScore() {
        assertEquals(15.0, result.getPrimaryResult().getScore(), 0.00001);
    }

    @Test
    public void testScoreUnit() {
        assertEquals((new ThroughputResult(ResultRole.PRIMARY, "test1", 1, 1, TimeUnit.MILLISECONDS)).getScoreUnit(), result.getScoreUnit());
    }

}
