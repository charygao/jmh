
package org.openjdk.jmh.runner.link;

import org.openjdk.jmh.runner.BenchmarkException;

import java.io.Serializable;

class ExceptionFrame implements Serializable {
    private static final long serialVersionUID = 5595622047639653401L;

    private final BenchmarkException error;

    public ExceptionFrame(BenchmarkException error) {
        this.error = error;
    }

    public BenchmarkException getError() {
        return error;
    }
}
