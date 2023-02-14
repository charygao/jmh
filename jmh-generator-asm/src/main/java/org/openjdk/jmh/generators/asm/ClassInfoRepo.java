
package org.openjdk.jmh.generators.asm;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.reflection.RFGeneratorSource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class ClassInfoRepo {

    private final Map<String, ClassInfo> map = new HashMap<>();

    public ClassInfo get(String desc) {
        desc = desc.replace('/', '.');
        ClassInfo info = map.get(desc);
        if (info != null) {
            return info;
        }

        if (desc.equals(boolean.class.getCanonicalName()))  return RFGeneratorSource.resolveClass(boolean.class);
        if (desc.equals(byte.class.getCanonicalName()))     return RFGeneratorSource.resolveClass(byte.class);
        if (desc.equals(char.class.getCanonicalName()))     return RFGeneratorSource.resolveClass(char.class);
        if (desc.equals(short.class.getCanonicalName()))    return RFGeneratorSource.resolveClass(short.class);
        if (desc.equals(int.class.getCanonicalName()))      return RFGeneratorSource.resolveClass(int.class);
        if (desc.equals(float.class.getCanonicalName()))    return RFGeneratorSource.resolveClass(float.class);
        if (desc.equals(long.class.getCanonicalName()))     return RFGeneratorSource.resolveClass(long.class);
        if (desc.equals(double.class.getCanonicalName()))   return RFGeneratorSource.resolveClass(double.class);

        if (desc.equals(boolean[].class.getCanonicalName()))  return RFGeneratorSource.resolveClass(boolean[].class);
        if (desc.equals(byte[].class.getCanonicalName()))     return RFGeneratorSource.resolveClass(byte[].class);
        if (desc.equals(char[].class.getCanonicalName()))     return RFGeneratorSource.resolveClass(char[].class);
        if (desc.equals(short[].class.getCanonicalName()))    return RFGeneratorSource.resolveClass(short[].class);
        if (desc.equals(int[].class.getCanonicalName()))      return RFGeneratorSource.resolveClass(int[].class);
        if (desc.equals(float[].class.getCanonicalName()))    return RFGeneratorSource.resolveClass(float[].class);
        if (desc.equals(long[].class.getCanonicalName()))     return RFGeneratorSource.resolveClass(long[].class);
        if (desc.equals(double[].class.getCanonicalName()))   return RFGeneratorSource.resolveClass(double[].class);

        if (desc.endsWith("[]")) {
            desc = "[L" + desc.substring(0, desc.length() - 2) + ";";
        }

        try {
            return RFGeneratorSource.resolveClass(Class.forName(desc, false, Thread.currentThread().getContextClassLoader()));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to resolve class: " + desc);
        }
    }

    public void put(String desc, ClassInfo info) {
        desc = desc.replace('/', '.');
        map.put(desc, info);
    }

    public Collection<ClassInfo> getInfos() {
        return map.values();
    }
}
