package p004bo.app;

import android.content.Context;
import com.appboy.configuration.CachedConfigurationProvider;

/* renamed from: bo.app.l */
public class C0619l extends CachedConfigurationProvider {
    public C0619l(Context context) {
        super(context);
    }

    /* renamed from: a */
    public long mo7310a() {
        return (long) (getIntValue("com_appboy_data_flush_interval_bad_network", 60) * 1000);
    }

    /* renamed from: b */
    public long mo7311b() {
        return (long) (getIntValue("com_appboy_data_flush_interval_good_network", 30) * 1000);
    }

    /* renamed from: c */
    public long mo7312c() {
        return (long) (getIntValue("com_appboy_data_flush_interval_great_network", 10) * 1000);
    }
}
