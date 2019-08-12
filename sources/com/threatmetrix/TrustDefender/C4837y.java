package com.threatmetrix.TrustDefender;

/* renamed from: com.threatmetrix.TrustDefender.y */
final class C4837y extends Thread {

    /* renamed from: a */
    private Runnable f4679a = null;

    public C4837y(Runnable runnable) {
        this.f4679a = runnable;
    }

    /* renamed from: a */
    public final C4824o mo46102a() {
        if (this.f4679a instanceof C4824o) {
            return (C4824o) this.f4679a;
        }
        return null;
    }

    public final void run() {
        this.f4679a.run();
    }

    public final void interrupt() {
        if (this.f4679a instanceof C4824o) {
            ((C4824o) this.f4679a).mo46088c();
        }
        super.interrupt();
    }
}
