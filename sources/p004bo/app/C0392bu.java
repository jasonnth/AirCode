package p004bo.app;

import com.appboy.models.IInAppMessage;
import com.appboy.support.AppboyLogger;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.bu */
public final class C0392bu {

    /* renamed from: a */
    private static final String f208a = AppboyLogger.getAppboyLogTag(C0392bu.class);

    /* renamed from: b */
    private final JSONArray f209b;

    /* renamed from: c */
    private final IInAppMessage f210c;

    /* renamed from: d */
    private final IInAppMessage f211d;

    /* renamed from: e */
    private final List<C0467dx> f212e;

    /* renamed from: f */
    private final C0394bw f213f;

    public C0392bu(JSONObject jSONObject, C0375bd bdVar) {
        C0394bw bwVar;
        JSONArray optJSONArray = jSONObject.optJSONArray("feed");
        if (optJSONArray != null) {
            this.f209b = optJSONArray;
        } else {
            this.f209b = null;
        }
        this.f210c = C0458dr.m526a(jSONObject.optJSONObject("inapp"), bdVar);
        this.f212e = C0515fo.m690a(jSONObject.optJSONArray("triggers"), bdVar);
        JSONObject optJSONObject = jSONObject.optJSONObject("config");
        if (optJSONObject != null) {
            try {
                bwVar = new C0394bw(optJSONObject);
            } catch (JSONException e) {
                AppboyLogger.m1740w(f208a, "Encountered JSONException processing server config: " + optJSONObject.toString(), e);
                bwVar = null;
            } catch (Exception e2) {
                AppboyLogger.m1740w(f208a, "Encountered Exception processing server config: " + optJSONObject.toString(), e2);
            }
            this.f213f = bwVar;
            this.f211d = C0515fo.m688a(jSONObject.optJSONObject("templated_message"), bdVar);
        }
        bwVar = null;
        this.f213f = bwVar;
        this.f211d = C0515fo.m688a(jSONObject.optJSONObject("templated_message"), bdVar);
    }

    /* renamed from: a */
    public boolean mo6846a() {
        return this.f209b != null;
    }

    /* renamed from: b */
    public boolean mo6847b() {
        return this.f210c != null;
    }

    /* renamed from: c */
    public boolean mo6848c() {
        return this.f211d != null;
    }

    /* renamed from: d */
    public boolean mo6849d() {
        return this.f213f != null;
    }

    /* renamed from: e */
    public boolean mo6850e() {
        return this.f212e != null;
    }

    /* renamed from: f */
    public JSONArray mo6851f() {
        return this.f209b;
    }

    /* renamed from: g */
    public IInAppMessage mo6852g() {
        return this.f210c;
    }

    /* renamed from: h */
    public IInAppMessage mo6853h() {
        return this.f211d;
    }

    /* renamed from: i */
    public C0394bw mo6854i() {
        return this.f213f;
    }

    /* renamed from: j */
    public List<C0467dx> mo6855j() {
        return this.f212e;
    }
}
