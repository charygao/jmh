
package org.openjdk.jmh.generators.core;

import org.openjdk.jmh.util.Utils;

public class SourceThrowableError extends SourceError {

    private final Throwable element;

    public SourceThrowableError(String message, Throwable element) {
        super(message);
        this.element = element;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + Utils.throwableToString(element);
    }
}
