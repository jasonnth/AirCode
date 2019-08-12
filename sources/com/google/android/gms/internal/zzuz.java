package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.internal.zzac;

public class zzuz {
    public static String zzct(String str) {
        boolean z = false;
        zzac.zzb(!TextUtils.isEmpty(str), (Object) "account type cannot be empty");
        String scheme = Uri.parse(str).getScheme();
        if (UriUtil.HTTP_SCHEME.equalsIgnoreCase(scheme) || UriUtil.HTTPS_SCHEME.equalsIgnoreCase(scheme)) {
            z = true;
        }
        zzac.zzb(z, (Object) "Account type must be an http or https URI");
        return str;
    }
}
