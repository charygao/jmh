
package org.openjdk.jmh.generators.core;

public class GenerationException extends RuntimeException {
    private static final long serialVersionUID = -3462499052514960496L;

    private final MetadataInfo element;

    public GenerationException(String message, MetadataInfo element) {
        super(message);
        this.element = element;
    }

    public MetadataInfo getElement() {
        return element;
    }
}
