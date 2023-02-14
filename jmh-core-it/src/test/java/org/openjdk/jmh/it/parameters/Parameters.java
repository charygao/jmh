
package org.openjdk.jmh.it.parameters;

import org.junit.Assert;
import org.openjdk.jmh.runner.BenchmarkList;
import org.openjdk.jmh.runner.BenchmarkListEntry;
import org.openjdk.jmh.runner.format.OutputFormatFactory;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class Parameters {

    public static BenchmarkListEntry get(Class<?> klass) {
        BenchmarkList list = BenchmarkList.fromFile("target/test-classes" + BenchmarkList.BENCHMARK_LIST);
        Set<BenchmarkListEntry> set = list.find(OutputFormatFactory.createFormatInstance(System.out, VerboseMode.EXTRA),
                Collections.singletonList(klass.getName().replaceAll("\\$", ".")),
                Collections.<String>emptyList());
        Assert.assertEquals("The single benchmark exists", 1, set.size());
        return set.iterator().next();
    }
}
