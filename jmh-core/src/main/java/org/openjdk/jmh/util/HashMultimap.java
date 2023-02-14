
package org.openjdk.jmh.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class HashMultimap<K, V> extends DelegatingMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2484428623123444998L;

    public HashMultimap() {
        super(new HashMap<>());
    }
}
