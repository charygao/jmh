
package org.openjdk.jmh.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestClassUtils {

    @Test
    public void testDenseClasses1() {
        List<String> src = Arrays.asList("org.openjdk.benches.ForkJoinTest.test1", "org.openjdk.benches.ForkJoinTest.test2", "org.openjdk.benches.AnotherTest.test0");
        Map<String,String> map = ClassUtils.denseClassNames(src);

        Assert.assertEquals("ForkJoinTest.test1", map.get("org.openjdk.benches.ForkJoinTest.test1"));
        Assert.assertEquals("ForkJoinTest.test2", map.get("org.openjdk.benches.ForkJoinTest.test2"));
    }

    @Test
    public void testDenseClasses2() {
        List<String> src = Collections.singletonList("org.openjdk.benches.ForkJoinTest.test1");
        Map<String,String> map = ClassUtils.denseClassNames(src);

        Assert.assertEquals("ForkJoinTest.test1", map.get("org.openjdk.benches.ForkJoinTest.test1"));
    }

    @Test
    public void testDenseClasses3() {
        List<String> src = Collections.singletonList("org.openjdk.benches.ForkJoinTest.test1:label1");
        Map<String,String> map = ClassUtils.denseClassNames(src);

        Assert.assertEquals("ForkJoinTest.test1:label1", map.get("org.openjdk.benches.ForkJoinTest.test1:label1"));
    }

    @Test
    public void testDifferentPackages() {
        List<String> src = Arrays.asList("com.some.even.longer.word.SomeEvenMoreWeirdBenchmark", "my.sample.pkg.MySampleBenchmark", "test.jmh.TestJmhBenchmark");
        Map<String,String> map = ClassUtils.denseClassNames(src);

        Assert.assertEquals("com.some.even.longer.word.SomeEvenMoreWeirdBenchmark", map.get("com.some.even.longer.word.SomeEvenMoreWeirdBenchmark"));
        Assert.assertEquals("my.sample.pkg.MySampleBenchmark", map.get("my.sample.pkg.MySampleBenchmark"));
        Assert.assertEquals("test.jmh.TestJmhBenchmark", map.get("test.jmh.TestJmhBenchmark"));
    }

    @Test
    public void testSemiDifferentPackages() {
        List<String> src = Arrays.asList("com.some.Benchmark", "com.some2.Benchmark2");
        Map<String,String> map = ClassUtils.denseClassNames(src);

        Assert.assertEquals("c.some.Benchmark", map.get("com.some.Benchmark"));
        Assert.assertEquals("c.some2.Benchmark2", map.get("com.some2.Benchmark2"));
    }


}
