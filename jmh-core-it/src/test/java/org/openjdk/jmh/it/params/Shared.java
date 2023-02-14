
package org.openjdk.jmh.it.params;

import org.junit.Assert;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.results.RunResult;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Shared {

    public static void compare(Collection<RunResult> res, int[] xs, String[] ys) {
        Set<String> actualPairs = new HashSet<>();
        for (RunResult r : res) {
            BenchmarkParams params = r.getParams();
            actualPairs.add(params.getParam("x") + params.getParam("y"));
        }

        for (int x : xs) {
            for (String y : ys) {
                Assert.assertTrue(x + y + " is not found", actualPairs.contains(x + y));
            }
        }
    }

}
