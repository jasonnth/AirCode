package com.alipay.sdk.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.android.app.IRemoteServiceCallback.Stub;
import com.alipay.sdk.app.Utils.AlipayPkg;

public class AuthHelper {
    /* access modifiers changed from: private */
    public IAlixPay mAlixPay;
    private IRemoteServiceCallback mCallback = new Stub() {
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }

        public void payEnd(boolean arg0, String arg1) throws RemoteException {
        }

        public void startActivity(String packageName, String className, int callingPid, Bundle bundle) throws RemoteException {
            Intent intent = new Intent("android.intent.action.MAIN", null);
            if (bundle == null) {
                bundle = new Bundle();
            }
            try {
                bundle.putInt("CallingPid", callingPid);
                intent.putExtras(bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
            intent.setClassName(packageName, className);
            AuthHelper.this.mContext.startActivity(intent);
        }
    };
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mIsAuthing = false;
    /* access modifiers changed from: private */
    public Object mLock = IAlixPay.class;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName name) {
            AuthHelper.this.mAlixPay = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            synchronized (AuthHelper.this.mLock) {
                AuthHelper.this.mAlixPay = IAlixPay.Stub.asInterface(service);
                AuthHelper.this.mLock.notify();
            }
        }
    };

    public AuthHelper(Context context) {
        this.mContext = context;
    }

    public String auth4Client(String authInfo) {
        AlipayPkg pkg = Utils.getSign(this.mContext, "com.eg.android.AlipayGphone");
        if (pkg == null) {
            return "failed";
        }
        String publicKey = Utils.getPublicKey(pkg.sign);
        if (TextUtils.isEmpty(publicKey) || !TextUtils.equals(publicKey, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")) {
            return "failed";
        }
        if (pkg.versionCode > 78) {
            try {
                Intent transApp = new Intent();
                transApp.setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.TransProcessPayActivity");
                this.mContext.startActivity(transApp);
                Thread.sleep(150);
            } catch (Throwable th) {
            }
        }
        Intent intent = new Intent();
        intent.setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.MspService");
        intent.setAction("com.eg.android.AlipayGphone.IAlixPay");
        return auth(authInfo, intent);
    }

    private String auth(String authInfo, Intent intent) {
        String result = null;
        if (this.mIsAuthing) {
            return "";
        }
        this.mIsAuthing = true;
        if (this.mAlixPay == null) {
            this.mContext.getApplicationContext().bindService(intent, this.mServiceConnection, 1);
        }
        try {
            synchronized (this.mLock) {
                if (this.mAlixPay == null) {
                    this.mLock.wait(3000);
                }
            }
            if (this.mAlixPay == null) {
                String str = "failed";
                try {
                    this.mContext.unbindService(this.mServiceConnection);
                } catch (Exception e) {
                    this.mAlixPay = null;
                }
                this.mIsAuthing = false;
                return str;
            }
            this.mAlixPay.registerCallback(this.mCallback);
            result = this.mAlixPay.Pay(authInfo);
            this.mAlixPay.unregisterCallback(this.mCallback);
            this.mAlixPay = null;
            try {
                this.mContext.unbindService(this.mServiceConnection);
            } catch (Exception e2) {
                this.mAlixPay = null;
            }
            this.mIsAuthing = false;
            return result;
        } catch (Exception e3) {
            try {
                this.mContext.unbindService(this.mServiceConnection);
            } catch (Exception e4) {
                this.mAlixPay = null;
            }
            this.mIsAuthing = false;
        } catch (Throwable th) {
            try {
                this.mContext.unbindService(this.mServiceConnection);
            } catch (Exception e5) {
                this.mAlixPay = null;
            }
            this.mIsAuthing = false;
            throw th;
        }
    }
}
