package com.mparticle.internal;

import android.content.SharedPreferences;
import com.mparticle.BuildConfig;
import com.mparticle.MParticle;
import com.mparticle.MParticle.Environment;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.mparticle.internal.l */
public class C4619l extends JSONObject {
    private C4619l() {
    }

    /* renamed from: a */
    public static C4619l m2293a(boolean z, ConfigManager configManager, SharedPreferences sharedPreferences, JSONObject jSONObject) throws JSONException {
        C4619l lVar = new C4619l();
        if (BuildConfig.MP_DEBUG.booleanValue()) {
            lVar.put("echo", true);
        }
        lVar.put("dt", "h");
        lVar.put("id", UUID.randomUUID().toString());
        lVar.put("ct", System.currentTimeMillis());
        lVar.put("sdk", BuildConfig.VERSION_NAME);
        lVar.put("oo", configManager.getOptedOut());
        lVar.put("uitl", configManager.getUploadInterval() / 1000);
        lVar.put("stl", configManager.getSessionTimeout() / 1000);
        lVar.put("mpid", configManager.getMpid());
        lVar.put("dbg", ConfigManager.getEnvironment().equals(Environment.Development));
        lVar.put("ltv", MParticle.getInstance().Commerce().getCurrentUserLtv());
        String apiKey = configManager.getApiKey();
        if (z) {
            String string = sharedPreferences.getString("mp::deleted_user_attrs::" + apiKey, null);
            if (string != null) {
                lVar.put("uad", new JSONArray(string));
                sharedPreferences.edit().remove("mp::deleted_user_attrs::" + apiKey).apply();
            }
        }
        if (MParticle.getInstance().ProductBags().getBags().size() > 0) {
            lVar.put("pb", new JSONObject(MParticle.getInstance().ProductBags().toString()));
        }
        lVar.put("ck", jSONObject);
        lVar.put("cms", configManager.getProviderPersistence());
        lVar.put("ia", configManager.getIntegrationAttributes());
        return lVar;
    }

    /* renamed from: a */
    public void mo44901a(JSONObject jSONObject) {
        try {
            if (!has("sh")) {
                put("sh", new JSONArray());
            }
            getJSONArray("sh").put(jSONObject);
        } catch (JSONException e) {
        }
    }

    /* renamed from: b */
    public void mo44903b(JSONObject jSONObject) {
        try {
            if (!has("msgs")) {
                put("msgs", new JSONArray());
            }
            getJSONArray("msgs").put(jSONObject);
        } catch (JSONException e) {
        }
    }

    /* renamed from: c */
    public void mo44905c(JSONObject jSONObject) {
        try {
            if (!has("fsr")) {
                put("fsr", new JSONArray());
            }
            getJSONArray("fsr").put(jSONObject);
        } catch (JSONException e) {
        }
    }

    /* renamed from: d */
    public void mo44907d(JSONObject jSONObject) {
        try {
            put("ai", jSONObject);
        } catch (JSONException e) {
        }
    }

    /* renamed from: e */
    public void mo44908e(JSONObject jSONObject) {
        try {
            put("di", jSONObject);
        } catch (JSONException e) {
        }
    }

    /* renamed from: a */
    public JSONObject mo44899a() {
        try {
            return getJSONObject("ai");
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: b */
    public JSONObject mo44902b() {
        try {
            return getJSONObject("di");
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: c */
    public JSONArray mo44904c() {
        try {
            return getJSONArray("sh");
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: d */
    public JSONArray mo44906d() {
        try {
            return getJSONArray("msgs");
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    public void mo44900a(JSONArray jSONArray) {
        try {
            put("ui", jSONArray);
        } catch (JSONException e) {
        }
    }

    /* renamed from: f */
    public void mo44909f(JSONObject jSONObject) {
        try {
            put("ua", jSONObject);
        } catch (JSONException e) {
        }
    }
}
