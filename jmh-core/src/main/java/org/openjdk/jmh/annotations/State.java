
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Marks the state object.</p>
 *
 * <p>State objects naturally encapsulate the state on which benchmark is working on.
 * The {@link Scope} of state object defines to which extent it is shared among the
 * worker threads.</p>
 *
 * <p>State objects are usually injected into {@link Benchmark} methods as arguments,
 * and JMH takes care of their instantiation and sharing. State objects may also be
 * injected into {@link Setup} and {@link TearDown} methods of other {@link State}
 * objects to get the staged initialization. In that case, the dependency graph
 * between the {@link State}-s should be directed acyclic graph.</p>
 *
 * <p>State objects may be inherited: you can place {@link State} on a super class and
 * use subclasses as states.</p>
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface State {

    /**
     * State scope.
     * @return state scope
     * @see Scope
     */
    Scope value();

}
