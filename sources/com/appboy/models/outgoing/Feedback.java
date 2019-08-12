package com.appboy.models.outgoing;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0399ca;

public final class Feedback implements IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f2896a = AppboyLogger.getAppboyLogTag(Feedback.class);

    /* renamed from: b */
    private final String f2897b;

    /* renamed from: c */
    private final String f2898c;

    /* renamed from: d */
    private final boolean f2899d;

    /* renamed from: e */
    private final Environment f2900e;

    /* renamed from: f */
    private final C0399ca f2901f;

    public Feedback(String message, String replyToEmail, boolean isReportingABug, Environment environment, C0399ca device) {
        if (StringUtils.isNullOrBlank(message)) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        this.f2897b = message;
        this.f2898c = replyToEmail;
        this.f2899d = isReportingABug;
        this.f2900e = environment;
        this.f2901f = device;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", StringUtils.checkNotNullOrEmpty(this.f2897b));
            jSONObject.put("reply_to", this.f2898c);
            jSONObject.put("is_bug", this.f2899d);
            if (this.f2901f != null) {
                jSONObject.put("device", this.f2901f.forJsonPut());
            }
            if (this.f2900e != null) {
                jSONObject.put("environment", this.f2900e.forStatelessJsonPut());
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f2896a, "Caught exception creating feedback Json.", e);
        }
        return jSONObject;
    }
}
