
package org.openjdk.jmh.ct.params;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
public class ParamAccessPublicTest {

    @Param("true")
    public boolean param_boolean;

    @Param("0")
    public byte param_byte;

    @Param("0")
    public short param_short;

    @Param("0")
    public char param_char;

    @Param("0")
    public int param_int;

    @Param("0")
    public float param_float;

    @Param("0")
    public long param_long;

    @Param("0")
    public double param_double;

    @Param("true")
    public Boolean param_Boolean;

    @Param("0")
    public Byte param_Byte;

    @Param("0")
    public Short param_Short;

    @Param("0")
    public Character param_Char;

    @Param("0")
    public Integer param_Int;

    @Param("0")
    public Float param_Float;

    @Param("0")
    public Long param_Long;

    @Param("0")
    public Double param_Double;

    @Param("null")
    public String param_String;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
