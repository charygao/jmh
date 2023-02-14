
package org.openjdk.jmh.generators.core;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Class metadata.
 */
public interface ClassInfo extends MetadataInfo {

    /**
     * @return fully qualified package name
     */
    String getPackageName();

    /**
     *
     * @return fully qualified class name
     */
    String getQualifiedName();

    /**
     * @return short class name
     */
    String getName();

    /**
     * @return reference to super-class metadata
     */
    ClassInfo getSuperClass();

    /**
     * @return reference to syntactically-enclosing class
     */
    ClassInfo getDeclaringClass();

    /**
     * @return collection of all fields in class
     */
    Collection<FieldInfo> getFields();

    /**
     * @return collection of all methods in class
     */
    Collection<MethodInfo> getMethods();

    /**
     * @return collection of all constructors in class
     */
    Collection<MethodInfo> getConstructors();

    /**
     * @param annClass annotation class
     * @param <T> annotation type
     * @return class-level annotation, if any; null otherwise
     */
    <T extends Annotation> T getAnnotation(Class<T> annClass);

    /**
     * @return true, if class is abstract
     */
    boolean isAbstract();

    /**
     * @return true, if class is abstract
     */
    boolean isPublic();

    /**
     * @return true, if class is strictfp
     */
    boolean isStrictFP();

    /**
     * @return true, if class is final
     */
    boolean isFinal();

    /**
     * @return true, if class is inner
     */
    boolean isInner();

    /**
     * @return true, if class is enum
     */
    boolean isEnum();

    /**
     * @return if class is enum, the collection of its constant values;
     * empty collection otherwise
     */
    Collection<String> getEnumConstants();
}
