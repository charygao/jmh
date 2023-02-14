
package org.openjdk.jmh.ct.params;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.ct.CompileTest;

@State(Scope.Benchmark)
public class ParamAccessProtectedTest {

    @Param("true")
    protected boolean param_boolean;

    @Param("0")
    protected byte param_byte;

    @Param("0")
    protected short param_short;

    @Param("0")
    protected char param_char;

    @Param("0")
    protected int param_int;

    @Param("0")
    protected float param_float;

    @Param("0")
    protected long param_long;

    @Param("0")
    protected double param_double;

    @Param("true")
    protected Boolean param_Boolean;

    @Param("0")
    protected Byte param_Byte;

    @Param("0")
    protected Short param_Short;

    @Param("0")
    protected Character param_Char;

    @Param("0")
    protected Integer param_Int;

    @Param("0")
    protected Float param_Float;

    @Param("0")
    protected Long param_Long;

    @Param("0")
    protected Double param_Double;

    @Param("null")
    protected String param_String;

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
