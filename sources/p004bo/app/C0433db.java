package p004bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.db */
public final class C0433db extends C0419cq {

    /* renamed from: b */
    private static final String f325b = AppboyLogger.getAppboyLogTag(C0433db.class);

    /* renamed from: c */
    private final String f326c;

    /* renamed from: d */
    private final long f327d;

    /* renamed from: e */
    private final String f328e;

    /* renamed from: f */
    private final C0495ex f329f;

    public C0433db(String str, C0469dz dzVar, C0495ex exVar) {
        super(Uri.parse(str + "template"), null);
        this.f326c = dzVar.mo7020g();
        this.f327d = dzVar.mo7019f();
        this.f328e = dzVar.mo7021h();
        this.f329f = exVar;
    }

    /* renamed from: a */
    public C0632u mo6908a() {
        return C0632u.POST;
    }

    /* renamed from: a */
    public void mo6910a(C0343ac acVar, C0392bu buVar) {
        if (buVar != null && buVar.mo6848c() && !StringUtils.isNullOrBlank(this.f328e)) {
            buVar.mo6853h().setLocalAssetPathForPrefetch(this.f328e);
        }
    }

    /* renamed from: f */
    public boolean mo6920f() {
        return false;
    }

    /* renamed from: h */
    public long mo6928h() {
        return this.f327d;
    }

    /* renamed from: e */
    public JSONObject mo6919e() {
        JSONObject e = super.mo6919e();
        if (e == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trigger_id", this.f326c);
            jSONObject.put("trigger_event_type", this.f329f.mo7048b());
            if (this.f329f.mo7051e() != null) {
                jSONObject.put("data", this.f329f.mo7051e().forJsonPut());
            }
            e.put("template", jSONObject);
            return e;
        } catch (JSONException e2) {
            AppboyLogger.m1740w(f325b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }
}
