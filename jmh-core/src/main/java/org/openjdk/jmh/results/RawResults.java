
package org.openjdk.jmh.results;

public class RawResults {

    public double allOps;
    public double measuredOps;
    public long realTime;
    public long startTime;
    public long stopTime;

    public long getTime() {
        return (realTime > 0) ? realTime : (stopTime - startTime);
    }

}
