
package org.openjdk.jmh;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.infra.Blackhole;

public class BlackholeTest {

    @Test
    public void test() {
        int tlr = 1;
        int tlrMask = 2;
        for (int t = 0; t < 1_000_000_000; t++) {
            tlr = (tlr * 48271);
            if ((tlr & tlrMask) == 0) {
                // SHOULD ALMOST NEVER HAPPEN IN MEASUREMENT
                tlrMask = (tlrMask << 1) + 2;
                System.out.println(t);
            }
        }
    }

    @Test
    public void testUserConstructor() {
        try {
            new Blackhole("Boyaa");
            Assert.fail("Should have failed");
        } catch (IllegalStateException e) {
            // expected
        }

        try {
            new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        } catch (Throwable e) {
            Assert.fail("Failed unexpectedly");
        }
    }

}
