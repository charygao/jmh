
package org.openjdk.jmh.runner;

import java.util.Collection;
import java.util.Collections;

/**
 * Internal exception in JMH. Always wraps the real cause.
 */
public class BenchmarkException extends RuntimeException {
    private static final long serialVersionUID = 4064666042830679837L;

    public BenchmarkException(Throwable ex) {
        this("Benchmark error", Collections.singleton(ex));
    }

    public BenchmarkException(String msg, Collection<Throwable> errors) {
        super(msg);
        for (Throwable err : errors) {
            addSuppressed(err);
        }
    }

    @Override
    public Throwable getCause() {
        return null;
    }
}
