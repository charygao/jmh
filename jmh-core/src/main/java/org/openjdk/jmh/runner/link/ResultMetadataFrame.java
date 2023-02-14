
package org.openjdk.jmh.runner.link;

import org.openjdk.jmh.results.BenchmarkResultMetaData;

import java.io.Serializable;

class ResultMetadataFrame implements Serializable {
    private static final long serialVersionUID = -5627086531281515824L;

    private final BenchmarkResultMetaData md;

    public ResultMetadataFrame(BenchmarkResultMetaData md) {
        this.md = md;
    }

    public BenchmarkResultMetaData getMD() {
        return md;
    }
}
