
package org.openjdk.jmh.annotations;

/**
 * {@link State} scope.
 */
public enum Scope {

    /**
     * <p>Benchmark state scope.</p>
     *
     * <p>With benchmark scope, all instances of the same type will be shared across all
     * worker threads.</p>
     *
     * <p>{@link Setup} and {@link TearDown} methods on this state object would be performed
     * by one of the worker threads, and only once per {@link Level}.
     * No other threads would ever touch the state object.</p>
     */
    Benchmark,

    /**
     * <p>Group state scope.</p>
     *
     * <p>With group scope, all instances of the same type will be shared across all
     * threads within the same group. Each thread group will be supplied with its own
     * state object.</p>
     *
     * <p>{@link Setup} and {@link TearDown} methods on this state object would be performed
     * by one of the group threads, and only once per {@link Level}.
     * No other threads would ever touch the state object.</p>
     *
     * @see Group
     */
    Group,

    /**
     * <p>Thread state scope.</p>
     *
     * <p>With thread scope, all instances of the same type are distinct, even if multiple
     * state objects are injected in the same benchmark</p>
     *
     * <p>{@link Setup} and {@link TearDown} methods on this state object would be performed
     * by single worker thread exclusively, and only once per {@link Level}.
     * No other threads would ever touch the state object.</p>
     *
     */
    Thread,

}
