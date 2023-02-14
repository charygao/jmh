
package org.openjdk.jmh.generators.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.MethodInfo;
import org.openjdk.jmh.generators.core.ParameterInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class ASMMethodInfo extends MethodVisitor implements MethodInfo  {

    private final ASMClassInfo declaringClass;
    private final Map<String, AnnotationInvocationHandler> annotations;
    private final int access;
    private final String name;
    private final String returnType;
    private final Type[] argumentTypes;
    private final ClassInfoRepo repo;

    public ASMMethodInfo(MethodVisitor methodVisitor, ClassInfoRepo repo, ASMClassInfo declaringClass, int access, String name, String desc, String signature) {
        super(Opcodes.ASM5, methodVisitor);
        this.declaringClass = declaringClass;
        this.repo = repo;
        this.access = access;
        this.name = name;
        this.returnType = Type.getReturnType(desc).getClassName();
        this.annotations = new HashMap<>();
        this.argumentTypes = Type.getArgumentTypes(desc);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annClass) {
        AnnotationInvocationHandler handler = annotations.get(annClass.getCanonicalName());
        if (handler == null) {
            return null;
        } else {
            return (T) Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class[]{annClass},
                    handler);
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        AnnotationInvocationHandler annHandler = new AnnotationInvocationHandler(className, super.visitAnnotation(desc, visible));
        annotations.put(className, annHandler);
        return annHandler;
    }

    @Override
    public ClassInfo getDeclaringClass() {
        return declaringClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getQualifiedName() {
        return declaringClass.getQualifiedName() + "." + name;
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    @Override
    public Collection<ParameterInfo> getParameters() {
        Collection<ParameterInfo> result = new ArrayList<>();
        for (Type t : argumentTypes) {
            ClassInfo ci = repo.get(t.getClassName());
            result.add(new ASMParameterInfo(ci));
        }
        return result;
    }

    @Override
    public boolean isPublic() {
        return (access & Opcodes.ACC_PUBLIC) > 0;
    }

    @Override
    public boolean isAbstract() {
        return (access & Opcodes.ACC_ABSTRACT) > 0;
    }

    @Override
    public boolean isSynchronized() {
        return (access & Opcodes.ACC_SYNCHRONIZED) > 0;
    }

    @Override
    public boolean isStrictFP() {
        return (access & Opcodes.ACC_STRICT) > 0;
    }

    @Override
    public boolean isStatic() {
        return (access & Opcodes.ACC_STATIC) > 0;
    }

    @Override
    public int compareTo(MethodInfo o) {
        return getQualifiedName().compareTo(o.getQualifiedName());
    }

    @Override
    public String toString() {
        return getQualifiedName() + "()";
    }

}
