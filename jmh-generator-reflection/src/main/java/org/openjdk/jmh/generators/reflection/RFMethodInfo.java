
package org.openjdk.jmh.generators.reflection;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.MethodInfo;
import org.openjdk.jmh.generators.core.ParameterInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

class RFMethodInfo implements MethodInfo {

    private final RFClassInfo declaringClass;
    private final Method m;

    public RFMethodInfo(RFClassInfo declaringClass, Method m) {
        this.declaringClass = declaringClass;
        this.m = m;
    }

    @Override
    public ClassInfo getDeclaringClass() {
        return declaringClass;
    }

    @Override
    public String getName() {
        return m.getName();
    }

    @Override
    public String getQualifiedName() {
        return declaringClass.getQualifiedName() + "." + m.getName();
    }

    @Override
    public String getReturnType() {
        return m.getReturnType().getCanonicalName();
    }

    @Override
    public Collection<ParameterInfo> getParameters() {
        Collection<ParameterInfo> pis = new ArrayList<>();
        for (Class<?> cl : m.getParameterTypes()) {
            pis.add(new RFParameterInfo(cl));
        }
        return pis;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annClass) {
        return m.getAnnotation(annClass);
    }

    @Override
    public boolean isPublic() {
        return Modifier.isPublic(m.getModifiers());
    }

    @Override
    public boolean isAbstract() {
        return Modifier.isAbstract(m.getModifiers());
    }

    @Override
    public boolean isSynchronized() {
        return Modifier.isSynchronized(m.getModifiers());
    }

    @Override
    public boolean isStrictFP() {
        return Modifier.isStrict(m.getModifiers());
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(m.getModifiers());
    }

    @Override
    public int compareTo(MethodInfo o) {
        return getQualifiedName().compareTo(o.getQualifiedName());
    }
}
