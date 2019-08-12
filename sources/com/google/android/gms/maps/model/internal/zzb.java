package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

public interface zzb extends IInterface {

    public static abstract class zza extends Binder implements zzb {

        /* renamed from: com.google.android.gms.maps.model.internal.zzb$zza$zza reason: collision with other inner class name */
        private static class C7821zza implements zzb {
            private IBinder zzrk;

            C7821zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public LatLng getCenter() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (LatLng) LatLng.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getFillColor() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public double getRadius() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getStrokeColor() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<PatternItem> getStrokePattern() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PatternItem.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getStrokeWidth() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getZIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int hashCodeRemote() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isClickable() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(20, obtain, obtain2, 0);
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

            public boolean isVisible() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(16, obtain, obtain2, 0);
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

            public void remove() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setCenter(LatLng latLng) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
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

            public void setClickable(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setFillColor(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(i);
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setRadius(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeDouble(d);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setStrokeColor(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(i);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setStrokePattern(List<PatternItem> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeTypedList(list);
                    this.zzrk.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setStrokeWidth(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setTag(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setVisible(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setZIndex(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzb(zzb zzb) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeStrongBinder(zzb != null ? zzb.asBinder() : null);
                    this.zzrk.transact(17, obtain, obtain2, 0);
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
        }

        public static zzb zzej(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzb)) ? new C7821zza(iBinder) : (zzb) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v1, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v48, types: [com.google.android.gms.maps.model.LatLng] */
        /* JADX WARNING: type inference failed for: r0v51, types: [com.google.android.gms.maps.model.LatLng] */
        /* JADX WARNING: type inference failed for: r0v56 */
        /* JADX WARNING: type inference failed for: r0v57 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.android.gms.maps.model.LatLng, android.os.IBinder]
          uses: [android.os.IBinder, com.google.android.gms.maps.model.LatLng]
          mth insns count: 178
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
                r2 = 0
                r1 = 1
                switch(r5) {
                    case 1: goto L_0x0012;
                    case 2: goto L_0x001f;
                    case 3: goto L_0x0030;
                    case 4: goto L_0x004b;
                    case 5: goto L_0x0065;
                    case 6: goto L_0x0076;
                    case 7: goto L_0x0087;
                    case 8: goto L_0x0099;
                    case 9: goto L_0x00ab;
                    case 10: goto L_0x00bd;
                    case 11: goto L_0x00cf;
                    case 12: goto L_0x00e1;
                    case 13: goto L_0x00f3;
                    case 14: goto L_0x0105;
                    case 15: goto L_0x0117;
                    case 16: goto L_0x012e;
                    case 17: goto L_0x0143;
                    case 18: goto L_0x0160;
                    case 19: goto L_0x0172;
                    case 20: goto L_0x0187;
                    case 21: goto L_0x019c;
                    case 22: goto L_0x01b0;
                    case 23: goto L_0x01c2;
                    case 24: goto L_0x01d8;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r1 = super.onTransact(r5, r6, r7, r8)
            L_0x000a:
                return r1
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r7.writeString(r0)
                goto L_0x000a
            L_0x0012:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                r4.remove()
                r7.writeNoException()
                goto L_0x000a
            L_0x001f:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                java.lang.String r0 = r4.getId()
                r7.writeNoException()
                r7.writeString(r0)
                goto L_0x000a
            L_0x0030:
                java.lang.String r2 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x0044
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r0 = com.google.android.gms.maps.model.LatLng.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.maps.model.LatLng r0 = (com.google.android.gms.maps.model.LatLng) r0
            L_0x0044:
                r4.setCenter(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x004b:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                com.google.android.gms.maps.model.LatLng r0 = r4.getCenter()
                r7.writeNoException()
                if (r0 == 0) goto L_0x0061
                r7.writeInt(r1)
                r0.writeToParcel(r7, r1)
                goto L_0x000a
            L_0x0061:
                r7.writeInt(r2)
                goto L_0x000a
            L_0x0065:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                double r2 = r6.readDouble()
                r4.setRadius(r2)
                r7.writeNoException()
                goto L_0x000a
            L_0x0076:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                double r2 = r4.getRadius()
                r7.writeNoException()
                r7.writeDouble(r2)
                goto L_0x000a
            L_0x0087:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                float r0 = r6.readFloat()
                r4.setStrokeWidth(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x0099:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                float r0 = r4.getStrokeWidth()
                r7.writeNoException()
                r7.writeFloat(r0)
                goto L_0x000a
            L_0x00ab:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                r4.setStrokeColor(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x00bd:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                int r0 = r4.getStrokeColor()
                r7.writeNoException()
                r7.writeInt(r0)
                goto L_0x000a
            L_0x00cf:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                r4.setFillColor(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x00e1:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                int r0 = r4.getFillColor()
                r7.writeNoException()
                r7.writeInt(r0)
                goto L_0x000a
            L_0x00f3:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                float r0 = r6.readFloat()
                r4.setZIndex(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x0105:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                float r0 = r4.getZIndex()
                r7.writeNoException()
                r7.writeFloat(r0)
                goto L_0x000a
            L_0x0117:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x012c
                r0 = r1
            L_0x0124:
                r4.setVisible(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x012c:
                r0 = r2
                goto L_0x0124
            L_0x012e:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                boolean r0 = r4.isVisible()
                r7.writeNoException()
                if (r0 == 0) goto L_0x013e
                r2 = r1
            L_0x013e:
                r7.writeInt(r2)
                goto L_0x000a
            L_0x0143:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.maps.model.internal.zzb r0 = zzej(r0)
                boolean r0 = r4.zzb(r0)
                r7.writeNoException()
                if (r0 == 0) goto L_0x015b
                r2 = r1
            L_0x015b:
                r7.writeInt(r2)
                goto L_0x000a
            L_0x0160:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                int r0 = r4.hashCodeRemote()
                r7.writeNoException()
                r7.writeInt(r0)
                goto L_0x000a
            L_0x0172:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x017f
                r2 = r1
            L_0x017f:
                r4.setClickable(r2)
                r7.writeNoException()
                goto L_0x000a
            L_0x0187:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                boolean r0 = r4.isClickable()
                r7.writeNoException()
                if (r0 == 0) goto L_0x0197
                r2 = r1
            L_0x0197:
                r7.writeInt(r2)
                goto L_0x000a
            L_0x019c:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.PatternItem> r0 = com.google.android.gms.maps.model.PatternItem.CREATOR
                java.util.ArrayList r0 = r6.createTypedArrayList(r0)
                r4.setStrokePattern(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x01b0:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                java.util.List r0 = r4.getStrokePattern()
                r7.writeNoException()
                r7.writeTypedList(r0)
                goto L_0x000a
            L_0x01c2:
                java.lang.String r0 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                r4.setTag(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x01d8:
                java.lang.String r2 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.enforceInterface(r2)
                com.google.android.gms.dynamic.IObjectWrapper r2 = r4.getTag()
                r7.writeNoException()
                if (r2 == 0) goto L_0x01eb
                android.os.IBinder r0 = r2.asBinder()
            L_0x01eb:
                r7.writeStrongBinder(r0)
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.internal.zzb.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    LatLng getCenter() throws RemoteException;

    int getFillColor() throws RemoteException;

    String getId() throws RemoteException;

    double getRadius() throws RemoteException;

    int getStrokeColor() throws RemoteException;

    List<PatternItem> getStrokePattern() throws RemoteException;

    float getStrokeWidth() throws RemoteException;

    IObjectWrapper getTag() throws RemoteException;

    float getZIndex() throws RemoteException;

    int hashCodeRemote() throws RemoteException;

    boolean isClickable() throws RemoteException;

    boolean isVisible() throws RemoteException;

    void remove() throws RemoteException;

    void setCenter(LatLng latLng) throws RemoteException;

    void setClickable(boolean z) throws RemoteException;

    void setFillColor(int i) throws RemoteException;

    void setRadius(double d) throws RemoteException;

    void setStrokeColor(int i) throws RemoteException;

    void setStrokePattern(List<PatternItem> list) throws RemoteException;

    void setStrokeWidth(float f) throws RemoteException;

    void setTag(IObjectWrapper iObjectWrapper) throws RemoteException;

    void setVisible(boolean z) throws RemoteException;

    void setZIndex(float f) throws RemoteException;

    boolean zzb(zzb zzb) throws RemoteException;
}
