
package org.openjdk.jmh.runner;

import org.openjdk.jmh.runner.format.OutputFormat;
import org.openjdk.jmh.util.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Helper class for listing micro benchmarks.
 */
public class BenchmarkList extends AbstractResourceReader {

    /** Location of the pre-compiled list of micro benchmarks */
    public static final String BENCHMARK_LIST = "/META-INF/BenchmarkList";

    public static BenchmarkList defaultList() {
        return fromResource(BENCHMARK_LIST);
    }

    public static BenchmarkList fromFile(String file) {
        return new BenchmarkList(file, null, null);
    }

    public static BenchmarkList fromResource(String resource) {
        return new BenchmarkList(null, resource, null);
    }

    public static BenchmarkList fromString(String strings) {
        return new BenchmarkList(null, null, strings);
    }

    public static List<BenchmarkListEntry> readBenchmarkList(InputStream stream) throws IOException {
        try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            List<BenchmarkListEntry> entries = new ArrayList<>();
            for (String line : FileUtils.readAllLines(reader)) {
                BenchmarkListEntry ble = new BenchmarkListEntry(line);
                entries.add(ble);
            }
            return entries;
        }
    }

    public static void writeBenchmarkList(OutputStream stream, Collection<BenchmarkListEntry> entries) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8))) {
            List<BenchmarkListEntry> sorted = new ArrayList<>(entries);
            Collections.sort(sorted);
            for (BenchmarkListEntry entry : sorted) {
                writer.println(entry.toLine());
            }
        }
    }

    private BenchmarkList(String file, String resource, String strings) {
        super(file, resource, strings);
    }

    /**
     * Gets all micro benchmarks from the list, sorted.
     *
     * @param out Output the messages here
     * @param excludes List of regexps to match excludes against
     * @return A list of all benchmarks, excluding matched
     */
    public Set<BenchmarkListEntry> getAll(OutputFormat out, List<String> excludes) {
        return find(out, Collections.singletonList(".*"), excludes);
    }

    /**
     * Gets all the micro benchmarks that matches the given regexp, sorted.
     *
     * @param out Output the messages here
     * @param includes  List of regexps to match against
     * @param excludes List of regexps to match excludes against
     * @return Names of all micro benchmarks in the list that matches includes and NOT matching excludes
     */
    public SortedSet<BenchmarkListEntry> find(OutputFormat out, List<String> includes, List<String> excludes) {

        // assume we match all benchmarks when include is empty
        List<String> regexps = new ArrayList<>(includes);
        if (regexps.isEmpty()) {
            regexps.add(Defaults.INCLUDE_BENCHMARKS);
        }

        // compile all patterns
        List<Pattern> includePatterns = new ArrayList<>(regexps.size());
        for (String regexp : regexps) {
            includePatterns.add(Pattern.compile(regexp));
        }
        List<Pattern> excludePatterns = new ArrayList<>(excludes.size());
        for (String regexp : excludes) {
            excludePatterns.add(Pattern.compile(regexp));
        }

        // find all benchmarks containing pattern
        SortedSet<BenchmarkListEntry> result = new TreeSet<>();
        try {
            for (Reader r : getReaders()) {
                try (BufferedReader reader = new BufferedReader(r)) {
                    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                        if (line.startsWith("#")) {
                            continue;
                        }

                        if (line.trim().isEmpty()) {
                            continue;
                        }

                        BenchmarkListEntry br = new BenchmarkListEntry(line);

                        for (Pattern pattern : includePatterns) {
                            if (pattern.matcher(br.getUsername()).find()) {
                                boolean exclude = false;

                                // excludes override
                                for (Pattern excludePattern : excludePatterns) {
                                    if (excludePattern.matcher(br.getUsername()).find()) {
                                        out.verbosePrintln("Excluding " + br.getUsername() + ", matches " + excludePattern);

                                        exclude = true;
                                        break;
                                    }
                                }

                                if (!exclude) {
                                    result.add(br);
                                }
                                break;
                            } else {
                                out.verbosePrintln("Excluding: " + br.getUsername() + ", does not match " + pattern);
                            }
                        }
                    }
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException("Error reading benchmark list", ex);
        }

        return result;
    }

}
