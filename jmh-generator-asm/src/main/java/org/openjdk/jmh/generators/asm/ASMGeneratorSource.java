
package org.openjdk.jmh.generators.asm;

import org.objectweb.asm.ClassReader;
import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.GeneratorSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class ASMGeneratorSource implements GeneratorSource {

    private final ClassInfoRepo classInfos;

    public ASMGeneratorSource() {
        this.classInfos = new ClassInfoRepo();
    }

    public void processClasses(Collection<File> classFiles)  throws IOException {
        for (File f : classFiles) {
            processClass(f);
        }
    }

    public void processClass(File classFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(classFile)){
            processClass(fis);
        }
    }

    public void processClass(InputStream stream) throws IOException {
        final ASMClassInfo ci = new ASMClassInfo(classInfos);
        ClassReader reader = new ClassReader(stream);
        reader.accept(ci, 0);
        classInfos.put(ci.getIdName(), ci);
    }

    @Override
    public Collection<ClassInfo> getClasses() {
        return classInfos.getInfos();
    }

    @Override
    public ClassInfo resolveClass(String className) {
        return classInfos.get(className);
    }

}
