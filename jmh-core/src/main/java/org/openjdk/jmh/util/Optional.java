
package org.openjdk.jmh.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Option class
 * @param <T> stored value.
 */
public class Optional<T> implements Serializable {
    private static final long serialVersionUID = 5691070564925468961L;

    private final T val;

    private Optional(T val) {
        if (val == null) {
            throw new IllegalArgumentException("Val can not be null");
        }
        this.val = val;
    }

    private Optional() {
        this.val = null;
    }

    public T orElse(T elseVal) {
        return (val == null) ? elseVal : val;
    }

    public Optional<T> orAnother(Optional<T> alternative) {
        return (val == null) ? alternative : this;
    }

    /**
     * Produce empty Option
     * @param <T> type
     * @return empty option
     */
    public static <T> Optional<T> none() {
        return new Optional<>();
    }

    /**
     * Wrap the existing value in Option.
     * @param val value to wrap
     * @param <T> type
     * @return option with value
     */
    public static <T> Optional<T> of(T val) {
        return new Optional<>(val);
    }

    public static <T> Optional<T> eitherOf(T val) {
        if (val == null) {
            return Optional.none();
        } else {
            return Optional.of(val);
        }
    }

    public boolean hasValue() {
        return val != null;
    }

    public String toString() {
        if (val == null) {
            return "[]";
        } else {
            return "[" + val + "]";
        }
    }

    public T get() {
        if (val == null) {
            throw new IllegalStateException("Optional is null");
        }
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Optional optional = (Optional) o;

        if (!Objects.equals(val, optional.val)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }

}
