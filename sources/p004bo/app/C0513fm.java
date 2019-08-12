package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: bo.app.fm */
public class C0513fm implements C0508fj {

    /* renamed from: a */
    private static final String f501a = AppboyLogger.getAppboyLogTag(C0513fm.class);

    /* renamed from: b */
    private final SharedPreferences f502b;

    /* renamed from: c */
    private Map<String, Long> f503c = mo7070a();

    public C0513fm(Context context, String str, String str2) {
        this.f502b = context.getSharedPreferences("com.appboy.storage.triggers.re_eligibility" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
    }

    /* renamed from: a */
    public void mo7060a(List<C0467dx> list) {
        HashSet hashSet = new HashSet();
        for (C0467dx b : list) {
            hashSet.add(b.mo7015b());
        }
        HashSet<String> hashSet2 = new HashSet<>(this.f503c.keySet());
        Editor edit = this.f502b.edit();
        for (String str : hashSet2) {
            if (!hashSet.contains(str)) {
                AppboyLogger.m1733d(f501a, String.format("Deleting outdated triggered action id %s from re-eligibility list.", new Object[]{str}));
                this.f503c.remove(str);
                edit.remove(str);
            } else {
                AppboyLogger.m1733d(f501a, String.format("Retaining triggered action %s in re-eligibility list.", new Object[]{str}));
            }
        }
        edit.apply();
    }

    /* renamed from: a */
    public boolean mo7062a(C0467dx dxVar) {
        C0488eq f = dxVar.mo7016c().mo7043f();
        if (f.mo7035a()) {
            AppboyLogger.m1733d(f501a, String.format("Triggered action id %s always eligible via configuration. Returning true for eligibility status", new Object[]{dxVar.mo7015b()}));
            return true;
        } else if (!this.f503c.containsKey(dxVar.mo7015b())) {
            AppboyLogger.m1733d(f501a, String.format("Triggered action id %s always eligible via never having been triggered. Returning true for eligibility status", new Object[]{dxVar.mo7015b()}));
            return true;
        } else if (f.mo7036b()) {
            AppboyLogger.m1733d(f501a, String.format("Triggered action id %s no longer eligible due to having been triggered in the past", new Object[]{dxVar.mo7015b()}));
            return false;
        } else {
            long a = C0455dp.m515a() - ((Long) this.f503c.get(dxVar.mo7015b())).longValue();
            if (a > ((long) f.mo7037c().intValue())) {
                AppboyLogger.m1733d(f501a, String.format("Trigger action is re-eligible for display since %d seconds have passed since the last time it was triggered (minimum interval: %d).", new Object[]{Long.valueOf(a), f.mo7037c()}));
                return true;
            }
            AppboyLogger.m1733d(f501a, String.format("Trigger action is not re-eligible for display since only %d seconds have passed since the last time it was triggered (minimum interval: %d).", new Object[]{Long.valueOf(a), f.mo7037c()}));
            return false;
        }
    }

    /* renamed from: a */
    public void mo7061a(C0467dx dxVar, long j) {
        AppboyLogger.m1733d(f501a, String.format("Updating re-eligibility for action Id %s to time %d.", new Object[]{dxVar.mo7015b(), Long.valueOf(j)}));
        this.f503c.put(dxVar.mo7015b(), Long.valueOf(j));
        Editor edit = this.f502b.edit();
        edit.putLong(dxVar.mo7015b(), j);
        edit.apply();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public Map<String, Long> mo7070a() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Map all = this.f502b.getAll();
        if (all == null || all.size() == 0) {
            return concurrentHashMap;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            return concurrentHashMap;
        }
        try {
            for (String str : keySet) {
                long j = this.f502b.getLong(str, 0);
                AppboyLogger.m1733d(f501a, String.format("Retrieving triggered action id %s eligibility information from local storage.", new Object[]{str}));
                concurrentHashMap.put(str, Long.valueOf(j));
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(f501a, "Encountered unexpected exception while parsing stored re-eligibility information.", e);
        }
        return concurrentHashMap;
    }
}
