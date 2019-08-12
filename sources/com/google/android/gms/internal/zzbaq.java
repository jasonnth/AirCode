package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzx;

public interface zzbaq extends IInterface {

    public static abstract class zza extends Binder implements zzbaq {

        /* renamed from: com.google.android.gms.internal.zzbaq$zza$zza reason: collision with other inner class name */
        private static class C7707zza implements zzbaq {
            private IBinder zzrk;

            C7707zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(int i, Account account, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(i);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzad zzad, zzx zzx) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzad != null) {
                        obtain.writeInt(1);
                        zzad.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzx != null ? zzx.asBinder() : null);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzd != null) {
                        obtain.writeInt(1);
                        zzd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzr zzr, int i, boolean z) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeStrongBinder(zzr != null ? zzr.asBinder() : null);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzban zzban) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzban != null) {
                        obtain.writeInt(1);
                        zzban.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzbar zzbar, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzbar != null) {
                        obtain.writeInt(1);
                        zzbar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzbau zzbau, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzbau != null) {
                        obtain.writeInt(1);
                        zzbau.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzaO(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzaP(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zznv(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(i);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzbaq zzff(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzbaq)) ? new C7707zza(iBinder) : (zzbaq) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.zzbau] */
        /* JADX WARNING: type inference failed for: r0v6, types: [com.google.android.gms.internal.zzbau] */
        /* JADX WARNING: type inference failed for: r0v10, types: [com.google.android.gms.internal.zzbar] */
        /* JADX WARNING: type inference failed for: r0v13, types: [com.google.android.gms.internal.zzbar] */
        /* JADX WARNING: type inference failed for: r0v17, types: [android.accounts.Account] */
        /* JADX WARNING: type inference failed for: r0v20, types: [android.accounts.Account] */
        /* JADX WARNING: type inference failed for: r0v23, types: [com.google.android.gms.common.internal.zzad] */
        /* JADX WARNING: type inference failed for: r0v26, types: [com.google.android.gms.common.internal.zzad] */
        /* JADX WARNING: type inference failed for: r0v32, types: [com.google.android.gms.internal.zzban] */
        /* JADX WARNING: type inference failed for: r0v35, types: [com.google.android.gms.internal.zzban] */
        /* JADX WARNING: type inference failed for: r0v36, types: [com.google.android.gms.common.internal.zzd] */
        /* JADX WARNING: type inference failed for: r0v39, types: [com.google.android.gms.common.internal.zzd] */
        /* JADX WARNING: type inference failed for: r0v41 */
        /* JADX WARNING: type inference failed for: r0v42 */
        /* JADX WARNING: type inference failed for: r0v43 */
        /* JADX WARNING: type inference failed for: r0v44 */
        /* JADX WARNING: type inference failed for: r0v45 */
        /* JADX WARNING: type inference failed for: r0v46 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.android.gms.internal.zzbar, com.google.android.gms.internal.zzbau, android.accounts.Account, com.google.android.gms.common.internal.zzad, com.google.android.gms.internal.zzban, com.google.android.gms.common.internal.zzd]
          uses: [com.google.android.gms.internal.zzbau, com.google.android.gms.internal.zzbar, android.accounts.Account, com.google.android.gms.common.internal.zzad, com.google.android.gms.internal.zzban, com.google.android.gms.common.internal.zzd]
          mth insns count: 126
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
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 7 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r6, android.os.Parcel r7, android.os.Parcel r8, int r9) throws android.os.RemoteException {
            /*
                r5 = this;
                r2 = 0
                r0 = 0
                r1 = 1
                switch(r6) {
                    case 2: goto L_0x0012;
                    case 3: goto L_0x0035;
                    case 4: goto L_0x0050;
                    case 5: goto L_0x0066;
                    case 7: goto L_0x0089;
                    case 8: goto L_0x009b;
                    case 9: goto L_0x00c3;
                    case 10: goto L_0x00e4;
                    case 11: goto L_0x0108;
                    case 12: goto L_0x011e;
                    case 13: goto L_0x0142;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r1 = super.onTransact(r6, r7, r8, r9)
            L_0x000a:
                return r1
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.signin.internal.ISignInService"
                r8.writeString(r0)
                goto L_0x000a
            L_0x0012:
                java.lang.String r2 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0026
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.zzd> r0 = com.google.android.gms.common.internal.zzd.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.common.internal.zzd r0 = (com.google.android.gms.common.internal.zzd) r0
            L_0x0026:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.internal.zzbap r2 = com.google.android.gms.internal.zzbap.zza.zzfe(r2)
                r5.zza(r0, r2)
                r8.writeNoException()
                goto L_0x000a
            L_0x0035:
                java.lang.String r2 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0049
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzban> r0 = com.google.android.gms.internal.zzban.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.internal.zzban r0 = (com.google.android.gms.internal.zzban) r0
            L_0x0049:
                r5.zza(r0)
                r8.writeNoException()
                goto L_0x000a
            L_0x0050:
                java.lang.String r0 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x0064
                r0 = r1
            L_0x005d:
                r5.zzaO(r0)
                r8.writeNoException()
                goto L_0x000a
            L_0x0064:
                r0 = r2
                goto L_0x005d
            L_0x0066:
                java.lang.String r2 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x007a
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.zzad> r0 = com.google.android.gms.common.internal.zzad.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.common.internal.zzad r0 = (com.google.android.gms.common.internal.zzad) r0
            L_0x007a:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.common.internal.zzx r2 = com.google.android.gms.common.internal.zzx.zza.zzbw(r2)
                r5.zza(r0, r2)
                r8.writeNoException()
                goto L_0x000a
            L_0x0089:
                java.lang.String r0 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                r5.zznv(r0)
                r8.writeNoException()
                goto L_0x000a
            L_0x009b:
                java.lang.String r2 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x00b3
                android.os.Parcelable$Creator r0 = android.accounts.Account.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x00b3:
                android.os.IBinder r3 = r7.readStrongBinder()
                com.google.android.gms.internal.zzbap r3 = com.google.android.gms.internal.zzbap.zza.zzfe(r3)
                r5.zza(r2, r0, r3)
                r8.writeNoException()
                goto L_0x000a
            L_0x00c3:
                java.lang.String r0 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.common.internal.zzr r0 = com.google.android.gms.common.internal.zzr.zza.zzbr(r0)
                int r3 = r7.readInt()
                int r4 = r7.readInt()
                if (r4 == 0) goto L_0x00dc
                r2 = r1
            L_0x00dc:
                r5.zza(r0, r3, r2)
                r8.writeNoException()
                goto L_0x000a
            L_0x00e4:
                java.lang.String r2 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x00f8
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzbar> r0 = com.google.android.gms.internal.zzbar.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.internal.zzbar r0 = (com.google.android.gms.internal.zzbar) r0
            L_0x00f8:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.internal.zzbap r2 = com.google.android.gms.internal.zzbap.zza.zzfe(r2)
                r5.zza(r0, r2)
                r8.writeNoException()
                goto L_0x000a
            L_0x0108:
                java.lang.String r0 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.internal.zzbap r0 = com.google.android.gms.internal.zzbap.zza.zzfe(r0)
                r5.zzb(r0)
                r8.writeNoException()
                goto L_0x000a
            L_0x011e:
                java.lang.String r2 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0132
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzbau> r0 = com.google.android.gms.internal.zzbau.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.internal.zzbau r0 = (com.google.android.gms.internal.zzbau) r0
            L_0x0132:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.internal.zzbap r2 = com.google.android.gms.internal.zzbap.zza.zzfe(r2)
                r5.zza(r0, r2)
                r8.writeNoException()
                goto L_0x000a
            L_0x0142:
                java.lang.String r0 = "com.google.android.gms.signin.internal.ISignInService"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x014f
                r2 = r1
            L_0x014f:
                r5.zzaP(r2)
                r8.writeNoException()
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbaq.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(int i, Account account, zzbap zzbap) throws RemoteException;

    void zza(zzad zzad, zzx zzx) throws RemoteException;

    void zza(zzd zzd, zzbap zzbap) throws RemoteException;

    void zza(zzr zzr, int i, boolean z) throws RemoteException;

    void zza(zzban zzban) throws RemoteException;

    void zza(zzbar zzbar, zzbap zzbap) throws RemoteException;

    void zza(zzbau zzbau, zzbap zzbap) throws RemoteException;

    void zzaO(boolean z) throws RemoteException;

    void zzaP(boolean z) throws RemoteException;

    void zzb(zzbap zzbap) throws RemoteException;

    void zznv(int i) throws RemoteException;
}
