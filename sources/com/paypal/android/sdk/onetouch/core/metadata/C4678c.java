package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.c */
public class C4678c {

    /* renamed from: a */
    private static final String f3920a = C4678c.class.getSimpleName();

    /* renamed from: g */
    private Context f3921g;

    /* renamed from: h */
    private String f3922h;

    /* renamed from: i */
    private JSONObject f3923i;

    /* renamed from: j */
    private boolean f3924j;

    public C4678c(Context context, String str) {
        this(context, str, 0);
    }

    private C4678c(Context context, String str, byte b) {
        C4677ai.m2390a(f3920a, "entering Configuration url loading");
        this.f3921g = context;
        this.f3922h = str;
        String p = m2415p();
        if ("".equals(p)) {
            throw new IOException("No valid config found for " + str);
        }
        C4677ai.m2390a(f3920a, "entering saveConfigDataToDisk");
        try {
            File file = new File(this.f3921g.getFilesDir(), "CONFIG_DATA");
            File file2 = new File(this.f3921g.getFilesDir(), "CONFIG_TIME");
            C4688n.m2474a(file, p);
            C4688n.m2474a(file2, String.valueOf(System.currentTimeMillis()));
            C4677ai.m2390a(f3920a, "leaving saveConfigDataToDisk successfully");
        } catch (IOException e) {
            new StringBuilder("Failed to write config data: ").append(e.toString());
        }
        this.f3923i = new JSONObject(p);
        C4677ai.m2390a(f3920a, "leaving Configuration url loading");
    }

    public C4678c(Context context, boolean z) {
        this.f3921g = context;
        this.f3922h = null;
        this.f3924j = z;
        C4677ai.m2388a(3, "PRD", "confIsUpdatable=" + Boolean.toString(this.f3924j));
        this.f3923i = m2410j();
        C4677ai.m2390a(f3920a, "Configuation initialize, dumping config");
        C4677ai.m2392a(f3920a, this.f3923i);
    }

