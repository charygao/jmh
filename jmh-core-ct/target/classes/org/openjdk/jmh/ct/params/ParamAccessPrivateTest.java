
package org.openjdk.jmh.ct.params;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
public class ParamAccessPrivateTest {

    @Param("true")
    private boolean param_boolean;

    @Param("0")
    private byte param_byte;

    @Param("0")
    private short param_short;

    @Param("0")
    private char param_char;

    @Param("0")
    private int param_int;

    @Param("0")
    private float param_float;

    @Param("0")
    private long param_long;

    @Param("0")
    private double param_double;

    @Param("true")
    private Boolean param_Boolean;

    @Param("0")
    private Byte param_Byte;

    @Param("0")
    private Short param_Short;

    @Param("0")
    private Character param_Char;

    @Param("0")
    private Integer param_Int;

    @Param("0")
    private Float param_Float;

    @Param("0")
    private Long param_Long;

    @Param("0")
    private Double param_Double;

    @Param("null")
    private String param_String;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
