
package org.openjdk.jmh.runner;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class WorkerThreadFactory implements ThreadFactory {

    private final AtomicInteger counter;
    private final String prefix;
    private final ThreadFactory factory;

    public WorkerThreadFactory(String prefix) {
        this.counter = new AtomicInteger();
        this.prefix = prefix;
        this.factory = Executors.defaultThreadFactory();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = factory.newThread(r);
        thread.setName(prefix + "-jmh-worker-" + counter.incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }
}
