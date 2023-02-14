
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>GroupThreads defines how many threads are participating in running
 * a particular {@link Benchmark} method in the group.</p>
 *
 * @see Group
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupThreads {

    /** @return number of threads to run the concrete method with. */
    int value() default 1;

}
