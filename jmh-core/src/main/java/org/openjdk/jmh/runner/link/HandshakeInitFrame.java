
package org.openjdk.jmh.runner.link;

import java.io.Serializable;

class HandshakeInitFrame implements Serializable {
    private static final long serialVersionUID = 2082214387637725282L;

    private final long pid;

    public HandshakeInitFrame(long pid) {
        this.pid = pid;
    }

    public long getPid() {
        return pid;
    }
}
