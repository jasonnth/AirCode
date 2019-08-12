package p004bo.app;

import android.content.Context;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.dz */
public final class C0469dz extends C0471ea implements C0467dx {

    /* renamed from: a */
    private static final String f417a = AppboyLogger.getAppboyLogTag(C0469dz.class);

    /* renamed from: b */
    private C0375bd f418b;

    /* renamed from: c */
    private String f419c;

    /* renamed from: d */
    private String f420d;

    /* renamed from: e */
    private String f421e;

    /* renamed from: f */
    private String f422f;

    /* renamed from: g */
    private long f423g = -1;

    public C0469dz(JSONObject jSONObject, C0375bd bdVar) {
        super(jSONObject);
        AppboyLogger.m1733d(f417a, "Parsing templated triggered action with JSON: " + jSONObject.toString());
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        this.f419c = jSONObject2.getString("trigger_id");
        JSONArray optJSONArray = jSONObject2.optJSONArray("prefetch_image_urls");
        if (optJSONArray != null) {
            this.f420d = optJSONArray.getString(0);
        }
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("prefetch_zip_urls");
        if (optJSONArray2 != null) {
            this.f421e = optJSONArray2.getString(0);
        }
        this.f418b = bdVar;
    }

    /* renamed from: d */
    public C0514fn mo7017d() {
        if (!StringUtils.isNullOrBlank(this.f420d)) {
            return new C0514fn(C0493ev.IMAGE, this.f420d);
        }
        if (!StringUtils.isNullOrBlank(this.f421e)) {
            return new C0514fn(C0493ev.ZIP, this.f421e);
        }
        return null;
    }

    /* renamed from: a */
    public void mo7012a(String str) {
        this.f422f = str;
    }

    /* renamed from: a */
    public void mo7011a(Context context, C0343ac acVar, C0495ex exVar, long j) {
        if (this.f418b != null) {
            this.f423g = j;
            AppboyLogger.m1733d(f417a, String.format("Posting templating request after delay of %d seconds.", new Object[]{Integer.valueOf(mo7016c().mo7041d())}));
            this.f418b.mo6764a(this, exVar);
        }
    }

    /* renamed from: f */
    public long mo7019f() {
        return this.f423g;
    }

    /* renamed from: g */
    public String mo7020g() {
        return this.f419c;
    }

    /* renamed from: h */
    public String mo7021h() {
        return this.f422f;
    }

    /* renamed from: e */
    public JSONObject forJsonPut() {
        try {
            JSONObject e = super.forJsonPut();
            e.put("type", "templated_iam");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("trigger_id", this.f419c);
            JSONArray jSONArray = new JSONArray();
            if (!StringUtils.isNullOrBlank(this.f420d)) {
                jSONArray.put(this.f420d);
                jSONObject.put("prefetch_image_urls", jSONArray);
            }
            JSONArray jSONArray2 = new JSONArray();
            if (!StringUtils.isNullOrBlank(this.f421e)) {
                jSONArray2.put(this.f421e);
                jSONObject.put("prefetch_zip_urls", jSONArray2);
            }
            e.put("data", jSONObject);
            return e;
        } catch (JSONException e2) {
            return null;
        }
    }
}
