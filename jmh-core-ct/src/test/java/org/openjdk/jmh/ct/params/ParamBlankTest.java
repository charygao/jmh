
package org.openjdk.jmh.ct.params;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
public class ParamBlankTest {

    @Param
    public boolean param_boolean;

    @Param
    public byte param_byte;

    @Param
    public short param_short;

    @Param
    public char param_char;

    @Param
    public int param_int;

    @Param
    public float param_float;

    @Param
    public long param_long;

    @Param
    public double param_double;

    @Param
    public Boolean param_Boolean;

    @Param
    public Byte param_Byte;

    @Param
    public Short param_Short;

    @Param
    public Character param_Char;

    @Param
    public Integer param_Int;

    @Param
    public Float param_Float;

    @Param
    public Long param_Long;

    @Param
    public Double param_Double;

    @Param
    public String param_String;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertFail(this.getClass(), "default parameters");
    }

}
