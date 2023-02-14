
package org.openjdk.jmh.runner.parameters;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

public class TimeValueTest {

    @Test
    public void testNano() {
        TimeValue v = TimeValue.fromString(TimeValue.nanoseconds(10).toString());
        Assert.assertEquals(TimeUnit.NANOSECONDS, v.getTimeUnit());
        Assert.assertEquals(10, v.getTime());
    }

    @Test
    public void testMicro() {
        TimeValue v = TimeValue.fromString(TimeValue.microseconds(10).toString());
        Assert.assertEquals(TimeUnit.MICROSECONDS, v.getTimeUnit());
        Assert.assertEquals(10, v.getTime());
    }

    @Test
    public void testMilli() {
        TimeValue v = TimeValue.fromString(TimeValue.milliseconds(10).toString());
        Assert.assertEquals(TimeUnit.MILLISECONDS, v.getTimeUnit());
        Assert.assertEquals(10, v.getTime());
    }

    @Test
    public void testSeconds() {
        TimeValue v = TimeValue.fromString(TimeValue.seconds(10).toString());
        Assert.assertEquals(TimeUnit.SECONDS, v.getTimeUnit());
        Assert.assertEquals(10, v.getTime());
    }

    @Test
    public void testMinutes() {
        TimeValue v = TimeValue.fromString(TimeValue.minutes(10).toString());
        Assert.assertEquals(TimeUnit.MINUTES, v.getTimeUnit());
        Assert.assertEquals(10, v.getTime());
    }

    @Test
    public void testHours() {
        TimeValue v = TimeValue.fromString(TimeValue.hours(10).toString());
        Assert.assertEquals(TimeUnit.HOURS, v.getTimeUnit());
        Assert.assertEquals(10, v.getTime());
    }

    @Test
    public void testDays() {
        TimeValue v = TimeValue.fromString(TimeValue.days(10).toString());
        Assert.assertEquals(TimeUnit.DAYS, v.getTimeUnit());
        Assert.assertEquals(10, v.getTime());
    }

}
