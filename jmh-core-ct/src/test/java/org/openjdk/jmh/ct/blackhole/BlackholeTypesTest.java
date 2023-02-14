
package org.openjdk.jmh.ct.blackhole;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.All)
public class BlackholeTypesTest {

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
    public byte implicit_testByte() {
        return b;
    }

    @Benchmark
    public boolean implicit_testBoolean() {
        return bool;
    }

    @Benchmark
    public char implicit_testChar() {
        return c;
    }

    @Benchmark
    public int implicit_testInt() {
        return i;
    }

    @Benchmark
    public long implicit_testLong() {
        return l;
    }

    @Benchmark
    public float implicit_testFloat() {
        return f;
    }

    @Benchmark
    public double implicit_testDouble() {
        return d;
    }

    @Benchmark
    public Object implicit_testObject() {
        return o;
    }

    @Benchmark
    public Object[] implicit_testArray() {
        return os;
    }

    @Benchmark
    public void explicit_testByte(Blackhole bh) {
        bh.consume(b);
    }

    @Benchmark
    public void explicit_testBoolean(Blackhole bh) {
        bh.consume(bool);
    }

    @Benchmark
    public void explicit_testChar(Blackhole bh) {
        bh.consume(c);
    }

    @Benchmark
    public void explicit_testInt(Blackhole bh) {
        bh.consume(i);
    }

    @Benchmark
    public void explicit_testLong(Blackhole bh) {
        bh.consume(l);
    }

    @Benchmark
    public void explicit_testFloat(Blackhole bh) {
        bh.consume(f);
    }

    @Benchmark
    public void explicit_testDouble(Blackhole bh) {
        bh.consume(d);
    }

    @Benchmark
    public void explicit_testObject(Blackhole bh) {
        bh.consume(o);
    }

    @Benchmark
    public void explicit_testArray(Blackhole bh) {
        bh.consume(os);
    }

    @Test
    public void test() {
        CompileTest.assertOK(this.getClass());
    }

}
