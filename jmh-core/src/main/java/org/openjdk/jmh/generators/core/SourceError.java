
package org.openjdk.jmh.generators.core;

public class SourceError {

    private final String message;

    public SourceError(String message) {
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
