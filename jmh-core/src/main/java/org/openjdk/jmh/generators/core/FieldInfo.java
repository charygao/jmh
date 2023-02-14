
package org.openjdk.jmh.generators.core;

import java.lang.annotation.Annotation;

/**
 * Field metadata info.
 */
public interface FieldInfo extends MetadataInfo {

    /**
     * @return field name
     */
    String getName();

    /**
     * @return fully qualified field type
     */
    ClassInfo getType();

    /**
     * @return reference to syntactically-enclosing class
     */
    ClassInfo getDeclaringClass();

    /**
     * @param annClass annotation class
     * @param <T> annotation type
     * @return field-level annotation, if any; null otherwise
     */
    <T extends Annotation> T getAnnotation(Class<T> annClass);

    /**
     * @return true, if field is public
     */
    boolean isPublic();

    /**
     * @return true, if field is static
     */
    boolean isStatic();

    /**
     * @return true, if field is final
     */
    boolean isFinal();
}
