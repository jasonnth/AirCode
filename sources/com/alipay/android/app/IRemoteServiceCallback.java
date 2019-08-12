package com.alipay.android.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteServiceCallback extends IInterface {

    public static abstract class Stub extends Binder implements IRemoteServiceCallback {

        private static class Proxy implements IRemoteServiceCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void startActivity(String packageName, String className, int iCallingPid, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
                    _data.writeString(packageName);
                    _data.writeString(className);
                    _data.writeInt(iCallingPid);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void payEnd(boolean isPayOk, String resultStatus) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
                    if (isPayOk) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeString(resultStatus);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isHideLoadingScreen() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.alipay.android.app.IRemoteServiceCallback");
        }

        public static IRemoteServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.alipay.android.app.IRemoteServiceCallback");
            if (iin == null || !(iin instanceof IRemoteServiceCallback)) {
                return new Proxy(obj);
            }
            return (IRemoteServiceCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg3;
            boolean _arg0 = false;
            switch (code) {
                case 1:
                    data.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                    String _arg02 = data.readString();
                    String _arg1 = data.readString();
                    int _arg2 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    startActivity(_arg02, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    payEnd(_arg0, data.readString());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
                    boolean _result = isHideLoadingScreen();
                    reply.writeNoException();
                    if (_result) {
                        _arg0 = true;
                    }
                    reply.writeInt(_arg0 ? 1 : 0);
                    return true;
                case 1598968902:
                    reply.writeString("com.alipay.android.app.IRemoteServiceCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean isHideLoadingScreen() throws RemoteException;

    void payEnd(boolean z, String str) throws RemoteException;

    void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException;
}
