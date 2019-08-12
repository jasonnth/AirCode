package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzv extends IInterface {

    public static abstract class zza extends Binder implements zzv {

        /* renamed from: com.google.android.gms.common.internal.zzv$zza$zza reason: collision with other inner class name */
        private static class C7681zza implements zzv {
            private final IBinder zzrk;

            C7681zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzu zzu, zzj zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(zzu != null ? zzu.asBinder() : null);
                    if (zzj != null) {
                        obtain.writeInt(1);
                        zzj.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzv zzbu(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzv)) ? new C7681zza(iBinder) : (zzv) queryLocalInterface;
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r12, android.os.Parcel r13, android.os.Parcel r14, int r15) throws android.os.RemoteException {
            /*
                r11 = this;
                r1 = 0
                r0 = 16777215(0xffffff, float:2.3509886E-38)
                if (r12 <= r0) goto L_0x000b
                boolean r0 = super.onTransact(r12, r13, r14, r15)
            L_0x000a:
                return r0
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.common.internal.IGmsServiceBroker"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.common.internal.zzu r2 = com.google.android.gms.common.internal.zzu.zza.zzbt(r0)
                r0 = 46
                if (r12 != r0) goto L_0x0033
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0122
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.zzj> r0 = com.google.android.gms.common.internal.zzj.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.common.internal.zzj r0 = (com.google.android.gms.common.internal.zzj) r0
            L_0x002b:
                r11.zza(r2, r0)
            L_0x002e:
                r14.writeNoException()
                r0 = 1
                goto L_0x000a
            L_0x0033:
                r0 = 47
                if (r12 != r0) goto L_0x0049
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x011f
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.zzan> r0 = com.google.android.gms.common.internal.zzan.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.common.internal.zzan r0 = (com.google.android.gms.common.internal.zzan) r0
            L_0x0045:
                r11.zza(r2, r0)
                goto L_0x002e
            L_0x0049:
                int r3 = r13.readInt()
                r0 = 4
                if (r12 == r0) goto L_0x011c
                java.lang.String r4 = r13.readString()
            L_0x0054:
                switch(r12) {
                    case 1: goto L_0x007b;
                    case 2: goto L_0x00f1;
                    case 3: goto L_0x0057;
                    case 4: goto L_0x0057;
                    case 5: goto L_0x00f1;
                    case 6: goto L_0x00f1;
                    case 7: goto L_0x00f1;
                    case 8: goto L_0x00f1;
                    case 9: goto L_0x0099;
                    case 10: goto L_0x00d8;
                    case 11: goto L_0x00f1;
                    case 12: goto L_0x00f1;
                    case 13: goto L_0x00f1;
                    case 14: goto L_0x00f1;
                    case 15: goto L_0x00f1;
                    case 16: goto L_0x00f1;
                    case 17: goto L_0x00f1;
                    case 18: goto L_0x00f1;
                    case 19: goto L_0x0063;
                    case 20: goto L_0x00bd;
                    case 21: goto L_0x0057;
                    case 22: goto L_0x0057;
                    case 23: goto L_0x00f1;
                    case 24: goto L_0x0057;
                    case 25: goto L_0x00f1;
                    case 26: goto L_0x0057;
                    case 27: goto L_0x00f1;
                    case 28: goto L_0x0057;
                    case 29: goto L_0x0057;
                    case 30: goto L_0x00bd;
                    case 31: goto L_0x0057;
                    case 32: goto L_0x0057;
                    case 33: goto L_0x0057;
                    case 34: goto L_0x00e6;
                    case 35: goto L_0x0057;
                    case 36: goto L_0x0057;
                    case 37: goto L_0x00f1;
                    case 38: goto L_0x00f1;
                    case 39: goto L_0x0057;
                    case 40: goto L_0x0057;
                    case 41: goto L_0x00f1;
                    case 42: goto L_0x0057;
                    case 43: goto L_0x00f1;
                    default: goto L_0x0057;
                }
            L_0x0057:
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                r6 = r1
                r5 = r1
            L_0x005d:
                r0 = r11
                r1 = r12
                r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
                goto L_0x002e
            L_0x0063:
                android.os.IBinder r8 = r13.readStrongBinder()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0115
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r9 = r1
                r7 = r0
                r6 = r1
                r5 = r1
                goto L_0x005d
            L_0x007b:
                java.lang.String r9 = r13.readString()
                java.lang.String[] r6 = r13.createStringArray()
                java.lang.String r5 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0110
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r8 = r1
                r7 = r0
                goto L_0x005d
            L_0x0099:
                java.lang.String r5 = r13.readString()
                java.lang.String[] r6 = r13.createStringArray()
                java.lang.String r9 = r13.readString()
                android.os.IBinder r8 = r13.readStrongBinder()
                java.lang.String r10 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x010d
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r7 = r0
                goto L_0x005d
            L_0x00bd:
                java.lang.String[] r6 = r13.createStringArray()
                java.lang.String r5 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0107
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r0
                goto L_0x005d
            L_0x00d8:
                java.lang.String r5 = r13.readString()
                java.lang.String[] r6 = r13.createStringArray()
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                goto L_0x005d
            L_0x00e6:
                java.lang.String r5 = r13.readString()
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                r6 = r1
                goto L_0x005d
            L_0x00f1:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0057
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r0
                r6 = r1
                r5 = r1
                goto L_0x005d
            L_0x0107:
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                goto L_0x005d
            L_0x010d:
                r7 = r1
                goto L_0x005d
            L_0x0110:
                r10 = r1
                r8 = r1
                r7 = r1
                goto L_0x005d
            L_0x0115:
                r10 = r1
                r9 = r1
                r7 = r1
                r6 = r1
                r5 = r1
                goto L_0x005d
            L_0x011c:
                r4 = r1
                goto L_0x0054
            L_0x011f:
                r0 = r1
                goto L_0x0045
            L_0x0122:
                r0 = r1
                goto L_0x002b
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzv.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        /* access modifiers changed from: protected */
        public void zza(int i, zzu zzu, int i2, String str, String str2, String[] strArr, Bundle bundle, IBinder iBinder, String str3, String str4) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: protected */
        public void zza(zzu zzu, zzan zzan) throws RemoteException {
            throw new UnsupportedOperationException();
        }
    }

    void zza(zzu zzu, zzj zzj) throws RemoteException;
}
