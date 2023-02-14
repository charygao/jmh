
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.Control;

public class ControlTest {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

    }

    @State(Scope.Thread)
    public static class ThreadState {

    }

    @State(Scope.Group)
    public static class GroupState {

    }

    @Benchmark
    @Group("plain")
    public void plain_test1(Control cnt) {

    }

    @Benchmark
    @Group("plain")
    public void plain_test2(Control cnt) {

    }

    @Benchmark
    @Group("plain")
    public void plain_test3() {

    }

    @Benchmark
    @Group("bench")
    public void bench_test1(BenchmarkState s, Control cnt) {

    }

    @Benchmark
    @Group("bench")
    public void bench_test2(BenchmarkState s, Control cnt) {

    }

    @Benchmark
    @Group("bench")
    public void bench_test3(BenchmarkState s) {

    }

    @Benchmark
    @Group("thread")
    public void thread_test1(ThreadState s, Control cnt) {

    }

    @Benchmark
    @Group("thread")
    public void thread_test2(ThreadState s, Control cnt) {

    }

    @Benchmark
    @Group("thread")
    public void thread_test3(ThreadState s) {

    }

    @Benchmark
    @Group("group")
    public void group_test1(GroupState s, Control cnt) {

    }

    @Benchmark
    @Group("group")
    public void group_test2(GroupState s, Control cnt) {

    }

    @Benchmark
    @Group("group")
    public void group_test3(GroupState s) {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
