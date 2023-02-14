
package org.openjdk.jmh.runner.link;

import java.io.Serializable;

class InfraFrame implements Serializable {
    private static final long serialVersionUID = 6341776120773421805L;

    private final Type type;

    public InfraFrame(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        ACTION_PLAN_REQUEST,
    }
}
