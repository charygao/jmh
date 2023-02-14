
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>TearDown marks the fixture method to be run after the benchmark.</p>
 *
 * <p>Since fixture methods manage the {@link State} lifecycles, {@link TearDown}
 * can only be declared in {@link State} classes. The {@link TearDown} method will
 * be executed by a thread which has the access to {@link State}, and it is not
 * defined which thread exactly. Note that means {@link Setup} may be executed
 * by a different thread, if {@link State} is shared between the threads.</p>
 *
 * <p>Uses may optionally provide the {@link Level} at which the fixture method
 * should run.</p>
 *
 * @see State
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TearDown {

    /**
     * @return At which level to run this fixture.
     * @see Level
     */
    Level value() default Level.Trial;

}
