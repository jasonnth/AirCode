package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface IMapViewDelegate extends IInterface {

    public static abstract class zza extends Binder implements IMapViewDelegate {

        /* renamed from: com.google.android.gms.maps.internal.IMapViewDelegate$zza$zza reason: collision with other inner class name */
        private static class C7780zza implements IMapViewDelegate {
            private IBinder zzrk;

            C7780zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public IGoogleMapDelegate getMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.maps.internal.IGoogleMapDelegate.zza.zzdu(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getMapAsync(zzt zzt) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    obtain.writeStrongBinder(zzt != null ? zzt.asBinder() : null);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onCreate(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void onDestroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onEnterAmbient(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onExitAmbient() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onLowMemory() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onResume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onSaveInstanceState(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IMapViewDelegate");
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IMapViewDelegate zzdy(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMapViewDelegate)) ? new C7780zza(iBinder) : (IMapViewDelegate) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v7, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r0v11, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r0v16, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v18, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v19, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r0v24, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r0v33, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r0v37, types: [android.os.Bundle] */
        /* JADX WARNING: type inference failed for: r0v38, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v40, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v44 */
        /* JADX WARNING: type inference failed for: r0v45 */
        /* JADX WARNING: type inference failed for: r0v46 */
        /* JADX WARNING: type inference failed for: r0v47 */
        /* JADX WARNING: type inference failed for: r0v48 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.os.IBinder, android.os.Bundle]
          uses: [android.os.Bundle, android.os.IBinder, ?[int, boolean, OBJECT, ARRAY, byte, short, char]]
          mth insns count: 104
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
        /* JADX WARNING: Unknown variable types count: 6 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 0
                r1 = 1
                switch(r4) {
                    case 1: goto L_0x0012;
                    case 2: goto L_0x002a;
                    case 3: goto L_0x0046;
                    case 4: goto L_0x0054;
                    case 5: goto L_0x0062;
                    case 6: goto L_0x0070;
                    case 7: goto L_0x007e;
                    case 8: goto L_0x00a8;
                    case 9: goto L_0x00c1;
                    case 10: goto L_0x00d8;
                    case 11: goto L_0x00f5;
                    case 12: goto L_0x0104;
                    case 13: goto L_0x0113;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r4, r5, r6, r7)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r6.writeString(r0)
                r0 = r1
                goto L_0x0009
            L_0x0012:
                java.lang.String r2 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r2)
                com.google.android.gms.maps.internal.IGoogleMapDelegate r2 = r3.getMap()
                r6.writeNoException()
                if (r2 == 0) goto L_0x0025
                android.os.IBinder r0 = r2.asBinder()
            L_0x0025:
                r6.writeStrongBinder(r0)
                r0 = r1
                goto L_0x0009
            L_0x002a:
                java.lang.String r2 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r2)
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x003e
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r5)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x003e:
                r3.onCreate(r0)
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0046:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                r3.onResume()
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0054:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                r3.onPause()
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0062:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                r3.onDestroy()
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0070:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                r3.onLowMemory()
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x007e:
                java.lang.String r2 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r2)
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x0092
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r5)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0092:
                r3.onSaveInstanceState(r0)
                r6.writeNoException()
                if (r0 == 0) goto L_0x00a3
                r6.writeInt(r1)
                r0.writeToParcel(r6, r1)
            L_0x00a0:
                r0 = r1
                goto L_0x0009
            L_0x00a3:
                r0 = 0
                r6.writeInt(r0)
                goto L_0x00a0
            L_0x00a8:
                java.lang.String r2 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r2)
                com.google.android.gms.dynamic.IObjectWrapper r2 = r3.getView()
                r6.writeNoException()
                if (r2 == 0) goto L_0x00bb
                android.os.IBinder r0 = r2.asBinder()
            L_0x00bb:
                r6.writeStrongBinder(r0)
                r0 = r1
                goto L_0x0009
            L_0x00c1:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                android.os.IBinder r0 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzt r0 = com.google.android.gms.maps.internal.zzt.zza.zzdO(r0)
                r3.getMapAsync(r0)
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x00d8:
                java.lang.String r2 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r2)
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x00ec
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r5)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00ec:
                r3.onEnterAmbient(r0)
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x00f5:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                r3.onExitAmbient()
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0104:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                r3.onStart()
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0113:
                java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
                r5.enforceInterface(r0)
                r3.onStop()
                r6.writeNoException()
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.IMapViewDelegate.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    IGoogleMapDelegate getMap() throws RemoteException;

    void getMapAsync(zzt zzt) throws RemoteException;

    IObjectWrapper getView() throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;
}
