
package org.openjdk.jmh.it.fails.inherit;

import org.junit.Assert;
import org.openjdk.jmh.annotations.Setup;

public abstract class AbstractSetupBase {
    @Setup
    public void setup() {
        Assert.fail();
    }
}
