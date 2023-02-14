
package org.openjdk.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class ThermalRundownBench {

    @Benchmark
    public void test() {
        Blackhole.consumeCPU(1_000_000);
    }

}
