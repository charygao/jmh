
package org.openjdk.jmh.runner;

public class NoBenchmarksException extends RunnerException {
    private static final long serialVersionUID = 1446854343206595167L;

    public String toString() {
        return "No benchmarks to run; check the include/exclude regexps.";
    }

}
