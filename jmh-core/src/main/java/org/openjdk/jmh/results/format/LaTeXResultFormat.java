
package org.openjdk.jmh.results.format;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.util.ClassUtils;
import org.openjdk.jmh.util.ScoreFormatter;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

class LaTeXResultFormat implements ResultFormat {

    private final PrintStream out;

    public LaTeXResultFormat(PrintStream out) {
        this.out = out;
    }

    @Override
    public void writeOut(Collection<RunResult> results) {
        SortedSet<String> params = new TreeSet<>();
        Set<String> benchNames = new HashSet<>();

        Set<String> units = new HashSet<>();
        for (RunResult rr : results) {
            String benchmark = rr.getParams().getBenchmark();
            benchNames.add(benchmark);
            params.addAll(rr.getParams().getParamsKeys());
            units.add(rr.getPrimaryResult().getScoreUnit());
            Map<String, Result> secondaries = rr.getSecondaryResults();
            for (String label : secondaries.keySet()) {
                benchNames.add(benchmark + ":" + label);
                units.add(secondaries.get(label).getScoreUnit());
            }
        }

        boolean singleUnit = (units.size() == 1);
        String unit = singleUnit ? units.iterator().next() : null;

        Map<String, String> prefixes = ClassUtils.denseClassNames(benchNames);

        printHeader(params, singleUnit, unit);

        for (RunResult rr : results) {
            BenchmarkParams bp = rr.getParams();
            String benchmark = bp.getBenchmark();
            Result res = rr.getPrimaryResult();

            printLine(benchmark, bp, params, prefixes, singleUnit, res);

            Map<String, Result> secondaries = rr.getSecondaryResults();
            for (String label : secondaries.keySet()) {
                Result subRes = secondaries.get(label);
                printLine(benchmark + ":" + label, bp, params, prefixes, singleUnit, subRes);
            }
        }

        printFooter();
    }

    private void printHeader(SortedSet<String> params, boolean singleUnit, String unit) {
        out.print("\\begin{tabular}{r|");
        for (String p : params) {
            out.print("l|");
        }
        out.print("rl" + (singleUnit ? "" : "l") + "}\n");
        out.print(" \\multicolumn{1}{c|}{\\texttt{Benchmark}} & ");
        for (String p : params) {
            out.printf("\\texttt{%s} & ", p);
        }
        out.print("\\multicolumn{" + (singleUnit ? 2 : 3) + "}{c}{\\texttt{Score" + (singleUnit ? ", " + unit : "") + "}} \\\\\n");
        out.print("\\hline\n");
    }

    private void printFooter() {
        out.print("\\end{tabular}");
    }

    private void printLine(String label, BenchmarkParams benchParams, SortedSet<String> params,
                           Map<String, String> prefixes, boolean singleUnit, Result res) {
        out.printf("\\texttt{%s} & ", escape(prefixes.get(label)));
        for (String p : params) {
            out.printf("\\texttt{%s} & ", escape(benchParams.getParam(p)));
        }
        out.printf("\\texttt{%s} & ", ScoreFormatter.formatLatex(res.getScore()));

        if (!Double.isNaN(res.getScoreError()) && !ScoreFormatter.isApproximate(res.getScore())) {
            out.printf("\\scriptsize $\\pm$ \\texttt{%s} ", ScoreFormatter.formatError(res.getScoreError()));
        }

        if (!singleUnit) {
            out.print("& ");
            out.printf("\\texttt{%s}", escape(res.getScoreUnit()));
        }
        out.print(" \\\\\n");
    }

    private static String escape(String s) {
        return s.replaceAll("_", "\\\\_")
                .replaceAll("#", "\\\\#")
                .replaceAll("\\{", "\\\\{")
                .replaceAll("\\}", "\\\\}");
    }

}
