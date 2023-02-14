
package org.openjdk.jmh.it.footprint;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class ForkedFootprintTest {

    @Benchmark
    public void bench() {
        // deliberately left empty: harness should work a lot
    }

    @Test
    public void testBenchmark() throws RunnerException {
        for (Mode mode : Mode.values()) {
            if (mode == Mode.All) continue;

            Options opts = new OptionsBuilder()
                    .include(Fixtures.getTestMask(this.getClass()))
                    .mode(mode)
                    .shouldFailOnError(true)
                    .measurementIterations(mode == Mode.SingleShotTime ? 500_000 : 1_000)
                    .measurementTime(TimeValue.milliseconds(5))
                    .warmupIterations(0)
                    .forks(1)
                    .jvmArgs("-Xmx16m", "-Xms16m")
                    .build();

            new Runner(opts).runSingle();
        }
    }

}
