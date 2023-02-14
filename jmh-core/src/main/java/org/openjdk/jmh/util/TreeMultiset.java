
package org.openjdk.jmh.util;

import java.io.Serializable;
import java.util.TreeMap;

public class TreeMultiset<T extends Comparable<T>> extends DelegatingMultiset<T> implements Serializable {
    private static final long serialVersionUID = 3571810468402616517L;

    public TreeMultiset() {
        super(new TreeMap<>());
    }
}
