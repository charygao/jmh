
package org.openjdk.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class CurrentTimeMillisTimerBench {

    private long last;

    @Benchmark
    public long latency() {
        return System.currentTimeMillis();
    }

    @Benchmark
    public long granularity() {
        long lst = last;
        long cur;
        do {
            cur = System.currentTimeMillis();
        } while (cur == lst);
        last = cur;
        return cur;
    }
}
