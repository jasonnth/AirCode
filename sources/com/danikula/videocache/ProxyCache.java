package com.danikula.videocache;

import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProxyCache {
    private static final Logger LOG = LoggerFactory.getLogger("ProxyCache");
    private final Cache cache;
    private volatile int percentsAvailable = -1;
    private final AtomicInteger readSourceErrorsCount;
    private final Source source;
    private volatile Thread sourceReaderThread;
    private final Object stopLock = new Object();
    private volatile boolean stopped;

    /* renamed from: wc */
    private final Object f3017wc = new Object();

    private class SourceReaderRunnable implements Runnable {
        private SourceReaderRunnable() {
        }

        public void run() {
            ProxyCache.this.readSource();
        }
    }

    public ProxyCache(Source source2, Cache cache2) {
        this.source = (Source) Preconditions.checkNotNull(source2);
        this.cache = (Cache) Preconditions.checkNotNull(cache2);
        this.readSourceErrorsCount = new AtomicInteger();
    }

    public int read(byte[] buffer, long offset, int length) throws ProxyCacheException {
        ProxyCacheUtils.assertBuffer(buffer, offset, length);
        while (!this.cache.isCompleted() && this.cache.available() < ((long) length) + offset && !this.stopped) {
            readSourceAsync();
            waitForSourceData();
            checkReadSourceErrorsCount();
        }
        int read = this.cache.read(buffer, offset, length);
        if (this.cache.isCompleted() && this.percentsAvailable != 100) {
            this.percentsAvailable = 100;
            onCachePercentsAvailableChanged(100);
        }
        return read;
    }

    private void checkReadSourceErrorsCount() throws ProxyCacheException {
        int errorsCount = this.readSourceErrorsCount.get();
        if (errorsCount >= 1) {
            this.readSourceErrorsCount.set(0);
            throw new ProxyCacheException("Error reading source " + errorsCount + " times");
        }
    }

    public void shutdown() {
        synchronized (this.stopLock) {
            LOG.debug("Shutdown proxy for " + this.source);
            try {
                this.stopped = true;
                if (this.sourceReaderThread != null) {
                    this.sourceReaderThread.interrupt();
                }
                this.cache.close();
            } catch (ProxyCacheException e) {
                onError(e);
            }
        }
    }

    private synchronized void readSourceAsync() throws ProxyCacheException {
        boolean readingInProgress = (this.sourceReaderThread == null || this.sourceReaderThread.getState() == State.TERMINATED) ? false : true;
        if (!this.stopped && !this.cache.isCompleted() && !readingInProgress) {
            this.sourceReaderThread = new Thread(new SourceReaderRunnable(), "Source reader for " + this.source);
            this.sourceReaderThread.start();
        }
    }

    private void waitForSourceData() throws ProxyCacheException {
        synchronized (this.f3017wc) {
            try {
                this.f3017wc.wait(1000);
            } catch (InterruptedException e) {
                throw new ProxyCacheException("Waiting source data is interrupted!", e);
            }
        }
    }

    private void notifyNewCacheDataAvailable(long cacheAvailable, long sourceAvailable) {
        onCacheAvailable(cacheAvailable, sourceAvailable);
        synchronized (this.f3017wc) {
            this.f3017wc.notifyAll();
        }
    }

    /* access modifiers changed from: protected */
    public void onCacheAvailable(long cacheAvailable, long sourceLength) {
        boolean zeroLengthSource;
        boolean percentsChanged;
        boolean sourceLengthKnown = true;
        if (sourceLength == 0) {
            zeroLengthSource = true;
        } else {
            zeroLengthSource = false;
        }
        int percents = zeroLengthSource ? 100 : (int) ((((float) cacheAvailable) / ((float) sourceLength)) * 100.0f);
        if (percents != this.percentsAvailable) {
            percentsChanged = true;
        } else {
            percentsChanged = false;
        }
        if (sourceLength < 0) {
            sourceLengthKnown = false;
        }
        if (sourceLengthKnown && percentsChanged) {
            onCachePercentsAvailableChanged(percents);
        }
        this.percentsAvailable = percents;
    }

    /* access modifiers changed from: protected */
    public void onCachePercentsAvailableChanged(int percentsAvailable2) {
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        r2 = r2 + ((long) r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readSource() {
        /*
            r10 = this;
            r6 = -1
            r2 = 0
            com.danikula.videocache.Cache r5 = r10.cache     // Catch:{ Throwable -> 0x003f }
            long r2 = r5.available()     // Catch:{ Throwable -> 0x003f }
            com.danikula.videocache.Source r5 = r10.source     // Catch:{ Throwable -> 0x003f }
            r5.open(r2)     // Catch:{ Throwable -> 0x003f }
            com.danikula.videocache.Source r5 = r10.source     // Catch:{ Throwable -> 0x003f }
            long r6 = r5.length()     // Catch:{ Throwable -> 0x003f }
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r5]     // Catch:{ Throwable -> 0x003f }
        L_0x0019:
            com.danikula.videocache.Source r5 = r10.source     // Catch:{ Throwable -> 0x003f }
            int r4 = r5.read(r0)     // Catch:{ Throwable -> 0x003f }
            r5 = -1
            if (r4 == r5) goto L_0x005a
            java.lang.Object r8 = r10.stopLock     // Catch:{ Throwable -> 0x003f }
            monitor-enter(r8)     // Catch:{ Throwable -> 0x003f }
            boolean r5 = r10.isStopped()     // Catch:{ all -> 0x004f }
            if (r5 == 0) goto L_0x0033
            monitor-exit(r8)     // Catch:{ all -> 0x004f }
            r10.closeSource()
            r10.notifyNewCacheDataAvailable(r2, r6)
        L_0x0032:
            return
        L_0x0033:
            com.danikula.videocache.Cache r5 = r10.cache     // Catch:{ all -> 0x004f }
            r5.append(r0, r4)     // Catch:{ all -> 0x004f }
            monitor-exit(r8)     // Catch:{ all -> 0x004f }
            long r8 = (long) r4
            long r2 = r2 + r8
            r10.notifyNewCacheDataAvailable(r2, r6)     // Catch:{ Throwable -> 0x003f }
            goto L_0x0019
        L_0x003f:
            r1 = move-exception
            java.util.concurrent.atomic.AtomicInteger r5 = r10.readSourceErrorsCount     // Catch:{ all -> 0x0052 }
            r5.incrementAndGet()     // Catch:{ all -> 0x0052 }
            r10.onError(r1)     // Catch:{ all -> 0x0052 }
            r10.closeSource()
            r10.notifyNewCacheDataAvailable(r2, r6)
            goto L_0x0032
        L_0x004f:
            r5 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x004f }
            throw r5     // Catch:{ Throwable -> 0x003f }
        L_0x0052:
            r5 = move-exception
            r10.closeSource()
            r10.notifyNewCacheDataAvailable(r2, r6)
            throw r5
        L_0x005a:
            r10.tryComplete()     // Catch:{ Throwable -> 0x003f }
            r10.onSourceRead()     // Catch:{ Throwable -> 0x003f }
            r10.closeSource()
            r10.notifyNewCacheDataAvailable(r2, r6)
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.ProxyCache.readSource():void");
    }

    private void onSourceRead() {
        this.percentsAvailable = 100;
        onCachePercentsAvailableChanged(this.percentsAvailable);
    }

    private void tryComplete() throws ProxyCacheException {
        synchronized (this.stopLock) {
            if (!isStopped() && this.cache.available() == this.source.length()) {
                this.cache.complete();
            }
        }
    }

    private boolean isStopped() {
        return Thread.currentThread().isInterrupted() || this.stopped;
    }

    private void closeSource() {
        try {
            this.source.close();
        } catch (ProxyCacheException e) {
            onError(new ProxyCacheException("Error closing source " + this.source, e));
        }
    }

    /* access modifiers changed from: protected */
    public final void onError(Throwable e) {
        if (e instanceof InterruptedProxyCacheException) {
            LOG.debug("ProxyCache is interrupted");
        } else {
            LOG.error("ProxyCache error", e);
        }
    }
}
