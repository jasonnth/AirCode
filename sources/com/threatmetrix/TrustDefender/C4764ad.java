package com.threatmetrix.TrustDefender;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.threatmetrix.TrustDefender.ad */
class C4764ad implements C4794e {

    /* renamed from: i */
    private static final String f4181i = C4834w.m2892a(C4764ad.class);

    /* renamed from: a */
    volatile boolean f4182a = false;

    /* renamed from: b */
    volatile boolean f4183b = false;

    /* renamed from: c */
    volatile boolean f4184c = false;

    /* renamed from: d */
    volatile boolean f4185d = false;

    /* renamed from: e */
    volatile boolean f4186e = false;

    /* renamed from: f */
    CountDownLatch f4187f = null;

    /* renamed from: g */
    CountDownLatch f4188g = null;

    /* renamed from: h */
    private final ReentrantReadWriteLock f4189h = new ReentrantReadWriteLock();

    C4764ad() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final boolean mo45951b() {
        this.f4189h.readLock().lock();
        try {
            return this.f4182a;
        } finally {
            this.f4189h.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final boolean mo45953c() {
        this.f4189h.readLock().lock();
        try {
            return this.f4182a && this.f4187f.getCount() == 0;
        } finally {
            this.f4189h.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final boolean mo45954d() {
        this.f4189h.writeLock().lock();
        try {
            if (!this.f4182a) {
                this.f4182a = true;
                this.f4187f = new CountDownLatch(1);
                return true;
            }
            this.f4189h.writeLock().unlock();
            return false;
        } finally {
            this.f4189h.writeLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo45948a(boolean wasSuccessful) {
        this.f4189h.readLock().lock();
        try {
            this.f4182a = wasSuccessful;
            CountDownLatch latch = this.f4187f;
            if (latch != null) {
                latch.countDown();
            }
        } finally {
            this.f4189h.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final boolean mo45955e() {
        CountDownLatch latch = null;
        this.f4189h.readLock().lock();
        try {
            if (this.f4182a) {
                latch = this.f4187f;
            }
            return latch != null && latch.getCount() > 0;
        } finally {
            this.f4189h.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo45950a(int timeout_ms) {
        boolean waitForInit = false;
        this.f4189h.readLock().lock();
        try {
            if (!this.f4182a || this.f4187f == null) {
                C4834w.m2901c(f4181i, "init not in progress, nothing to wait for");
            } else {
                CountDownLatch initLatch = this.f4187f;
                this.f4189h.readLock().unlock();
                C4834w.m2901c(f4181i, "Waiting for init to complete");
                boolean initCompleted = false;
                try {
                    initCompleted = initLatch.await((long) timeout_ms, TimeUnit.MILLISECONDS);
                    if (!initCompleted) {
                        C4834w.m2894a(f4181i, "Timed out waiting for init to complete");
                    }
                } catch (InterruptedException e) {
                    C4834w.m2895a(f4181i, "Waiting for init to complete interrupted", (Throwable) e);
                }
                this.f4189h.readLock().lock();
                try {
                    if (this.f4182a && initCompleted) {
                        waitForInit = true;
                    }
                } finally {
                    this.f4189h.readLock().unlock();
                }
            }
            return waitForInit;
        } finally {
            this.f4189h.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final boolean mo45956g() {
        this.f4189h.writeLock().lock();
        try {
            if (!this.f4183b) {
                this.f4183b = true;
                this.f4184c = false;
                return true;
            }
            this.f4189h.writeLock().unlock();
            return false;
        } finally {
            this.f4189h.writeLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: h */
    public final void mo45957h() {
        this.f4189h.writeLock().lock();
        try {
            this.f4183b = false;
        } finally {
            this.f4189h.writeLock().unlock();
        }
    }

    /* renamed from: a */
    public final boolean mo45949a() {
        this.f4189h.readLock().lock();
        try {
            return this.f4184c;
        } finally {
            this.f4189h.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: k */
    public final boolean mo45958k() {
        C4834w.m2901c(f4181i, "Attempting to cancel doPackageScan");
        this.f4189h.writeLock().lock();
        try {
            if (!this.f4185d) {
                this.f4185d = true;
                return true;
            }
            this.f4189h.writeLock().unlock();
            return false;
        } finally {
            this.f4189h.writeLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final boolean mo45952b(boolean checkProfiling) {
        this.f4189h.writeLock().lock();
        try {
            if (this.f4186e || ((!checkProfiling || this.f4183b) && checkProfiling)) {
                this.f4189h.writeLock().unlock();
                return false;
            } else if (this.f4185d) {
                C4834w.m2901c(f4181i, "startScanning: aborted, marked as cancelled");
                this.f4185d = false;
                return false;
            } else {
                this.f4186e = true;
                this.f4188g = new CountDownLatch(1);
                this.f4189h.writeLock().unlock();
                return true;
            }
        } finally {
            this.f4189h.writeLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: l */
    public final void mo45959l() {
        CountDownLatch latch = null;
        this.f4189h.readLock().lock();
        try {
            if (this.f4186e) {
                this.f4186e = false;
                this.f4185d = false;
                latch = this.f4188g;
            }
            if (latch != null) {
                latch.countDown();
            }
        } finally {
            this.f4189h.readLock().unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    /* renamed from: m */
    public final boolean mo45960m() {
        this.f4189h.readLock().lock();
        try {
            if (!this.f4186e || this.f4188g == null) {
                C4834w.m2901c(f4181i, "waitForScan: No scan in progress, nothing to wait for");
                this.f4189h.readLock().unlock();
                return true;
            }
            CountDownLatch initLatch = this.f4188g;
            this.f4189h.readLock().unlock();
            C4834w.m2901c(f4181i, "waitForScan: Waiting for scan to complete");
            try {
                initLatch.await();
                return true;
            } catch (InterruptedException e) {
                if (!mo45949a()) {
                    C4834w.m2895a(f4181i, "waitForScan: Waiting for scan to complete interrupted", (Throwable) e);
                    return false;
                }
                C4834w.m2901c(f4181i, "waitForScan: interrupted by cancel");
                return false;
            }
        } catch (Throwable th) {
            this.f4189h.readLock().unlock();
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: n */
    public final boolean mo45961n() {
        CountDownLatch latch = null;
        this.f4189h.readLock().lock();
        try {
            if (this.f4186e) {
                latch = this.f4188g;
            }
            return latch != null && latch.getCount() > 0;
        } finally {
            this.f4189h.readLock().unlock();
        }
    }
}
