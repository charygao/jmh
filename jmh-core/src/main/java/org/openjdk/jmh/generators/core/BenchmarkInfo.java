
package org.openjdk.jmh.generators.core;

class BenchmarkInfo {
    public final String userClassQName;
    public final String generatedClassQName;
    public final String generatedPackageName;
    public final String generatedClassName;
    public final MethodGroup methodGroup;

    public BenchmarkInfo(String userClassQName, String generatedPackageName, String generatedClassName, MethodGroup methodGroup) {
        this.userClassQName = userClassQName;
        this.generatedPackageName = generatedPackageName;
        this.generatedClassName = generatedClassName;
        this.generatedClassQName = generatedPackageName + "." + generatedClassName;
        this.methodGroup = methodGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BenchmarkInfo that = (BenchmarkInfo) o;

        if (!methodGroup.equals(that.methodGroup)) return false;
        if (!userClassQName.equals(that.userClassQName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userClassQName.hashCode();
        result = 31 * result + methodGroup.hashCode();
        return result;
    }
}
