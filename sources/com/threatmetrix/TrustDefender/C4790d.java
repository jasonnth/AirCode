package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.threatmetrix.TrustDefender.d */
class C4790d extends C4789c {
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static final String f4330i = C4834w.m2892a(C4790d.class);
    /* access modifiers changed from: private */

    /* renamed from: j */
    public Context f4331j = null;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public boolean f4332k = false;

    /* renamed from: l */
    private boolean f4333l = false;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public C1500u f4334m = null;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public C4832t f4335n = null;

    /* renamed from: o */
    private CountDownLatch f4336o = null;

    /* renamed from: p */
    private long f4337p = 0;

    /* renamed from: com.threatmetrix.TrustDefender.d$a */
    static class C4793a implements Runnable {

        /* renamed from: b */
        C4790d f4340b = null;

        /* renamed from: c */
        CountDownLatch f4341c = null;

        public C4793a(C4790d info, CountDownLatch latch) {
            this.f4340b = info;
            this.f4341c = latch;
        }

        public void run() {
            throw new NoSuchMethodError();
        }
    }

    C4790d() {
    }

    /* renamed from: e */
    static /* synthetic */ void m2756e(C4790d x0) throws InterruptedException {
        if (!Thread.currentThread().isInterrupted()) {
            if ((x0.f4337p & 32) != 0) {
                String a = x0.f4335n.mo46092a("(function () { var plugins_string='', i=0; for (p=navigator.plugins[0]; i< navigator.plugins.length;p=navigator.plugins[i++]) {  plugins_string += p.name + '<FIELD_SEP>' + p.description + '<FIELD_SEP>' + p.filename + '<FIELD_SEP>' + p.length.toString() + '<REC_SEP>'; } return plugins_string;})();");
                if (a != null) {
                    x0.m2751a(a);
                }
            }
            if (!Thread.currentThread().isInterrupted() && (x0.f4337p & 4) != 0) {
                String a2 = x0.f4335n.mo46092a("navigator.mimeTypes.length");
                if (a2 != null) {
                    try {
                        x0.f4327f = Integer.parseInt(a2);
                    } catch (NumberFormatException e) {
                        C4834w.m2895a(f4330i, "failed to convert", (Throwable) e);
                    }
                }
                x0.f4328g = x0.f4335n.mo46092a("(function () { var mime_string='', i=0; for (var m=navigator.mimeTypes[0]; i< navigator.mimeTypes.length;m=navigator.mimeTypes[i++]) {  mime_string += m.type; } return mime_string;})();");
                if (x0.f4328g != null) {
                    x0.f4329h = C4770ai.m2628b(x0.f4328g);
                    C4834w.m2901c(f4330i, "Got:" + x0.f4328g);
                    return;
                }
                x0.f4329h = "";
            }
        }
    }

    /* renamed from: a */
    public final String mo46035a() {
        if (this.f4324c == null) {
            if (this.f4335n == null || this.f4333l) {
                this.f4326e = C4832t.m2886c();
            } else {
                this.f4326e = this.f4335n.mo46091a(this.f4331j);
            }
        }
        return this.f4326e;
    }

