
package org.openjdk.jmh.infra;

import org.openjdk.jmh.runner.IterationType;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.util.Utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Iteration parameters.
 *
 * <p>Iteration parameters are separated in at least two instances, with different {@link IterationType}-s.
 * The complete benchmark parameters not specific for a particular iteration are available in
 * {@link org.openjdk.jmh.infra.BenchmarkParams}.</p>
 * <p>This class is dual-purpose:</p>
 * <ol>
 *     <li>It acts as the interface between host JVM and forked JVM, so that the latter
 *     would not have to figure out the benchmark configuration again</li>
 *     <li>It can be injected into benchmark methods to access the runtime configuration
 *     info about the benchmark</li>
 * </ol>
 */
public final class IterationParams extends IterationParamsL4 {
    private static final long serialVersionUID = -8111111319033802892L;

    static {
        Utils.check(IterationParams.class, "type", "count", "timeValue", "batchSize");
    }

    public IterationParams(IterationType type, int count, TimeValue time, int batchSize) {
        super(type, count, time, batchSize);
    }
}

abstract class IterationParamsL4 extends IterationParamsL3 {
    private static final long serialVersionUID = 9079354621906758255L;

    private int markerEnd;
    public IterationParamsL4(IterationType type, int count, TimeValue time, int batchSize) {
        super(type, count, time, batchSize);
    }
}

abstract class IterationParamsL3 extends IterationParamsL2 {
    private static final long serialVersionUID = 3907464940104879178L;

    private boolean q001, q002, q003, q004, q005, q006, q007, q008;
    private boolean q011, q012, q013, q014, q015, q016, q017, q018;
    private boolean q021, q022, q023, q024, q025, q026, q027, q028;
    private boolean q031, q032, q033, q034, q035, q036, q037, q038;
    private boolean q041, q042, q043, q044, q045, q046, q047, q048;
    private boolean q051, q052, q053, q054, q055, q056, q057, q058;
    private boolean q061, q062, q063, q064, q065, q066, q067, q068;
    private boolean q071, q072, q073, q074, q075, q076, q077, q078;
    private boolean q101, q102, q103, q104, q105, q106, q107, q108;
    private boolean q111, q112, q113, q114, q115, q116, q117, q118;
    private boolean q121, q122, q123, q124, q125, q126, q127, q128;
    private boolean q131, q132, q133, q134, q135, q136, q137, q138;
    private boolean q141, q142, q143, q144, q145, q146, q147, q148;
    private boolean q151, q152, q153, q154, q155, q156, q157, q158;
    private boolean q161, q162, q163, q164, q165, q166, q167, q168;
    private boolean q171, q172, q173, q174, q175, q176, q177, q178;

    public IterationParamsL3(IterationType type, int count, TimeValue time, int batchSize) {
        super(type, count, time, batchSize);
    }
}

abstract class IterationParamsL1 extends IterationParamsL0 {
    private boolean p001, p002, p003, p004, p005, p006, p007, p008;
    private boolean p011, p012, p013, p014, p015, p016, p017, p018;
    private boolean p021, p022, p023, p024, p025, p026, p027, p028;
    private boolean p031, p032, p033, p034, p035, p036, p037, p038;
    private boolean p041, p042, p043, p044, p045, p046, p047, p048;
    private boolean p051, p052, p053, p054, p055, p056, p057, p058;
    private boolean p061, p062, p063, p064, p065, p066, p067, p068;
    private boolean p071, p072, p073, p074, p075, p076, p077, p078;
    private boolean p101, p102, p103, p104, p105, p106, p107, p108;
    private boolean p111, p112, p113, p114, p115, p116, p117, p118;
    private boolean p121, p122, p123, p124, p125, p126, p127, p128;
    private boolean p131, p132, p133, p134, p135, p136, p137, p138;
    private boolean p141, p142, p143, p144, p145, p146, p147, p148;
    private boolean p151, p152, p153, p154, p155, p156, p157, p158;
    private boolean p161, p162, p163, p164, p165, p166, p167, p168;
    private boolean p171, p172, p173, p174, p175, p176, p177, p178;
}

abstract class IterationParamsL0 {
    private int markerBegin;
}

abstract class IterationParamsL2 extends IterationParamsL1 implements Serializable {
    private static final long serialVersionUID = -6138850517953881052L;

    /**
     * iteration type
     */
    protected final IterationType type;

    /**
     * amount of iterations
     */
    protected final int count;

    /**
     * iteration runtime
     */
    protected final TimeValue timeValue;

    /**
     * batch size (method invocations inside the single op)
     */
    protected final int batchSize;

    public IterationParamsL2(IterationType type, int count, TimeValue time, int batchSize) {
        this.type = type;
        this.count = count;
        this.timeValue = time;
        this.batchSize = batchSize;
    }

    /**
     * Iteration type: separates warmup iterations vs. measurement iterations.
     * @return iteration type.
     */
    public IterationType getType() {
        return type;
    }

    /**
     * Number of iterations.
     * @return number of iterations of given type.
     */
    public int getCount() {
        return count;
    }

    /**
     * Time for iteration.
     * @return time
     */
    public TimeValue getTime() {
        return timeValue;
    }

    /**
     * Batch size for iteration.
     * @return batch size
     */
    public int getBatchSize() {
        return batchSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IterationParams that = (IterationParams) o;

        if (count != that.count) return false;
        if (batchSize != that.batchSize) return false;
        if (!Objects.equals(timeValue, that.timeValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + batchSize;
        result = 31 * result + (timeValue != null ? timeValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IterationParams("+ getCount()+", "+ getTime()+", "+ getBatchSize()+")";
    }

}
