package com.google.firebase.appindexing.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzabb;

public interface zzl extends IInterface {

    public static abstract class zza extends Binder implements zzl {

        /* renamed from: com.google.firebase.appindexing.internal.zzl$zza$zza reason: collision with other inner class name */
        private static class C7830zza implements zzl {
            private IBinder zzrk;

            C7830zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzabb zzabb, zzq zzq) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.appindexing.internal.IAppIndexingService");
                    obtain.writeStrongBinder(zzabb != null ? zzabb.asBinder() : null);
                    if (zzq != null) {
                        obtain.writeInt(1);
                        zzq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzabb zzabb, Thing[] thingArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.appindexing.internal.IAppIndexingService");
                    obtain.writeStrongBinder(zzabb != null ? zzabb.asBinder() : null);
                    obtain.writeTypedArray(thingArr, 0);
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzabb zzabb, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.appindexing.internal.IAppIndexingService");
                    obtain.writeStrongBinder(zzabb != null ? zzabb.asBinder() : null);
                    obtain.writeStringArray(strArr);
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzk zzk, zzg zzg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.appindexing.internal.IAppIndexingService");
                    obtain.writeStrongBinder(zzk != null ? zzk.asBinder() : null);
                    if (zzg != null) {
                        obtain.writeInt(1);
                        zzg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzc(zzabb zzabb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.appindexing.internal.IAppIndexingService");
                    obtain.writeStrongBinder(zzabb != null ? zzabb.asBinder() : null);
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzl zzfI(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.appindexing.internal.IAppIndexingService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzl)) ? new C7830zza(iBinder) : (zzl) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v1, types: [com.google.firebase.appindexing.internal.zzg] */
        /* JADX WARNING: type inference failed for: r0v5, types: [com.google.firebase.appindexing.internal.zzg] */
        /* JADX WARNING: type inference failed for: r0v6, types: [com.google.firebase.appindexing.internal.zzq] */
        /* JADX WARNING: type inference failed for: r0v10, types: [com.google.firebase.appindexing.internal.zzq] */
        /* JADX WARNING: type inference failed for: r0v28 */
        /* JADX WARNING: type inference failed for: r0v29 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.firebase.appindexing.internal.zzq, com.google.firebase.appindexing.internal.zzg]
          uses: [com.google.firebase.appindexing.internal.zzg, com.google.firebase.appindexing.internal.zzq]
          mth insns count: 58
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
                    case 2: goto L_0x0030;
                    case 3: goto L_0x004a;
                    case 4: goto L_0x0060;
                    case 5: goto L_0x0084;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r5, r6, r7, r8)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.firebase.appindexing.internal.IAppIndexingService"
                r7.writeString(r0)
                r0 = r1
                goto L_0x0009
            L_0x0012:
                java.lang.String r0 = "com.google.firebase.appindexing.internal.IAppIndexingService"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzabb r2 = com.google.android.gms.internal.zzabb.zza.zzbp(r0)
                android.os.Parcelable$Creator<com.google.firebase.appindexing.internal.Thing> r0 = com.google.firebase.appindexing.internal.Thing.CREATOR
                java.lang.Object[] r0 = r6.createTypedArray(r0)
                com.google.firebase.appindexing.internal.Thing[] r0 = (com.google.firebase.appindexing.internal.Thing[]) r0
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0030:
                java.lang.String r0 = "com.google.firebase.appindexing.internal.IAppIndexingService"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzabb r0 = com.google.android.gms.internal.zzabb.zza.zzbp(r0)
                java.lang.String[] r2 = r6.createStringArray()
                r4.zza(r0, r2)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x004a:
                java.lang.String r0 = "com.google.firebase.appindexing.internal.IAppIndexingService"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzabb r0 = com.google.android.gms.internal.zzabb.zza.zzbp(r0)
                r4.zzc(r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0060:
                java.lang.String r2 = "com.google.firebase.appindexing.internal.IAppIndexingService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzabb r2 = com.google.android.gms.internal.zzabb.zza.zzbp(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x007c
                android.os.Parcelable$Creator<com.google.firebase.appindexing.internal.zzq> r0 = com.google.firebase.appindexing.internal.zzq.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.firebase.appindexing.internal.zzq r0 = (com.google.firebase.appindexing.internal.zzq) r0
            L_0x007c:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0084:
                java.lang.String r2 = "com.google.firebase.appindexing.internal.IAppIndexingService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.firebase.appindexing.internal.zzk r2 = com.google.firebase.appindexing.internal.zzk.zza.zzfH(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x00a0
                android.os.Parcelable$Creator<com.google.firebase.appindexing.internal.zzg> r0 = com.google.firebase.appindexing.internal.zzg.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.firebase.appindexing.internal.zzg r0 = (com.google.firebase.appindexing.internal.zzg) r0
            L_0x00a0:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appindexing.internal.zzl.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(zzabb zzabb, zzq zzq) throws RemoteException;

    void zza(zzabb zzabb, Thing[] thingArr) throws RemoteException;

    void zza(zzabb zzabb, String[] strArr) throws RemoteException;

    void zza(zzk zzk, zzg zzg) throws RemoteException;

    void zzc(zzabb zzabb) throws RemoteException;
}
