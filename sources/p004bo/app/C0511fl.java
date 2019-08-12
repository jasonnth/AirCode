package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.fl */
public class C0511fl implements C0506fh {

    /* renamed from: b */
    private static final String f486b = AppboyLogger.getAppboyLogTag(C0511fl.class);

    /* renamed from: a */
    public final Object f487a = new Object();
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final Context f488c;

    /* renamed from: d */
    private final C0375bd f489d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final C0343ac f490e;

    /* renamed from: f */
    private final long f491f;

    /* renamed from: g */
    private final SharedPreferences f492g;

    /* renamed from: h */
    private final C0505fg f493h;

    /* renamed from: i */
    private final C0508fj f494i;

    /* renamed from: j */
    private Map<String, C0467dx> f495j;

    /* renamed from: k */
    private volatile long f496k = 0;

    public C0511fl(Context context, C0375bd bdVar, ThreadPoolExecutor threadPoolExecutor, C0343ac acVar, AppboyConfigurationProvider appboyConfigurationProvider, String str, String str2) {
        this.f488c = context.getApplicationContext();
        this.f489d = bdVar;
        this.f490e = acVar;
        this.f491f = appboyConfigurationProvider.getTriggerActionMinimumTimeIntervalInSeconds();
        this.f492g = context.getSharedPreferences("com.appboy.storage.triggers.actions" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
        this.f493h = new C0509fk(context, threadPoolExecutor, str2);
        this.f494i = new C0513fm(context, str, str2);
        this.f495j = mo7067a();
    }

    /* renamed from: a */
    public void mo7060a(List<C0467dx> list) {
        boolean z;
        boolean z2 = false;
        C0502fd fdVar = new C0502fd();
        if (list == null) {
            AppboyLogger.m1739w(f486b, "Received a null list of triggers in registerTriggeredActions(). Doing nothing.");
            return;
        }
        synchronized (this.f487a) {
            this.f495j.clear();
            Editor edit = this.f492g.edit();
            edit.clear();
            AppboyLogger.m1733d(f486b, String.format("Registering %d new triggered actions.", new Object[]{Integer.valueOf(list.size())}));
            for (C0467dx dxVar : list) {
                AppboyLogger.m1733d(f486b, String.format("Registering triggered action id %s.", new Object[]{dxVar.mo7015b()}));
                this.f495j.put(dxVar.mo7015b(), dxVar);
                edit.putString(dxVar.mo7015b(), ((JSONObject) dxVar.forJsonPut()).toString());
                if (dxVar.mo7014a((C0495ex) fdVar)) {
                    z = true;
                } else {
                    z = z2;
                }
                z2 = z;
            }
            edit.apply();
        }
        this.f494i.mo7060a(list);
        this.f493h.mo7060a(list);
        if (z2) {
            AppboyLogger.m1737i(f486b, "Test triggered actions found, triggering test event.");
            mo7059a((C0495ex) fdVar);
            return;
        }
        AppboyLogger.m1733d(f486b, "No test triggered actions found.");
    }

    /* renamed from: a */
    public void mo7059a(C0495ex exVar) {
        final long j;
        AppboyLogger.m1733d(f486b, String.format("New incoming <%s>. Searching for matching triggers.", new Object[]{exVar.mo7048b()}));
        final C0467dx b = mo7068b(exVar);
        if (b != null) {
            b.mo7012a(this.f493h.mo7058a(b));
            if (b.mo7016c().mo7042e() != -1) {
                j = ((long) b.mo7016c().mo7042e()) + exVar.mo7050d();
            } else {
                j = -1;
            }
            final C0495ex exVar2 = exVar;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    b.mo7011a(C0511fl.this.f488c, C0511fl.this.f490e, exVar2, j);
                }
            }, (long) (b.mo7016c().mo7041d() * 1000));
            this.f494i.mo7061a(b, exVar.mo7049c());
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public C0467dx mo7068b(C0495ex exVar) {
        boolean z;
        C0467dx dxVar;
        int i;
        C0467dx dxVar2;
        synchronized (this.f487a) {
            long a = C0455dp.m515a() - this.f496k;
            if (exVar instanceof C0502fd) {
                AppboyLogger.m1733d(f486b, "Ignoring minimum time interval between triggered actions because the trigger event is a test.");
                z = true;
            } else {
                AppboyLogger.m1737i(f486b, String.format("%d seconds have passed since the last trigger action (minimum interval: %d).", new Object[]{Long.valueOf(a), Long.valueOf(this.f491f)}));
                if (a >= this.f491f) {
                    z = true;
                } else {
                    z = false;
                }
            }
            dxVar = null;
            int i2 = Integer.MIN_VALUE;
            for (C0467dx dxVar3 : this.f495j.values()) {
                if (dxVar3.mo7014a(exVar) && this.f494i.mo7062a(dxVar3)) {
                    AppboyLogger.m1733d(f486b, String.format("Found potential triggered action for incoming trigger event. Action id %s.", new Object[]{dxVar3.mo7015b()}));
                    C0489er c = dxVar3.mo7016c();
                    if (c.mo7040c() > i2) {
                        dxVar2 = dxVar3;
                        i = c.mo7040c();
                        dxVar = dxVar2;
                        i2 = i;
                    }
                }
                i = i2;
                dxVar2 = dxVar;
                dxVar = dxVar2;
                i2 = i;
            }
            if (dxVar != null) {
                String str = f486b;
                String str2 = "Found best triggered action for incoming trigger event %s. Matched Action id: %s.";
                Object[] objArr = new Object[2];
                objArr[0] = exVar.mo7051e() != null ? exVar.mo7051e().toString() : "";
                objArr[1] = dxVar.mo7015b();
                AppboyLogger.m1733d(str, String.format(str2, objArr));
                if (z) {
                    AppboyLogger.m1737i(f486b, "Minimum time interval requirement met for matched trigger.");
                } else if (dxVar.mo7016c().mo7044g() <= -1 || ((long) dxVar.mo7016c().mo7044g()) > a) {
                    AppboyLogger.m1737i(f486b, String.format("Minimum time interval requirement and triggered action override time interval requirement of %d not met for matched trigger. Returning null.", new Object[]{Integer.valueOf(dxVar.mo7016c().mo7044g())}));
                    dxVar = null;
                } else {
                    AppboyLogger.m1737i(f486b, String.format("Triggered action override time interval requirement met: %d", new Object[]{Integer.valueOf(dxVar.mo7016c().mo7044g())}));
                }
                this.f496k = exVar.mo7049c();
            } else {
                AppboyLogger.m1733d(f486b, String.format("Failed to match triggered action for incoming <%s>.", new Object[]{exVar.mo7048b()}));
                dxVar = null;
            }
        }
        return dxVar;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public Map<String, C0467dx> mo7067a() {
        HashMap hashMap = new HashMap();
        Map all = this.f492g.getAll();
        if (all == null || all.size() == 0) {
            return hashMap;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            return hashMap;
        }
        try {
            for (String str : keySet) {
                String string = this.f492g.getString(str, null);
                if (StringUtils.isNullOrBlank(string)) {
                    AppboyLogger.m1739w(f486b, String.format("Received null or blank serialized triggered action string for action id %s from shared preferences. Not parsing.", new Object[]{str}));
                } else {
                    C0467dx b = C0515fo.m691b(new JSONObject(string), this.f489d);
                    if (b != null) {
                        hashMap.put(b.mo7015b(), b);
                        AppboyLogger.m1733d(f486b, String.format("Retrieving templated triggered action id %s from local storage.", new Object[]{b.mo7015b()}));
                    }
                }
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f486b, "Encountered Json exception while parsing stored triggered actions.", e);
        } catch (Exception e2) {
            AppboyLogger.m1736e(f486b, "Encountered unexpected exception while parsing stored triggered actions.", e2);
        }
        return hashMap;
    }
}
