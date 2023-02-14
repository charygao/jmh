
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * <p>Warmup annotation allows to set the default warmup parameters for the benchmark.</p>
 *
 * <p>This annotation may be put at {@link Benchmark} method to have effect on that method
 * only, or at the enclosing class instance to have the effect over all {@link Benchmark}
 * methods in the class. This annotation may be overridden with the runtime options.</p>
 *
 * @see Measurement
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Warmup {

    int BLANK_ITERATIONS = -1;
    int BLANK_TIME = -1;
    int BLANK_BATCHSIZE = -1;

    /** @return Number of warmup iterations */
    int iterations() default BLANK_ITERATIONS;

    /** @return Time for each warmup iteration */
    int time() default BLANK_TIME;

    /** @return Time unit for warmup iteration duration */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /** @return batch size: number of benchmark method calls per operation */
    int batchSize() default BLANK_BATCHSIZE;

}
