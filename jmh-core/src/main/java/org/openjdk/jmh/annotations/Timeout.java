
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * <p>Timeout annotation allows to set the default timeout parameters for the benchmark.</p>
 *
 * <p>This annotation may be put at {@link org.openjdk.jmh.annotations.Benchmark} method to have effect on that method
 * only, or at the enclosing class instance to have the effect over all {@link org.openjdk.jmh.annotations.Benchmark}
 * methods in the class. This annotation may be overridden with the runtime options.</p>
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Timeout {

    /** @return Timeout for each iteration */
    int time();

    /** @return Time unit for timeout duration */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
