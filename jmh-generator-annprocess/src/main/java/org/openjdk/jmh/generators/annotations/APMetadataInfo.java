
package org.openjdk.jmh.generators.annotations;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

class APMetadataInfo {

    protected final ProcessingEnvironment processEnv;
    private final Element element;

    public APMetadataInfo(ProcessingEnvironment processEnv, Element element) {
        this.processEnv = processEnv;
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
