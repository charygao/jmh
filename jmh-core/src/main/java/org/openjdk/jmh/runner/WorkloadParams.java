
package org.openjdk.jmh.runner;

import java.io.Serializable;
import java.util.*;

public class WorkloadParams implements Comparable<WorkloadParams>, Serializable {
    private static final long serialVersionUID = 780563934988950196L;

    private final SortedMap<String, Value> params;

    public WorkloadParams() {
        params = new TreeMap<>();
    }

    private WorkloadParams(SortedMap<String, Value> params) {
        this();
        this.params.putAll(params);
    }

    @Override
    public int compareTo(WorkloadParams o) {
        if (!params.keySet().equals(o.params.keySet())) {
            throw new IllegalStateException("Comparing actual params with different key sets.");
        }

        for (Map.Entry<String, Value> e : params.entrySet()) {
            int cr = e.getValue().compareTo(o.params.get(e.getKey()));
            if (cr != 0) {
                return cr;
            }
        }
        return 0;
    }

    public void put(String k, String v, int vOrder) {
        params.put(k, new Value(v, vOrder));
    }

    public boolean containsKey(String name) {
        return params.containsKey(name);
    }

    public String get(String name) {
        Value value = params.get(name);
        if (value == null) {
            return null;
        } else {
            return value.value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkloadParams that = (WorkloadParams) o;
        return Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return params != null ? params.hashCode() : 0;
    }

    @Override
    public String toString() {
        return params.toString();
    }

    public WorkloadParams copy() {
        return new WorkloadParams(params);
    }

    public boolean isEmpty() {
        return params.isEmpty();
    }

    public Collection<String> keys() {
        return params.keySet();
    }

    private static class Value implements Comparable<Value>, Serializable {
        private static final long serialVersionUID = 8846779314306880977L;

        private final String value;
        private final int order;

        public Value(String value, int order) {
            this.value = value;
            this.order = order;
        }

        @Override
        public int compareTo(Value o) {
            return Integer.compare(order, o.order);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Value value1 = (Value) o;

            return Objects.equals(value, value1.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

}
