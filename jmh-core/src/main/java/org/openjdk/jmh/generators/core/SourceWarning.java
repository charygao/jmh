
package org.openjdk.jmh.generators.core;

public class SourceWarning {

    private final String message;

    public SourceWarning(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
