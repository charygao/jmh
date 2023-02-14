
package org.openjdk.jmh.util;

import java.io.Serializable;
import java.util.HashMap;

public class HashMultiset<T> extends DelegatingMultiset<T> implements Serializable {
    private static final long serialVersionUID = 8149201968248505516L;

    public HashMultiset() {
        super(new HashMap<>());
    }
}
