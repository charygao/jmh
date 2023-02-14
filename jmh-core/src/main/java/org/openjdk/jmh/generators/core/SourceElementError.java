
package org.openjdk.jmh.generators.core;

public class SourceElementError extends SourceError {

    private final MetadataInfo element;

    public SourceElementError(String message, MetadataInfo element) {
        super(message);
        this.element = element;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   [" + element.toString() + "]";
    }
}
