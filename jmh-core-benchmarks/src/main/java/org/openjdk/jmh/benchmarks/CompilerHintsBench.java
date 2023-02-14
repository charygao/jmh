
package org.openjdk.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class CompilerHintsBench {

    @Benchmark
    public void baseI_baseline() {
        do_Plain(Math.PI);
    }

    @Benchmark
    @Fork(jvmArgsPrepend = {"-XX:MaxInlineSize=0", "-XX:FreqInlineSize=0"})
    public void baseNI_baseline() {
        do_Plain(Math.PI);
    }

    @Benchmark
    public void baseI_inline() {
        do_Inline(Math.PI);
    }

    @Benchmark
    @Fork(jvmArgsPrepend = {"-XX:MaxInlineSize=0", "-XX:FreqInlineSize=0"})
    public void baseNI_inline() {
        do_Inline(Math.PI);
    }

    @Benchmark
    public void baseI_dontInline() {
        do_DontInline(Math.PI);
    }

    @Benchmark
    @Fork(jvmArgsPrepend = {"-XX:MaxInlineSize=0", "-XX:FreqInlineSize=0"})
    public void baseNI_dontInline() {
        do_DontInline(Math.PI);
    }

    @Benchmark
    public void baseI_exclude() {
        do_Exclude(Math.PI);
    }

    @Benchmark
    @Fork(jvmArgsPrepend = {"-XX:MaxInlineSize=0", "-XX:FreqInlineSize=0"})
    public void baseNI_exclude() {
        do_Exclude(Math.PI);
    }

    private double do_Plain(double x) {
        return Math.log(x);
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    private double do_Inline(double x) {
        return Math.log(x);
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private double do_DontInline(double x) {
        return Math.log(x);
    }

    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    private double do_Exclude(double x) {
        return Math.log(x);
    }


}
