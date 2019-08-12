package p032rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p032rx.Subscription;
import p032rx.exceptions.Exceptions;

/* renamed from: rx.subscriptions.CompositeSubscription */
public final class CompositeSubscription implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public CompositeSubscription() {
    }

    public CompositeSubscription(Subscription... subscriptions2) {
        this.subscriptions = new HashSet(Arrays.asList(subscriptions2));
    }

    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription s) {
        if (!s.isUnsubscribed()) {
            if (!this.unsubscribed) {
                synchronized (this) {
                    if (!this.unsubscribed) {
                        if (this.subscriptions == null) {
                            this.subscriptions = new HashSet(4);
                        }
                        this.subscriptions.add(s);
                        return;
                    }
                }
            }
            s.unsubscribe();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r3.unsubscribe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(p032rx.Subscription r3) {
        /*
            r2 = this;
            boolean r1 = r2.unsubscribed
            if (r1 != 0) goto L_0x000e
            monitor-enter(r2)
            boolean r1 = r2.unsubscribed     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x000d
            java.util.Set<rx.Subscription> r1 = r2.subscriptions     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x000f
        L_0x000d:
            monitor-exit(r2)     // Catch:{ all -> 0x001c }
        L_0x000e:
            return
        L_0x000f:
            java.util.Set<rx.Subscription> r1 = r2.subscriptions     // Catch:{ all -> 0x001c }
            boolean r0 = r1.remove(r3)     // Catch:{ all -> 0x001c }
            monitor-exit(r2)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x000e
            r3.unsubscribe()
            goto L_0x000e
        L_0x001c:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p032rx.subscriptions.CompositeSubscription.remove(rx.Subscription):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
            r2 = this;
            boolean r1 = r2.unsubscribed
            if (r1 != 0) goto L_0x000e
            monitor-enter(r2)
            boolean r1 = r2.unsubscribed     // Catch:{ all -> 0x0019 }
            if (r1 != 0) goto L_0x000d
            java.util.Set<rx.Subscription> r1 = r2.subscriptions     // Catch:{ all -> 0x0019 }
            if (r1 != 0) goto L_0x000f
        L_0x000d:
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
        L_0x000e:
            return
        L_0x000f:
            java.util.Set<rx.Subscription> r0 = r2.subscriptions     // Catch:{ all -> 0x0019 }
            r1 = 0
            r2.subscriptions = r1     // Catch:{ all -> 0x0019 }
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            unsubscribeFromAll(r0)
            goto L_0x000e
        L_0x0019:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0019 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p032rx.subscriptions.CompositeSubscription.clear():void");
    }

    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    this.unsubscribed = true;
                    Collection<Subscription> unsubscribe = this.subscriptions;
                    this.subscriptions = null;
                    unsubscribeFromAll(unsubscribe);
                }
            }
        }
    }

    private static void unsubscribeFromAll(Collection<Subscription> subscriptions2) {
        if (subscriptions2 != null) {
            List<Throwable> es = null;
            for (Subscription s : subscriptions2) {
                try {
                    s.unsubscribe();
                } catch (Throwable e) {
                    if (es == null) {
                        es = new ArrayList<>();
                    }
                    es.add(e);
                }
            }
            Exceptions.throwIfAny(es);
        }
    }
}