    /* renamed from: a */
    private static String m2750a(String name, String description, ArrayList<HashMap<String, String>> pluginArray) {
        HashMap hashMap;
        String version = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
        Iterator it = pluginArray.iterator();
        while (true) {
            if (!it.hasNext()) {
                hashMap = null;
                break;
            }
            HashMap hashMap2 = (HashMap) it.next();
            String str = (String) hashMap2.get("name");
            if (str != null && str.toLowerCase(Locale.US).contains(name.toLowerCase(Locale.US))) {
                hashMap = hashMap2;
                break;
            }
        }
        if (hashMap != null) {
            String n = (String) hashMap.get("name");
            if (n != null) {
                version = n.replace("[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY -]", "");
                if (!version.isEmpty()) {
                    version = "true";
                }
            }
        }
        return description + "^" + version + "!";
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo46037a(Context context, boolean needWebview, long options) {
        boolean altJSInterface;
        this.f4331j = context;
        this.f4332k = needWebview;
        this.f4337p = options;
        if (!this.f4332k) {
            return false;
        }
        C4834w.m2901c(f4330i, "initJSExecutor: jsProblem = " + this.f4333l + ", jsExec = " + this.f4335n + ", hasBadOptions = " + (this.f4335n != null ? Boolean.valueOf(this.f4335n.mo46093a(needWebview)) : "true"));
        if ((this.f4333l || this.f4335n != null) && (this.f4335n == null || !this.f4335n.mo46093a(this.f4332k))) {
            C4834w.m2901c(f4330i, "reused JS Interface");
        } else {
            CountDownLatch latch = new CountDownLatch(1);
            Handler handler = new Handler(Looper.getMainLooper());
            if (C4832t.m2885b() || C4832t.m2884a()) {
                altJSInterface = true;
            } else {
                altJSInterface = false;
            }
            this.f4334m = new C1500u(altJSInterface ? latch : null);
            C4834w.m2901c(f4330i, "Firing off initJSExecutor on UI thread using latch: " + latch.hashCode() + " with count: " + latch.getCount());
            handler.post(new C4793a(this, latch) {
                public final void run() {
                    C4834w.m2901c(C4790d.f4330i, "Calling initJSExecutor() - on UI thread");
                    this.f4340b.f4335n = new C4832t(C4790d.this.f4331j, C4790d.this.f4334m, C4790d.this.f4332k);
                    try {
                        this.f4340b.f4335n.mo46094d();
                    } catch (InterruptedException e) {
                        C4834w.m2894a(C4790d.f4330i, "Interrupted initing js engine");
                    }
                    C4834w.m2901c(C4790d.f4330i, "js exec init complete");
                    if (this.f4341c != null) {
                        C4834w.m2901c(C4790d.f4330i, "js exec init countdown using latch: " + this.f4341c.hashCode() + " with count: " + this.f4341c.getCount());
                        this.f4341c.countDown();
                    }
                }
            });
            try {
                if (!latch.await(10, TimeUnit.SECONDS)) {
                    this.f4333l = true;
                    C4834w.m2894a(f4330i, "initJSExecutor no response from UI thread before timeout using init latch: " + latch.hashCode() + " with count: " + latch.getCount());
                    return false;
                }
            } catch (InterruptedException e) {
                C4834w.m2894a(f4330i, "Interrupted initing js engine");
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cc A[Catch:{ InterruptedException -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0106 A[Catch:{ InterruptedException -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0133 A[Catch:{ InterruptedException -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0139 A[Catch:{ InterruptedException -> 0x011b }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo46036a(boolean r11) {
        /*
            r10 = this;
            r8 = 0
            r2 = 1
            r1 = 0
            java.util.concurrent.CountDownLatch r3 = r10.f4336o
            if (r3 == 0) goto L_0x0113
            java.lang.String r3 = f4330i     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r5 = "waiting for getBrowserInfo to finished, latch: "
            r4.<init>(r5)     // Catch:{ InterruptedException -> 0x011b }
            java.util.concurrent.CountDownLatch r5 = r10.f4336o     // Catch:{ InterruptedException -> 0x011b }
            long r6 = r5.getCount()     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r5 = " - "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InterruptedException -> 0x011b }
            java.util.concurrent.CountDownLatch r5 = r10.f4336o     // Catch:{ InterruptedException -> 0x011b }
            int r5 = r5.hashCode()     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r4 = r4.toString()     // Catch:{ InterruptedException -> 0x011b }
            com.threatmetrix.TrustDefender.C4834w.m2901c(r3, r4)     // Catch:{ InterruptedException -> 0x011b }
            java.util.concurrent.CountDownLatch r3 = r10.f4336o     // Catch:{ InterruptedException -> 0x011b }
            r4 = 10
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x011b }
            boolean r3 = r3.await(r4, r6)     // Catch:{ InterruptedException -> 0x011b }
            if (r3 == 0) goto L_0x013d
            if (r11 == 0) goto L_0x0113
            boolean r3 = com.threatmetrix.TrustDefender.C4832t.m2885b()     // Catch:{ InterruptedException -> 0x011b }
            if (r3 != 0) goto L_0x004e
            boolean r3 = com.threatmetrix.TrustDefender.C4832t.m2884a()     // Catch:{ InterruptedException -> 0x011b }
            if (r3 == 0) goto L_0x0113
        L_0x004e:
            long r4 = r10.f4337p     // Catch:{ InterruptedException -> 0x011b }
            r6 = 32
            long r4 = r4 & r6
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 == 0) goto L_0x016d
            com.threatmetrix.TrustDefender.u r3 = r10.f4334m     // Catch:{ InterruptedException -> 0x011b }
            java.util.ArrayList<java.lang.String> r3 = r3.f1395c     // Catch:{ InterruptedException -> 0x011b }
            int r3 = r3.size()     // Catch:{ InterruptedException -> 0x011b }
            if (r3 <= 0) goto L_0x016d
            com.threatmetrix.TrustDefender.u r1 = r10.f4334m     // Catch:{ InterruptedException -> 0x011b }
            java.util.ArrayList<java.lang.String> r1 = r1.f1395c     // Catch:{ InterruptedException -> 0x011b }
            r3 = 0
            java.lang.Object r1 = r1.get(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ InterruptedException -> 0x011b }
            if (r1 == 0) goto L_0x0114
            boolean r3 = r1.isEmpty()     // Catch:{ InterruptedException -> 0x011b }
            if (r3 != 0) goto L_0x0114
            r10.m2751a(r1)     // Catch:{ InterruptedException -> 0x011b }
        L_0x0077:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x011b }
            boolean r1 = r1.isInterrupted()     // Catch:{ InterruptedException -> 0x011b }
            if (r1 != 0) goto L_0x0113
            long r4 = r10.f4337p     // Catch:{ InterruptedException -> 0x011b }
            r6 = 4
            long r4 = r4 & r6
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 == 0) goto L_0x0113
            com.threatmetrix.TrustDefender.u r1 = r10.f4334m     // Catch:{ InterruptedException -> 0x011b }
            java.util.ArrayList<java.lang.String> r1 = r1.f1395c     // Catch:{ InterruptedException -> 0x011b }
            int r1 = r1.size()     // Catch:{ InterruptedException -> 0x011b }
            if (r1 <= r2) goto L_0x0113
            com.threatmetrix.TrustDefender.u r1 = r10.f4334m     // Catch:{ InterruptedException -> 0x011b }
            java.util.ArrayList<java.lang.String> r1 = r1.f1395c     // Catch:{ InterruptedException -> 0x011b }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ InterruptedException -> 0x011b }
            int r2 = r2 + 1
            if (r1 == 0) goto L_0x012e
            boolean r3 = r1.isEmpty()     // Catch:{ InterruptedException -> 0x011b }
            if (r3 != 0) goto L_0x012e
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0125 }
            r10.f4327f = r1     // Catch:{ NumberFormatException -> 0x0125 }
        L_0x00ae:
            int r1 = r10.f4327f     // Catch:{ InterruptedException -> 0x011b }
            if (r1 <= 0) goto L_0x00c8
            com.threatmetrix.TrustDefender.u r1 = r10.f4334m     // Catch:{ InterruptedException -> 0x011b }
            java.util.ArrayList<java.lang.String> r1 = r1.f1395c     // Catch:{ InterruptedException -> 0x011b }
            int r1 = r1.size()     // Catch:{ InterruptedException -> 0x011b }
            if (r1 <= r2) goto L_0x00c8
            com.threatmetrix.TrustDefender.u r1 = r10.f4334m     // Catch:{ InterruptedException -> 0x011b }
            java.util.ArrayList<java.lang.String> r1 = r1.f1395c     // Catch:{ InterruptedException -> 0x011b }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ InterruptedException -> 0x011b }
            r10.f4328g = r1     // Catch:{ InterruptedException -> 0x011b }
        L_0x00c8:
            java.lang.String r1 = r10.f4328g     // Catch:{ InterruptedException -> 0x011b }
            if (r1 == 0) goto L_0x0133
            java.lang.String r1 = r10.f4328g     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r1 = com.threatmetrix.TrustDefender.C4770ai.m2628b(r1)     // Catch:{ InterruptedException -> 0x011b }
            r10.f4329h = r1     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r1 = f4330i     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r3 = "Got:"
            r2.<init>(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r3 = r10.f4328g     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r2 = r2.toString()     // Catch:{ InterruptedException -> 0x011b }
            com.threatmetrix.TrustDefender.C4834w.m2901c(r1, r2)     // Catch:{ InterruptedException -> 0x011b }
        L_0x00eb:
            java.lang.String r2 = f4330i     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r3 = "Got mime "
            r1.<init>(r3)     // Catch:{ InterruptedException -> 0x011b }
            int r3 = r10.f4327f     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r3 = ":"
            java.lang.StringBuilder r3 = r1.append(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r1 = r10.f4328g     // Catch:{ InterruptedException -> 0x011b }
            if (r1 == 0) goto L_0x0139
            java.lang.String r1 = r10.f4328g     // Catch:{ InterruptedException -> 0x011b }
        L_0x0108:
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r1 = r1.toString()     // Catch:{ InterruptedException -> 0x011b }
            com.threatmetrix.TrustDefender.C4834w.m2901c(r2, r1)     // Catch:{ InterruptedException -> 0x011b }
        L_0x0113:
            return
        L_0x0114:
            java.lang.String r1 = ""
            r10.f4325d = r1     // Catch:{ InterruptedException -> 0x011b }
            goto L_0x0077
        L_0x011b:
            r0 = move-exception
            java.lang.String r1 = f4330i
            java.lang.String r2 = "getBrowserInfo interrupted"
            com.threatmetrix.TrustDefender.C4834w.m2895a(r1, r2, r0)
            goto L_0x0113
        L_0x0125:
            r1 = move-exception
            java.lang.String r3 = f4330i     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r4 = "failed to convert"
            com.threatmetrix.TrustDefender.C4834w.m2895a(r3, r4, r1)     // Catch:{ InterruptedException -> 0x011b }
        L_0x012e:
            r1 = 0
            r10.f4327f = r1     // Catch:{ InterruptedException -> 0x011b }
            goto L_0x00ae
        L_0x0133:
            java.lang.String r1 = ""
            r10.f4329h = r1     // Catch:{ InterruptedException -> 0x011b }
            goto L_0x00eb
        L_0x0139:
            java.lang.String r1 = ""
            goto L_0x0108
        L_0x013d:
            java.lang.String r1 = f4330i     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r3 = "getBrowserInfo no response from UI thread before timeout using latch: "
            r2.<init>(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.util.concurrent.CountDownLatch r3 = r10.f4336o     // Catch:{ InterruptedException -> 0x011b }
            int r3 = r3.hashCode()     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r3 = " with count: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ InterruptedException -> 0x011b }
            java.util.concurrent.CountDownLatch r3 = r10.f4336o     // Catch:{ InterruptedException -> 0x011b }
            long r4 = r3.getCount()     // Catch:{ InterruptedException -> 0x011b }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ InterruptedException -> 0x011b }
            java.lang.String r2 = r2.toString()     // Catch:{ InterruptedException -> 0x011b }
            com.threatmetrix.TrustDefender.C4834w.m2894a(r1, r2)     // Catch:{ InterruptedException -> 0x011b }
            r1 = 1
            r10.f4333l = r1     // Catch:{ InterruptedException -> 0x011b }
            goto L_0x0113
        L_0x016d:
            r2 = r1
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4790d.mo46036a(boolean):void");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final boolean mo46038b() {
        return (this.f4335n != null && !this.f4333l) && this.f4332k;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final void mo46039c() {
        boolean altJSInterface = C4832t.m2885b() || C4832t.m2884a();
        int latchCount = 1;
        if (altJSInterface) {
            if ((this.f4337p & 32) != 0) {
                latchCount = 1 + 1;
            }
            if ((this.f4337p & 4) != 0) {
                latchCount += 2;
            }
        }
        this.f4336o = new CountDownLatch(latchCount);
        Handler handler = new Handler(Looper.getMainLooper());
        C4834w.m2901c(f4330i, "Firing off getBrowserInfo on UI thread using latch: " + this.f4336o.hashCode() + " with count: " + latchCount);
        this.f4334m.mo17671a(altJSInterface ? this.f4336o : null);
        handler.post(new C4793a(this, this.f4336o) {
            public final void run() {
                try {
                    C4834w.m2901c(C4790d.f4330i, "Calling getBrowserInfo() - on UI thread");
                    C4790d.m2756e(this.f4340b);
                } catch (InterruptedException e) {
                    C4834w.m2902c(C4790d.f4330i, "getBrowserInfo interrupted", e);
                }
                if (this.f4341c != null) {
                    C4834w.m2901c(C4790d.f4330i, "getBrowserInfo countdown using latch: " + this.f4341c.hashCode() + " with count: " + this.f4341c.getCount());
                    this.f4341c.countDown();
                }
            }
        });
    }

    /* renamed from: a */
    private void m2751a(String jsPluginList) throws InterruptedException {
        this.f4324c = jsPluginList.replaceAll("(<FIELD_SEP>|<REC_SEP>)", "");
        this.f4325d = C4770ai.m2628b(this.f4324c);
        ArrayList<HashMap<String, String>> pluginArray = new ArrayList<>();
        String[] arr$ = jsPluginList.split("<REC_SEP>");
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String pluginString = arr$[i$];
            if (!Thread.currentThread().isInterrupted()) {
                HashMap<String, String> p = new HashMap<>();
                String[] arr = pluginString.split("<FIELD_SEP>");
                if (arr.length == 4) {
                    p.put("name", arr[0]);
                    p.put("description", arr[1]);
                    p.put("filename", arr[2]);
                    p.put("length", arr[3]);
                    pluginArray.add(p);
                }
                i$++;
            } else {
                return;
            }
        }
        this.f4323b = Integer.toString(pluginArray.size());
        StringBuilder plugins = new StringBuilder();
        plugins.append(m2750a("QuickTime Plug-in", "plugin_quicktime", pluginArray));
        plugins.append(m2750a("Adobe Acrobat", "plugin_adobe_acrobat", pluginArray));
        plugins.append(m2750a("Java", "plugin_java", pluginArray));
        plugins.append(m2750a("SVG Viewer", "plugin_svg_viewer", pluginArray));
        plugins.append(m2750a("Flash", "plugin_flash", pluginArray));
        plugins.append(m2750a("Windows Media Player", "plugin_windows_media_player", pluginArray));
        plugins.append(m2750a("Silverlight", "plugin_silverlight", pluginArray));
        plugins.append(m2750a("Real Player", "plugin_realplayer", pluginArray));
        plugins.append(m2750a("ShockWave Director", "plugin_shockwave", pluginArray));
        plugins.append(m2750a("VLC", "plugin_vlc_player", pluginArray));
        plugins.append(m2750a("DevalVR", "plugin_devalvr", pluginArray));
        this.f4322a = plugins.toString();
        C4834w.m2901c(f4330i, "Got" + this.f4323b + ":" + (this.f4322a != null ? this.f4322a : ""));
    }
}
