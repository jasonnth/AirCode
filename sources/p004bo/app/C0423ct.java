package p004bo.app;

import android.net.Uri;
import com.appboy.events.SubmitFeedbackFailed;
import com.appboy.events.SubmitFeedbackSucceeded;
import com.appboy.models.ResponseError;
import com.appboy.models.outgoing.Feedback;
import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ct */
public final class C0423ct extends C0419cq {

    /* renamed from: b */
    private static final String f304b = AppboyLogger.getAppboyLogTag(C0423ct.class);

    /* renamed from: c */
    private final Feedback f305c;

    public C0423ct(String str, Feedback feedback) {
        super(Uri.parse(str + "feedback"), null);
        this.f305c = feedback;
    }

    /* renamed from: a */
    public C0632u mo6908a() {
        return C0632u.POST;
    }

    /* renamed from: a */
    public void mo6910a(C0343ac acVar, C0392bu buVar) {
        acVar.mo6736a(new SubmitFeedbackSucceeded(this.f305c), SubmitFeedbackSucceeded.class);
    }

    /* renamed from: f */
    public boolean mo6920f() {
        return false;
    }

    /* renamed from: a */
    public void mo6911a(C0343ac acVar, ResponseError responseError) {
        super.mo6911a(acVar, responseError);
        acVar.mo6736a(new SubmitFeedbackFailed(this.f305c, responseError), SubmitFeedbackFailed.class);
    }

    /* renamed from: e */
    public JSONObject mo6919e() {
        JSONObject e = super.mo6919e();
        if (e == null) {
            return null;
        }
        try {
            e.put("feedback", this.f305c.forJsonPut());
            return e;
        } catch (JSONException e2) {
            AppboyLogger.m1740w(f304b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }
}
