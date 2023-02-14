
package org.openjdk.jmh.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.TreeMap;

public class TreeMultimap<K, V> extends DelegatingMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 1323519395777393861L;

    public TreeMultimap() {
        super(new TreeMap<>());
    }
}
