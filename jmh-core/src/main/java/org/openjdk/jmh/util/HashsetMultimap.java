
package org.openjdk.jmh.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class HashsetMultimap<K, V> extends DelegatingMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = -4236100656731956836L;

    public HashsetMultimap() {
        super(new HashMap<>());
    }

    @Override
    protected Collection<V> createValueCollection() {
        return new HashSet<>();
    }
}
