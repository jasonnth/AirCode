package p004bo.app;

import android.content.Context;
import com.appboy.Appboy;
import com.appboy.events.InAppMessageEvent;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.dy */
public final class C0468dy extends C0471ea implements C0467dx {

    /* renamed from: a */
    private static final String f413a = AppboyLogger.getAppboyLogTag(C0468dy.class);

    /* renamed from: b */
    private IInAppMessage f414b;

    /* renamed from: c */
    private C0375bd f415c;

    /* renamed from: d */
    private String f416d;

    public C0468dy(JSONObject jSONObject, C0375bd bdVar) {
        super(jSONObject);
        AppboyLogger.m1733d(f413a, "Parsing in-app message triggered action with JSON: " + jSONObject.toString(2));
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        if (jSONObject2 != null) {
            this.f415c = bdVar;
            if (jSONObject2.optBoolean("is_control", false)) {
                AppboyLogger.m1733d(f413a, "Control triggered action found. Parsing in-app message.");
                this.f414b = new C0389br(jSONObject2, this.f415c);
                return;
            }
            AppboyLogger.m1733d(f413a, "Non-control triggered action found. Parsing in-app message.");
            this.f414b = C0458dr.m526a(jSONObject2, this.f415c);
            return;
        }
        AppboyLogger.m1739w(f413a, "InAppMessageTriggeredAction Json did not contain in-app message.");
    }

    /* renamed from: d */
    public C0514fn mo7017d() {
        if (StringUtils.isNullOrBlank(this.f414b.getRemoteAssetPathForPrefetch())) {
            return null;
        }
        if (this.f414b instanceof IInAppMessageHtml) {
            return new C0514fn(C0493ev.ZIP, this.f414b.getRemoteAssetPathForPrefetch());
        }
        return new C0514fn(C0493ev.IMAGE, this.f414b.getRemoteAssetPathForPrefetch());
    }

    /* renamed from: a */
    public void mo7012a(String str) {
        this.f416d = str;
    }

    /* renamed from: a */
    public void mo7011a(Context context, C0343ac acVar, C0495ex exVar, long j) {
        try {
            JSONObject jSONObject = (JSONObject) this.f414b.forJsonPut();
            if (this.f414b instanceof C0389br) {
                AppboyLogger.m1733d(f413a, "Attempting to log control impression in place of publishing in-app message.");
                new C0389br(jSONObject, this.f415c).mo6827a();
                return;
            }
            AppboyLogger.m1733d(f413a, String.format("Attempting to publish in-app message after delay of %d seconds.", new Object[]{Integer.valueOf(mo7016c().mo7041d())}));
            IInAppMessage a = C0458dr.m526a(jSONObject, this.f415c);
            if (!StringUtils.isNullOrBlank(this.f416d)) {
                a.setLocalAssetPathForPrefetch(this.f416d);
            }
            a.setExpirationTimestamp(j);
            acVar.mo6736a(new InAppMessageEvent(a, Appboy.getInstance(context).getCurrentUser().getUserId()), InAppMessageEvent.class);
        } catch (JSONException e) {
            AppboyLogger.m1740w(f413a, "Caught JSON exception while performing triggered action.", e);
        } catch (Exception e2) {
            AppboyLogger.m1740w(f413a, "Caught exception while performing triggered action.", e2);
        }
    }

    /* renamed from: e */
    public JSONObject forJsonPut() {
        try {
            JSONObject e = super.forJsonPut();
            e.put("data", this.f414b.forJsonPut());
            e.put("type", "inapp");
            return e;
        } catch (JSONException e2) {
            return null;
        }
    }
}
