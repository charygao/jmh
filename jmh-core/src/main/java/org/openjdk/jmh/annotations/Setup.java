
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Setup marks the fixture method to be run before the benchmark.</p>
 *
 * <p>Since fixture methods manage the {@link State} lifecycles, {@link Setup}
 * can only be declared in {@link State} classes. The {@link Setup} method will
 * be executed by a thread which has the access to {@link State}, and it is not
 * defined which thread exactly. Note that means {@link TearDown} may be executed
 * by a different thread, if {@link State} is shared between the threads.</p>
 *
 * <p>Uses may optionally provide the {@link Level} at which the fixture method
 * should run.</p>
 *
 * @see State
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Setup {

    /**
     * @return Level of this method.
     * @see Level
     */
    Level value() default Level.Trial;

}
