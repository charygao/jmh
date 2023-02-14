
package org.openjdk.jmh.generators.reflection;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.ParameterInfo;

class RFParameterInfo implements ParameterInfo {
    private final Class<?> cl;

    public RFParameterInfo(Class<?> cl) {
        this.cl = cl;
    }

    @Override
    public ClassInfo getType() {
        return new RFClassInfo(cl);
    }
}
