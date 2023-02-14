
package org.openjdk.jmh.generators.core;

import java.util.Collection;

/**
 * Generator source.
 * <p>The entry point for {@link org.openjdk.jmh.generators.core.BenchmarkGenerator}.</p>
 */
public interface GeneratorSource {

    /**
     * @return collection of all resolved classes
     */
    Collection<ClassInfo> getClasses();

    /**
     * Resolve class info for a name.
     *
     * <p>Users may call this method for the classes not
     * listed in {@link #getClasses()} call, the implementation
     * has to have the fall-back strategy for these cases.</p>
     *
     * @param className class name
     * @return class metainfo
     */
    ClassInfo resolveClass(String className);

}
