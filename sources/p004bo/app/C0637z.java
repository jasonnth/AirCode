package p004bo.app;

import java.util.HashMap;
import java.util.Map;

/* renamed from: bo.app.z */
public enum C0637z {
    CUSTOM_EVENT,
    LOG_PURCHASE,
    LOG_PUSH_NOTIFICATION_OPENED,
    SUBMIT_FEEDBACK,
    ADD_TO_CUSTOM_ATTRIBUTE_ARRAY,
    INCREMENT_CUSTOM_ATTRIBUTE,
    REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY,
    SET_CUSTOM_ATTRIBUTE_ARRAY,
    SET_CUSTOM_ATTRIBUTE,
    UNSET_CUSTOM_ATTRIBUTE,
    SET_CUSTOM_ATTRIBUTE_TO_NOW,
    SET_CUSTOM_ATTRIBUTE_TO_SECONDS_FROM_EPOCH,
    SET_LAST_KNOWN_LOCATION,
    SET_AVATAR_IMAGE_URL,
    SET_COUNTRY,
    SET_DATE_OF_BIRTH,
    SET_EMAIL,
    SET_FIRST_NAME,
    SET_GENDER,
    SET_HOME_CITY,
    SET_LAST_NAME,
    SET_PHONE_NUMBER,
    SEND_WEAR_DEVICE,
    UNSUPPORTED_SDK_ACTION;
    

    /* renamed from: y */
    private static final Map<String, C0637z> f960y = null;

    static {
        int i;
        C0637z[] values;
        f960y = new HashMap();
        for (C0637z zVar : values()) {
            f960y.put(zVar.name(), zVar);
        }
    }

    /* renamed from: a */
    public static C0637z m1180a(String str) {
        if (!f960y.containsKey(str)) {
            return UNSUPPORTED_SDK_ACTION;
        }
        return (C0637z) f960y.get(str);
    }
}
