
package org.openjdk.jmh.generators.annotations;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.FieldInfo;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.annotation.Annotation;

class APFieldInfo extends APMetadataInfo implements FieldInfo {

    private final VariableElement ve;

    public APFieldInfo(ProcessingEnvironment processEnv, VariableElement ve) {
        super(processEnv, ve);
        if (ve == null) {
            throw new IllegalArgumentException("element is null");
        }
        this.ve = ve;
    }

    @Override
    public String getName() {
        return ve.getSimpleName().toString();
    }

    @Override
    public ClassInfo getType() {
        return new APClassInfo(processEnv, ve.asType());
    }

    @Override
    public boolean isPublic() {
        return ve.getModifiers().contains(Modifier.PUBLIC);
    }

    @Override
    public boolean isStatic() {
        return ve.getModifiers().contains(Modifier.STATIC);
    }

    @Override
    public boolean isFinal() {
        return ve.getModifiers().contains(Modifier.FINAL);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annClass) {
        return ve.getAnnotation(annClass);
    }

    @Override
    public ClassInfo getDeclaringClass() {
        return new APClassInfo(processEnv, (TypeElement)ve.getEnclosingElement());
    }

    public String toString() {
        return getType() + " " + getName();
    }
}
