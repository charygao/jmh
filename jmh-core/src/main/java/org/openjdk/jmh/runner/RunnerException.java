
package org.openjdk.jmh.runner;

public class RunnerException extends Exception {
    private static final long serialVersionUID = 7366763183238649668L;

    public RunnerException(Throwable t) {
        super(t);
    }

    public RunnerException() {
        super();
    }

    public RunnerException(String s) {
        super(s);
    }

    public RunnerException(String s, Throwable cause) {
        super(s, cause);
    }

}
