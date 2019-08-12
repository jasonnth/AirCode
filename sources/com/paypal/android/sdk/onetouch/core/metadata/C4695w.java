package com.paypal.android.sdk.onetouch.core.metadata;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.w */
public final class C4695w extends C4670aa {

    /* renamed from: f */
    private String f4037f;

    /* renamed from: g */
    private HashMap<String, String> f4038g;

    /* renamed from: h */
    private Map<String, String> f4039h = new HashMap();

    /* renamed from: i */
    private Handler f4040i;

    /* renamed from: j */
    private boolean f4041j;

    static {
        C4682h.class.getSimpleName();
    }

    public C4695w(String str, HashMap<String, String> hashMap, Handler handler, boolean z) {
        this.f4037f = str;
        this.f4038g = hashMap;
        this.f4040i = handler;
        this.f4041j = z;
    }

    public final void run() {
        boolean z;
        boolean z2 = true;
        if (this.f4040i != null) {
            try {
                this.f4040i.sendMessage(Message.obtain(this.f4040i, 0, this.f4037f));
                if (!this.f4041j) {
                    this.f4039h.put("CLIENT-AUTH", "No cert");
                }
                this.f4039h.put("X-PAYPAL-RESPONSE-DATA-FORMAT", "NV");
                this.f4039h.put("X-PAYPAL-REQUEST-DATA-FORMAT", "NV");
                this.f4039h.put("X-PAYPAL-SERVICE-VERSION", "1.0.0");
                if (this.f4041j) {
                    C4692t a = C4682h.f3991b.mo45438a();
                    a.mo45435a(Uri.parse(this.f4037f));
                    a.mo45436a(this.f4039h);
                    HashMap<String, String> hashMap = this.f4038g;
                    StringBuilder sb = new StringBuilder();
                    for (Entry entry : hashMap.entrySet()) {
                        if (z2) {
                            z = false;
                        } else {
                            sb.append("&");
                            z = z2;
                        }
                        sb.append(URLEncoder.encode((String) entry.getKey(), JPushConstants.ENCODING_UTF_8));
                        sb.append("=");
                        sb.append(URLEncoder.encode((String) entry.getValue(), JPushConstants.ENCODING_UTF_8));
                        z2 = z;
                    }
                    int a2 = a.mo45434a(sb.toString().getBytes(JPushConstants.ENCODING_UTF_8));
                    if (a2 != 200) {
                        throw new Exception("Network Connection Error with wrong http code: " + a2);
                    }
                    this.f4040i.sendMessage(Message.obtain(this.f4040i, 2, new String(a.mo45437b(), JPushConstants.ENCODING_UTF_8)));
                } else {
                    this.f4040i.sendMessage(Message.obtain(this.f4040i, 2, "not supported"));
                }
            } catch (Exception e) {
                this.f4040i.sendMessage(Message.obtain(this.f4040i, 1, e));
            } finally {
                C4671ab.m2370a().mo45395b(this);
            }
        }
    }
}
