package p004bo.app;

import com.appboy.models.IPutIntoJson;
import java.util.Locale;

/* renamed from: bo.app.w */
public enum C0634w implements IPutIntoJson<String> {
    FEED,
    INAPP,
    CONFIG,
    TRIGGERS;

    /* renamed from: a */
    public String forJsonPut() {
        return toString().toLowerCase(Locale.US);
    }
}
