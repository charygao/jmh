
package org.openjdk.jmh.generators.core;

import java.io.*;

/**
 * Generator destination.
 *
 * <p>The exit point for {@link org.openjdk.jmh.generators.core.BenchmarkGenerator}.</p>
 */
public interface GeneratorDestination {

    /**
     * Returns the stream for the given resource.
     * Callers are responsible for closing streams.
     *
     * @param resourcePath resource path
     * @return output stream to write the resource to.
     * @throws java.io.IOException if something wacked happens
     */
    OutputStream newResource(String resourcePath) throws IOException;

    /**
     * Returns the stream for the given resource.
     * Callers are responsible for closing streams.
     *
     * @param resourcePath resource path
     * @return stream usable to read the resource
     * @throws java.io.IOException if something wacked happens
     */
    InputStream getResource(String resourcePath) throws IOException;

    /**
     * Returns the Writer for the given class.
     * Callers are responsible for closing Writers.
     *
     * @param className class name
     * @param originatingClassName class name causing the creation of this class
     * @return writer usable to write the resource
     * @throws IOException if something wacked happens
     */
    Writer newClass(String className, String originatingClassName) throws IOException;

    /**
     * Print the error.
     * Calling this method should not terminate anything.
     *
     * @param message error.
     */
    void printError(String message);

    /**
     * Print the error.
     * Calling this method should not terminate anything.
     *
     * @param message error.
     * @param element metadata element, to which this error is tailored
     */
    void printError(String message, MetadataInfo element);

    /**
     * Print the error.
     * Calling this method should not terminate anything.
     *
     * @param message error.
     * @param throwable exception causing the error
     */
    void printError(String message, Throwable throwable);

    /**
     * Print the warning.
     * Calling this method should not terminate anything.
     *
     * @param message warning.
     */
    void printWarning(String message);

    /**
     * Print the warning.
     * Calling this method should not terminate anything.
     *
     * @param message warning.
     * @param element metadata element, to which this error is tailored
     */
    void printWarning(String message, MetadataInfo element);

    /**
     * Print the warning.
     * Calling this method should not terminate anything.
     *
     * @param message warning.
     * @param throwable exception causing the error
     */
    void printWarning(String message, Throwable throwable);

    /**
     * Print the informative message.
     * Calling this method should not terminate anything.
     *
     * @param message message.
     */
    void printNote(String message);
}
