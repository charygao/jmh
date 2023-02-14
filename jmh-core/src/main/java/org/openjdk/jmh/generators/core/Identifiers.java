
package org.openjdk.jmh.generators.core;

import org.openjdk.jmh.annotations.Scope;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Identifiers {

    private final Map<String, String> collapsedTypes = new HashMap<>();
    private int collapsedIndex = 0;

    private final Set<String> claimedJmhTypes = new HashSet<>();
    private final Map<String, String> jmhTypes = new HashMap<>();

    public String getJMHtype(ClassInfo type) {
        String id = BenchmarkGeneratorUtils.getGeneratedName(type);
        String jmhType = jmhTypes.get(id);
        if (jmhType == null) {
            int v = 0;
            do {
                jmhType = id + (v == 0 ? "" : "_" + v) + "_jmhType";
                v++;
            } while (!claimedJmhTypes.add(jmhType));
            jmhTypes.put(id, jmhType);
        }
        return jmhType;
    }

    public String collapseTypeName(String e) {
        if (collapsedTypes.containsKey(e)) {
            return collapsedTypes.get(e);
        }

        String[] strings = e.split("\\.");
        String name = strings[strings.length - 1].toLowerCase();

        String collapsedName = name + (collapsedIndex++) + "_";
        collapsedTypes.put(e, collapsedName);
        return collapsedName;
    }

    private int index = 0;

    public String identifier(Scope scope) {
        switch (scope) {
            case Benchmark:
            case Group: {
                return "G";
            }
            case Thread: {
                return String.valueOf(index++);
            }
            default:
                throw new GenerationException("Unknown scope: " + scope, null);
        }
    }

}
