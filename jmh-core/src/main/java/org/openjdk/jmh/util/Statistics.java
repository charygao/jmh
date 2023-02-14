
package org.openjdk.jmh.util;

import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;


public interface Statistics extends Serializable, StatisticalSummary, Comparable<Statistics> {

    /**
     * Gets the confidence interval at given confidence level.
     * @param confidence confidence level (e.g. 0.95)
     * @return the interval in which mean lies with the given confidence level
     */
    double[] getConfidenceIntervalAt(double confidence);

    /**
     * Gets the mean error at given confidence level.
     * @param confidence confidence level (e.g. 0.95)
     * @return the mean error with the given confidence level
     */
    double getMeanErrorAt(double confidence);

    /**
     * Checks if this statistics statistically different from the given one
     * with the given confidence level.
     * @param other statistics to test against
     * @param confidence confidence level (e.g. 0.95)
     * @return true, if mean difference is statistically significant
     */
    boolean isDifferent(Statistics other, double confidence);

    /**
     * Compares this statistics to another one.
     * Follows the contract of {@link Comparable}.
     *
     * @param other statistics to compare against
     * @return a negative integer, zero, or a positive integer as this statistics
     *          is less than, equal to, or greater than the specified statistics.
     */
    int compareTo(Statistics other);

    /**
     * Compares this statistics to another one.
     * Follows the contract of {@link Comparable}.
     *
     * @param other statistics to compare against
     * @param confidence confidence level (e.g. 0.99)
     * @return a negative integer, zero, or a positive integer as this statistics
     *          is less than, equal to, or greater than the specified statistics.
     */

    int compareTo(Statistics other, double confidence);

    /**
     * Returns the maximum for this statistics.
     * @return maximum
     */
    double getMax();

    /**
     * Returns the minimum for this statistics.
     * @return minimum
     */
    double getMin();

    /**
     * Returns the arithmetic mean for this statistics.
     * @return arithmetic mean
     */
    double getMean();

    /**
     * Returns the number of samples in this statistics.
     * @return number of samples
     */
    long getN();

    /**
     * Returns the sum of samples in this statistics.
     * @return sum
     */
    double getSum();

    /**
     * Returns the standard deviation for this statistics.
     * @return standard deviation
     */
    double getStandardDeviation();

    /**
     * Returns the variance for this statistics.
     * @return variance
     */
    double getVariance();

    /**
     * Returns the percentile at given rank.
     * @param rank the rank, [0..100]
     * @return percentile
     */
    double getPercentile(double rank);

    /**
     * Returns the histogram for this statistics. The histogram bin count would
     * be equal to number of levels, minus one; so that each i-th bin is the
     * number of samples in [i-th, (i+1)-th) levels.
     *
     * @param levels levels
     * @return histogram data
     */
    int[] getHistogram(double[] levels);

    /**
     * Returns the raw data for this statistics. This data can be useful for
     * custom postprocessing and statistics computations.  Note, that values of
     * multiple calls may not be unique. Ordering of the values is not specified.
     *
     * @return iterator to raw data. Each item is pair of actual value and
     *          number of occurrences of this value.
     */
    Iterator<Entry<Double, Long>> getRawData();
}
