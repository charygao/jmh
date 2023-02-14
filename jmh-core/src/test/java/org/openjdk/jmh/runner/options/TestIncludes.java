
package org.openjdk.jmh.runner.options;

import org.junit.Assert;
import org.junit.Test;

public class TestIncludes {

    @Test
    public void testCmdLine_OptBuilder() throws CommandLineOptionException {
        Options opts = new OptionsBuilder()
                .parent(new CommandLineOptions())
                .include(".*boo.*")
                .build();
        Assert.assertEquals(1, opts.getIncludes().size());
        Assert.assertEquals(".*boo.*", opts.getIncludes().get(0));
    }

    @Test
    public void testOptBuilder_OptBuilder() {
        Options opts = new OptionsBuilder()
                .parent(new OptionsBuilder().build())
                .include(".*boo.*")
                .build();
        Assert.assertEquals(1, opts.getIncludes().size());
        Assert.assertEquals(".*boo.*", opts.getIncludes().get(0));
    }

}
