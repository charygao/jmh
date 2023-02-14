
package org.openjdk.jmh.runner.format;

import org.openjdk.jmh.runner.options.VerboseMode;

import java.io.PrintStream;

public class OutputFormatFactory {

    /**
     * Factory method for OutputFormat instances
     *
     * @param out  output stream to use
     * @param mode how much verbosity to use
     * @return a new OutputFormat instance of given type
     */
    public static OutputFormat createFormatInstance(PrintStream out, VerboseMode mode) {
        switch (mode) {
            case SILENT:
                return new SilentFormat(out, mode);
            case NORMAL:
            case EXTRA:
                return new TextReportFormat(out, mode);
            default:
                throw new IllegalArgumentException("Mode " + mode + " not found!");
        }
    }

}
