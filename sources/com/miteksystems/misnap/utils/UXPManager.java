package com.miteksystems.misnap.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UXPManager {
    private static UXPManager uxpManager;
    private final int UXP_INT_VALUE_IGNORED;
    private final String UXP_STR_VALUE_IGNORED;
    private long mStartTime;
    private List<C4579a> mUserMetrics;

    /* renamed from: com.miteksystems.misnap.utils.UXPManager$a */
    class C4579a {

        /* renamed from: a */
        long f3676a;

        /* renamed from: b */
        int f3677b;

        /* renamed from: c */
        String f3678c;

        /* renamed from: d */
        String f3679d;

        C4579a(String str, long j, int i) {
            this.f3676a = j;
            this.f3679d = str;
            this.f3677b = i;
            this.f3678c = "";
        }

        C4579a(String str, long j, String str2) {
            this.f3676a = j;
            this.f3679d = str;
            this.f3677b = -1;
            this.f3678c = str2;
        }

        public final String toString() {
            return this.f3679d + " at " + (this.f3676a / 1000) + "s of value " + (this.f3678c.equals("") ? Integer.valueOf(this.f3677b) : this.f3678c) + "; ";
        }
    }

    private UXPManager() {
        this.mUserMetrics = null;
        this.UXP_INT_VALUE_IGNORED = -1;
        this.UXP_STR_VALUE_IGNORED = "";
        this.mUserMetrics = new ArrayList();
        this.mStartTime = System.currentTimeMillis();
    }

    public static synchronized UXPManager getInstance() {
        UXPManager uXPManager;
        synchronized (UXPManager.class) {
            if (uxpManager == null) {
                uxpManager = new UXPManager();
            }
            uXPManager = uxpManager;
        }
        return uXPManager;
    }

    public void resetTime() {
        this.mUserMetrics.clear();
        this.mStartTime = System.currentTimeMillis();
    }

    public void cleanup() {
        resetTime();
        uxpManager = null;
    }

    /* access modifiers changed from: 0000 */
    public void addMessageToMetrics(String str) {
        addMessageToMetrics(str, -1);
    }

    /* access modifiers changed from: 0000 */
    public void addMessageToMetrics(String str, int i) {
        this.mUserMetrics.add(new C4579a(str, System.currentTimeMillis() - this.mStartTime, i));
    }

    /* access modifiers changed from: 0000 */
    public void addMessageToMetrics(String str, String str2) {
        this.mUserMetrics.add(new C4579a(str, System.currentTimeMillis() - this.mStartTime, str2));
    }

    public JSONArray getUXPMetrics() {
        JSONArray jSONArray = new JSONArray();
        for (C4579a aVar : this.mUserMetrics) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            try {
                if (aVar.f3677b == -1) {
                    if (aVar.f3678c.equals("")) {
                        jSONArray2.put(aVar.f3676a);
                        jSONObject.put(aVar.f3679d, jSONArray2);
                    } else {
                        jSONArray2.put(aVar.f3676a);
                        jSONArray2.put(aVar.f3678c);
                        jSONObject.put(aVar.f3679d, jSONArray2);
                    }
                    jSONArray.put(jSONObject);
                } else {
                    jSONArray2.put(aVar.f3676a);
                    jSONArray2.put(aVar.f3677b);
                    jSONObject.put(aVar.f3679d, jSONArray2);
                    jSONArray.put(jSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }
}
