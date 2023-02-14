
package org.openjdk.jmh.it.params;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Thread)
public class EmptyTrailingStringParamTest {

    @Param({"val", ""})
    public String x;

    @Benchmark
    public void test() {
        Fixtures.work();
    }

    @Test
    public void test_ann() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .build();

        Collection<RunResult> res = new Runner(opts).run();

        Set<String> actualP = new HashSet<>();
        for (RunResult r : res) {
            actualP.add(r.getParams().getParam("x"));
        }

        Assert.assertEquals(2, actualP.size());
        Assert.assertTrue(actualP.contains("val"));
        Assert.assertTrue(actualP.contains(""));
    }

    @Test
    public void test_api() throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Fixtures.getTestMask(this.getClass()))
                .shouldFailOnError(true)
                .param("x", "val", "")
                .build();


        Collection<RunResult> res = new Runner(opts).run();

        Set<String> actualP = new HashSet<>();
        for (RunResult r : res) {
            actualP.add(r.getParams().getParam("x"));
        }

        Assert.assertEquals(2, actualP.size());
        Assert.assertTrue(actualP.contains("val"));
        Assert.assertTrue(actualP.contains(""));
    }

}
