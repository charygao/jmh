
package org.openjdk.jmh.it.params;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class UTF8ParamsTest {

    @Param("\uff11000")
    String value;

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    public void test() {
        Fixtures.work();
        Assert.assertEquals("\uff11000", value);
    }

    @Test
    public void vals() throws RunnerException {
        Locale prev = Locale.getDefault();
        Locale.setDefault(Locale.ROOT);

        try {
            Options opts = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .shouldFailOnError(true)
                    .build();

            new Runner(opts).run();
        } finally {
            Locale.setDefault(prev);
        }
    }

}
