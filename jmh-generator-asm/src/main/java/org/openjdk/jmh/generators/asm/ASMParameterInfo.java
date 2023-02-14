
package org.openjdk.jmh.generators.asm;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.ParameterInfo;

class ASMParameterInfo implements ParameterInfo {

    private final ClassInfo ci;

    public ASMParameterInfo(ClassInfo ci) {
        this.ci = ci;
    }

    @Override
    public ClassInfo getType() {
        return ci;
    }
}
