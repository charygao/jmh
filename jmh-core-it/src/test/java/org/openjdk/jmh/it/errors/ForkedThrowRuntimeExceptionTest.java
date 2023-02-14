
package org.openjdk.jmh.it.errors;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.it.Fixtures;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
public class ForkedThrowRuntimeExceptionTest {

    @Benchmark
    public void bench() {
        throw new MyException("Something wicked");
    }

    @Test
    public void test() throws RunnerException, IOException {
        File output = FileUtils.tempFile("output");

        Shared.doRun(Fixtures.getTestMask(this.getClass()), true, output);

        Collection<String> lines = FileUtils.readAllLines(output);
        Shared.print(lines);

        Assert.assertTrue(Shared.contains(lines, "MyException"));
        Assert.assertTrue(Shared.contains(lines, "Something wicked"));
    }

    public static class MyException extends RuntimeException {
        public MyException(String s) {
            super(s);
        }
    }

}
