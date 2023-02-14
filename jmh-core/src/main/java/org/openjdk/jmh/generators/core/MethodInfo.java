
package org.openjdk.jmh.generators.core;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Method info.
 */
public interface MethodInfo extends Comparable<MethodInfo>, MetadataInfo {

    /**
     * @return short method name.
     */
    String getName();

    /**
     * @return fully qualified method name, includes class qualified name
     */
    String getQualifiedName();

    /**
     * @return fully qualified return type
     */
    String getReturnType();

    /**
     * @return collection of method parameters.
     */
    Collection<ParameterInfo> getParameters();

    /**
     * @return reference to syntactically-enclosing class
     */
    ClassInfo getDeclaringClass();

    /**
     * @param annClass annotation class
     * @param <T> annotation type
     * @return method-level annotation, if any; null otherwise
     */
    <T extends Annotation> T getAnnotation(Class<T> annClass);

    /**
     * @return true, if method is public
     */
    boolean isPublic();

    /**
     * @return true, if method is abstract
     */
    boolean isAbstract();

    /**
     * @return true, if method is synchronized
     */
    boolean isSynchronized();

    /**
     * @return true, if method is strictfp
     */
    boolean isStrictFP();

    /**
     * @return true, if method is static
     */
    boolean isStatic();
}
