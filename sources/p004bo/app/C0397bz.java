package p004bo.app;

import com.appboy.models.MessageButton;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.bz */
public final class C0397bz implements C0386bo {

    /* renamed from: a */
    private static final String f227a = AppboyLogger.getAppboyLogTag(C0397bz.class);

    /* renamed from: b */
    private final C0631t f228b;

    /* renamed from: c */
    private final JSONObject f229c;

    /* renamed from: d */
    private final double f230d;

    private C0397bz(C0631t tVar, JSONObject jSONObject) {
        this(tVar, jSONObject, C0455dp.m522b());
    }

    private C0397bz(C0631t tVar, JSONObject jSONObject, double d) {
        if (tVar.forJsonPut() == null || jSONObject == null) {
            throw new NullPointerException();
        }
        this.f228b = tVar;
        this.f229c = jSONObject;
        this.f230d = d;
    }

    /* renamed from: a */
    public static C0397bz m281a(String str, AppboyProperties appboyProperties) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("n", StringUtils.checkNotNullOrEmpty(str));
        if (appboyProperties != null && appboyProperties.size() > 0) {
            jSONObject.put("p", appboyProperties.forJsonPut());
        }
        return new C0397bz(C0631t.CUSTOM_EVENT, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m287a(String str, String str2, BigDecimal bigDecimal, int i, AppboyProperties appboyProperties) {
        BigDecimal a = C0452dm.m512a(bigDecimal);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", str);
        jSONObject.put("c", str2);
        jSONObject.put("p", a.doubleValue());
        jSONObject.put("q", i);
        if (appboyProperties != null && appboyProperties.size() > 0) {
            jSONObject.put("pr", appboyProperties.forJsonPut());
        }
        return new C0397bz(C0631t.PURCHASE, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m278a(C0403cd cdVar) {
        return new C0397bz(C0631t.LOCATION_RECORDED, cdVar.forJsonPut());
    }

    /* renamed from: a */
    public static C0397bz m289a(Throwable th, C0395bx bxVar) {
        String b = m297b(th, bxVar);
        StringBuilder append = new StringBuilder(b).append("\n").append(m292a(th));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", append.toString());
        return new C0397bz(C0631t.INTERNAL_ERROR, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m277a(C0356ap apVar, C0395bx bxVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("nop", true);
        String b = m297b((Throwable) apVar, bxVar);
        jSONObject.put("e", "\n" + m292a((Throwable) apVar));
        return new C0397bz(C0631t.INTERNAL_ERROR, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m279a(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("new", str);
        return new C0397bz(C0631t.USER_TRANSITION, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m282a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("old", str);
        jSONObject.put("new", str2);
        return new C0397bz(C0631t.USER_TRANSITION, jSONObject);
    }

    /* renamed from: b */
    public static C0397bz m294b(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        return new C0397bz(C0631t.PUSH_NOTIFICATION_TRACKING, jSONObject);
    }

    /* renamed from: b */
    public static C0397bz m295b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        jSONObject.put("a", str2);
        return new C0397bz(C0631t.PUSH_NOTIFICATION_ACTION_TRACKING, jSONObject);
    }

    /* renamed from: c */
    public static C0397bz m299c(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new C0397bz(C0631t.CARD_IMPRESSION, jSONObject);
    }

    /* renamed from: d */
    public static C0397bz m302d(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new C0397bz(C0631t.CARD_CLICK, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m284a(String str, String str2, String str3) {
        return new C0397bz(C0631t.INAPP_MESSAGE_CONTROL_IMPRESSION, m303d(str, str2, str3));
    }

    /* renamed from: b */
    public static C0397bz m296b(String str, String str2, String str3) {
        return new C0397bz(C0631t.INAPP_MESSAGE_IMPRESSION, m303d(str, str2, str3));
    }

    /* renamed from: c */
    public static C0397bz m301c(String str, String str2, String str3) {
        return new C0397bz(C0631t.INAPP_MESSAGE_CLICK, m303d(str, str2, str3));
    }

    /* renamed from: a */
    public static C0397bz m285a(String str, String str2, String str3, MessageButton messageButton) {
        return new C0397bz(C0631t.INAPP_MESSAGE_BUTTON_CLICK, m298b(str, str2, str3, m291a(messageButton)));
    }

    /* renamed from: a */
    public static C0397bz m286a(String str, String str2, String str3, String str4) {
        return new C0397bz(C0631t.INAPP_MESSAGE_BUTTON_CLICK, m298b(str, str2, str3, str4));
    }

    /* renamed from: d */
    static JSONObject m303d(String str, String str2, String str3) {
        return m298b(str, str2, str3, null);
    }

    /* renamed from: b */
    static JSONObject m298b(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        if (!StringUtils.isNullOrEmpty(str)) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("campaign_ids", jSONArray);
        }
        if (!StringUtils.isNullOrEmpty(str2)) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(str2);
            jSONObject.put("card_ids", jSONArray2);
        }
        if (!StringUtils.isNullOrEmpty(str3)) {
            JSONArray jSONArray3 = new JSONArray();
            jSONArray3.put(str3);
            jSONObject.put("trigger_ids", jSONArray3);
        }
        if (!StringUtils.isNullOrEmpty(str4)) {
            jSONObject.put("bid", str4);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static String m291a(MessageButton messageButton) {
        if (messageButton != null) {
            return String.valueOf(messageButton.getId());
        }
        return null;
    }

    /* renamed from: e */
    public static C0397bz m304e() {
        return m305e("feed_displayed");
    }

    /* renamed from: f */
    public static C0397bz m306f() {
        return m305e("feedback_displayed");
    }

    /* renamed from: e */
    public static C0397bz m305e(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("n", str);
        return new C0397bz(C0631t.INTERNAL, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m280a(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        jSONObject.put("value", i);
        return new C0397bz(C0631t.INCREMENT, jSONObject);
    }

    /* renamed from: c */
    public static C0397bz m300c(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        jSONObject.put("value", str2);
        return new C0397bz(C0631t.ADD_TO_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m288a(String str, String[] strArr) {
        JSONArray jSONArray = strArr == null ? null : new JSONArray();
        if (strArr != null) {
            for (String put : strArr) {
                jSONArray.put(put);
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        if (strArr == null) {
            jSONObject.put("value", JSONObject.NULL);
        } else {
            jSONObject.put("value", jSONArray);
        }
        return new C0397bz(C0631t.SET_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    /* renamed from: a */
    public static C0397bz m283a(String str, String str2, double d) {
        C0631t a = C0631t.m1175a(str);
        if (a != null) {
            return new C0397bz(a, new JSONObject(str2), d);
        }
        throw new IllegalArgumentException(String.format("Cannot parse eventType %s", new Object[]{str}));
    }

    /* renamed from: a */
    public static C0397bz m290a(JSONObject jSONObject) {
        String string = jSONObject.getString("n");
        C0631t a = C0631t.m1175a(jSONObject.getString("n"));
        if (a != null) {
            return new C0397bz(a, jSONObject.getJSONObject("d"), jSONObject.getDouble("t"));
        }
        throw new IllegalArgumentException(String.format("Cannot parse eventType %s", new Object[]{string}));
    }

    /* renamed from: b */
    public C0631t mo6824b() {
        return this.f228b;
    }

    /* renamed from: c */
    public JSONObject mo6825c() {
        return this.f229c;
    }

    /* renamed from: a */
    public double mo6823a() {
        return this.f230d;
    }

    /* renamed from: d */
    public String mo6826d() {
        return forJsonPut().toString();
    }

    /* renamed from: b */
    static String m297b(Throwable th, C0395bx bxVar) {
        StringBuilder sb = new StringBuilder();
        String th2 = th.toString();
        if (th2.length() > 5000) {
            th2 = th2.substring(0, 5000);
        }
        sb.append("exception_class: ").append(th2).append(",");
        sb.append("session_id: ").append(bxVar != null ? bxVar.toString() : null);
        return sb.toString();
    }

    /* renamed from: a */
    static String m292a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        if (obj.length() > 5000) {
            return obj.substring(0, 5000);
        }
        return obj;
    }

    /* renamed from: a */
    public static boolean m293a(C0386bo boVar) {
        if (boVar.mo6824b() != C0631t.INTERNAL_ERROR || !boVar.mo6825c().optBoolean("nop", false)) {
            return false;
        }
        return true;
    }

    /* renamed from: g */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", this.f228b.forJsonPut());
            jSONObject.put("d", this.f229c);
            jSONObject.put("t", this.f230d);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f227a, "Caught exception creating Appboy event Json.", e);
        }
        return jSONObject;
    }

    public String toString() {
        JSONObject g = forJsonPut();
        if (g == null || g.length() <= 0) {
            return "";
        }
        return g.toString();
    }
}
