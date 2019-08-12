package com.google.firebase.appindexing.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzk extends IInterface {

    public static abstract class zza extends Binder implements zzk {

        /* renamed from: com.google.firebase.appindexing.internal.zzk$zza$zza reason: collision with other inner class name */
        private static class C7829zza implements zzk {
            private IBinder zzrk;

            C7829zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzi zzi) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.appindexing.internal.IAppIndexingCallback");
                    if (zzi != null) {
                        obtain.writeInt(1);
                        zzi.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static zzk zzfH(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.appindexing.internal.IAppIndexingCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzk)) ? new C7829zza(iBinder) : (zzk) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 2:
                    parcel.enforceInterface("com.google.firebase.appindexing.internal.IAppIndexingCallback");
                    zza(parcel.readInt() != 0 ? (zzi) zzi.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.firebase.appindexing.internal.IAppIndexingCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zza(zzi zzi) throws RemoteException;
}
