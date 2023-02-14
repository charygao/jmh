
package org.openjdk.jmh.results.format;

import org.openjdk.jmh.results.RunResult;

import java.util.Collection;

public interface ResultFormat {

    void writeOut(Collection<RunResult> results);

}
