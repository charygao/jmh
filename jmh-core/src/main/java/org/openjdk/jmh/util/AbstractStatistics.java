
package org.openjdk.jmh.util;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.inference.TestUtils;

public abstract class AbstractStatistics implements Statistics {
    private static final long serialVersionUID = 1536835581997509117L;

    /**
     * Returns the interval c1, c2 of which there's an 1-alpha
     * probability of the mean being within the interval.
     *
     * @param confidence level
     * @return the confidence interval
     */
    @Override
    public double[] getConfidenceIntervalAt(double confidence) {
        double[] interval = new double[2];

        if (getN() <= 2) {
            interval[0] = interval[1] = Double.NaN;
            return interval;
        }

        TDistribution tDist = new TDistribution(getN() - 1);
        double a = tDist.inverseCumulativeProbability(1 - (1 - confidence) / 2);
        interval[0] = getMean() - a * getStandardDeviation() / Math.sqrt(getN());
        interval[1] = getMean() + a * getStandardDeviation() / Math.sqrt(getN());

        return interval;
    }

    @Override
    public boolean isDifferent(Statistics other, double confidence) {
        return TestUtils.tTest(this, other, 1 - confidence);
    }

    @Override
    public double getMeanErrorAt(double confidence) {
        if (getN() <= 2) return Double.NaN;
        TDistribution tDist = new TDistribution(getN() - 1);
        double a = tDist.inverseCumulativeProbability(1 - (1 - confidence) / 2);
        return a * getStandardDeviation() / Math.sqrt(getN());
    }

    @Override
    public String toString() {
        return "N:" + getN() + " Mean: " + getMean()
                + " Min: " + getMin() + " Max: " + getMax()
                + " StdDev: " + getStandardDeviation();
    }

    @Override
    public double getMean() {
        if (getN() > 0) {
            return getSum() / getN();
        } else {
            return Double.NaN;
        }
    }

    @Override
    public double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }

    @Override
    public int compareTo(Statistics other, double confidence) {
        if (isDifferent(other, confidence)) {
            return Double.compare(getMean(), other.getMean());
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Statistics other) {
        return compareTo(other, 0.99);
    }
}
