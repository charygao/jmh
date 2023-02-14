
package org.openjdk.jmh.generators.reflection;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.GeneratorSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class RFGeneratorSource implements GeneratorSource {

    private final Collection<Class> classes;

    public RFGeneratorSource() {
        this.classes = new ArrayList<>();
    }

    @Override
    public Collection<ClassInfo> getClasses() {
        Collection<ClassInfo> cis = new ArrayList<>();
        for (Class c : classes) {
            cis.add(new RFClassInfo(c));
        }
        return cis;
    }

    public static ClassInfo resolveClass(Class<?> klass) {
        return new RFClassInfo(klass);
    }

    @Override
    public ClassInfo resolveClass(String className) {
        String desc = className.replace('/', '.');
        try {
            return resolveClass(Class.forName(desc, false, Thread.currentThread().getContextClassLoader()));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to resolve class: " + desc);
        }
    }

    public void processClasses(Class... cs) {
        processClasses(Arrays.asList(cs));
    }

    public void processClasses(Collection<Class> cs) {
        classes.addAll(cs);
    }
}
