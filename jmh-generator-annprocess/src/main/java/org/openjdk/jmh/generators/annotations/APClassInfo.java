
package org.openjdk.jmh.generators.annotations;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.FieldInfo;
import org.openjdk.jmh.generators.core.MethodInfo;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class APClassInfo extends APMetadataInfo implements ClassInfo {

    private final TypeElement el;
    private final boolean isSpecial;
    private final TypeMirror mirror;

    public APClassInfo(ProcessingEnvironment processEnv, TypeElement element) {
        super(processEnv, element);
        if (element == null) {
            throw new IllegalArgumentException("element is null");
        }
        this.el = element;
        this.isSpecial = false;
        this.mirror = null;
    }

    public APClassInfo(ProcessingEnvironment processEnv, TypeMirror mirror) {
        super(processEnv, processEnv.getTypeUtils().asElement(mirror));
        this.mirror = mirror;
        this.isSpecial = mirror.getKind() != TypeKind.DECLARED;
        if (isSpecial) {
            this.el = null;
        } else {
            Element element = processEnv.getTypeUtils().asElement(mirror);
            this.el = (TypeElement) element;
        }
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annClass) {
        if (isSpecial) return null;
        return el.getAnnotation(annClass);
    }

    @Override
    public Collection<MethodInfo> getConstructors() {
        if (isSpecial) return Collections.emptyList();
        Collection<MethodInfo> mis = new ArrayList<>();
        for (ExecutableElement e : ElementFilter.constructorsIn(el.getEnclosedElements())) {
            mis.add(new APMethodInfo(processEnv, this, e));
        }
        return mis;
    }

    @Override
    public String getName() {
        if (isSpecial) return mirror.toString();
        return el.getSimpleName().toString();
    }

    @Override
    public String getQualifiedName() {
        if (isSpecial) return mirror.toString();
        return el.getQualifiedName().toString();
    }

    @Override
    public Collection<FieldInfo> getFields() {
        if (isSpecial) return Collections.emptyList();
        List<FieldInfo> ls = new ArrayList<>();
        for (VariableElement e : ElementFilter.fieldsIn(el.getEnclosedElements())) {
            ls.add(new APFieldInfo(processEnv, e));
        }
        return ls;
    }

    @Override
    public Collection<MethodInfo> getMethods() {
        if (isSpecial) return Collections.emptyList();
        Collection<MethodInfo> mis = new ArrayList<>();
        for (ExecutableElement e : ElementFilter.methodsIn(el.getEnclosedElements())) {
            mis.add(new APMethodInfo(processEnv, this, e));
        }
        return mis;
    }

    @Override
    public String getPackageName() {
        if (isSpecial) return "";
        Element walk = el;
        while (walk.getKind() != ElementKind.PACKAGE) {
            walk = walk.getEnclosingElement();
        }
        return ((PackageElement)walk).getQualifiedName().toString();
    }

    @Override
    public ClassInfo getSuperClass() {
        if (isSpecial) return null;
        TypeMirror superclass = el.getSuperclass();
        if (superclass.getKind() == TypeKind.NONE) {
            return null;
        } else {
            TypeElement element = (TypeElement) processEnv.getTypeUtils().asElement(superclass);
            return new APClassInfo(processEnv, element);
        }
    }

    @Override
    public ClassInfo getDeclaringClass() {
        if (isSpecial) return null;
        Element enclosingElement = el.getEnclosingElement();
        if (enclosingElement.getKind() == ElementKind.CLASS) {
            return new APClassInfo(processEnv, (TypeElement) enclosingElement);
        } else {
            return null;
        }
    }

    @Override
    public boolean isAbstract() {
        if (isSpecial) return false;
        return el.getModifiers().contains(Modifier.ABSTRACT);
    }

    @Override
    public boolean isPublic() {
        if (isSpecial) return true;
        return el.getModifiers().contains(Modifier.PUBLIC);
    }

    @Override
    public boolean isStrictFP() {
        if (isSpecial) return false;
        return el.getModifiers().contains(Modifier.STRICTFP);
    }

    @Override
    public boolean isFinal() {
        if (isSpecial) return false;
        return el.getModifiers().contains(Modifier.FINAL);
    }

    @Override
    public boolean isInner() {
        if (isSpecial) return false;
        return (getDeclaringClass() != null) && !el.getModifiers().contains(Modifier.STATIC);
    }

    @Override
    public boolean isEnum() {
        if (isSpecial) return false;
        return el.getKind() == ElementKind.ENUM;
    }

    @Override
    public Collection<String> getEnumConstants() {
        Collection<String> result = new ArrayList<>();
        for (Element e : el.getEnclosedElements()) {
            if (e.getKind() == ElementKind.ENUM_CONSTANT) {
                result.add(e.getSimpleName().toString());
            }
        }
        return result;
    }

    public String toString() {
        return getQualifiedName();
    }

}
