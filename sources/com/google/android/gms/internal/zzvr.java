package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

public interface zzvr extends IInterface {

    public static abstract class zza extends Binder implements zzvr {

        /* renamed from: com.google.android.gms.internal.zzvr$zza$zza reason: collision with other inner class name */
        private static class C7771zza implements zzvr {
            private IBinder zzrk;

            C7771zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzvq zzvq) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
                    obtain.writeStrongBinder(zzvq != null ? zzvq.asBinder() : null);
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzvq zzvq, ProxyRequest proxyRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
                    obtain.writeStrongBinder(zzvq != null ? zzvq.asBinder() : null);
                    if (proxyRequest != null) {
                        obtain.writeInt(1);
                        proxyRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzvq zzvq, com.google.android.gms.auth.api.proxy.zza zza) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
                    obtain.writeStrongBinder(zzvq != null ? zzvq.asBinder() : null);
                    if (zza != null) {
                        obtain.writeInt(1);
                        zza.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzvr zzaJ(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzvr)) ? new C7771zza(iBinder) : (zzvr) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.auth.api.proxy.zza] */
        /* JADX WARNING: type inference failed for: r0v9, types: [com.google.android.gms.auth.api.proxy.zza] */
        /* JADX WARNING: type inference failed for: r0v10, types: [com.google.android.gms.auth.api.proxy.ProxyRequest] */
        /* JADX WARNING: type inference failed for: r0v14, types: [com.google.android.gms.auth.api.proxy.ProxyRequest] */
        /* JADX WARNING: type inference failed for: r0v18 */
        /* JADX WARNING: type inference failed for: r0v19 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.android.gms.auth.api.proxy.ProxyRequest, com.google.android.gms.auth.api.proxy.zza]
          uses: [com.google.android.gms.auth.api.proxy.zza, com.google.android.gms.auth.api.proxy.ProxyRequest]
          mth insns count: 40
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 0
                r1 = 1
                switch(r5) {
                    case 1: goto L_0x0012;
                    case 2: goto L_0x0036;
                    case 3: goto L_0x005a;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r5, r6, r7, r8)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.auth.api.internal.IAuthService"
                r7.writeString(r0)
                r0 = r1
                goto L_0x0009
            L_0x0012:
                java.lang.String r2 = "com.google.android.gms.auth.api.internal.IAuthService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvq r2 = com.google.android.gms.internal.zzvq.zza.zzaI(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x002e
                android.os.Parcelable$Creator<com.google.android.gms.auth.api.proxy.ProxyRequest> r0 = com.google.android.gms.auth.api.proxy.ProxyRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.auth.api.proxy.ProxyRequest r0 = (com.google.android.gms.auth.api.proxy.ProxyRequest) r0
            L_0x002e:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0036:
                java.lang.String r2 = "com.google.android.gms.auth.api.internal.IAuthService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvq r2 = com.google.android.gms.internal.zzvq.zza.zzaI(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x0052
                android.os.Parcelable$Creator<com.google.android.gms.auth.api.proxy.zza> r0 = com.google.android.gms.auth.api.proxy.zza.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.auth.api.proxy.zza r0 = (com.google.android.gms.auth.api.proxy.zza) r0
            L_0x0052:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x005a:
                java.lang.String r0 = "com.google.android.gms.auth.api.internal.IAuthService"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvq r0 = com.google.android.gms.internal.zzvq.zza.zzaI(r0)
                r4.zza(r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzvr.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(zzvq zzvq) throws RemoteException;

    void zza(zzvq zzvq, ProxyRequest proxyRequest) throws RemoteException;

    void zza(zzvq zzvq, com.google.android.gms.auth.api.proxy.zza zza2) throws RemoteException;
}
