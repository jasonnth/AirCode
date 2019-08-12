package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzkb extends IInterface {

    public static abstract class zza extends Binder implements zzkb {

        /* renamed from: com.google.android.gms.internal.zzkb$zza$zza reason: collision with other inner class name */
        private static class C7742zza implements zzkb {
            private IBinder zzrk;

            C7742zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void destroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getInterstitialAdapterInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isInitialized() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showInterstitial() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showVideo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzkc zzkc) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzom zzom, String str2) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzom != null) {
                        iBinder = zzom.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str2);
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc, zzhc zzhc, List<String> list) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (zzhc != null) {
                        obtain.writeInt(1);
                        zzhc.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringList(list);
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, zzkc zzkc) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzeg != null) {
                        obtain.writeInt(1);
                        zzeg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzeg != null) {
                        obtain.writeInt(1);
                        zzeg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzom zzom, List<String> list) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzom != null) {
                        iBinder = zzom.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStringList(list);
                    this.zzrk.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzec zzec, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.zzrk.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzd(zzec zzec, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzke zzhc() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.internal.zzke.zza.zzQ(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzkf zzhd() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.internal.zzkf.zza.zzR(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle zzhe() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle zzhf() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzhg() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzk(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }

        public static zzkb zzN(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzkb)) ? new C7742zza(iBinder) : (zzkb) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX WARNING: type inference failed for: r6v0 */
        /* JADX WARNING: type inference failed for: r6v1, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v2, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v3, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v4, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v5, types: [com.google.android.gms.internal.zzhc] */
        /* JADX WARNING: type inference failed for: r0v28, types: [com.google.android.gms.internal.zzhc] */
        /* JADX WARNING: type inference failed for: r6v6 */
        /* JADX WARNING: type inference failed for: r6v8, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v9, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v10 */
        /* JADX WARNING: type inference failed for: r6v11 */
        /* JADX WARNING: type inference failed for: r6v12 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.os.IBinder, ?[OBJECT, ARRAY]]
          uses: [android.os.IBinder, com.google.android.gms.internal.zzhc]
          mth insns count: 279
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
        /* JADX WARNING: Unknown variable types count: 5 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
            /*
                r9 = this;
                r0 = 0
                r6 = 0
                r8 = 1
                switch(r10) {
                    case 1: goto L_0x0012;
                    case 2: goto L_0x0056;
                    case 3: goto L_0x006d;
                    case 4: goto L_0x009f;
                    case 5: goto L_0x00ad;
                    case 6: goto L_0x00bb;
                    case 7: goto L_0x0104;
                    case 8: goto L_0x013c;
                    case 9: goto L_0x014a;
                    case 10: goto L_0x0158;
                    case 11: goto L_0x0190;
                    case 12: goto L_0x01b2;
                    case 13: goto L_0x01c0;
                    case 14: goto L_0x01d5;
                    case 15: goto L_0x0220;
                    case 16: goto L_0x0238;
                    case 17: goto L_0x0250;
                    case 18: goto L_0x026c;
                    case 19: goto L_0x0288;
                    case 20: goto L_0x02a4;
                    case 21: goto L_0x02ca;
                    case 22: goto L_0x02e0;
                    case 23: goto L_0x02f5;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r8 = super.onTransact(r10, r11, r12, r13)
            L_0x000a:
                return r8
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r12.writeString(r0)
                goto L_0x000a
            L_0x0012:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x0052
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzeg> r0 = com.google.android.gms.internal.zzeg.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzeg r0 = (com.google.android.gms.internal.zzeg) r0
                r2 = r0
            L_0x002f:
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x0054
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
                r3 = r0
            L_0x003e:
                java.lang.String r4 = r11.readString()
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.internal.zzkc r5 = com.google.android.gms.internal.zzkc.zza.zzO(r0)
                r0 = r9
                r0.zza(r1, r2, r3, r4, r5)
                r12.writeNoException()
                goto L_0x000a
            L_0x0052:
                r2 = r6
                goto L_0x002f
            L_0x0054:
                r3 = r6
                goto L_0x003e
            L_0x0056:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                com.google.android.gms.dynamic.IObjectWrapper r0 = r9.getView()
                r12.writeNoException()
                if (r0 == 0) goto L_0x0069
                android.os.IBinder r6 = r0.asBinder()
            L_0x0069:
                r12.writeStrongBinder(r6)
                goto L_0x000a
            L_0x006d:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x009d
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
            L_0x0089:
                java.lang.String r2 = r11.readString()
                android.os.IBinder r3 = r11.readStrongBinder()
                com.google.android.gms.internal.zzkc r3 = com.google.android.gms.internal.zzkc.zza.zzO(r3)
                r9.zza(r1, r0, r2, r3)
                r12.writeNoException()
                goto L_0x000a
            L_0x009d:
                r0 = r6
                goto L_0x0089
            L_0x009f:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                r9.showInterstitial()
                r12.writeNoException()
                goto L_0x000a
            L_0x00ad:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                r9.destroy()
                r12.writeNoException()
                goto L_0x000a
            L_0x00bb:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x0100
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzeg> r0 = com.google.android.gms.internal.zzeg.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzeg r0 = (com.google.android.gms.internal.zzeg) r0
                r2 = r0
            L_0x00d8:
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x0102
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
                r3 = r0
            L_0x00e7:
                java.lang.String r4 = r11.readString()
                java.lang.String r5 = r11.readString()
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.internal.zzkc r6 = com.google.android.gms.internal.zzkc.zza.zzO(r0)
                r0 = r9
                r0.zza(r1, r2, r3, r4, r5, r6)
                r12.writeNoException()
                goto L_0x000a
            L_0x0100:
                r2 = r6
                goto L_0x00d8
            L_0x0102:
                r3 = r6
                goto L_0x00e7
            L_0x0104:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x013a
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
                r2 = r0
            L_0x0121:
                java.lang.String r3 = r11.readString()
                java.lang.String r4 = r11.readString()
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.internal.zzkc r5 = com.google.android.gms.internal.zzkc.zza.zzO(r0)
                r0 = r9
                r0.zza(r1, r2, r3, r4, r5)
                r12.writeNoException()
                goto L_0x000a
            L_0x013a:
                r2 = r6
                goto L_0x0121
            L_0x013c:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                r9.pause()
                r12.writeNoException()
                goto L_0x000a
            L_0x014a:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                r9.resume()
                r12.writeNoException()
                goto L_0x000a
            L_0x0158:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x018e
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
                r2 = r0
            L_0x0175:
                java.lang.String r3 = r11.readString()
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.internal.zzom r4 = com.google.android.gms.internal.zzom.zza.zzal(r0)
                java.lang.String r5 = r11.readString()
                r0 = r9
                r0.zza(r1, r2, r3, r4, r5)
                r12.writeNoException()
                goto L_0x000a
            L_0x018e:
                r2 = r6
                goto L_0x0175
            L_0x0190:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x01b0
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
            L_0x01a4:
                java.lang.String r1 = r11.readString()
                r9.zzd(r0, r1)
                r12.writeNoException()
                goto L_0x000a
            L_0x01b0:
                r0 = r6
                goto L_0x01a4
            L_0x01b2:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                r9.showVideo()
                r12.writeNoException()
                goto L_0x000a
            L_0x01c0:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r1)
                boolean r1 = r9.isInitialized()
                r12.writeNoException()
                if (r1 == 0) goto L_0x01d0
                r0 = r8
            L_0x01d0:
                r12.writeInt(r0)
                goto L_0x000a
            L_0x01d5:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x021e
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
                r2 = r0
            L_0x01f2:
                java.lang.String r3 = r11.readString()
                java.lang.String r4 = r11.readString()
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.internal.zzkc r5 = com.google.android.gms.internal.zzkc.zza.zzO(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x0211
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzhc> r0 = com.google.android.gms.internal.zzhc.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzhc r0 = (com.google.android.gms.internal.zzhc) r0
                r6 = r0
            L_0x0211:
                java.util.ArrayList r7 = r11.createStringArrayList()
                r0 = r9
                r0.zza(r1, r2, r3, r4, r5, r6, r7)
                r12.writeNoException()
                goto L_0x000a
            L_0x021e:
                r2 = r6
                goto L_0x01f2
            L_0x0220:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                com.google.android.gms.internal.zzke r0 = r9.zzhc()
                r12.writeNoException()
                if (r0 == 0) goto L_0x0233
                android.os.IBinder r6 = r0.asBinder()
            L_0x0233:
                r12.writeStrongBinder(r6)
                goto L_0x000a
            L_0x0238:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                com.google.android.gms.internal.zzkf r0 = r9.zzhd()
                r12.writeNoException()
                if (r0 == 0) goto L_0x024b
                android.os.IBinder r6 = r0.asBinder()
            L_0x024b:
                r12.writeStrongBinder(r6)
                goto L_0x000a
            L_0x0250:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r1)
                android.os.Bundle r1 = r9.zzhe()
                r12.writeNoException()
                if (r1 == 0) goto L_0x0267
                r12.writeInt(r8)
                r1.writeToParcel(r12, r8)
                goto L_0x000a
            L_0x0267:
                r12.writeInt(r0)
                goto L_0x000a
            L_0x026c:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r1)
                android.os.Bundle r1 = r9.getInterstitialAdapterInfo()
                r12.writeNoException()
                if (r1 == 0) goto L_0x0283
                r12.writeInt(r8)
                r1.writeToParcel(r12, r8)
                goto L_0x000a
            L_0x0283:
                r12.writeInt(r0)
                goto L_0x000a
            L_0x0288:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r1)
                android.os.Bundle r1 = r9.zzhf()
                r12.writeNoException()
                if (r1 == 0) goto L_0x029f
                r12.writeInt(r8)
                r1.writeToParcel(r12, r8)
                goto L_0x000a
            L_0x029f:
                r12.writeInt(r0)
                goto L_0x000a
            L_0x02a4:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                int r0 = r11.readInt()
                if (r0 == 0) goto L_0x02c8
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r11)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
            L_0x02b8:
                java.lang.String r1 = r11.readString()
                java.lang.String r2 = r11.readString()
                r9.zza(r0, r1, r2)
                r12.writeNoException()
                goto L_0x000a
            L_0x02c8:
                r0 = r6
                goto L_0x02b8
            L_0x02ca:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                r9.zzk(r0)
                r12.writeNoException()
                goto L_0x000a
            L_0x02e0:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r1)
                boolean r1 = r9.zzhg()
                r12.writeNoException()
                if (r1 == 0) goto L_0x02f0
                r0 = r8
            L_0x02f0:
                r12.writeInt(r0)
                goto L_0x000a
            L_0x02f5:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.enforceInterface(r0)
                android.os.IBinder r0 = r11.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                android.os.IBinder r1 = r11.readStrongBinder()
                com.google.android.gms.internal.zzom r1 = com.google.android.gms.internal.zzom.zza.zzal(r1)
                java.util.ArrayList r2 = r11.createStringArrayList()
                r9.zza(r0, r1, r2)
                r12.writeNoException()
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzkb.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    IObjectWrapper getView() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzom zzom, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc, zzhc zzhc, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzom zzom, List<String> list) throws RemoteException;

    void zza(zzec zzec, String str, String str2) throws RemoteException;

    void zzd(zzec zzec, String str) throws RemoteException;

    zzke zzhc() throws RemoteException;

    zzkf zzhd() throws RemoteException;

    Bundle zzhe() throws RemoteException;

    Bundle zzhf() throws RemoteException;

    boolean zzhg() throws RemoteException;

    void zzk(IObjectWrapper iObjectWrapper) throws RemoteException;
}
