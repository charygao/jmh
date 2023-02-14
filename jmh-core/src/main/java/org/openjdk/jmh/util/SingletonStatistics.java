
package org.openjdk.jmh.util;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Calculate statistics with just a single value.
 */
public class SingletonStatistics extends AbstractStatistics {

    private static final long serialVersionUID = -90642978235578197L;

    private final double value;

    public SingletonStatistics(double value) {
        this.value = value;
    }

    @Override
    public double getMax() {
        return value;
    }

    @Override
    public double getMin() {
        return value;
    }

    @Override
    public long getN() {
        return 1;
    }

    @Override
    public double getSum() {
        return value;
    }

    @Override
    public double getPercentile(double rank) {
        return value;
    }

    @Override
    public double getVariance() {
        return Double.NaN;
    }

    @Override
    public int[] getHistogram(double[] levels) {
        int[] result = new int[levels.length - 1];
        for (int c = 0; c < levels.length - 1; c++) {
            if (levels[c] <= value && value < levels[c + 1]) {
                result[c] = 1;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<Map.Entry<Double, Long>> getRawData() {
        return new SingletonStatisticsIterator();
    }

    private class SingletonStatisticsIterator implements Iterator<Map.Entry<Double, Long>> {
        private boolean entryReturned = false;

        @Override
        public boolean hasNext() {
            return !entryReturned;
        }

        @Override
        public Map.Entry<Double, Long> next() {
            if (entryReturned) {
                throw new NoSuchElementException("No more elements.");
            }
            entryReturned = true;
            return new AbstractMap.SimpleImmutableEntry<>(value, 1L);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Element cannot be removed.");
        }
    }
}
