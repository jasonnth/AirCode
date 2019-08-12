package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzcy.zzb;
import java.util.Map;
import org.json.JSONObject;

@zzme
public interface zzqw extends zzu, zzb, zzjj {
    void destroy();

    Context getContext();

    LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    int getMeasuredHeight();

    int getMeasuredWidth();

    ViewParent getParent();

    String getRequestId();

    int getRequestedOrientation();

    View getView();

    WebView getWebView();

    boolean isDestroyed();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    void setBackgroundColor(int i);

    void setContext(Context context);

    void setOnClickListener(OnClickListener onClickListener);

    void setOnTouchListener(OnTouchListener onTouchListener);

    void setRequestedOrientation(int i);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void stopLoading();

    void zzJ(boolean z);

    void zzK(boolean z);

    void zzL(boolean z);

    void zzM(int i);

    void zzM(boolean z);

    void zza(Context context, zzeg zzeg, zzgl zzgl);

    void zza(zzeg zzeg);

    void zza(zzrb zzrb);

    void zza(String str, Map<String, ?> map);

    void zza(String str, JSONObject jSONObject);

    void zzb(zze zze);

    void zzb(zzgy zzgy);

    zzeg zzbC();

    void zzbi(String str);

    void zzbj(String str);

    com.google.android.gms.ads.internal.zze zzby();

    void zzc(zze zze);

    void zzhK();

    void zzj(String str, String str2);

    void zzlA();

    boolean zzlB();

    boolean zzlC();

    zzqv zzlD();

    zzgj zzlE();

    zzgk zzlF();

    zzrb zzlG();

    boolean zzlH();

    void zzlI();

    void zzlJ();

    OnClickListener zzlK();

    zzgy zzlL();

    void zzlM();

    void zzlp();

    void zzlq();

    Activity zzlr();

    Context zzls();

    zze zzlt();

    zze zzlu();

    zzqx zzlv();

    boolean zzlw();

    zzaw zzlx();

    zzqh zzly();

    boolean zzlz();
}
