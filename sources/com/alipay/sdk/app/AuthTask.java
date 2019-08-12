package com.alipay.sdk.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

public class AuthTask {
    private Context mContext;

    public AuthTask(Context context) {
        this.mContext = context;
    }

    public String auth(String info) {
        AuthHelper authHelper = new AuthHelper(this.mContext);
        String result = authHelper.auth4Client(new BizContext(this.mContext).format(info));
        if (TextUtils.equals(result, "failed")) {
            result = authHelper.auth4Client(info);
            if (TextUtils.equals(result, "failed")) {
                return Result.getError();
            }
        } else if (TextUtils.isEmpty(result)) {
            return Result.getCancel();
        }
        return result;
    }

    public boolean isExistAlipay() {
        try {
            PackageInfo apkInfo = this.mContext.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
            if (apkInfo != null && apkInfo.versionCode >= 73) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
