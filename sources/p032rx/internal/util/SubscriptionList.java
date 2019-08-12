package p032rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import p032rx.Subscription;
import p032rx.exceptions.Exceptions;

/* renamed from: rx.internal.util.SubscriptionList */
public final class SubscriptionList implements Subscription {
    private List<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public SubscriptionList() {
    }

    public SubscriptionList(Subscription... subscriptions2) {
        this.subscriptions = new LinkedList(Arrays.asList(subscriptions2));
    }

    public SubscriptionList(Subscription s) {
        this.subscriptions = new LinkedList();
        this.subscriptions.add(s);
    }

    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription s) {
        if (!s.isUnsubscribed()) {
            if (!this.unsubscribed) {
                synchronized (this) {
                    if (!this.unsubscribed) {
                        List<Subscription> subs = this.subscriptions;
                        if (subs == null) {
                            subs = new LinkedList<>();
                            this.subscriptions = subs;
                        }
                        subs.add(s);
                        return;
                    }
                }
            }
            s.unsubscribe();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r1 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        r4.unsubscribe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(p032rx.Subscription r4) {
        /*
            r3 = this;
            boolean r2 = r3.unsubscribed
            if (r2 != 0) goto L_0x000e
            monitor-enter(r3)
            java.util.List<rx.Subscription> r0 = r3.subscriptions     // Catch:{ all -> 0x001a }
            boolean r2 = r3.unsubscribed     // Catch:{ all -> 0x001a }
            if (r2 != 0) goto L_0x000d
            if (r0 != 0) goto L_0x000f
        L_0x000d:
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
        L_0x000e:
            return
        L_0x000f:
            boolean r1 = r0.remove(r4)     // Catch:{ all -> 0x001a }
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x000e
            r4.unsubscribe()
            goto L_0x000e
        L_0x001a:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p032rx.internal.util.SubscriptionList.remove(rx.Subscription):void");
    }

    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    this.unsubscribed = true;
                    List<Subscription> list = this.subscriptions;
                    this.subscriptions = null;
                    unsubscribeFromAll(list);
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
