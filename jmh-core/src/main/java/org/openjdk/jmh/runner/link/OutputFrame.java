
package org.openjdk.jmh.runner.link;

import java.io.Serializable;

class OutputFrame implements Serializable {
    private static final long serialVersionUID = 8570795333046092668L;

    private final Type type;
    private final byte[] data;

    public OutputFrame(Type type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        OUT,
        ERR,
    }

}
