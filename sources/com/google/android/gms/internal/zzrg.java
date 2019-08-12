package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzw;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p005cn.jpush.android.JPushConstants;

@zzme
@TargetApi(11)
public class zzrg extends zzqx {
    public zzrg(zzqw zzqw, boolean z) {
        super(zzqw, z);
    }

    /* access modifiers changed from: protected */
    public WebResourceResponse zza(WebView webView, String str, Map<String, String> map) {
        if (!(webView instanceof zzqw)) {
            zzpk.zzbh("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzqw zzqw = (zzqw) webView;
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            return super.shouldInterceptRequest(webView, str);
        }
        if (zzqw.zzlv() != null) {
            zzqw.zzlv().zzhG();
        }
        String str2 = zzqw.zzbC().zzzz ? (String) zzgd.zzBY.get() : zzqw.zzlz() ? (String) zzgd.zzBX.get() : (String) zzgd.zzBW.get();
        try {
            return zzn(zzqw.getContext(), zzqw.zzly().zzba, str2);
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            String str3 = "Could not fetch MRAID JS. ";
            String valueOf = String.valueOf(e.getMessage());
            zzpk.zzbh(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public WebResourceResponse zzn(Context context, String str, String str2) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", zzw.zzcM().zzu(context, str));
        hashMap.put("Cache-Control", "max-stale=3600");
        String str3 = (String) new zzpv(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
        if (str3 == null) {
            return null;
        }
        return new WebResourceResponse("application/javascript", JPushConstants.ENCODING_UTF_8, new ByteArrayInputStream(str3.getBytes(JPushConstants.ENCODING_UTF_8)));
    }
}
