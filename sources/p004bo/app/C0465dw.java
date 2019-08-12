package p004bo.app;

import com.appboy.IAppboy;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.wearable.DataMap;
import java.math.BigDecimal;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.dw */
public class C0465dw {

    /* renamed from: a */
    private static final String f411a = AppboyLogger.getAppboyLogTag(C0465dw.class);

    /* renamed from: a */
    public static C0406cg m555a(DataMap dataMap) {
        C0406cg cgVar = null;
        if (m558b(dataMap) != C0637z.SEND_WEAR_DEVICE) {
            return cgVar;
        }
        try {
            return C0406cg.m338a(new JSONObject(dataMap.getString("v0")));
        } catch (JSONException e) {
            AppboyLogger.m1736e(f411a, "Wear device couldn't be recreated.", e);
            return cgVar;
        }
    }

    /* renamed from: b */
    public static C0637z m558b(DataMap dataMap) {
        return C0637z.m1180a(dataMap.getString("t"));
    }

    /* renamed from: a */
    public static boolean m557a(DataMap dataMap, IAppboy iAppboy) {
        AppboyProperties appboyProperties;
        Double d;
        Double d2;
        C0637z b = m558b(dataMap);
        boolean z = dataMap.getBoolean("h");
        if (z) {
            appboyProperties = m556a(dataMap.getString("p"));
        } else {
            appboyProperties = null;
        }
        switch (b) {
            case CUSTOM_EVENT:
                String string = dataMap.getString("v0");
                if (!z || appboyProperties == null) {
                    return iAppboy.logCustomEvent(string);
                }
                return iAppboy.logCustomEvent(string, appboyProperties);
            case LOG_PURCHASE:
                String string2 = dataMap.getString("v0");
                String string3 = dataMap.getString("v1");
                BigDecimal bigDecimal = new BigDecimal(dataMap.getString("v2"));
                if (!z || appboyProperties == null) {
                    return iAppboy.logPurchase(string2, string3, bigDecimal);
                }
                return iAppboy.logPurchase(string2, string3, bigDecimal, appboyProperties);
            case LOG_PUSH_NOTIFICATION_OPENED:
                return iAppboy.logPushNotificationOpened(dataMap.getString("v0"));
            case SUBMIT_FEEDBACK:
                return iAppboy.submitFeedback(dataMap.getString("v0"), dataMap.getString("v1"), dataMap.getBoolean("v2"));
            case ADD_TO_CUSTOM_ATTRIBUTE_ARRAY:
                return iAppboy.getCurrentUser().addToCustomAttributeArray(dataMap.getString("k"), dataMap.getString("v0"));
            case INCREMENT_CUSTOM_ATTRIBUTE:
                return iAppboy.getCurrentUser().incrementCustomUserAttribute(dataMap.getString("k"), dataMap.getInt("v0"));
            case SET_CUSTOM_ATTRIBUTE_ARRAY:
                return iAppboy.getCurrentUser().setCustomAttributeArray(dataMap.getString("k"), dataMap.getStringArray("v0"));
            case SET_CUSTOM_ATTRIBUTE:
                String string4 = dataMap.getString("k");
                int i = dataMap.getInt("v1");
                if (i == 1) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string4, dataMap.getBoolean("v0"));
                }
                if (i == 3) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string4, dataMap.getFloat("v0"));
                }
                if (i == 4) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string4, dataMap.getInt("v0"));
                }
                if (i == 5) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string4, dataMap.getLong("v0"));
                }
                if (i == 2) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string4, dataMap.getString("v0"));
                }
                return false;
            case SET_CUSTOM_ATTRIBUTE_TO_NOW:
                return iAppboy.getCurrentUser().setCustomUserAttributeToNow(dataMap.getString("k"));
            case UNSET_CUSTOM_ATTRIBUTE:
                return iAppboy.getCurrentUser().unsetCustomUserAttribute(dataMap.getString("k"));
            case SET_CUSTOM_ATTRIBUTE_TO_SECONDS_FROM_EPOCH:
                return iAppboy.getCurrentUser().setCustomUserAttributeToSecondsFromEpoch(dataMap.getString("k"), dataMap.getLong("v0"));
            case SET_LAST_KNOWN_LOCATION:
                double d3 = dataMap.getDouble("v0");
                double d4 = dataMap.getDouble("v1");
                if (dataMap.containsKey("v2")) {
                    d = Double.valueOf(dataMap.getDouble("v2"));
                } else {
                    d = null;
                }
                if (dataMap.containsKey("v3")) {
                    d2 = Double.valueOf(dataMap.getDouble("v3"));
                } else {
                    d2 = null;
                }
                iAppboy.getCurrentUser().setLastKnownLocation(d3, d4, d2, d);
                return true;
            case SET_AVATAR_IMAGE_URL:
                return iAppboy.getCurrentUser().setAvatarImageUrl(dataMap.getString("v0"));
            case SET_COUNTRY:
                return iAppboy.getCurrentUser().setCountry(dataMap.getString("v0"));
            case SET_EMAIL:
                return iAppboy.getCurrentUser().setEmail(dataMap.getString("v0"));
            case SET_FIRST_NAME:
                return iAppboy.getCurrentUser().setFirstName(dataMap.getString("v0"));
            case SET_HOME_CITY:
                return iAppboy.getCurrentUser().setHomeCity(dataMap.getString("v0"));
            case SET_LAST_NAME:
                return iAppboy.getCurrentUser().setLastName(dataMap.getString("v0"));
            case SET_PHONE_NUMBER:
                return iAppboy.getCurrentUser().setPhoneNumber(dataMap.getString("v0"));
            case SET_GENDER:
                return iAppboy.getCurrentUser().setGender(Gender.valueOf(dataMap.getString("v0")));
            case SET_DATE_OF_BIRTH:
                return iAppboy.getCurrentUser().setDateOfBirth(dataMap.getInt("v0"), Month.valueOf(dataMap.getString("v1")), dataMap.getInt("v2"));
            case UNSUPPORTED_SDK_ACTION:
                AppboyLogger.m1737i(f411a, "Got an unsupported wearable sdk action. DataMap: " + dataMap.toString());
                return false;
            default:
                AppboyLogger.m1737i(f411a, "No current implementation for action in DataMap: " + dataMap.toString());
                return false;
        }
    }

    /* renamed from: a */
    static AppboyProperties m556a(String str) {
        try {
            return new AppboyProperties(new JSONObject(str));
        } catch (JSONException e) {
            AppboyLogger.m1736e(f411a, "Failed to create properties object from string: " + str, e);
            return null;
        }
    }
}
