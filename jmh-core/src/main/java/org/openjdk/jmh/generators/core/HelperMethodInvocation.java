
package org.openjdk.jmh.generators.core;

import org.openjdk.jmh.annotations.Level;

class HelperMethodInvocation implements Comparable<HelperMethodInvocation> {
    public final MethodInfo method;
    public final StateObject state;
    public final Level helperLevel;
    public final HelperType type;

    public HelperMethodInvocation(MethodInfo method, StateObject state, Level helperLevel, HelperType type) {
        this.method = method;
        this.state = state;
        this.helperLevel = helperLevel;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HelperMethodInvocation that = (HelperMethodInvocation) o;

        if (helperLevel != that.helperLevel) return false;
        if (!method.equals(that.method)) return false;
        if (!state.equals(that.state)) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + helperLevel.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public int compareTo(HelperMethodInvocation o) {
        return method.compareTo(o.method);
    }
}
