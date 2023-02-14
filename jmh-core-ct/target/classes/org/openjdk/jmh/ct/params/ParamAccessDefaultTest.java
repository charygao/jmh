
package org.openjdk.jmh.ct.params;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
public class ParamAccessDefaultTest {

    @Param("true")
    boolean param_boolean;

    @Param("0")
    byte param_byte;

    @Param("0")
    short param_short;

    @Param("0")
    char param_char;

    @Param("0")
    int param_int;

    @Param("0")
    float param_float;

    @Param("0")
    long param_long;

    @Param("0")
    double param_double;

    @Param("true")
    Boolean param_Boolean;

    @Param("0")
    Byte param_Byte;

    @Param("0")
    Short param_Short;

    @Param("0")
    Character param_Char;

    @Param("0")
    Integer param_Int;

    @Param("0")
    Float param_Float;

    @Param("0")
    Long param_Long;

    @Param("0")
    Double param_Double;

    @Param("null")
    String param_String;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
