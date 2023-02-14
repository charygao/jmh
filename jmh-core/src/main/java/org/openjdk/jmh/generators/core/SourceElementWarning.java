
package org.openjdk.jmh.generators.core;

public class SourceElementWarning extends SourceWarning {

    private final MetadataInfo element;

    public SourceElementWarning(String message, MetadataInfo element) {
        super(message);
        this.element = element;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   [" + element.toString() + "]";
    }
}
