
package org.openjdk.jmh.it.fails.inherit;

import org.junit.Assert;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class InheritableThreadSetupState {
    @Setup
    public void setup() {
        Assert.fail();
    }
}
