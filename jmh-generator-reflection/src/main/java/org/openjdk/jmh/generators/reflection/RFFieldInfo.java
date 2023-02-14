
package org.openjdk.jmh.generators.reflection;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.FieldInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class RFFieldInfo implements FieldInfo {
    private final ClassInfo declaringClass;
    private final Field f;

    public RFFieldInfo(ClassInfo declaringClass, Field f) {
        this.declaringClass = declaringClass;
        this.f = f;
    }

    @Override
    public ClassInfo getDeclaringClass() {
        return declaringClass;
    }

    @Override
    public String getName() {
        return f.getName();
    }

    @Override
    public ClassInfo getType() {
        return new RFClassInfo(f.getType());
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annClass) {
        return f.getAnnotation(annClass);
    }

    @Override
    public boolean isPublic() {
        return Modifier.isPublic(f.getModifiers());
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(f.getModifiers());
    }

    @Override
    public boolean isFinal() {
        return Modifier.isFinal(f.getModifiers());
    }

    @Override
    public String toString() {
        return declaringClass.getQualifiedName() + "." + f.getName();
    }
}
