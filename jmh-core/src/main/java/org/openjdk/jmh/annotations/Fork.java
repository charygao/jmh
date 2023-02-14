
package org.openjdk.jmh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>Fork annotation allows to set the default forking parameters for the benchmark.</b>
 *
 * <p>This annotation may be put at {@link Benchmark} method to have effect on that
 * method only, or at the enclosing class instance to have the effect over all
 * {@link Benchmark} methods in the class. This annotation may be overridden with
 * the runtime options.</p>
 */
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Fork {

    int BLANK_FORKS = -1;

    String BLANK_ARGS = "blank_blank_blank_2014";

    /** @return number of times harness should fork, zero means "no fork" */
    int value() default BLANK_FORKS;

    /** @return number of times harness should fork and ignore the results */
    int warmups() default BLANK_FORKS;

    /** @return JVM executable to run with */
    String jvm() default BLANK_ARGS;

    /** @return JVM arguments to replace in the command line */
    String[] jvmArgs() default { BLANK_ARGS };

    /** @return JVM arguments to prepend in the command line */
    String[] jvmArgsPrepend() default { BLANK_ARGS };

    /** @return JVM arguments to append in the command line */
    String[] jvmArgsAppend() default { BLANK_ARGS };

}
