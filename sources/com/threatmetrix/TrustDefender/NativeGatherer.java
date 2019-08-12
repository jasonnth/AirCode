package com.threatmetrix.TrustDefender;

import android.content.Context;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NativeGatherer {

    /* renamed from: a */
    static final /* synthetic */ boolean f4073a = (!NativeGatherer.class.desiredAssertionStatus());

    /* renamed from: b */
    private static volatile NativeGatherer f4074b;

    /* renamed from: c */
    private static final String f4075c = C4834w.m2892a(NativeGatherer.class);

    /* renamed from: d */
    private static final Lock f4076d = new ReentrantLock();

    /* renamed from: e */
    private NativeGathererHelper f4077e = new NativeGathererHelper();

    /* renamed from: f */
    private String[] f4078f = null;

    /* renamed from: g */
    private long f4079g = 0;

    private class NativeGathererHelper {

        /* renamed from: g */
        static final /* synthetic */ boolean f4080g = (!NativeGatherer.class.desiredAssertionStatus());

        /* renamed from: a */
        boolean f4081a = false;

        /* renamed from: b */
        boolean f4082b = false;

        /* renamed from: c */
        boolean f4083c = false;

        /* renamed from: d */
        String f4084d = "";

        /* renamed from: e */
        int f4085e = 0;

        /* renamed from: f */
        String[] f4086f = {"/system/app", "/system/priv-app"};

        /* renamed from: i */
        private final String f4088i = C4834w.m2892a(NativeGathererHelper.class);

        /* renamed from: j */
        private final Lock f4089j = new ReentrantLock();

        /* access modifiers changed from: 0000 */
        public native int cancel();

        /* access modifiers changed from: 0000 */
        public native String[] checkURLs(String[] strArr);

        /* access modifiers changed from: 0000 */
        public native String[] findAllProcs();

        /* access modifiers changed from: 0000 */
        public native String[] findInstalledProcs();

        /* access modifiers changed from: 0000 */
        public native int findPackages(int i, int i2, String[] strArr, int i3);

        /* access modifiers changed from: 0000 */
        public native String[] findRunningProcs();

        /* access modifiers changed from: 0000 */
        public native void finit();

        /* access modifiers changed from: 0000 */
        public native String getBinaryArch();

        /* access modifiers changed from: 0000 */
        public native String getConfig(String str);

        /* access modifiers changed from: 0000 */
        public native String[] getFontList(String str);

        /* access modifiers changed from: 0000 */
        public native String[] getNetworkInfo();

        /* access modifiers changed from: 0000 */
        public native String getRandomString(int i);

        /* access modifiers changed from: 0000 */
        public native String hashFile(String str);

        /* access modifiers changed from: 0000 */
        public native boolean init(int i, String str, int i2);

        /* access modifiers changed from: 0000 */
        public native String md5(String str);

        /* access modifiers changed from: 0000 */
        public native int setConfig(String str, String str2);

        /* access modifiers changed from: 0000 */
        public native void setInfoLogging(int i);

        /* access modifiers changed from: 0000 */
        public native String sha1(String str);

        /* access modifiers changed from: 0000 */
        public native String urlEncode(String str);

        /* access modifiers changed from: 0000 */
        public native int waitUntilCancelled();

        /* access modifiers changed from: 0000 */
        public native String xor(String str, String str2);

        NativeGathererHelper() {
        }

        /* renamed from: a */
        private boolean m2537a(String dataPath, String sharedObjPath, int infoLoggingStatus) {
            if (this.f4081a) {
                return this.f4081a;
            }
            try {
                this.f4089j.lock();
                if (this.f4081a) {
                    boolean z = this.f4081a;
                    this.f4089j.unlock();
                    return z;
                }
                String shPath = sharedObjPath;
                ClassLoader classLoader = NativeGatherer.class.getClassLoader();
                if (!(classLoader instanceof PathClassLoader)) {
                    classLoader = classLoader.getParent();
                }
                if (classLoader instanceof PathClassLoader) {
                    Object returned = C4787at.m2741a((Object) classLoader, C4787at.m2746b(ClassLoader.class, "findLibrary", String.class), "tdm-4.0-90-jni");
                    if (returned != null) {
                        shPath = String.valueOf(returned);
                    }
                    if (C4770ai.m2633f(shPath)) {
                        String str = this.f4088i;
                        this.f4084d = C4770ai.m2630c(shPath);
                        if (!"36a80b06f150a2992b344b46296c9ec471d45caf|f3477b1e7ad0cdcd08e78f5573a7bb22a5db312d|003c121233031073e0ef405c247f3a2df6dd5b54|6391b0a41ca80934ae9df3b825236d78efa69559|8b4fc32b863660b423159d5320028bc04ae0bea6|46488b641dbcdd70a559755dfed006a3bd1a59b4|aee9a1076de5436f2aad09f444cc6642634c8fe4|9aafeaee349c8909dcc88dfe1bb83dc21a50b3e6|7e204bbb780a37fb49c88cd6603b10f30580c7c6|27fe124a88e49203d77859d80d87d2c3b7feda03|5faf2a774672f3ee03b65f386c2f0cb09b9ece16|79e97d903d5edbaa95195b8fa367e2f9497abeb4|f0d896d61f4283a49089acf5ac6d8d86e1fb6563|824513af9ae7006357cbc74678d26a663729a16b".contains(this.f4084d)) {
                            this.f4081a = false;
                            this.f4089j.unlock();
                            return false;
                        }
                        this.f4083c = true;
                    }
                }
                System.loadLibrary("tdm-4.0-90-jni");
                this.f4081a = init(TrustDefenderVersion.numeric.intValue(), dataPath, infoLoggingStatus);
                String str2 = this.f4088i;
                new StringBuilder("NativeGatherer() complete, found ").append(this.f4085e);
                this.f4089j.unlock();
                return this.f4081a;
            } catch (UnsatisfiedLinkError e) {
                C4834w.m2895a(this.f4088i, "Native code:", (Throwable) e);
                this.f4081a = false;
            } catch (Throwable th) {
                this.f4089j.unlock();
                throw th;
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final boolean mo45879a(Context context, int infoLoggingStatus) {
            if (f4080g || context != null) {
                String path = context.getFilesDir().getAbsolutePath();
                String nativePath = new C4798a(context).mo46043c();
                if (!m2537a(path, nativePath, infoLoggingStatus)) {
                    String[] files = new File(nativePath).list(new FilenameFilter() {
                        public final boolean accept(File dir, String filename) {
                            return filename.contains("tdm-4.0-90-jni");
                        }
                    });
                    if (!(files == null || files.length == 0)) {
                        this.f4082b = true;
                    }
                }
                return this.f4081a;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public final void finalize() throws Throwable {
            finit();
        }
    }

    /* renamed from: a */
    static NativeGatherer m2512a() {
        if (f4074b == null) {
            try {
                f4076d.lock();
                if (f4074b == null) {
                    f4074b = new NativeGatherer();
                }
            } finally {
                f4076d.unlock();
            }
        }
        return f4074b;
    }

    private NativeGatherer() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo45859a(Context context, int infoLoggingStatus) {
        return this.f4077e.mo45879a(context, infoLoggingStatus);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final boolean mo45864b() {
        return this.f4077e.f4081a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final int mo45855a(Context context, int flags, int pkgLimit, int timeLimit) throws InterruptedException {
        String[] strArr;
        if (f4073a || context != null) {
            int found = 0;
            try {
                if (this.f4077e.f4081a) {
                    NativeGathererHelper nativeGathererHelper = this.f4077e;
                    if (this.f4078f == null || TimeUnit.SECONDS.convert(System.nanoTime() - this.f4079g, TimeUnit.NANOSECONDS) >= 60) {
                        C4834w.m2901c(f4075c, "Starting path find for apk");
                        this.f4079g = System.nanoTime();
                        ArrayList a = new C4808i(context).mo46046a();
                        C4834w.m2901c(f4075c, "findAPKPaths found : " + a.size());
                        this.f4078f = (String[]) a.toArray(new String[a.size()]);
                        strArr = this.f4078f;
                    } else {
                        strArr = this.f4078f;
                    }
                    nativeGathererHelper.f4086f = strArr;
                    found = this.f4077e.findPackages(pkgLimit, timeLimit, this.f4077e.f4086f, flags);
                }
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
            } catch (Throwable th) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                throw th;
            }
            return found;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String[] mo45860a(String[] urls) throws InterruptedException {
        try {
            C4834w.m2901c(f4075c, (this.f4077e.f4081a ? " available " : "not available ") + " Found " + this.f4077e.f4085e);
            if (!this.f4077e.f4081a || urls == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String[] checkURLs = this.f4077e.checkURLs(urls);
            if (!Thread.interrupted()) {
                return checkURLs;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final int mo45865c() {
        try {
            if (this.f4077e.f4081a) {
                return this.f4077e.cancel();
            }
        } catch (Throwable t) {
            C4834w.m2895a(f4075c, "Native code:", t);
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final int mo45867d() {
        try {
            if (this.f4077e.f4081a) {
                return this.f4077e.waitUntilCancelled();
            }
        } catch (Throwable t) {
            C4834w.m2895a(f4075c, "Native code:", t);
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo45858a(String filename) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || filename == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String hashFile = this.f4077e.hashFile(filename);
            if (!Thread.interrupted()) {
                return hashFile;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final String mo45861b(String blob) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || blob == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String md5 = this.f4077e.md5(blob);
            if (!Thread.interrupted()) {
                return md5;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final String mo45866c(String key) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || key == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String config = this.f4077e.getConfig(key);
            if (!Thread.interrupted()) {
                return config;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final int mo45856a(String key, String value) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || key == null || value == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return -1;
            }
            int config = this.f4077e.setConfig(key, value);
            if (!Thread.interrupted()) {
                return config;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final String mo45868d(String blob) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || blob == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String sha1 = this.f4077e.sha1(blob);
            if (!Thread.interrupted()) {
                return sha1;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo45857a(int length) throws InterruptedException {
        try {
            if (this.f4077e.f4081a) {
                String randomString = this.f4077e.getRandomString(32);
                if (!Thread.interrupted()) {
                    return randomString;
                }
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final String mo45869e(String in) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || in == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String urlEncode = this.f4077e.urlEncode(in);
            if (!Thread.interrupted()) {
                return urlEncode;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final List<String> mo45871f(String fontPath) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || fontPath == null) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String[] fonts = this.f4077e.getFontList(fontPath);
            if (fonts != null) {
                List<String> asList = Arrays.asList(fonts);
                if (!Thread.interrupted()) {
                    return asList;
                }
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            ArrayList arrayList = new ArrayList();
            if (!Thread.interrupted()) {
                return arrayList;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final String[] mo45870e() throws InterruptedException {
        try {
            if (this.f4077e.f4081a) {
                String[] findRunningProcs = this.f4077e.findRunningProcs();
                if (!Thread.interrupted()) {
                    return findRunningProcs;
                }
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final String[] mo45872f() throws InterruptedException {
        try {
            if (this.f4077e.f4081a) {
                String[] findInstalledProcs = this.f4077e.findInstalledProcs();
                if (!Thread.interrupted()) {
                    return findInstalledProcs;
                }
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final String[] mo45873g() throws InterruptedException {
        try {
            if (this.f4077e.f4081a) {
                String[] findAllProcs = this.f4077e.findAllProcs();
                if (!Thread.interrupted()) {
                    return findAllProcs;
                }
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            return null;
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final String mo45862b(String source, String key) throws InterruptedException {
        try {
            if (!this.f4077e.f4081a || key == null || source == null || key.length() <= 0 || source.isEmpty()) {
                if (Thread.interrupted()) {
                    C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                    throw new InterruptedException();
                }
                return null;
            }
            String xor = this.f4077e.xor(source, key);
            if (!Thread.interrupted()) {
                return xor;
            }
            C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
            throw new InterruptedException();
        } catch (Throwable th) {
            if (Thread.interrupted()) {
                C4834w.m2901c(f4075c, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: h */
    public final String mo45874h() {
        try {
            if (this.f4077e.f4081a) {
                return this.f4077e.getBinaryArch();
            }
        } catch (Throwable t) {
            C4834w.m2895a(f4075c, "Native code:", t);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: i */
    public final String[] mo45875i() {
        try {
            if (this.f4077e.f4081a) {
                return this.f4077e.getNetworkInfo();
            }
        } catch (Throwable t) {
            C4834w.m2895a(f4075c, "Native code:", t);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public final boolean mo45876j() {
        return this.f4077e.f4082b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: k */
    public final String mo45877k() {
        return this.f4077e.f4084d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: l */
    public final boolean mo45878l() {
        return this.f4077e.f4083c;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final void mo45863b(int infoLogging) {
        try {
            if (this.f4077e.f4081a) {
                this.f4077e.setInfoLogging(infoLogging);
            }
        } catch (Throwable t) {
            C4834w.m2895a(f4075c, "Native code:", t);
        }
    }
}
