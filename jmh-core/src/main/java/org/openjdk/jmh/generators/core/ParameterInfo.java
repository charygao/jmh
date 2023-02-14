
package org.openjdk.jmh.generators.core;

/**
 * Method parameter metadata.
 */
public interface ParameterInfo extends MetadataInfo {

    /**
     * @return parameter type
     */
    ClassInfo getType();
}
