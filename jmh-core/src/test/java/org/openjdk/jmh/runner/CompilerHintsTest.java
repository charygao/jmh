
package org.openjdk.jmh.runner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompilerHintsTest {

    private String vmName;

    @Before
    public void storeCurrentVM() {
        vmName = System.getProperty("java.vm.name");
    }

    @Test
    public void testListNotEmptyForCompliantJvms() {
        for (String name : CompilerHints.HINT_COMPATIBLE_JVMS) {
            System.setProperty("java.vm.name", name);
            List<String> args = new ArrayList<>();
            CompilerHints.addCompilerHints(args);
            assertFalse(args.isEmpty());
        }
    }

    @Test
    public void testListEmptyForOldZingJvms() {
        System.setProperty("java.vm.name", "Zing");
        System.setProperty("java.version", "1.7.0-zing_5.9.2.0");
        // load up some default hints
        List<String> args = new ArrayList<>();
        CompilerHints.addCompilerHints(args);
        assertTrue(args.isEmpty());
    }

    @Test
    public void testListNotEmptyForNewerZingJvms() {
        System.setProperty("java.vm.name", "Zing");
        System.setProperty("java.version", "1.7.0-zing_5.10.2.0");
        // load up some default hints
        List<String> args = new ArrayList<>();
        CompilerHints.addCompilerHints(args);
        assertFalse(args.isEmpty());
    }

    @Test
    public void testListEmptyForNonCompliantJvms() {
        System.setProperty("java.vm.name", "StupidVmCantTakeAHint");
        List<String> args = new ArrayList<>();
        CompilerHints.addCompilerHints(args);
        assertTrue(args.isEmpty());
    }

    @After
    public void restoreCurrentVM() {
        System.setProperty("java.vm.name", vmName);
    }
}
