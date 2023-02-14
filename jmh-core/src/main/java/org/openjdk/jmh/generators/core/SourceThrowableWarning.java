
package org.openjdk.jmh.generators.core;

import org.openjdk.jmh.util.Utils;

public class SourceThrowableWarning extends SourceWarning {

    private final Throwable element;

    public SourceThrowableWarning(String message, Throwable element) {
        super(message);
        this.element = element;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + Utils.throwableToString(element);
    }
}
