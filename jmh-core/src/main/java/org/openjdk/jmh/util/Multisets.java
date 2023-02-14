
package org.openjdk.jmh.util;

import java.util.*;

public class Multisets {

    public static <T> List<T> countHighest(Multiset<T> set, int top) {
        Queue<Map.Entry<T, Long>> q = new BoundedPriorityQueue<>(top, Map.Entry.<T, Long>comparingByValue().reversed());

        q.addAll(set.entrySet());

        List<T> result = new ArrayList<>(q.size());

        Map.Entry<T, Long> pair;
        while ((pair = q.poll()) != null) {
            result.add(pair.getKey());
        }

        // BoundedPriorityQueue returns "smallest to largest", so we reverse the result
        Collections.reverse(result);

        return result;
    }

    public static <T> List<T> sortedDesc(final Multiset<T> set) {
        List<T> sorted = new ArrayList<>(set.keys());
        sorted.sort((o1, o2) -> Long.compare(set.count(o2), set.count(o1)));
        return sorted;
    }

}
