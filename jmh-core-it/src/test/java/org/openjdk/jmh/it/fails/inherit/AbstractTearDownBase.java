
package org.openjdk.jmh.it.fails.inherit;

import org.junit.Assert;
import org.openjdk.jmh.annotations.TearDown;

public abstract class AbstractTearDownBase {
    @TearDown
    public void tearDown() {
        Assert.fail();
    }
}
