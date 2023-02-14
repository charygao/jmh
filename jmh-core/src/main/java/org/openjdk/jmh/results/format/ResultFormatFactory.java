
package org.openjdk.jmh.results.format;

import org.openjdk.jmh.results.RunResult;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

public class ResultFormatFactory {

    private ResultFormatFactory() {}

    /**
     * Get the instance of ResultFormat of given type which writes the result to file
     * @param type result format type
     * @param file target file
     * @return result format
     */
    public static ResultFormat getInstance(final ResultFormatType type, final String file) {
        return results -> {
            try {
                PrintStream pw = new PrintStream(file, "UTF-8");
                ResultFormat rf = getInstance(type, pw);
                rf.writeOut(results);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        };
    }

    /**
     * Get the instance of ResultFormat of given type which write the result to out.
     * It is a user responsibility to initialize and finish the out as appropriate.
     *
     * @param type result format type
     * @param out target out
     * @return result format.
     */
    public static ResultFormat getInstance(ResultFormatType type, PrintStream out) {
        switch (type) {
            case TEXT:
                return new TextResultFormat(out);
            case CSV:
                return new XSVResultFormat(out, ",");
            case SCSV:
                /*
                 *    Since some implementations, notably Excel, think it is a good
                 *    idea to hijack the CSV standard, and use semi-colon instead of
                 *    comma in some locales, this is the specialised
                 *     Semi-Colon Separated Values formatter.
                 */
                return new XSVResultFormat(out, ";");
            case JSON:
                return new JSONResultFormat(out);
            case LATEX:
                return new LaTeXResultFormat(out);
            default:
                throw new IllegalStateException("Unsupported result format: " + type);
        }
    }

}
