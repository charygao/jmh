
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>OperationsPerInvocation annotations allows to communicate the benchmark does more than
 * one operation, and let JMH to adjust the scores appropriately.</p>
 *
 * <p>For example, a benchmark which uses the internal loop to have multiple operations, may
 * want to measure the performance of a single operation:</p>
 *
 * <blockquote><pre>
 * &#64;Benchmark
 * &#64;OperationsPerInvocation(10)
 * public void test() {
 *      for (int i = 0; i &lt; 10; i++) {
 *          // do something
 *      }
 * }
 * </pre></blockquote>
 *
 * <p>This annotation may be put at {@link Benchmark} method to have effect on that method
 * only, or at the enclosing class instance to have the effect over all {@link Benchmark}
 * methods in the class.</p>
 */
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationsPerInvocation {

    /**
     * @return Number of operations per single {@link Benchmark} call.
     */
    int value() default 1;

}
