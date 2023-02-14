
package org.openjdk.jmh.ct.other;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.ct.CompileTest;

import javax.swing.*;

public class SwingTest {

    public static class S extends JFrame {

    }

    @Benchmark
    public void test() {

    }

    @Test
    public void compileTest() {
        CompileTest.assertOK(this.getClass());
    }

}
