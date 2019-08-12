package com.threatmetrix.TrustDefender;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.threatmetrix.TrustDefender.k */
class C4818k {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f4548a = C4834w.m2892a(C4818k.class);

    /* renamed from: b */
    private CountDownLatch f4549b = new CountDownLatch(1);

    /* renamed from: c */
    private C4819a f4550c = new C4819a(this.f4549b);

    /* renamed from: com.threatmetrix.TrustDefender.k$a */
    static class C4819a implements ServiceConnection {

        /* renamed from: a */
        private IBinder f4551a = null;

        /* renamed from: b */
        private String f4552b = null;

        /* renamed from: c */
        private CountDownLatch f4553c;

        public C4819a(CountDownLatch latch) {
            this.f4553c = latch;
        }

        public final void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {
                this.f4551a = service;
                this.f4552b = m2828b();
                this.f4553c.countDown();
            }
        }

        public final void onServiceDisconnected(ComponentName name) {
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final String mo46061a() {
            return this.f4552b;
        }

        /* renamed from: b */
        private String m2828b() {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f4551a.transact(1, data, reply, 0);
                reply.readException();
                return reply.readString();
            } catch (Exception e) {
                C4818k.f4548a;
                return null;
            } finally {
                reply.recycle();
                data.recycle();
            }
        }
    }

    /* renamed from: a */
    public final boolean mo46060a(Context context) {
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        return context.bindService(intent, this.f4550c, 1);
    }

    /* renamed from: a */
    public final String mo46059a(int timeout) {
        try {
            if (this.f4549b.await((long) timeout, TimeUnit.MILLISECONDS)) {
                return this.f4550c.mo46061a();
            }
            String str = f4548a;
            return null;
        } catch (InterruptedException e) {
            String str2 = f4548a;
        } catch (Exception e2) {
            C4834w.m2901c(f4548a, e2.getMessage());
        }
    }
}
