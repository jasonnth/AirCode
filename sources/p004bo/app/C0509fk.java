package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Process;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.StringUtils;
import com.appboy.support.WebContentUtils;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: bo.app.fk */
public class C0509fk implements C0505fg {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f477a = AppboyLogger.getAppboyLogTag(C0509fk.class);

    /* renamed from: b */
    private final Context f478b;

    /* renamed from: c */
    private final ThreadPoolExecutor f479c;

    /* renamed from: d */
    private final SharedPreferences f480d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Map<String, String> f481e;

    /* renamed from: f */
    private Map<String, String> f482f = new HashMap();

    public C0509fk(Context context, ThreadPoolExecutor threadPoolExecutor, String str) {
        this.f478b = context;
        this.f479c = threadPoolExecutor;
        this.f480d = context.getSharedPreferences("com.appboy.storage.triggers.local_assets." + str, 0);
        this.f481e = mo7064a();
    }

    /* renamed from: a */
    public void mo7060a(List<C0467dx> list) {
        if (!AppboyFileUtils.canStoreAssetsLocally(this.f478b)) {
            AppboyLogger.m1737i(f477a, "Can not store assets locally. Write external permission must be grantedon devices running lower than Kit-Kat (API 19).");
            return;
        }
        final HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (C0467dx dxVar : list) {
            C0514fn d = dxVar.mo7017d();
            if (d != null && !StringUtils.isNullOrBlank(d.mo7072b())) {
                if (dxVar.mo7013a()) {
                    AppboyLogger.m1733d(f477a, String.format("Received new remote path for triggered action %s at %s.", new Object[]{dxVar.mo7015b(), d.mo7072b()}));
                    hashSet.add(d);
                    hashSet2.add(d.mo7072b());
                } else {
                    AppboyLogger.m1733d(f477a, String.format("Pre-fetch off for triggered action %s. Not pre-fetching assets at remote path %s.", new Object[]{dxVar.mo7015b(), d.mo7072b()}));
                }
            }
        }
        final Editor edit = this.f480d.edit();
        for (String str : new HashSet(this.f481e.keySet())) {
            if (this.f482f.containsKey(str)) {
                AppboyLogger.m1733d(f477a, String.format("Not removing local path for remote path %s from cache because it is being preserved until the end of the app run.", new Object[]{str}));
            } else if (!hashSet2.contains(str)) {
                String str2 = (String) this.f481e.get(str);
                AppboyLogger.m1733d(f477a, String.format("Removing obsolete local path %s for obsolete remote path %s from cache.", new Object[]{str2, str}));
                this.f481e.remove(str);
                edit.remove(str);
                AppboyFileUtils.deleteFileOrDirectory(new File(str2));
            }
        }
        edit.apply();
        try {
            File[] listFiles = mo7065b().listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    String path = file.getPath();
                    if (this.f481e.containsValue(path)) {
                        AppboyLogger.m1733d(f477a, String.format("Asset %s is not obsolete. Not deleting.", new Object[]{path}));
                    } else if (!this.f482f.containsValue(path)) {
                        AppboyLogger.m1733d(f477a, String.format("Deleting obsolete asset %s from filesystem.", new Object[]{path}));
                        AppboyFileUtils.deleteFileOrDirectory(file);
                    } else {
                        AppboyLogger.m1733d(f477a, String.format("Asset %s is being preserved. Not deleting.", new Object[]{path}));
                    }
                }
            }
        } catch (Exception e) {
            AppboyLogger.m1734d(f477a, "Exception while deleting obsolete assets from filesystem.", e);
        }
        this.f479c.execute(new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                for (C0514fn fnVar : hashSet) {
                    String b = fnVar.mo7072b();
                    if (!C0509fk.this.f481e.containsKey(b)) {
                        try {
                            String a = C0509fk.this.mo7063a(fnVar);
                            if (!StringUtils.isNullOrBlank(a)) {
                                AppboyLogger.m1733d(C0509fk.f477a, String.format("Adding new local path %s for remote path %s to cache.", new Object[]{a, b}));
                                C0509fk.this.f481e.put(b, a);
                                edit.putString(b, a);
                            }
                        } catch (Exception e) {
                            AppboyLogger.m1734d(C0509fk.f477a, String.format("Failed to add new local path for remote path %s.", new Object[]{b}), e);
                        }
                    }
                }
                edit.apply();
            }
        });
    }

    /* renamed from: a */
    public String mo7058a(C0467dx dxVar) {
        if (!dxVar.mo7013a()) {
            AppboyLogger.m1733d(f477a, "Prefetch turned off for this triggered action. Not retrieving local asset path.");
            return null;
        }
        C0514fn d = dxVar.mo7017d();
        if (d == null) {
            AppboyLogger.m1737i(f477a, "Remote path was null or blank. Not retrieving local asset path.");
            return null;
        }
        String b = d.mo7072b();
        if (StringUtils.isNullOrBlank(b)) {
            AppboyLogger.m1739w(f477a, "Remote asset path string was null or blank. Not retrieving local asset path.");
            return null;
        } else if (this.f481e.containsKey(b)) {
            String str = (String) this.f481e.get(b);
            if (!new File(str).exists()) {
                AppboyLogger.m1739w(f477a, "Local asset for remote asset path did not exist: " + b);
                return null;
            }
            AppboyLogger.m1737i(f477a, "Retrieving local asset path for remote asset path: " + b);
            this.f482f.put(b, str);
            return str;
        } else {
            AppboyLogger.m1739w(f477a, "No local asset path found for remote asset path: " + b);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public String mo7063a(C0514fn fnVar) {
        File b = mo7065b();
        String b2 = fnVar.mo7072b();
        if (fnVar.mo7071a().equals(C0493ev.ZIP)) {
            String localHtmlUrlFromRemoteUrl = WebContentUtils.getLocalHtmlUrlFromRemoteUrl(b, b2);
            if (!StringUtils.isNullOrBlank(localHtmlUrlFromRemoteUrl)) {
                AppboyLogger.m1737i(f477a, String.format("Storing local triggered action html zip asset at local path %s for remote path %s.", new Object[]{localHtmlUrlFromRemoteUrl, b2}));
                return localHtmlUrlFromRemoteUrl;
            }
            AppboyLogger.m1733d(f477a, String.format("Failed to store html zip asset for remote path %s. Not storing local asset", new Object[]{b2}));
            return null;
        }
        File downloadFileToPath = AppboyFileUtils.downloadFileToPath(b.toString(), b2, Integer.toString(IntentUtils.getRequestCode()), null);
        if (downloadFileToPath == null) {
            return null;
        }
        Uri fromFile = Uri.fromFile(downloadFileToPath);
        if (fromFile != null) {
            AppboyLogger.m1737i(f477a, String.format("Storing local triggered action image asset at local path %s for remote path %s.", new Object[]{fromFile.getPath(), b2}));
            return fromFile.getPath();
        }
        AppboyLogger.m1733d(f477a, String.format("Failed to store image asset for remote path %s. Not storing local asset", new Object[]{b2}));
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public Map<String, String> mo7064a() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Map all = this.f480d.getAll();
        if (all == null || all.size() == 0) {
            return concurrentHashMap;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            return concurrentHashMap;
        }
        try {
            for (String str : keySet) {
                String string = this.f480d.getString(str, null);
                if (!StringUtils.isNullOrBlank(string)) {
                    AppboyLogger.m1733d(f477a, String.format("Retrieving trigger local asset path %s from local storage for remote path %s.", new Object[]{string, str}));
                    concurrentHashMap.put(str, string);
                }
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(f477a, "Encountered unexpected exception while parsing stored triggered action local assets.", e);
        }
        return concurrentHashMap;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public File mo7065b() {
        return new File(AppboyFileUtils.getApplicationCacheDir(this.f478b).getPath() + "/" + "ab_triggers");
    }
}
