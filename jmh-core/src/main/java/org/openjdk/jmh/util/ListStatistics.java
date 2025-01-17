
package org.openjdk.jmh.util;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Calculate statistics over a list of doubles.
 */
public class ListStatistics extends AbstractStatistics {

    private static final long serialVersionUID = -90642978235578197L;

    private double[] values;
    private int count;

    public ListStatistics() {
        values = new double[0];
        count = 0;
    }

    public ListStatistics(double[] samples) {
        this();
        for (double d : samples) {
            addValue(d);
        }
    }

    public ListStatistics(long[] samples) {
        this();
        for (long l : samples) {
            addValue((double) l);
        }
    }

    public void addValue(double d) {
        if (count >= values.length) {
            values = Arrays.copyOf(values, Math.max(1, values.length << 1));
        }
        values[count] = d;
        count++;
    }

    @Override
    public double getMax() {
        if (count > 0) {
            double m = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < count; i++) {
                m = Math.max(m, values[i]);
            }
            return m;
        } else {
            return Double.NaN;
        }
    }

    @Override
    public double getMin() {
        if (count > 0) {
            double m = Double.POSITIVE_INFINITY;
            for (int i = 0; i < count; i++) {
                m = Math.min(m, values[i]);
            }
            return m;
        } else {
            return Double.NaN;
        }
    }

    @Override
    public long getN() {
        return count;
    }

    @Override
    public double getSum() {
        if (count > 0) {
            double s = 0;
            for (int i = 0; i < count; i++) {
                s += values[i];
            }
            return s;
        } else {
            return Double.NaN;
        }
    }

    @Override
    public double getPercentile(double rank) {
        if (count == 0) {
            return Double.NaN;
        }

        if (rank == 0) {
            return getMin();
        }

        // trim
        values = Arrays.copyOf(values, count);

        Percentile p = new Percentile();
        return p.evaluate(values, rank);
    }

    @Override
    public int[] getHistogram(double[] levels) {
        if (levels.length < 2) {
            throw new IllegalArgumentException("Expected more than two levels");
        }

        double[] vs = Arrays.copyOf(values, count);
        Arrays.sort(vs);

        int[] result = new int[levels.length - 1];

        int c = 0;
        values: for (double v : vs) {
            while (levels[c] > v || v >= levels[c + 1]) {
                c++;
                if (c > levels.length - 2) break values;
            }
            result[c]++;
        }

        return result;
    }

    @Override
    public Iterator<Map.Entry<Double, Long>> getRawData() {
        return new ListStatisticsIterator();
    }

    @Override
    public double getVariance() {
        if (count > 1) {
            double v = 0;
            double m = getMean();
            for (int i = 0; i < count; i++) {
                v += Math.pow(values[i] - m, 2);
            }
            return v / (count - 1);
        } else {
            return Double.NaN;
        }
    }

    private class ListStatisticsIterator implements Iterator<Map.Entry<Double, Long>> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < count;
        }

        @Override
        public Map.Entry<Double, Long> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements.");
            }
            return new AbstractMap.SimpleImmutableEntry<>(values[currentIndex++], 1L);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Element cannot be removed.");
        }
    }

}
