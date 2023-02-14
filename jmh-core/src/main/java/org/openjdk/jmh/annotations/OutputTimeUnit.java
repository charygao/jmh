
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * <p>OutputTimeUnit provides the default time unit to report the results in.</p>
 *
 * <p>This annotation may be put at {@link Benchmark} method to have effect on that
 * method only, or at the enclosing class instance to have the effect over all
 * {@link Benchmark} methods in the class. This annotation may be overridden with
 * the runtime options.</p>
 */
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OutputTimeUnit {

    /**
     * @return Time unit to use.
     */
    TimeUnit value();

}
