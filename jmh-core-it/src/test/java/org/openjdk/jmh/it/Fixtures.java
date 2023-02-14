
package org.openjdk.jmh.it;

import java.util.concurrent.TimeUnit;

public class Fixtures {

    private static final int REPS = Integer.getInteger("jmh.it.reps", 1);

    public static int repetitionCount() {
        return REPS;
    }

    public static String getTestMask(Class<?> klass) {
        return klass.getCanonicalName();
    }

    public static void work() {
        // courtesy for parallel-running tests
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
