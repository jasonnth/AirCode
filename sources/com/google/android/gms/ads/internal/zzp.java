package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.google.android.gms.ads.internal.zzg.zza;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzgv;
import com.google.android.gms.internal.zzgw;
import com.google.android.gms.internal.zzhf;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzjv;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

@zzme
public class zzp {
    private static zzgv zza(zzke zzke) throws RemoteException {
        return new zzgv(zzke.getHeadline(), zzke.getImages(), zzke.getBody(), zzke.zzfQ(), zzke.getCallToAction(), zzke.getStarRating(), zzke.getStore(), zzke.getPrice(), null, zzke.getExtras(), null, null);
    }

    private static zzgw zza(zzkf zzkf) throws RemoteException {
        return new zzgw(zzkf.getHeadline(), zzkf.getImages(), zzkf.getBody(), zzkf.zzfV(), zzkf.getCallToAction(), zzkf.getAdvertiser(), null, zzkf.getExtras(), null, null);
    }

    static zzid zza(final zzke zzke, final zzkf zzkf, final zza zza) {
        return new zzid() {
            public void zza(zzqw zzqw, Map<String, String> map) {
                View view = zzqw.getView();
                if (view != null) {
                    try {
                        if (zzke.this != null) {
                            if (!zzke.this.getOverrideClickHandling()) {
                                zzke.this.zzl(zzd.zzA(view));
                                zza.onClick();
                                return;
                            }
                            zzp.zza(zzqw);
                        } else if (zzkf == null) {
                        } else {
                            if (!zzkf.getOverrideClickHandling()) {
                                zzkf.zzl(zzd.zzA(view));
                                zza.onClick();
                                return;
                            }
                            zzp.zza(zzqw);
                        }
                    } catch (RemoteException e) {
                        zzpk.zzc("Unable to call handleClick on mapper", e);
                    }
                }
            }
        };
    }

    static zzid zza(final CountDownLatch countDownLatch) {
        return new zzid() {
            public void zza(zzqw zzqw, Map<String, String> map) {
                countDownLatch.countDown();
                zzqw.getView().setVisibility(0);
            }
        };
    }

