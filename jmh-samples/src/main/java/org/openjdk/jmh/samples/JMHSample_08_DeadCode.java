
package org.openjdk.jmh.samples;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_08_DeadCode {

    /*
     * The downfall of many benchmarks is Dead-Code Elimination (DCE): compilers
     * are smart enough to deduce some computations are redundant and eliminate
     * them completely. If the eliminated part was our benchmarked code, we are
     * in trouble.
     *
     * Fortunately, JMH provides the essential infrastructure to fight this
     * where appropriate: returning the result of the computation will ask JMH
     * to deal with the result to limit dead-code elimination (returned results
     * are implicitly consumed by Blackholes, see JMHSample_09_Blackholes).
     */

    private double x = Math.PI;

    private double compute(double d) {
        for (int c = 0; c < 10; c++) {
            d = d * d / Math.PI;
        }
        return d;
    }

    @Benchmark
    public void baseline() {
        // do nothing, this is a baseline
    }

    @Benchmark
    public void measureWrong() {
        // This is wrong: result is not used and the entire computation is optimized away.
        compute(x);
    }

    @Benchmark
    public double measureRight() {
        // This is correct: the result is being used.
        return compute(x);
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can see the unrealistically fast calculation in with measureWrong(),
     * while realistic measurement with measureRight().
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_08 -f 1
     *    (we requested single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_08_DeadCode.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
