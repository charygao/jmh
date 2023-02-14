
package org.openjdk.jmh.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class NanoTimerBench {

    private long last;

    @Benchmark
    public long latency() {
        return System.nanoTime();
    }

    @Benchmark
    public long granularity() {
        long lst = last;
        long cur;
        do {
            cur = System.nanoTime();
        } while (cur == lst);
        last = cur;
        return cur;
    }
}
