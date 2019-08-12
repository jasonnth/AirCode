package com.threatmetrix.TrustDefender;

import java.util.concurrent.CountDownLatch;

/* renamed from: com.threatmetrix.TrustDefender.h */
class C4815h implements Runnable {

    /* renamed from: a */
    private C4751TrustDefender f4531a = null;

    /* renamed from: b */
    private CountDownLatch f4532b = null;

    public C4815h(C4751TrustDefender profile) {
        this.f4531a = profile;
        this.f4532b = null;
    }

    public void run() {
        this.f4531a.mo45920a();
        if (this.f4532b != null) {
            this.f4532b.countDown();
        }
    }
}
