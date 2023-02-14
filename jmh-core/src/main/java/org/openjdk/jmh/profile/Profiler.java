
package org.openjdk.jmh.profile;

/**
 * Root profiler interface.
 *
 * <p>Profiler classes are expected to provide either a non-arg constructor,
 * or a constructor accepting single String argument, as the option line.
 * The treatment of option line is unspecified, and can be handled in
 * profiler-specific way. Profiler constructors can throw
 * {@link org.openjdk.jmh.profile.ProfilerException} if profiler cannot
 * operate, either because of misconfiguration, or help message requested.
 * The message in {@link org.openjdk.jmh.profile.ProfilerException} should
 * clearly articulate the reason.
 *
 * <p>JMH will try to discover profiler implementations using the SPI mechanism.
 * Note: discoverable implementations <em>must</em> provide a no-arg constructor
 * for initial discovery; the instance created during discovery will be rejected.
 * If implementation would have a constructor accepting the String option line,
 * it would be preferred for subsequent instantiation over the no-arg constructor.
 *
 * <p>Profilers normally implement one of the subinterfaces.</p>
 * @see org.openjdk.jmh.profile.ExternalProfiler
 * @see org.openjdk.jmh.profile.InternalProfiler
 */
public interface Profiler {

    /**
     * Human-readable one-line description of the profiler.
     * @return description
     */
    String getDescription();

}
