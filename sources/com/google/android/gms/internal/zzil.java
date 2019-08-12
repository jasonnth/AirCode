package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.ads.internal.zzw;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzme
public final class zzil implements zzid {
    private final zzf zzIo;
    private final zzkr zzIp;
    private final zzif zzIr;

    public static class zza {
        private final zzqw zzIs;

        public zza(zzqw zzqw) {
            this.zzIs = zzqw;
        }

        public Intent zza(Context context, Map<String, String> map) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            String str = (String) map.get("u");
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (this.zzIs != null) {
                str = zzw.zzcM().zza(this.zzIs, str);
            }
            Uri parse = Uri.parse(str);
            boolean parseBoolean = Boolean.parseBoolean((String) map.get("use_first_package"));
            boolean parseBoolean2 = Boolean.parseBoolean((String) map.get("use_running_process"));
            Uri uri = UriUtil.HTTP_SCHEME.equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme(UriUtil.HTTPS_SCHEME).build() : UriUtil.HTTPS_SCHEME.equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme(UriUtil.HTTP_SCHEME).build() : null;
            ArrayList arrayList = new ArrayList();
            Intent zzf = zzf(parse);
            Intent zzf2 = zzf(uri);
            ResolveInfo zza = zza(context, zzf, arrayList);
            if (zza != null) {
                return zza(zzf, zza);
            }
            if (zzf2 != null) {
                ResolveInfo zza2 = zza(context, zzf2);
                if (zza2 != null) {
                    Intent zza3 = zza(zzf, zza2);
                    if (zza(context, zza3) != null) {
                        return zza3;
                    }
                }
            }
            if (arrayList.size() == 0) {
                return zzf;
            }
            if (parseBoolean2 && activityManager != null) {
                List runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ResolveInfo resolveInfo = (ResolveInfo) it.next();
                        Iterator it2 = runningAppProcesses.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (((RunningAppProcessInfo) it2.next()).processName.equals(resolveInfo.activityInfo.packageName)) {
                                    return zza(zzf, resolveInfo);
                                }
                            }
                        }
                    }
                }
            }
            return parseBoolean ? zza(zzf, (ResolveInfo) arrayList.get(0)) : zzf;
        }

        public Intent zza(Intent intent, ResolveInfo resolveInfo) {
            Intent intent2 = new Intent(intent);
            intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            return intent2;
        }

        public ResolveInfo zza(Context context, Intent intent) {
            return zza(context, intent, new ArrayList());
        }

        public ResolveInfo zza(Context context, Intent intent, ArrayList<ResolveInfo> arrayList) {
            ResolveInfo resolveInfo;
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= queryIntentActivities.size()) {
                        break;
                    }
                    ResolveInfo resolveInfo2 = (ResolveInfo) queryIntentActivities.get(i2);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo = resolveActivity;
                        break;
                    }
                    i = i2 + 1;
                }
            }
            resolveInfo = null;
            arrayList.addAll(queryIntentActivities);
            return resolveInfo;
        }

        public Intent zzf(Uri uri) {
            if (uri == null) {
                return null;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(uri);
            intent.setAction("android.intent.action.VIEW");
            return intent;
        }
    }

    public zzil(zzif zzif, zzf zzf, zzkr zzkr) {
        this.zzIr = zzif;
        this.zzIo = zzf;
        this.zzIp = zzkr;
    }

    private static boolean zzd(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zze(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return zzw.zzcO().zzkR();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzw.zzcO().zzkQ();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzw.zzcO().zzkS();
            }
        }
        return -1;
    }

    private static void zzf(zzqw zzqw, Map<String, String> map) {
        Context context = zzqw.getContext();
        if (TextUtils.isEmpty((String) map.get("u"))) {
            zzpk.zzbh("Destination url cannot be empty.");
            return;
        }
        try {
            zzqw.zzlv().zza(new zzc(new zza(zzqw).zza(context, map)));
        } catch (ActivityNotFoundException e) {
            zzpk.zzbh(e.getMessage());
        }
    }

    private void zzq(boolean z) {
        if (this.zzIp != null) {
            this.zzIp.zzr(z);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0194  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(com.google.android.gms.internal.zzqw r10, java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
            r9 = this;
            r3 = 1
            r2 = 0
            java.lang.String r0 = "a"
            java.lang.Object r0 = r11.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = "Action missing from an open GMSG."
            com.google.android.gms.internal.zzpk.zzbh(r0)
        L_0x0013:
            return
        L_0x0014:
            com.google.android.gms.ads.internal.zzf r1 = r9.zzIo
            if (r1 == 0) goto L_0x002f
            com.google.android.gms.ads.internal.zzf r1 = r9.zzIo
            boolean r1 = r1.zzcd()
            if (r1 != 0) goto L_0x002f
            com.google.android.gms.ads.internal.zzf r1 = r9.zzIo
            java.lang.String r0 = "u"
            java.lang.Object r0 = r11.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r1.zzx(r0)
            goto L_0x0013
        L_0x002f:
            com.google.android.gms.internal.zzqx r8 = r10.zzlv()
            java.lang.String r1 = "expand"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x0058
            boolean r0 = r10.zzlz()
            if (r0 == 0) goto L_0x0049
            java.lang.String r0 = "Cannot expand WebView that is already expanded."
            com.google.android.gms.internal.zzpk.zzbh(r0)
            goto L_0x0013
        L_0x0049:
            r9.zzq(r2)
            boolean r0 = zzd(r11)
            int r1 = zze(r11)
            r8.zza(r0, r1)
            goto L_0x0013
        L_0x0058:
            java.lang.String r1 = "webapp"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x009a
            java.lang.String r0 = "u"
            java.lang.Object r0 = r11.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r9.zzq(r2)
            if (r0 == 0) goto L_0x007b
            boolean r1 = zzd(r11)
            int r2 = zze(r11)
            r8.zza(r1, r2, r0)
            goto L_0x0013
        L_0x007b:
            boolean r2 = zzd(r11)
            int r3 = zze(r11)
            java.lang.String r0 = "html"
            java.lang.Object r0 = r11.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "baseurl"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            r8.zza(r2, r3, r0, r1)
            goto L_0x0013
        L_0x009a:
            java.lang.String r1 = "in_app_purchase"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x00e4
            java.lang.String r0 = "product_id"
            java.lang.Object r0 = r11.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "report_urls"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            com.google.android.gms.internal.zzif r2 = r9.zzIr
            if (r2 == 0) goto L_0x0013
            if (r1 == 0) goto L_0x00d8
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x00d8
            java.lang.String r2 = " "
            java.lang.String[] r1 = r1.split(r2)
            com.google.android.gms.internal.zzif r2 = r9.zzIr
            java.util.ArrayList r3 = new java.util.ArrayList
            java.util.List r1 = java.util.Arrays.asList(r1)
            r3.<init>(r1)
            r2.zza(r0, r3)
            goto L_0x0013
        L_0x00d8:
            com.google.android.gms.internal.zzif r1 = r9.zzIr
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.zza(r0, r2)
            goto L_0x0013
        L_0x00e4:
            java.lang.String r1 = "app"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x0107
            java.lang.String r1 = "true"
            java.lang.String r0 = "system_browser"
            java.lang.Object r0 = r11.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x0107
            r9.zzq(r3)
            zzf(r10, r11)
            goto L_0x0013
        L_0x0107:
            r9.zzq(r3)
            java.lang.String r0 = "intent_url"
            java.lang.Object r0 = r11.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "u"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            r2 = 0
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x016f
            r3 = 0
            android.content.Intent r0 = android.content.Intent.parseUri(r0, r3)     // Catch:{ URISyntaxException -> 0x015a }
            r3 = r0
        L_0x0129:
            if (r3 == 0) goto L_0x014e
            android.net.Uri r0 = r3.getData()
            if (r0 == 0) goto L_0x014e
            android.net.Uri r2 = r3.getData()
            java.lang.String r0 = r2.toString()
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L_0x018c
            com.google.android.gms.internal.zzpo r4 = com.google.android.gms.ads.internal.zzw.zzcM()
            java.lang.String r0 = r4.zza(r10, r0)
            android.net.Uri r0 = android.net.Uri.parse(r0)     // Catch:{ Exception -> 0x0177 }
        L_0x014b:
            r3.setData(r0)
        L_0x014e:
            if (r3 == 0) goto L_0x0194
            com.google.android.gms.ads.internal.overlay.zzc r0 = new com.google.android.gms.ads.internal.overlay.zzc
            r0.<init>(r3)
            r8.zza(r0)
            goto L_0x0013
        L_0x015a:
            r3 = move-exception
            java.lang.String r4 = "Error parsing the url: "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r5 = r0.length()
            if (r5 == 0) goto L_0x0171
            java.lang.String r0 = r4.concat(r0)
        L_0x016c:
            com.google.android.gms.internal.zzpk.zzb(r0, r3)
        L_0x016f:
            r3 = r2
            goto L_0x0129
        L_0x0171:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            goto L_0x016c
        L_0x0177:
            r4 = move-exception
            java.lang.String r5 = "Error parsing the uri: "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r6 = r0.length()
            if (r6 == 0) goto L_0x018e
            java.lang.String r0 = r5.concat(r0)
        L_0x0189:
            com.google.android.gms.internal.zzpk.zzb(r0, r4)
        L_0x018c:
            r0 = r2
            goto L_0x014b
        L_0x018e:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r5)
            goto L_0x0189
        L_0x0194:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x01e2
            com.google.android.gms.internal.zzpo r0 = com.google.android.gms.ads.internal.zzw.zzcM()
            java.lang.String r2 = r0.zza(r10, r1)
        L_0x01a2:
            com.google.android.gms.ads.internal.overlay.zzc r0 = new com.google.android.gms.ads.internal.overlay.zzc
            java.lang.String r1 = "i"
            java.lang.Object r1 = r11.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r3 = "m"
            java.lang.Object r3 = r11.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "p"
            java.lang.Object r4 = r11.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "c"
            java.lang.Object r5 = r11.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "f"
            java.lang.Object r6 = r11.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "e"
            java.lang.Object r7 = r11.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            r8.zza(r0)
            goto L_0x0013
        L_0x01e2:
            r2 = r1
            goto L_0x01a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzil.zza(com.google.android.gms.internal.zzqw, java.util.Map):void");
    }
}
