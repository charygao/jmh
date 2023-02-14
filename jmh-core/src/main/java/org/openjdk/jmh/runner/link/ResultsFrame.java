
package org.openjdk.jmh.runner.link;

import org.openjdk.jmh.results.IterationResult;

import java.io.Serializable;

class ResultsFrame implements Serializable {
    private static final long serialVersionUID = -5627086531281515824L;

    private final IterationResult res;

    public ResultsFrame(IterationResult res) {
        this.res = res;
    }

    public IterationResult getRes() {
        return res;
    }
}
