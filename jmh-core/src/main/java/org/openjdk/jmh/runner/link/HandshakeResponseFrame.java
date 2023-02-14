
package org.openjdk.jmh.runner.link;

import org.openjdk.jmh.runner.options.Options;

import java.io.Serializable;

class HandshakeResponseFrame implements Serializable {
    private static final long serialVersionUID = 2082214387637725282L;

    private final Options opts;

    public HandshakeResponseFrame(Options opts) {
        this.opts = opts;
    }

    public Options getOpts() {
        return opts;
    }
}