    private static String zza(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzpk.zzbh("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        String valueOf2 = String.valueOf(encodeToString);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    static String zza(zzhf zzhf) {
        if (zzhf == null) {
            zzpk.zzbh("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzhf.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzpk.zzbh("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzhf);
    }

    /* access modifiers changed from: private */
    public static JSONObject zza(Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if (ContentFrameworkAnalytics.IMAGE.equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzpk.zzbh("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzpk.zzbh("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    public static void zza(zzpb zzpb, zza zza) {
        zzkf zzkf = null;
        if (zzpb != null && zzh(zzpb)) {
            zzqw zzqw = zzpb.zzNH;
            Object obj = zzqw != null ? zzqw.getView() : null;
            if (obj == null) {
                zzpk.zzbh("AdWebView is null");
                return;
            }
            try {
                List list = zzpb.zzLi != null ? zzpb.zzLi.zzKB : null;
                if (list == null || list.isEmpty()) {
                    zzpk.zzbh("No template ids present in mediation response");
                    return;
                }
                zzke zzke = zzpb.zzLj != null ? zzpb.zzLj.zzhc() : null;
                if (zzpb.zzLj != null) {
                    zzkf = zzpb.zzLj.zzhd();
                }
                if (list.contains("2") && zzke != null) {
                    zzke.zzm(zzd.zzA(obj));
                    if (!zzke.getOverrideImpressionRecording()) {
                        zzke.recordImpression();
                    }
                    zzqw.zzlv().zza("/nativeExpressViewClicked", zza(zzke, (zzkf) null, zza));
                } else if (!list.contains("1") || zzkf == null) {
                    zzpk.zzbh("No matching template id and mapper");
                } else {
                    zzkf.zzm(zzd.zzA(obj));
                    if (!zzkf.getOverrideImpressionRecording()) {
                        zzkf.recordImpression();
                    }
                    zzqw.zzlv().zza("/nativeExpressViewClicked", zza((zzke) null, zzkf, zza));
                }
            } catch (RemoteException e) {
                zzpk.zzc("Error occurred while recording impression and registering for clicks", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void zza(zzqw zzqw) {
        OnClickListener zzlK = zzqw.zzlK();
        if (zzlK != null) {
            zzlK.onClick(zzqw.getView());
        }
    }

    private static void zza(final zzqw zzqw, final zzgv zzgv, final String str) {
        zzqw.zzlv().zza((zzqx.zza) new zzqx.zza() {
            public void zza(zzqw zzqw, boolean z) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("headline", zzgv.this.getHeadline());
                    jSONObject.put("body", zzgv.this.getBody());
                    jSONObject.put("call_to_action", zzgv.this.getCallToAction());
                    jSONObject.put(EditPriceFragment.RESULT_PRICE, zzgv.this.getPrice());
                    jSONObject.put("star_rating", String.valueOf(zzgv.this.getStarRating()));
                    jSONObject.put("store", zzgv.this.getStore());
                    jSONObject.put("icon", zzp.zza(zzgv.this.zzfQ()));
                    JSONArray jSONArray = new JSONArray();
                    List<Object> images = zzgv.this.getImages();
                    if (images != null) {
                        for (Object zzf : images) {
                            jSONArray.put(zzp.zza(zzp.zze(zzf)));
                        }
                    }
                    jSONObject.put("images", jSONArray);
                    jSONObject.put("extras", zzp.zza(zzgv.this.getExtras(), str));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("assets", jSONObject);
                    jSONObject2.put("template_id", "2");
                    zzqw.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
                } catch (JSONException e) {
                    zzpk.zzc("Exception occurred when loading assets", e);
                }
            }
        });
    }

    private static void zza(final zzqw zzqw, final zzgw zzgw, final String str) {
        zzqw.zzlv().zza((zzqx.zza) new zzqx.zza() {
            public void zza(zzqw zzqw, boolean z) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("headline", zzgw.this.getHeadline());
                    jSONObject.put("body", zzgw.this.getBody());
                    jSONObject.put("call_to_action", zzgw.this.getCallToAction());
                    jSONObject.put("advertiser", zzgw.this.getAdvertiser());
                    jSONObject.put("logo", zzp.zza(zzgw.this.zzfV()));
                    JSONArray jSONArray = new JSONArray();
                    List<Object> images = zzgw.this.getImages();
                    if (images != null) {
                        for (Object zzf : images) {
                            jSONArray.put(zzp.zza(zzp.zze(zzf)));
                        }
                    }
                    jSONObject.put("images", jSONArray);
                    jSONObject.put("extras", zzp.zza(zzgw.this.getExtras(), str));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("assets", jSONObject);
                    jSONObject2.put("template_id", "1");
                    zzqw.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
                } catch (JSONException e) {
                    zzpk.zzc("Exception occurred when loading assets", e);
                }
            }
        });
    }

    private static void zza(zzqw zzqw, CountDownLatch countDownLatch) {
        zzqw.zzlv().zza("/nativeExpressAssetsLoaded", zza(countDownLatch));
        zzqw.zzlv().zza("/nativeExpressAssetsLoadingFailed", zzb(countDownLatch));
    }

    public static boolean zza(zzqw zzqw, zzjv zzjv, CountDownLatch countDownLatch) {
        boolean z = false;
        try {
            z = zzb(zzqw, zzjv, countDownLatch);
        } catch (RemoteException e) {
            zzpk.zzc("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    static zzid zzb(final CountDownLatch countDownLatch) {
        return new zzid() {
            public void zza(zzqw zzqw, Map<String, String> map) {
                zzpk.zzbh("Adapter returned an ad, but assets substitution failed");
                countDownLatch.countDown();
                zzqw.destroy();
            }
        };
    }

    private static String zzb(zzhf zzhf) {
        try {
            IObjectWrapper zzfP = zzhf.zzfP();
            if (zzfP == null) {
                zzpk.zzbh("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) zzd.zzF(zzfP);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzpk.zzbh("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzpk.zzbh("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    private static boolean zzb(zzqw zzqw, zzjv zzjv, CountDownLatch countDownLatch) throws RemoteException {
        View view = zzqw.getView();
        if (view == null) {
            zzpk.zzbh("AdWebView is null");
            return false;
        }
        view.setVisibility(4);
        List<String> list = zzjv.zzLi.zzKB;
        if (list == null || list.isEmpty()) {
            zzpk.zzbh("No template ids present in mediation response");
            return false;
        }
        zza(zzqw, countDownLatch);
        zzke zzhc = zzjv.zzLj.zzhc();
        zzkf zzhd = zzjv.zzLj.zzhd();
        if (list.contains("2") && zzhc != null) {
            zza(zzqw, zza(zzhc), zzjv.zzLi.zzKA);
        } else if (!list.contains("1") || zzhd == null) {
            zzpk.zzbh("No matching template id and mapper");
            return false;
        } else {
            zza(zzqw, zza(zzhd), zzjv.zzLi.zzKA);
        }
        String str = zzjv.zzLi.zzKy;
        String str2 = zzjv.zzLi.zzKz;
        if (str2 != null) {
            zzqw.loadDataWithBaseURL(str2, str, "text/html", JPushConstants.ENCODING_UTF_8, null);
        } else {
            zzqw.loadData(str, "text/html", JPushConstants.ENCODING_UTF_8);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static zzhf zze(Object obj) {
        if (obj instanceof IBinder) {
            return zzhf.zza.zzB((IBinder) obj);
        }
        return null;
    }

    public static View zzg(zzpb zzpb) {
        if (zzpb == null) {
            zzpk.m1280e("AdState is null");
            return null;
        } else if (zzh(zzpb) && zzpb.zzNH != null) {
            return zzpb.zzNH.getView();
        } else {
            try {
                IObjectWrapper iObjectWrapper = zzpb.zzLj != null ? zzpb.zzLj.getView() : null;
                if (iObjectWrapper != null) {
                    return (View) zzd.zzF(iObjectWrapper);
                }
                zzpk.zzbh("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                zzpk.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzh(zzpb zzpb) {
        return (zzpb == null || !zzpb.zzSn || zzpb.zzLi == null || zzpb.zzLi.zzKy == null) ? false : true;
    }
}
