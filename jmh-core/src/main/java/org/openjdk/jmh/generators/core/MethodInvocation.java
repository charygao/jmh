
package org.openjdk.jmh.generators.core;

class MethodInvocation implements Comparable<MethodInvocation> {
    public final MethodInfo method;
    public final int threads;

    public MethodInvocation(MethodInfo method, int threads) {
        this.method = method;
        this.threads = threads;
    }

    @Override
    public int compareTo(MethodInvocation o) {
        return method.getName().compareTo(o.method.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodInvocation that = (MethodInvocation) o;

        if (!method.getName().equals(that.method.getName())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return method.getName().hashCode();
    }
}
