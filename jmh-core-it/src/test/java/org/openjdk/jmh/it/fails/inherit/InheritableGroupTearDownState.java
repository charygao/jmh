
package org.openjdk.jmh.it.fails.inherit;

import org.junit.Assert;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Group)
public class InheritableGroupTearDownState {
    @TearDown
    public void tearDown() {
        Assert.fail();
    }
}