    /* renamed from: a */
    private JSONObject m2408a(String str) {
        try {
            C4677ai.m2390a(f3920a, "entering getIncrementalConfig");
            JSONObject jSONObject = new JSONObject(C4677ai.m2398b(this.f3921g, str));
            C4677ai.m2390a(f3920a, "leaving getIncrementalConfig");
            return jSONObject;
        } catch (Exception e) {
            C4677ai.m2389a(6, "PRD", "Error while loading prdc Config " + str, (Throwable) e);
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m2409a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            C4677ai.m2390a(f3920a, "entering mergeConfig");
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                C4677ai.m2390a(f3920a, "overridding " + str);
                jSONObject.put(str, jSONObject2.get(str));
            }
            C4677ai.m2390a(f3920a, "leaving mergeConfig");
            return jSONObject;
        } catch (Exception e) {
            C4677ai.m2389a(6, "PRD", "Error encountered while applying prdc Config", (Throwable) e);
            return null;
        }
    }

    /* renamed from: j */
    private JSONObject m2410j() {
        try {
            JSONObject m = m2412m();
            if (m != null) {
                if (C4677ai.m2401b(m.optString("conf_version", ""), "3.0")) {
                    if (this.f3924j) {
                        if (System.currentTimeMillis() > Long.parseLong(m2418s()) + (m.optLong("conf_refresh_time_interval", 0) * 1000)) {
                            m2414o();
                        }
                    }
                    C4677ai.m2390a(f3920a, "Using cached config");
                    return m;
                }
                m2417r();
            }
            JSONObject l = m2411l();
            if (l == null) {
                Log.e(f3920a, "default Configuration loading failed,Using hardcoded config");
                return m2413n();
            }
            String a = C4677ai.m2384a(this.f3921g, "prdc", (String) null);
            if (a == null) {
                if (this.f3924j) {
                    m2414o();
                }
                C4677ai.m2388a(3, "PRD", "prdc field not configured, using default config");
                return l;
            }
            C4677ai.m2388a(3, "PRD", "prdc field is configured, loading path:" + a);
            JSONObject a2 = m2408a(a);
            if (a2 == null) {
                C4677ai.m2388a(6, "PRD", "prdc Configuration loading failed, using default config");
                return l;
            }
            JSONObject a3 = m2409a(l, a2);
            if (a3 == null) {
                C4677ai.m2388a(6, "PRD", "applying prdc Configuration failed, using default config");
                return l;
            }
            C4677ai.m2388a(3, "PRD", "prdc configuration loaded successfully");
            return a3;
        } catch (Exception e) {
            C4677ai.m2389a(6, "PRD", "Severe Error while loading config, using hard code version", (Throwable) e);
            return m2413n();
        }
    }

    /* renamed from: l */
    private static JSONObject m2411l() {
        C4677ai.m2390a(f3920a, "entering getDefaultConfigurations");
        try {
            String str = new String(Base64.decode("eyAiY29uZl92ZXJzaW9uIjogIjMuMCIsImFzeW5jX3VwZGF0ZV90aW1lX2ludGVydmFsIjogMzYwMCwgImZvcmNlZF9mdWxsX3VwZGF0ZV90aW1lX2ludGVydmFsIjogMTgwMCwgImNvbmZfcmVmcmVzaF90aW1lX2ludGVydmFsIjogODY0MDAsICJhbmRyb2lkX2FwcHNfdG9fY2hlY2siOiBbICJjb20uZWJheS5jbGFzc2lmaWVkcy9jb20uZWJheS5hcHAuTWFpblRhYkFjdGl2aXR5IiwgImNvbS5lYmF5Lm1vYmlsZS9jb20uZWJheS5tb2JpbGUuYWN0aXZpdGllcy5lQmF5IiwgImNvbS5lYmF5LnJlZGxhc2VyL2NvbS5lYmF5LnJlZGxhc2VyLlNjYW5uZWRJdGVtc0FjdGl2aXR5IiwgImNvbS5taWxvLmFuZHJvaWQvY29tLm1pbG8uYW5kcm9pZC5hY3Rpdml0eS5Ib21lQWN0aXZpdHkiLCAiY29tLnBheXBhbC5hbmRyb2lkLnAycG1vYmlsZS9jb20ucGF5cGFsLmFuZHJvaWQucDJwbW9iaWxlLmFjdGl2aXR5LlNlbmRNb25leUFjdGl2aXR5IiwgImNvbS5yZW50L2NvbS5yZW50LmFjdGl2aXRpZXMuc2Vzc2lvbi5BY3Rpdml0eUhvbWUiLCAiY29tLnN0dWJodWIvY29tLnN0dWJodWIuQWJvdXQiLCAiY29tLnVsb2NhdGUvY29tLnVsb2NhdGUuYWN0aXZpdGllcy5TZXR0aW5ncyIsICJjb20ubm9zaHVmb3UuYW5kcm9pZC5zdS9jb20ubm9zaHVmb3UuYW5kcm9pZC5zdS5TdSIsICJzdGVyaWNzb24uYnVzeWJveC9zdGVyaWNzb24uYnVzeWJveC5BY3Rpdml0eS5NYWluQWN0aXZpdHkiLCAib3JnLnByb3h5ZHJvaWQvb3JnLnByb3h5ZHJvaWQuUHJveHlEcm9pZCIsICJjb20uYWVkLmRyb2lkdnBuL2NvbS5hZWQuZHJvaWR2cG4uTWFpbkdVSSIsICJuZXQub3BlbnZwbi5vcGVudnBuL25ldC5vcGVudnBuLm9wZW52cG4uT3BlblZQTkNsaWVudCIsICJjb20ucGhvbmVhcHBzOTkuYWFiaXByb3h5L2NvbS5waG9uZWFwcHM5OS5hYWJpcHJveHkuT3Jib3QiLCAiY29tLmV2YW5oZS5wcm94eW1hbmFnZXIucHJvL2NvbS5ldmFuaGUucHJveHltYW5hZ2VyLk1haW5BY3Rpdml0eSIsICJjb20uZXZhbmhlLnByb3h5bWFuYWdlci9jb20uZXZhbmhlLnByb3h5bWFuYWdlci5NYWluQWN0aXZpdHkiLCAiY29tLmFuZHJvbW8uZGV2MzA5MzYuYXBwNzYxOTgvY29tLmFuZHJvbW8uZGV2MzA5MzYuYXBwNzYxOTguQW5kcm9tb0Rhc2hib2FyZEFjdGl2aXR5IiwgImNvbS5tZ3JhbmphLmF1dG9wcm94eV9saXRlL2NvbS5tZ3JhbmphLmF1dG9wcm94eV9saXRlLlByb3h5TGlzdEFjdGl2aXR5IiwgImNvbS52cG5vbmVjbGljay5hbmRyb2lkL2NvbS52cG5vbmVjbGljay5hbmRyb2lkLk1haW5BY3Rpdml0eSIsICJuZXQuaGlkZW1hbi9uZXQuaGlkZW1hbi5TdGFydGVyQWN0aXZpdHkiLCAiY29tLmRvZW50ZXIuYW5kcm9pZC52cG4uZml2ZXZwbi9jb20uZG9lbnRlci5hbmRyb2lkLnZwbi5maXZldnBuLkZpdmVWcG4iLCAiY29tLnRpZ2VydnBucy5hbmRyb2lkL2NvbS50aWdlcnZwbnMuYW5kcm9pZC5NYWluQWN0aXZpdHkiLCAiY29tLnBhbmRhcG93LnZwbi9jb20ucGFuZGFwb3cudnBuLlBhbmRhUG93IiwgImNvbS5leHByZXNzdnBuLnZwbi9jb20uZXhwcmVzc3Zwbi52cG4uTWFpbkFjdGl2aXR5IiwgImNvbS5sb25kb250cnVzdG1lZGlhLnZwbi9jb20ubG9uZG9udHJ1c3RtZWRpYS52cG4uVnBuU2VydmljZUFjdGl2aXR5IiwgImZyLm1lbGVjb20uVlBOUFBUUC52MTAxL2ZyLm1lbGVjb20uVlBOUFBUUC52MTAxLlNwbGFzaFNjcmVlbiIsICJjb20uY2hlY2twb2ludC5WUE4vY29tLmNoZWNrcG9pbnQuVlBOLk1haW5IYW5kbGVyIiwgImNvbS50dW5uZWxiZWFyLmFuZHJvaWQvY29tLnR1bm5lbGJlYXIuYW5kcm9pZC5UYmVhck1haW5BY3Rpdml0eSIsICJkZS5ibGlua3Qub3BlbnZwbi9kZS5ibGlua3Qub3BlbnZwbi5NYWluQWN0aXZpdHkiLCAib3JnLmFqZWplLmZha2Vsb2NhdGlvbi9vcmcuYWplamUuZmFrZWxvY2F0aW9uLkZha2UiLCAiY29tLmxleGEuZmFrZWdwcy9jb20ubGV4YS5mYWtlZ3BzLlBpY2tQb2ludCIsICJjb20uZm9yZ290dGVucHJvamVjdHMubW9ja2xvY2F0aW9ucy9jb20uZm9yZ290dGVucHJvamVjdHMubW9ja2xvY2F0aW9ucy5NYWluIiwgImtyLndvb3QwcGlhLmdwcy9rci53b290MHBpYS5ncHMuQ2F0Y2hNZUlmVUNhbiIsICJjb20ubXkuZmFrZS5sb2NhdGlvbi9jb20ubXkuZmFrZS5sb2NhdGlvbi5jb20ubXkuZmFrZS5sb2NhdGlvbiIsICJqcC5uZXRhcnQuYXJzdGFsa2luZy9qcC5uZXRhcnQuYXJzdGFsa2luZy5NeVByZWZlcmVuY2VBY3Rpdml0eSIsICJsb2NhdGlvblBsYXkuR1BTQ2hlYXRGcmVlL2xvY2F0aW9uUGxheS5HUFNDaGVhdEZyZWUuQWN0aXZpdHlTbWFydExvY2F0aW9uIiwgIm9yZy5nb29kZXYubGF0aXR1ZGUvb3JnLmdvb2Rldi5sYXRpdHVkZS5MYXRpdHVkZUFjdGl2aXR5IiwgImNvbS5zY2hlZmZzYmxlbmQuZGV2aWNlc3Bvb2YvY29tLnNjaGVmZnNibGVuZC5kZXZpY2VzcG9vZi5EZXZpY2VTcG9vZkFjdGl2aXR5IiwgImNvbS5wcm94eUJyb3dzZXIvY29tLnByb3h5QnJvd3Nlci5OZXdQcm94eUJyb3dzZXJBY3Rpdml0eSIsICJjb20uaWNlY29sZGFwcHMucHJveHlzZXJ2ZXJwcm8vY29tLmljZWNvbGRhcHBzLnByb3h5c2VydmVycHJvLnZpZXdTdGFydCIsICJob3RzcG90c2hpZWxkLmFuZHJvaWQudnBuL2NvbS5hbmNob3JmcmVlLnVpLkhvdFNwb3RTaGllbGQiLCAiY29tLmRvZW50ZXIub25ldnBuL2NvbS5kb2VudGVyLm9uZXZwbi5WcG5TZXR0aW5ncyIsICJjb20ueWVzdnBuLmVuL2NvbS55ZXN2cG4uTWFpblRhYiIsICJjb20ub2ZmaWNld3l6ZS5wbHV0b3Zwbi9jb20ub2ZmaWNld3l6ZS5wbHV0b3Zwbi5Ib21lQWN0aXZpdHkiLCAib3JnLmFqZWplLmxvY2F0aW9uc3Bvb2ZlcnByby9vcmcuYWplamUubG9jYXRpb25zcG9vZmVycHJvLkZha2UiLCAibG9jYXRpb25QbGF5LkdQU0NoZWF0L2xvY2F0aW9uUGxheS5HUFNDaGVhdC5BY3Rpdml0eVNtYXJ0TG9jYXRpb24iIF0sICJsb2NhdGlvbl9taW5fYWNjdXJhY3kiOiA1MDAsICJsb2NhdGlvbl9tYXhfY2FjaGVfYWdlIjogMTgwMCwgInNlbmRfb25fYXBwX3N0YXJ0IjogMSwgImVuZHBvaW50X3VybCI6ICJodHRwczovL3N2Y3MucGF5cGFsLmNvbS9BY2Nlc3NDb250cm9sL0xvZ1Jpc2tNZXRhZGF0YSIsICJpbnRlcm5hbF9jYWNoZV9tYXhfYWdlIjogMzAsICJjb21wX3RpbWVvdXQiOiA2MDAgfQ==", 0), JPushConstants.ENCODING_UTF_8);
            C4677ai.m2390a(f3920a, "leaving getDefaultConfigurations, Default Conf load succeed");
            return new JSONObject(str);
        } catch (Exception e) {
            C4677ai.m2389a(6, "PRD", "Read default config file exception.", (Throwable) e);
            C4677ai.m2390a(f3920a, "leaving getDefaultConfigurations,returning null");
            return null;
        }
    }

    /* renamed from: m */
    private JSONObject m2412m() {
        C4677ai.m2390a(f3920a, "entering getCachedConfiguration");
        try {
            String q = m2416q();
            if (!"".equals(q)) {
                C4677ai.m2390a(f3920a, "leaving getCachedConfiguration,cached config load succeed");
                return new JSONObject(q);
            }
        } catch (Exception e) {
            C4677ai.m2391a(f3920a, "JSON Exception in creating config file", (Throwable) e);
        }
        C4677ai.m2390a(f3920a, "leaving getCachedConfiguration,cached config load failed");
        return null;
    }

    /* renamed from: n */
    private static JSONObject m2413n() {
        C4677ai.m2390a(f3920a, "entering getHardcodedConfig");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("conf_version", "3.0");
            jSONObject.put("async_update_time_interval", 3600);
            jSONObject.put("forced_full_update_time_interval", 1800);
            jSONObject.put("conf_refresh_time_interval", JPushConstants.STOPED_RTC_RESTART);
            jSONObject.put("location_min_accuracy", 500);
            jSONObject.put("location_max_cache_age", 1800);
            jSONObject.put("endpoint_url", "https://svcs.paypal.com/AccessControl/LogRiskMetadata");
        } catch (JSONException e) {
        }
        C4677ai.m2390a(f3920a, "leaving getHardcodedConfig");
        return jSONObject;
    }

    /* renamed from: o */
    private static void m2414o() {
        C4677ai.m2390a(f3920a, "Loading web config");
        C4682h.m2450h().mo45425i();
    }

    /* renamed from: p */
    private String m2415p() {
        BufferedReader bufferedReader;
        InputStream inputStream = null;
        C4677ai.m2390a(f3920a, "entering getRemoteConfig");
        StringBuilder sb = new StringBuilder();
        try {
            InputStream openStream = new URL(this.f3922h).openStream();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(openStream, JPushConstants.ENCODING_UTF_8));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            C4674af.m2376a(openStream);
                            C4674af.m2376a(bufferedReader);
                            C4677ai.m2390a(f3920a, "leaving getRemoteConfig successfully");
                            return sb.toString();
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream = openStream;
                        C4674af.m2376a(inputStream);
                        C4674af.m2376a(bufferedReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                inputStream = openStream;
                C4674af.m2376a(inputStream);
                C4674af.m2376a(bufferedReader);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    /* renamed from: q */
    private String m2416q() {
        try {
            return C4688n.m2473a(new File(this.f3921g.getFilesDir(), "CONFIG_DATA"));
        } catch (IOException e) {
            C4677ai.m2391a(f3920a, "Load cached config failed", (Throwable) e);
            return "";
        }
    }

    /* renamed from: r */
    private boolean m2417r() {
        try {
            C4677ai.m2390a(f3920a, "entering deleteCachedConfigDataFromDisk");
            File file = new File(this.f3921g.getFilesDir(), "CONFIG_DATA");
            File file2 = new File(this.f3921g.getFilesDir(), "CONFIG_TIME");
            if (file.exists()) {
                C4677ai.m2390a(f3920a, "cached Config Data found, deleting");
                file.delete();
            }
            if (file2.exists()) {
                C4677ai.m2390a(f3920a, "cached Config Time found, deleting");
                file.delete();
            }
            C4677ai.m2390a(f3920a, "leaving deleteCachedConfigDataFromDisk");
            return true;
        } catch (Exception e) {
            C4677ai.m2391a(f3920a, "error encountered while deleteCachedConfigData", (Throwable) e);
            return false;
        }
    }

    /* renamed from: s */
    private String m2418s() {
        try {
            return C4688n.m2473a(new File(this.f3921g.getFilesDir(), "CONFIG_TIME"));
        } catch (IOException e) {
            return "";
        }
    }

    /* renamed from: a */
    public final String mo45405a() {
        return this.f3922h;
    }

    /* renamed from: b */
    public final String mo45406b() {
        return this.f3923i.optString("conf_version", "");
    }

    /* renamed from: c */
    public final long mo45407c() {
        return this.f3923i.optLong("async_update_time_interval", 0);
    }

    /* renamed from: d */
    public final long mo45408d() {
        return this.f3923i.optLong("forced_full_update_time_interval", 0);
    }

    /* renamed from: e */
    public final long mo45409e() {
        return this.f3923i.optLong("comp_timeout", 0);
    }

    /* renamed from: f */
    public final List<String> mo45410f() {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = this.f3923i.optJSONArray("android_apps_to_check");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            arrayList.add(optJSONArray.getString(i));
            i++;
        }
        return arrayList;
    }

    /* renamed from: g */
    public final String mo45411g() {
        return this.f3923i.optString("endpoint_url", null);
    }

    /* renamed from: h */
    public final boolean mo45412h() {
        return this.f3923i.optBoolean("endpoint_is_stage", false);
    }

    /* renamed from: i */
    public final C4675ag mo45413i() {
        try {
            String optString = this.f3923i.optString("CDS", "");
            if (optString == null || "".equals(optString)) {
                C4677ai.m2388a(3, "PRD", "No CDS is configured, enabling all variables");
                return C4675ag.f3827a;
            }
            C4677ai.m2388a(3, "PRD", "CDS field was found");
            return new C4675ag(optString.trim());
        } catch (Exception e) {
            C4677ai.m2389a(6, "PRD", "Failed to decode CDS", (Throwable) e);
            return C4675ag.f3827a;
        }
    }
}
