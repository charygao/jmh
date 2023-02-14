
package org.openjdk.jmh.util;

import java.io.Serializable;

/**
 * Sampling buffer accepts samples.
 */
public class SampleBuffer implements Serializable {
    private static final long serialVersionUID = 6124923853916845327L;

    private static final int PRECISION_BITS = 10;
    private static final int BUCKETS = Long.SIZE - PRECISION_BITS;

    private final int[][] hdr;

    public SampleBuffer() {
        hdr = new int[BUCKETS][];
    }

    public void half() {
        for (int[] bucket : hdr) {
            if (bucket != null) {
                for (int j = 0; j < bucket.length; j++) {
                    int nV = bucket[j] / 2;
                    if (nV != 0) { // prevent halving to zero
                        bucket[j] = nV;
                    }
                }
            }
        }
    }

    public void add(long sample) {
        int bucket = Math.max(0, BUCKETS - Long.numberOfLeadingZeros(sample));
        int subBucket = (int) (sample >> bucket);

        int[] b = hdr[bucket];
        if (b == null) {
            b = new int[1 << PRECISION_BITS];
            hdr[bucket] = b;
        }
        b[subBucket]++;
    }

    public Statistics getStatistics(double multiplier) {
        MultisetStatistics stat = new MultisetStatistics();
        for (int i = 0; i < hdr.length; i++) {
            int[] bucket = hdr[i];
            if (bucket != null) {
                for (int j = 0; j < bucket.length; j++) {
                    long ns = (long) j << i;
                    stat.addValue(multiplier * ns, bucket[j]);
                }
            }
        }
        return stat;
    }

    public void addAll(SampleBuffer other) {
        for (int i = 0; i < other.hdr.length; i++) {
            int[] otherBucket = other.hdr[i];
            if (otherBucket != null) {
                int[] myBucket = hdr[i];
                if (myBucket == null) {
                    myBucket = new int[1 << PRECISION_BITS];
                    hdr[i] = myBucket;
                }
                for (int j = 0; j < otherBucket.length; j++) {
                    myBucket[j] += otherBucket[j];
                }
            }
        }
    }

    public int count() {
        int count = 0;
        for (int[] bucket : hdr) {
            if (bucket != null) {
                for (int v : bucket) {
                    count += v;
                }
            }
        }
        return count;
    }
}
