
package org.openjdk.jmh.runner.options;

import joptsimple.HelpFormatter;
import joptsimple.OptionDescriptor;
import org.openjdk.jmh.util.Utils;

import java.util.List;
import java.util.Map;

public class OptionFormatter implements HelpFormatter {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public String format(Map<String, ? extends OptionDescriptor> options) {
        StringBuilder sb = new StringBuilder();
        sb.append("Usage: java -jar ... [regexp*] [options]");
        sb.append("\n");
        sb.append(" [opt] means optional argument.\n");
        sb.append(" <opt> means required argument.\n");
        sb.append(" \"+\" means comma-separated list of values.\n");
        sb.append(" \"time\" arguments accept time suffixes, like \"100ms\".\n");
        sb.append("\n");
        sb.append("Command line options usually take precedence over annotations.");
        sb.append("\n");
        sb.append("\n");
        for (OptionDescriptor each : options.values()) {
            sb.append(lineFor(each));
        }

        return sb.toString();
    }

    private String lineFor(OptionDescriptor d) {
        StringBuilder line = new StringBuilder();

        StringBuilder o = new StringBuilder();
        o.append("  ");
        for (String str : d.options()) {
            if (!d.representsNonOptions()) {
                o.append("-");
            }
            o.append(str);
            if (d.acceptsArguments()) {
                o.append(" ");
                if (d.requiresArgument()) {
                    o.append("<");
                } else {
                    o.append("[");
                }
                o.append(d.argumentDescription());
                if (d.requiresArgument()) {
                    o.append(">");
                } else {
                    o.append("]");
                }
            }
        }

        final int optWidth = 30;

        line.append(String.format("%-" + optWidth + "s", o.toString()));
        boolean first = true;
        String desc = d.description();
        List<?> defaults = d.defaultValues();
        if (defaults != null && !defaults.isEmpty()) {
            desc += " (default: " + defaults.toString() + ")";
        }
        for (String l : Utils.rewrap(desc)) {
            if (first) {
                first = false;
            } else {
                line.append(LINE_SEPARATOR);
                line.append(String.format("%-" + optWidth + "s", ""));
            }
            line.append(l);
        }

        line.append(LINE_SEPARATOR);
        line.append(LINE_SEPARATOR);
        return line.toString();
    }

}
