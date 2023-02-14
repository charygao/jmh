
package org.openjdk.jmh.runner.link;

import java.io.Serializable;

/**
 * Encapsulates the OutputFormat call
 *   - method name
 *   - arguments (assumed to be serializable)
 */
class OutputFormatFrame implements Serializable {
    private static final long serialVersionUID = -7151852354574635295L;
    public final String method;
    public final Object[] args;

    public OutputFormatFrame(String method, Object[] args) {
        this.method = method;
        this.args = args;
    }
}
