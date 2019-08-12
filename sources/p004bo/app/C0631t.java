package p004bo.app;

import com.appboy.models.IPutIntoJson;
import java.util.HashMap;
import java.util.Map;

/* renamed from: bo.app.t */
public enum C0631t implements IPutIntoJson<String> {
    LOCATION_RECORDED("lr"),
    CUSTOM_EVENT("ce"),
    PURCHASE("p"),
    PUSH_NOTIFICATION_TRACKING("pc"),
    PUSH_NOTIFICATION_ACTION_TRACKING("ca"),
    INTERNAL("i"),
    INTERNAL_ERROR("ie"),
    CARD_IMPRESSION("ci"),
    CARD_CLICK("cc"),
    USER_TRANSITION("ut"),
    INCREMENT("inc"),
    ADD_TO_CUSTOM_ATTRIBUTE_ARRAY("add"),
    REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY("rem"),
    SET_CUSTOM_ATTRIBUTE_ARRAY("set"),
    INAPP_MESSAGE_IMPRESSION("si"),
    INAPP_MESSAGE_CONTROL_IMPRESSION("iec"),
    INAPP_MESSAGE_CLICK("sc"),
    INAPP_MESSAGE_BUTTON_CLICK("sbc");
    

    /* renamed from: t */
    private static final Map<String, C0631t> f906t = null;

    /* renamed from: s */
    private final String f908s;

    static {
        int i;
        C0631t[] values;
        HashMap hashMap = new HashMap();
        for (C0631t tVar : values()) {
            hashMap.put(tVar.f908s, tVar);
        }
        f906t = new HashMap(hashMap);
    }

    private C0631t(String str) {
        this.f908s = str;
    }

    /* renamed from: a */
    public static C0631t m1175a(String str) {
        if (f906t.containsKey(str)) {
            return (C0631t) f906t.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    /* renamed from: a */
    public String forJsonPut() {
        return this.f908s;
    }
}
