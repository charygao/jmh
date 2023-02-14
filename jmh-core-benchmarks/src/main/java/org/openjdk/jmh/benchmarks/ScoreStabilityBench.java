
package org.openjdk.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50)
@Measurement(iterations = 50)
@Threads(1)
@State(Scope.Thread)
public class ScoreStabilityBench {

    @Param("10")
    private int delay;

    @Setup(Level.Iteration)
    public void sleep() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(delay);
    }

    @Benchmark
    public void test() {
        Blackhole.consumeCPU(1_000_000);
    }

}
