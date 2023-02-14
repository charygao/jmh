
package org.openjdk.jmh.generators.annotations;

import org.openjdk.jmh.generators.core.ClassInfo;
import org.openjdk.jmh.generators.core.ParameterInfo;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.VariableElement;

class APParameterInfo extends APMetadataInfo implements ParameterInfo {
    private final VariableElement ve;

    public APParameterInfo(ProcessingEnvironment processEnv, VariableElement ve) {
        super(processEnv, ve);
        if (ve == null) {
            throw new IllegalArgumentException("element is null");
        }
        this.ve = ve;
    }

    @Override
    public ClassInfo getType() {
        return new APClassInfo(processEnv, ve.asType());
    }

    public String toString() {
        return getType() + " " + ve.getSimpleName();
    }
}
