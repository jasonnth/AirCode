package com.threatmetrix.TrustDefender;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* renamed from: com.threatmetrix.TrustDefender.i */
class C4816i implements Runnable {

    /* renamed from: c */
    private static final String f4533c = C4834w.m2892a(C4816i.class);

    /* renamed from: a */
    private final String f4534a;

    /* renamed from: b */
    private InetAddress f4535b;

    public C4816i(String domain) {
        this.f4534a = domain;
    }

    public void run() {
        try {
            C4834w.m2901c(f4533c, "Starting DNS lookup");
            InetAddress addr = InetAddress.getByName(this.f4534a);
            C4834w.m2901c(f4533c, "DNS lookup complete");
            m2822a(addr);
        } catch (UnknownHostException e) {
            C4834w.m2901c(f4533c, "Failed DNS lookup");
        }
    }

    /* renamed from: a */
    private synchronized void m2822a(InetAddress inetAddr) {
        this.f4535b = inetAddr;
    }
}
