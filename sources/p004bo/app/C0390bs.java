package p004bo.app;

import com.appboy.models.cards.BannerImageCard;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.models.cards.Card;
import com.appboy.models.cards.CrossPromotionSmallCard;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.bs */
public final class C0390bs {

    /* renamed from: a */
    private static final String f198a = AppboyLogger.getAppboyLogTag(C0390bs.class);

    /* renamed from: a */
    public static <T> T m221a(String str, Class<T> cls, C0365ax axVar, C0444dh dhVar) {
        if (cls.equals(String.class)) {
            return C0453dn.m513a(str, cls);
        }
        if (cls.equals(Card.class)) {
            return C0453dn.m513a(m220a(new JSONObject(str), axVar, dhVar), cls);
        }
        throw new JSONException(String.format("Failed to construct java object %s, target class %s isn'tString nor Card. Please update the createObject in ModelFactory to handle extra class type.", new Object[]{str, cls.toString()}));
    }

    /* renamed from: a */
    public static <T> List<T> m222a(JSONArray jSONArray, Class<T> cls, C0365ax axVar, C0444dh dhVar) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object a = m221a(jSONArray.optString(i), cls, axVar, dhVar);
                if (a != null) {
                    arrayList.add(a);
                }
            } catch (JSONException e) {
                AppboyLogger.m1734d(f198a, String.format("Unable to cast JSON to [%s] in array. Ignoring.", new Object[]{cls.getName()}), e);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    static Card m220a(JSONObject jSONObject, C0365ax axVar, C0444dh dhVar) {
        String string = jSONObject.getString("type");
        if ("banner_image".equals(string)) {
            return new BannerImageCard(jSONObject, axVar, dhVar);
        }
        if ("captioned_image".equals(string)) {
            return new CaptionedImageCard(jSONObject, axVar, dhVar);
        }
        if ("cross_promotion_small".equals(string)) {
            return new CrossPromotionSmallCard(jSONObject, axVar, dhVar);
        }
        if ("short_news".equals(string)) {
            return new ShortNewsCard(jSONObject, axVar, dhVar);
        }
        if ("text_announcement".equals(string)) {
            return new TextAnnouncementCard(jSONObject, axVar, dhVar);
        }
        throw new JSONException(String.format("Failed to construct java object of type %s from JSON [%s]", new Object[]{string, jSONObject.toString()}));
    }
}
