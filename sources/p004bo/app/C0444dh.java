package p004bo.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: bo.app.dh */
public class C0444dh {

    /* renamed from: a */
    private static final String f352a = AppboyLogger.getAppboyLogTag(C0444dh.class);

    /* renamed from: b */
    private final SharedPreferences f353b;

    /* renamed from: c */
    private final Set<String> f354c;

    /* renamed from: d */
    private final Set<String> f355d;

    /* renamed from: e */
    private C0365ax f356e;

    /* renamed from: bo.app.dh$a */
    enum C0445a {
        READ_CARDS("read_cards_set", "read_cards_flat"),
        VIEWED_CARDS("viewed_cards_set", "viewed_cards_flat");
        

        /* renamed from: c */
        private final String f360c;

        /* renamed from: d */
        private final String f361d;

        private C0445a(String str, String str2) {
            this.f360c = str;
            this.f361d = str2;
        }

        /* renamed from: a */
        public String mo6961a() {
            return this.f360c;
        }

        /* renamed from: b */
        public String mo6962b() {
            return this.f361d;
        }
    }

    public C0444dh(Context context, String str) {
        this.f353b = context.getSharedPreferences("com.appboy.storage.feedstorageprovider" + StringUtils.getCacheFileSuffix(context, str == null ? "" : str), 0);
        this.f354c = m443a(C0445a.VIEWED_CARDS);
        this.f355d = m443a(C0445a.READ_CARDS);
        m447d(str);
    }

    /* renamed from: a */
    public void mo6957a(C0365ax axVar) {
        this.f356e = axVar;
    }

    /* renamed from: a */
    public void mo6958a(String str) {
        if (!this.f354c.contains(str)) {
            this.f354c.add(str);
            mo6959a(this.f354c, C0445a.VIEWED_CARDS);
        }
    }

    /* renamed from: b */
    public void mo6960b(String str) {
        if (!this.f355d.contains(str)) {
            this.f355d.add(str);
            mo6959a(this.f355d, C0445a.READ_CARDS);
        }
    }

    /* renamed from: a */
    public FeedUpdatedEvent mo6956a(JSONArray jSONArray, String str) {
        String str2 = str == null ? "" : str;
        String string = this.f353b.getString(JPushReportInterface.UID, "");
        if (string.equals(str2)) {
            AppboyLogger.m1737i(f352a, "Updating offline feed for user with id: " + str);
            long a = C0455dp.m515a();
            m445a(jSONArray, a);
            this.f354c.retainAll(m444a(jSONArray));
            mo6959a(this.f354c, C0445a.VIEWED_CARDS);
            this.f355d.retainAll(m444a(jSONArray));
            mo6959a(this.f355d, C0445a.READ_CARDS);
            return m442a(jSONArray, str, false, a);
        }
        AppboyLogger.m1737i(f352a, "The received cards are for user " + str + " and the current user is " + string + " , the cards will be discarded and no changes will be made.");
        return null;
    }

    /* renamed from: a */
    public FeedUpdatedEvent mo6955a() {
        String string = this.f353b.getString(JPushReportInterface.UID, "");
        return m442a(new JSONArray(this.f353b.getString("cards", "[]")), string, true, this.f353b.getLong("cards_timestamp", -1));
    }

    /* renamed from: a */
    private FeedUpdatedEvent m442a(JSONArray jSONArray, String str, boolean z, long j) {
        List<Card> list;
        if (jSONArray == null || jSONArray.length() == 0) {
            list = new ArrayList<>();
        } else {
            list = C0390bs.m222a(jSONArray, Card.class, this.f356e, this);
        }
        for (Card card : list) {
            if (this.f354c.contains(card.getId())) {
                card.setViewed(true);
                card.setIsRead(true);
            }
            if (this.f355d.contains(card.getId())) {
                card.setIsRead(true);
            }
        }
        return new FeedUpdatedEvent(list, str, z, j);
    }

    @TargetApi(11)
    /* renamed from: a */
    private Set<String> m443a(C0445a aVar) {
        String a = aVar.mo6961a();
        String b = aVar.mo6962b();
        if (VERSION.SDK_INT < 11) {
            return m446c(this.f353b.getString(b, null));
        }
        if (!this.f353b.contains(b)) {
            return this.f353b.getStringSet(a, new HashSet());
        }
        Set<String> c = m446c(this.f353b.getString(b, null));
        Editor edit = this.f353b.edit();
        edit.remove(b);
        edit.apply();
        mo6959a(c, aVar);
        return c;
    }

    /* renamed from: a */
    private void m445a(JSONArray jSONArray, long j) {
        Editor edit = this.f353b.edit();
        if (jSONArray == null || jSONArray.length() == 0) {
            edit.remove("cards");
        } else {
            edit.putString("cards", jSONArray.toString());
        }
        edit.putLong("cards_timestamp", j);
        edit.apply();
    }

    /* renamed from: d */
    private void m447d(String str) {
        Editor edit = this.f353b.edit();
        edit.putString(JPushReportInterface.UID, str);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(11)
    /* renamed from: a */
    public void mo6959a(Set<String> set, C0445a aVar) {
        String a = aVar.mo6961a();
        String b = aVar.mo6962b();
        Editor edit = this.f353b.edit();
        if (VERSION.SDK_INT < 11) {
            if (set == null || set.isEmpty()) {
                edit.remove(b);
            } else {
                edit.putString(b, StringUtils.join((Collection<String>) set, ";"));
            }
        } else if (set == null || set.isEmpty()) {
            edit.remove(a);
        } else {
            edit.putStringSet(a, set);
        }
        edit.apply();
    }

    /* renamed from: c */
    static Set<String> m446c(String str) {
        HashSet hashSet = new HashSet();
        if (str != null) {
            Collections.addAll(hashSet, str.split(";"));
        }
        return hashSet;
    }

    /* renamed from: a */
    static Set<String> m444a(JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("id")) {
                    hashSet.add(jSONObject.getString("id"));
                }
            }
        }
        return hashSet;
    }
}
