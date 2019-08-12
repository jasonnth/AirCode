package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class InterruptibleTask implements Runnable {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log = Logger.getLogger(InterruptibleTask.class.getName());
    private volatile boolean doneInterrupting;
    /* access modifiers changed from: private */
    public volatile Thread runner;

    private static abstract class AtomicHelper {
        /* access modifiers changed from: 0000 */
        public abstract boolean compareAndSetRunner(InterruptibleTask interruptibleTask, Thread thread, Thread thread2);

        private AtomicHelper() {
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<InterruptibleTask, Thread> runnerUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater runnerUpdater2) {
            super();
            this.runnerUpdater = runnerUpdater2;
        }

        /* access modifiers changed from: 0000 */
        public boolean compareAndSetRunner(InterruptibleTask task, Thread expect, Thread update) {
            return this.runnerUpdater.compareAndSet(task, expect, update);
        }
    }

    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        /* access modifiers changed from: 0000 */
        public boolean compareAndSetRunner(InterruptibleTask task, Thread expect, Thread update) {
            synchronized (task) {
                if (task.runner == expect) {
                    task.runner = update;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void runInterruptibly();

    /* access modifiers changed from: 0000 */
    public abstract boolean wasInterrupted();

    InterruptibleTask() {
    }

    static {
        AtomicHelper helper;
        try {
            helper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(InterruptibleTask.class, Thread.class, "runner"));
        } catch (Throwable reflectionFailure) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", reflectionFailure);
            helper = new SynchronizedAtomicHelper();
        }
        ATOMIC_HELPER = helper;
    }

    public final void run() {
        if (ATOMIC_HELPER.compareAndSetRunner(this, null, Thread.currentThread())) {
            try {
                runInterruptibly();
            } finally {
                if (wasInterrupted()) {
                    while (!this.doneInterrupting) {
                        Thread.yield();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void interruptTask() {
        Thread currentRunner = this.runner;
        if (currentRunner != null) {
            currentRunner.interrupt();
        }
        this.doneInterrupting = true;
    }
}
