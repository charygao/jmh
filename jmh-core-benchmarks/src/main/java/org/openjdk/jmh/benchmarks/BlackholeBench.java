
package org.openjdk.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BlackholeBench {

    private List<String> strs;

    @Setup(Level.Iteration)
    public void makeGarbage() {
        // make some garbage to evict blackhole from the TLAB/eden
        strs = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            strs.add("str" + i);
        }
    }

    public byte b;
    public boolean bool;
    public char c;
    public short s;
    public int i;
    public long l;
    public float f;
    public double d;
    public Object o;
    public Object[] os;

    @Benchmark
    public void baseline() {
        // do nothing
    }

    @Benchmark
    public byte implicit_byte() {
        return b;
    }

    @Benchmark
    public boolean implicit_boolean() {
        return bool;
    }

    @Benchmark
    public char implicit_char() {
        return c;
    }

    @Benchmark
    public short implicit_short() {
        return s;
    }

    @Benchmark
    public int implicit_int() {
        return i;
    }

    @Benchmark
    public long implicit_long() {
        return l;
    }

    @Benchmark
    public float implicit_float() {
        return f;
    }

    @Benchmark
    public double implicit_double() {
        return d;
    }

    @Benchmark
    public Object implicit_Object() {
        return o;
    }

    @Benchmark
    public Object[] implicit_Array() {
        return os;
    }

    @Benchmark
    public void explicit_byte(Blackhole bh) {
        bh.consume(b);
    }

    @Benchmark
    public void explicit_boolean(Blackhole bh) {
        bh.consume(bool);
    }

    @Benchmark
    public void explicit_char(Blackhole bh) {
        bh.consume(c);
    }

    @Benchmark
    public void explicit_short(Blackhole bh) {
        bh.consume(s);
    }

    @Benchmark
    public void explicit_int(Blackhole bh) {
        bh.consume(i);
    }

    @Benchmark
    public void explicit_long(Blackhole bh) {
        bh.consume(l);
    }

    @Benchmark
    public void explicit_float(Blackhole bh) {
        bh.consume(f);
    }

    @Benchmark
    public void explicit_double(Blackhole bh) {
        bh.consume(d);
    }

    @Benchmark
    public void explicit_Object(Blackhole bh) {
        bh.consume(o);
    }

    @Benchmark
    public void explicit_Array(Blackhole bh) {
        bh.consume(os);
    }

}
