
package org.openjdk.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class LevelInvocationBench {

    @State(Scope.Benchmark)
    public static class BenchmarkSetup {
        @Setup(Level.Invocation)
        public void setup() {}
    }

    @State(Scope.Benchmark)
    public static class BenchmarkTeardown {
        @TearDown(Level.Invocation)
        public void tearDown() {}
    }

    @State(Scope.Thread)
    public static class ThreadSetup {
        @Setup(Level.Invocation)
        public void setup() {}
    }

    @State(Scope.Thread)
    public static class ThreadTeardown {
        @TearDown(Level.Invocation)
        public void tearDown() {}
    }

    @State(Scope.Group)
    public static class GroupSetup {
        @Setup(Level.Invocation)
        public void setup() {}
    }

    @State(Scope.Group)
    public static class GroupTeardown {
        @TearDown(Level.Invocation)
        public void tearDown() {}
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void raw() {
        // do nothing
    }

    @Benchmark
    public void benchmark_setup(BenchmarkSetup g) {}

    @Benchmark
    public void benchmark_teardown(BenchmarkTeardown g) {}

    @Benchmark
    @Group("group_setup")
    public void group_setup_1(GroupSetup g) {}

    @Benchmark
    @Group("group_setup")
    public void group_setup_2(GroupSetup g) {}

    @Benchmark
    @Group("group_teardown")
    public void group_teardown_1(GroupTeardown g) {}

    @Benchmark
    @Group("group_teardown")
    public void group_teardown_2(GroupTeardown g) {}

    @Benchmark
    public void thread_setup(ThreadSetup g) {}

    @Benchmark
    public void thread_teardown(ThreadTeardown g) {}

}
