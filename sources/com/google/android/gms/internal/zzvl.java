package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

public interface zzvl extends IInterface {

    public static abstract class zza extends Binder implements zzvl {

        /* renamed from: com.google.android.gms.internal.zzvl$zza$zza reason: collision with other inner class name */
        private static class C7769zza implements zzvl {
            private IBinder zzrk;

            C7769zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzvk zzvk) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                    obtain.writeStrongBinder(zzvk != null ? zzvk.asBinder() : null);
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzvk zzvk, CredentialRequest credentialRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                    obtain.writeStrongBinder(zzvk != null ? zzvk.asBinder() : null);
                    if (credentialRequest != null) {
                        obtain.writeInt(1);
                        credentialRequest.writeToParcel(obtain, 0);
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

            public void zza(zzvk zzvk, zzvg zzvg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                    obtain.writeStrongBinder(zzvk != null ? zzvk.asBinder() : null);
                    if (zzvg != null) {
                        obtain.writeInt(1);
                        zzvg.writeToParcel(obtain, 0);
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

            public void zza(zzvk zzvk, zzvi zzvi) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                    obtain.writeStrongBinder(zzvk != null ? zzvk.asBinder() : null);
                    if (zzvi != null) {
                        obtain.writeInt(1);
                        zzvi.writeToParcel(obtain, 0);
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

            public void zza(zzvk zzvk, zzvm zzvm) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                    obtain.writeStrongBinder(zzvk != null ? zzvk.asBinder() : null);
                    if (zzvm != null) {
                        obtain.writeInt(1);
                        zzvm.writeToParcel(obtain, 0);
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

        public static zzvl zzaG(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzvl)) ? new C7769zza(iBinder) : (zzvl) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.gms.internal.zzvi] */
        /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.internal.zzvi] */
        /* JADX WARNING: type inference failed for: r0v10, types: [com.google.android.gms.internal.zzvg] */
        /* JADX WARNING: type inference failed for: r0v14, types: [com.google.android.gms.internal.zzvg] */
        /* JADX WARNING: type inference failed for: r0v15, types: [com.google.android.gms.internal.zzvm] */
        /* JADX WARNING: type inference failed for: r0v19, types: [com.google.android.gms.internal.zzvm] */
        /* JADX WARNING: type inference failed for: r0v20, types: [com.google.android.gms.auth.api.credentials.CredentialRequest] */
        /* JADX WARNING: type inference failed for: r0v24, types: [com.google.android.gms.auth.api.credentials.CredentialRequest] */
        /* JADX WARNING: type inference failed for: r0v28 */
        /* JADX WARNING: type inference failed for: r0v29 */
        /* JADX WARNING: type inference failed for: r0v30 */
        /* JADX WARNING: type inference failed for: r0v31 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.android.gms.internal.zzvg, com.google.android.gms.internal.zzvi, com.google.android.gms.internal.zzvm, com.google.android.gms.auth.api.credentials.CredentialRequest]
          uses: [com.google.android.gms.internal.zzvi, com.google.android.gms.internal.zzvg, com.google.android.gms.internal.zzvm, com.google.android.gms.auth.api.credentials.CredentialRequest]
          mth insns count: 66
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
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 0
                r1 = 1
                switch(r5) {
                    case 1: goto L_0x0012;
                    case 2: goto L_0x0036;
                    case 3: goto L_0x005a;
                    case 4: goto L_0x007e;
                    case 5: goto L_0x0095;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r5, r6, r7, r8)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.auth.api.credentials.internal.ICredentialsService"
                r7.writeString(r0)
                r0 = r1
                goto L_0x0009
            L_0x0012:
                java.lang.String r2 = "com.google.android.gms.auth.api.credentials.internal.ICredentialsService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvk r2 = com.google.android.gms.internal.zzvk.zza.zzaF(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x002e
                android.os.Parcelable$Creator<com.google.android.gms.auth.api.credentials.CredentialRequest> r0 = com.google.android.gms.auth.api.credentials.CredentialRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.auth.api.credentials.CredentialRequest r0 = (com.google.android.gms.auth.api.credentials.CredentialRequest) r0
            L_0x002e:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0036:
                java.lang.String r2 = "com.google.android.gms.auth.api.credentials.internal.ICredentialsService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvk r2 = com.google.android.gms.internal.zzvk.zza.zzaF(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x0052
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzvm> r0 = com.google.android.gms.internal.zzvm.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzvm r0 = (com.google.android.gms.internal.zzvm) r0
            L_0x0052:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x005a:
                java.lang.String r2 = "com.google.android.gms.auth.api.credentials.internal.ICredentialsService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvk r2 = com.google.android.gms.internal.zzvk.zza.zzaF(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x0076
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzvg> r0 = com.google.android.gms.internal.zzvg.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzvg r0 = (com.google.android.gms.internal.zzvg) r0
            L_0x0076:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x007e:
                java.lang.String r0 = "com.google.android.gms.auth.api.credentials.internal.ICredentialsService"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvk r0 = com.google.android.gms.internal.zzvk.zza.zzaF(r0)
                r4.zza(r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0095:
                java.lang.String r2 = "com.google.android.gms.auth.api.credentials.internal.ICredentialsService"
                r6.enforceInterface(r2)
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzvk r2 = com.google.android.gms.internal.zzvk.zza.zzaF(r2)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x00b1
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzvi> r0 = com.google.android.gms.internal.zzvi.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzvi r0 = (com.google.android.gms.internal.zzvi) r0
            L_0x00b1:
                r4.zza(r2, r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzvl.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(zzvk zzvk) throws RemoteException;

    void zza(zzvk zzvk, CredentialRequest credentialRequest) throws RemoteException;

    void zza(zzvk zzvk, zzvg zzvg) throws RemoteException;

    void zza(zzvk zzvk, zzvi zzvi) throws RemoteException;

    void zza(zzvk zzvk, zzvm zzvm) throws RemoteException;
}
