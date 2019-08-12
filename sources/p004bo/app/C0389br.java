package p004bo.app;

import com.appboy.models.InAppMessageBase;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.br */
public class C0389br extends InAppMessageBase {

    /* renamed from: i */
    private boolean f197i = false;

    public C0389br(JSONObject jSONObject, C0375bd bdVar) {
        super(jSONObject, bdVar);
    }

    /* renamed from: a */
    public boolean mo6827a() {
        if (StringUtils.isNullOrEmpty(this.f2795d)) {
            AppboyLogger.m1739w(f2789a, "Trigger Id not found. Not logging in-app message control impression.");
            return false;
        } else if (this.f197i) {
            AppboyLogger.m1737i(f2789a, "Control impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.f2799h == null) {
            AppboyLogger.m1735e(f2789a, "Cannot log an in-app message control impression because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.f2799h.mo6769a((C0386bo) C0397bz.m284a(this.f2793b, this.f2794c, this.f2795d));
                this.f197i = true;
                return true;
            } catch (JSONException e) {
                this.f2799h.mo6767a((Throwable) e);
                return false;
            }
        }
    }

    public JSONObject forJsonPut() {
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.put("is_control", true);
            return forJsonPut;
        } catch (JSONException e) {
            return null;
        }
    }
}
