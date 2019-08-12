package com.threatmetrix.TrustDefender;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: com.threatmetrix.TrustDefender.g */
class C4797g {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f4344a = C4834w.m2892a(C4797g.class);

    /* renamed from: com.threatmetrix.TrustDefender.g$a */
    class C4798a {

        /* renamed from: b */
        private ApplicationInfo f4346b = null;

        C4798a(Context context) {
            if (C4803d.f4385c != null && C4803d.f4386d != null) {
                this.f4346b = context.getApplicationInfo();
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final String mo46041a() {
            if (this.f4346b != null) {
                return this.f4346b.packageName;
            }
            return "";
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public final String mo46042b() {
            if (this.f4346b != null) {
                return this.f4346b.sourceDir;
            }
            return "";
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: c */
        public final String mo46043c() {
            if (this.f4346b != null) {
                return this.f4346b.nativeLibraryDir;
            }
            return "";
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$b */
    static class C4799b {

        /* renamed from: a */
        static long f4347a;

        /* renamed from: b */
        static String f4348b;

        /* renamed from: c */
        static String f4349c;

        /* renamed from: d */
        static String f4350d;

        /* renamed from: e */
        static String f4351e;

        /* renamed from: f */
        static String f4352f;

        /* renamed from: g */
        static String f4353g;

        /* renamed from: h */
        static String f4354h;

        /* renamed from: i */
        static String f4355i;

        /* renamed from: j */
        static String f4356j;

        /* renamed from: k */
        static String f4357k;

        /* renamed from: l */
        static String f4358l;

        /* renamed from: m */
        static String f4359m;

        /* renamed from: n */
        private static Class<?> f4360n;

        /* renamed from: com.threatmetrix.TrustDefender.g$b$a */
        static class C4800a {

            /* renamed from: a */
            static String f4361a;

            /* renamed from: b */
            static String f4362b;

            /* renamed from: c */
            static int f4363c;

            /* renamed from: d */
            private static Class<?> f4364d;

            static {
                f4361a = null;
                f4362b = null;
                f4363c = -1;
                Class<?> b = C4787at.m2745b("android.os.Build$VERSION");
                f4364d = b;
                if (C4787at.m2742a((Class) b, "RELEASE") != null) {
                    f4361a = VERSION.RELEASE;
                }
                if (C4787at.m2742a((Class) f4364d, "SDK_INT") != null) {
                    f4363c = VERSION.SDK_INT;
                }
                if (C4787at.m2742a((Class) f4364d, "CODENAME") != null) {
                    f4362b = VERSION.CODENAME;
                }
            }
        }

        /* renamed from: com.threatmetrix.TrustDefender.g$b$b */
        static class C4801b {

            /* renamed from: a */
            static int f4365a;

            /* renamed from: b */
            static int f4366b;

            /* renamed from: c */
            static int f4367c;

            /* renamed from: d */
            static int f4368d;

            /* renamed from: e */
            static int f4369e;

            /* renamed from: f */
            static int f4370f;

            /* renamed from: g */
            static int f4371g;

            /* renamed from: h */
            static int f4372h;

            /* renamed from: i */
            static int f4373i;

            /* renamed from: j */
            static int f4374j;

            /* renamed from: k */
            static int f4375k;

            /* renamed from: l */
            static int f4376l;

            /* renamed from: m */
            static int f4377m;

            /* renamed from: n */
            static int f4378n;

            /* renamed from: o */
            static int f4379o;

            /* renamed from: p */
            private static Class<?> f4380p;

            static {
                f4365a = 8;
                f4366b = 9;
                f4367c = 10;
                f4368d = 11;
                f4369e = 12;
                f4370f = 13;
                f4371g = 14;
                f4372h = 15;
                f4373i = 16;
                f4374j = 17;
                f4375k = 18;
                f4376l = 19;
                f4377m = 20;
                f4378n = 21;
                f4379o = 22;
                Class<?> b = C4787at.m2745b("android.os.Build$VERSION_CODES");
                f4380p = b;
                if (C4787at.m2742a((Class) b, "FROYO") != null) {
                    f4365a = 8;
                }
                if (C4787at.m2742a((Class) f4380p, "GINGERBREAD") != null) {
                    f4366b = 9;
                }
                if (C4787at.m2742a((Class) f4380p, "GINGERBREAD_MR1") != null) {
                    f4367c = 10;
                }
                if (C4787at.m2742a((Class) f4380p, "HONEYCOMB") != null) {
                    f4368d = 11;
                }
                if (C4787at.m2742a((Class) f4380p, "HONEYCOMB_MR1") != null) {
                    f4369e = 12;
                }
                if (C4787at.m2742a((Class) f4380p, "HONEYCOMB_MR2") != null) {
                    f4370f = 13;
                }
                if (C4787at.m2742a((Class) f4380p, "ICE_CREAM_SANDWICH") != null) {
                    f4371g = 14;
                }
                if (C4787at.m2742a((Class) f4380p, "ICE_CREAM_SANDWICH_MR1") != null) {
                    f4372h = 15;
                }
                if (C4787at.m2742a((Class) f4380p, "JELLY_BEAN") != null) {
                    f4373i = 16;
                }
                if (C4787at.m2742a((Class) f4380p, "JELLY_BEAN_MR1") != null) {
                    f4374j = 17;
                }
                if (C4787at.m2742a((Class) f4380p, "JELLY_BEAN_MR2") != null) {
                    f4375k = 18;
                }
                if (C4787at.m2742a((Class) f4380p, "KITKAT") != null) {
                    f4376l = 19;
                }
                if (C4787at.m2742a((Class) f4380p, "KITKAT_WATCH") != null) {
                    f4377m = 20;
                }
                if (C4787at.m2742a((Class) f4380p, "LOLLIPOP") != null) {
                    f4378n = 21;
                }
                if (C4787at.m2742a((Class) f4380p, "LOLLIPOP_MR1") != null) {
                    f4379o = 22;
                }
            }
        }

        static {
            f4347a = Long.MAX_VALUE;
            f4348b = null;
            f4349c = null;
            f4350d = null;
            f4351e = null;
            f4352f = null;
            f4353g = null;
            f4354h = null;
            f4355i = null;
            f4356j = null;
            f4357k = null;
            f4358l = null;
            f4359m = null;
            Class<?> b = C4787at.m2745b("android.os.Build");
            f4360n = b;
            if (C4787at.m2742a((Class) b, "TIME") != null) {
                f4347a = Build.TIME;
            }
            if (C4787at.m2742a((Class) f4360n, "TYPE") != null) {
                f4348b = Build.TYPE;
            }
            if (C4787at.m2742a((Class) f4360n, "TAGS") != null) {
                f4349c = Build.TAGS;
            }
            if (C4787at.m2742a((Class) f4360n, "HOST") != null) {
                f4350d = Build.HOST;
            }
            if (C4787at.m2742a((Class) f4360n, "BRAND") != null) {
                f4351e = Build.BRAND;
            }
            if (C4787at.m2742a((Class) f4360n, "USER") != null) {
                f4352f = Build.USER;
            }
            if (C4787at.m2742a((Class) f4360n, "ID") != null) {
                f4353g = Build.ID;
            }
            if (C4787at.m2742a((Class) f4360n, "SERIAL") != null) {
                f4354h = Build.SERIAL;
            }
            if (C4787at.m2742a((Class) f4360n, "DEVICE") != null) {
                f4355i = Build.DEVICE;
            }
            if (C4787at.m2742a((Class) f4360n, "MODEL") != null) {
                f4356j = Build.MODEL;
            }
            if (C4787at.m2742a((Class) f4360n, "DISPLAY") != null) {
                f4357k = Build.DISPLAY;
            }
            if (C4787at.m2742a((Class) f4360n, "PRODUCT") != null) {
                f4358l = Build.PRODUCT;
            }
            if (C4787at.m2742a((Class) f4360n, "MANUFACTURER") != null) {
                f4359m = Build.MANUFACTURER;
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$c */
    static class C4802c {

        /* renamed from: a */
        static Method f4381a;

        /* renamed from: b */
        private static Class<?> f4382b;

        static {
            Class<?> b = C4787at.m2745b("android.app.admin.DevicePolicyManager");
            f4382b = b;
            f4381a = C4787at.m2743a((Class) b, "getStorageEncryptionStatus", new Class[0]);
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$d */
    static class C4803d {

        /* renamed from: a */
        static final int f4383a = 1;

        /* renamed from: b */
        static final int f4384b = 128;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public static Class<?> f4385c = C4787at.m2745b("android.content.pm.ApplicationInfo");
        /* access modifiers changed from: private */

        /* renamed from: d */
        public static Class<?> f4386d = C4787at.m2745b("android.content.pm.PackageItemInfo");
        /* access modifiers changed from: private */

        /* renamed from: e */
        public static Class<?> f4387e = C4787at.m2745b("android.content.pm.PackageManager");
        /* access modifiers changed from: private */

        /* renamed from: f */
        public static Class<?> f4388f = C4787at.m2745b("android.content.pm.PackageInfo");
        /* access modifiers changed from: private */

        /* renamed from: g */
        public static Method f4389g = C4787at.m2743a((Class) f4387e, "checkPermission", String.class, String.class);
        /* access modifiers changed from: private */

        /* renamed from: h */
        public static Field f4390h = C4787at.m2742a((Class) f4388f, "versionCode");
        /* access modifiers changed from: private */

        /* renamed from: i */
        public static Field f4391i = C4787at.m2742a((Class) f4388f, "versionName");

        static {
            Class<?> cls = f4387e;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$e */
    static class C4804e {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static Class<?> f4392a = C4787at.m2745b("android.content.SharedPreferences$Editor");
        /* access modifiers changed from: private */

        /* renamed from: b */
        public static Class<?> f4393b = C4787at.m2745b("android.content.SharedPreferences");
        /* access modifiers changed from: private */

        /* renamed from: c */
        public static Method f4394c = C4787at.m2743a((Class) f4393b, "getString", String.class, String.class);
        /* access modifiers changed from: private */

        /* renamed from: d */
        public static Method f4395d = C4787at.m2743a((Class) f4393b, "getInt", String.class, Integer.TYPE);
        /* access modifiers changed from: private */

        /* renamed from: e */
        public static Method f4396e = C4787at.m2743a((Class) f4393b, "getLong", String.class, Long.TYPE);
        /* access modifiers changed from: private */

        /* renamed from: f */
        public static Method f4397f = C4787at.m2743a((Class) f4392a, "putString", String.class, String.class);
        /* access modifiers changed from: private */

        /* renamed from: g */
        public static Method f4398g = C4787at.m2743a((Class) f4392a, "putLong", String.class, Long.TYPE);
        /* access modifiers changed from: private */

        /* renamed from: h */
        public static Method f4399h = C4787at.m2743a((Class) f4392a, "putInt", String.class, Integer.TYPE);
        /* access modifiers changed from: private */

        /* renamed from: i */
        public static Method f4400i = C4787at.m2743a((Class) f4392a, "apply", new Class[0]);
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$f */
    static class C4805f {

        /* renamed from: a */
        private static Class<?> f4401a = C4787at.m2745b("android.location.Criteria");

        /* renamed from: b */
        private static Class<?> f4402b = C4787at.m2745b("android.location.Location");

        /* renamed from: c */
        private static Class<?> f4403c = C4787at.m2745b("android.location.LocationProvider");

        /* renamed from: d */
        private static Class<?> f4404d = C4787at.m2745b("android.location.LocationListener");

        /* renamed from: e */
        private static Method f4405e = C4787at.m2743a((Class) f4401a, "setAccuracy", Integer.TYPE);

        /* renamed from: f */
        private static Method f4406f = C4787at.m2743a((Class) f4401a, "setAltitudeRequired", Boolean.TYPE);

        /* renamed from: g */
        private static Method f4407g = C4787at.m2743a((Class) f4401a, "setBearingAccuracy", Integer.TYPE);

        /* renamed from: h */
        private static Method f4408h = C4787at.m2743a((Class) f4401a, "setCostAllowed", Boolean.TYPE);

        /* renamed from: i */
        private static Method f4409i = C4787at.m2743a((Class) f4401a, "setSpeedAccuracy", Integer.TYPE);

        /* renamed from: j */
        private static Method f4410j = C4787at.m2743a((Class) f4401a, "setSpeedRequired", Boolean.TYPE);

        /* renamed from: k */
        private static Method f4411k = C4787at.m2743a((Class) f4401a, "setVerticalAccuracy", Integer.TYPE);

        /* renamed from: l */
        private static Method f4412l = C4787at.m2743a((Class) f4401a, "setPowerRequirement", Integer.TYPE);

        /* renamed from: m */
        private static Method f4413m = C4787at.m2743a((Class) f4402b, "getTime", new Class[0]);

        /* renamed from: n */
        private static Method f4414n = C4787at.m2743a((Class) f4402b, "getProvider", new Class[0]);

        /* renamed from: o */
        private static Method f4415o = C4787at.m2743a((Class) f4402b, "getAccuracy", new Class[0]);

        /* renamed from: p */
        private static Method f4416p = C4787at.m2743a((Class) f4402b, "getLatitude", new Class[0]);

        /* renamed from: q */
        private static Method f4417q = C4787at.m2743a((Class) f4402b, "getLongitude", new Class[0]);

        /* renamed from: r */
        private static Field f4418r = C4787at.m2742a((Class) f4401a, "NO_REQUIREMENT");

        /* renamed from: s */
        private static Field f4419s = C4787at.m2742a((Class) f4401a, "POWER_LOW");

        /* renamed from: t */
        private static Field f4420t = C4787at.m2742a((Class) f4401a, "ACCURACY_LOW");

        /* renamed from: u */
        private static Field f4421u = C4787at.m2742a((Class) f4401a, "ACCURACY_COARSE");

        /* renamed from: v */
        private static Field f4422v = C4787at.m2742a((Class) f4403c, "AVAILABLE");

        /* renamed from: w */
        private static Field f4423w = C4787at.m2742a((Class) f4403c, "TEMPORARILY_UNAVAILABLE");

        /* renamed from: x */
        private static Field f4424x = C4787at.m2742a((Class) f4403c, "OUT_OF_SERVICE");

        /* renamed from: a */
        static boolean m2788a() {
            return (f4405e == null || f4406f == null || f4407g == null || f4408h == null || f4409i == null || f4410j == null || f4411k == null || f4412l == null || f4418r == null || f4419s == null || f4420t == null || f4421u == null) ? false : true;
        }

        /* renamed from: b */
        static boolean m2789b() {
            return (f4404d == null || f4413m == null || f4414n == null || f4416p == null || f4417q == null || f4422v == null || f4423w == null || f4424x == null) ? false : true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$g */
    static class C4806g {

        /* renamed from: a */
        private static Class<?> f4425a = C4787at.m2745b("android.net.ConnectivityManager");

        /* renamed from: b */
        private static Class<?> f4426b = C4787at.m2745b("android.net.NetworkInfo");

        /* renamed from: c */
        private static Class<?> f4427c = C4787at.m2745b("android.net.NetworkInfo$State");

        /* renamed from: d */
        private static Class<?> f4428d = C4787at.m2745b("android.net.wifi.WifiInfo");

        /* renamed from: e */
        private static Class<?> f4429e = C4787at.m2745b("android.net.wifi.WifiManager");

        /* renamed from: f */
        private static Method f4430f = C4787at.m2743a((Class) f4425a, "getActiveNetworkInfo", new Class[0]);

        /* renamed from: g */
        private static Method f4431g = C4787at.m2743a((Class) f4426b, "isConnectedOrConnecting", new Class[0]);

        /* renamed from: h */
        private static Method f4432h = C4787at.m2743a((Class) f4426b, "getState", new Class[0]);

        /* renamed from: i */
        private static Method f4433i = C4787at.m2743a((Class) f4426b, "getType", new Class[0]);

        /* renamed from: j */
        private static Method f4434j = C4787at.m2743a((Class) f4426b, "getExtraInfo", new Class[0]);

        /* renamed from: k */
        private static Method f4435k = C4787at.m2743a((Class) f4428d, "getBSSID", new Class[0]);

        /* renamed from: l */
        private static Method f4436l = C4787at.m2743a((Class) f4428d, "getSSID", new Class[0]);

        /* renamed from: m */
        private static Method f4437m = C4787at.m2743a((Class) f4428d, "getRssi", new Class[0]);

        /* renamed from: n */
        private static Method f4438n = C4787at.m2743a((Class) f4429e, "getConnectionInfo", new Class[0]);

        /* renamed from: o */
        private static Field f4439o = C4787at.m2742a((Class) f4425a, "CONNECTIVITY_ACTION");

        /* renamed from: p */
        private static Field f4440p = C4787at.m2742a((Class) f4425a, "TYPE_MOBILE");

        /* renamed from: q */
        private static Field f4441q = C4787at.m2742a((Class) f4425a, "TYPE_WIFI");

        /* renamed from: r */
        private static Field f4442r = C4787at.m2742a((Class) f4425a, "TYPE_BLUETOOTH");

        /* renamed from: s */
        private static Field f4443s = C4787at.m2742a((Class) f4425a, "TYPE_ETHERNET");

        /* renamed from: t */
        private static Field f4444t = C4787at.m2742a((Class) f4427c, "CONNECTED");

        /* renamed from: u */
        private static Field f4445u = C4787at.m2742a((Class) f4429e, "NETWORK_STATE_CHANGED_ACTION");

        /* renamed from: a */
        static boolean m2790a() {
            return (f4430f == null || f4431g == null) ? false : true;
        }

        /* renamed from: b */
        static boolean m2791b() {
            boolean result;
            if (f4439o == null || f4444t == null || f4432h == null || f4434j == null || f4433i == null || f4440p == null || f4441q == null) {
                result = false;
            } else {
                result = true;
            }
            if (C4800a.f4363c < C4801b.f4370f) {
                return result;
            }
            if (!result || f4443s == null || f4442r == null) {
                return false;
            }
            return true;
        }

        /* renamed from: c */
        static boolean m2792c() {
            return (f4445u == null || f4444t == null || f4435k == null || f4436l == null || f4437m == null || f4432h == null || f4434j == null) ? false : true;
        }

        /* renamed from: d */
        static boolean m2793d() {
            return (f4438n == null || f4435k == null || f4436l == null || f4437m == null) ? false : true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$h */
    class C4807h {

        /* renamed from: b */
        private PackageInfo f4447b = null;

        C4807h(Context context, String pkgName, int flags) {
            if (C4803d.f4388f != null && C4803d.f4387e != null) {
                try {
                    this.f4447b = context.getPackageManager().getPackageInfo(pkgName, flags);
                } catch (NameNotFoundException e) {
                    C4797g.f4344a;
                } catch (SecurityException e2) {
                    C4797g.f4344a;
                } catch (Exception e3) {
                    C4834w.m2901c(C4797g.f4344a, e3.getMessage());
                }
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final int mo46044a() {
            if (C4803d.f4390h == null || this.f4447b == null) {
                return -1;
            }
            return this.f4447b.versionCode;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public final String mo46045b() {
            if (C4803d.f4391i == null || this.f4447b == null) {
                return null;
            }
            return this.f4447b.versionName;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$i */
    class C4808i {

        /* renamed from: b */
        private PackageManager f4449b = null;

        C4808i(Context context) {
            if (C4803d.f4387e != null) {
                try {
                    this.f4449b = context.getPackageManager();
                } catch (SecurityException e) {
                    C4797g.f4344a;
                } catch (Exception e2) {
                    C4834w.m2901c(C4797g.f4344a, e2.getMessage());
                }
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final boolean mo46048a(String permName, String pkgName) {
            if (C4803d.f4389g == null || this.f4449b == null) {
                return false;
            }
            try {
                if (this.f4449b.checkPermission(permName, pkgName) == 0) {
                    return true;
                }
                return false;
            } catch (SecurityException e) {
                C4797g.f4344a;
                return false;
            } catch (Exception e2) {
                C4834w.m2901c(C4797g.f4344a, e2.getMessage());
                return false;
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final boolean mo46047a(String packageName, int flags) {
            if (!(C4803d.f4387e == null || C4803d.f4388f == null || this.f4449b == null)) {
                try {
                    this.f4449b.getPackageInfo(packageName, flags);
                    return true;
                } catch (NameNotFoundException e) {
                    C4797g.f4344a;
                } catch (SecurityException e2) {
                    C4797g.f4344a;
                } catch (Exception e3) {
                    C4834w.m2901c(C4797g.f4344a, e3.getMessage());
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final ArrayList<String> mo46046a() {
            ArrayList<String> packageList = new ArrayList<>();
            if (!(C4803d.f4387e == null || C4803d.f4385c == null || this.f4449b == null)) {
                try {
                    for (ApplicationInfo a : this.f4449b.getInstalledApplications(0)) {
                        if (!a.sourceDir.startsWith("/system/app") && !a.sourceDir.startsWith("/system/priv-app")) {
                            packageList.add(a.sourceDir);
                        }
                    }
                } catch (SecurityException e) {
                    C4797g.f4344a;
                } catch (Exception e2) {
                    C4834w.m2901c(C4797g.f4344a, e2.getMessage());
                }
            }
            packageList.add("/system/app");
            packageList.add("/system/priv-app");
            return packageList;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$j */
    static class C4809j {

        /* renamed from: a */
        private static Class<?> f4450a;

        /* renamed from: b */
        private static Method f4451b;

        /* renamed from: c */
        private static Method f4452c = C4787at.m2743a((Class) f4450a, "isScreenOn", new Class[0]);

        static {
            Class<?> b = C4787at.m2745b("android.os.PowerManager");
            f4450a = b;
            f4451b = C4787at.m2743a((Class) b, "isInteractive", new Class[0]);
        }

        /* renamed from: a */
        static boolean m2799a() {
            return (f4450a == null || f4452c == null) ? false : true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$k */
    static class C4810k {

        /* renamed from: a */
        private static Class<?> f4453a;

        /* renamed from: b */
        private static Method f4454b;

        /* renamed from: c */
        private static Field f4455c = C4787at.m2742a((Class) f4453a, "ANDROID_ID");

        /* renamed from: d */
        private static Field f4456d = C4787at.m2742a((Class) f4453a, "ALLOW_MOCK_LOCATION");

        static {
            Class<?> b = C4787at.m2745b("android.provider.Settings$Secure");
            f4453a = b;
            f4454b = C4787at.m2743a((Class) b, "getString", ContentResolver.class, String.class);
        }

        /* renamed from: a */
        static String m2800a(ContentResolver contentResolver, String name) {
            if (contentResolver == null || C4770ai.m2632e(name) || f4454b == null) {
                return null;
            }
            try {
                if ("ANDROID_ID".equals(name) && f4455c != null) {
                    return Secure.getString(contentResolver, JPushReportInterface.ANDROID_ID);
                }
                if (!"ALLOW_MOCK_LOCATION".equals(name) || f4456d == null) {
                    return null;
                }
                return Secure.getString(contentResolver, "mock_location");
            } catch (SecurityException e) {
                C4797g.f4344a;
                return null;
            } catch (Exception e2) {
                C4834w.m2901c(C4797g.f4344a, e2.getMessage());
                return null;
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$l */
    class C4811l {

        /* renamed from: b */
        private SharedPreferences f4458b = null;

        /* renamed from: c */
        private Editor f4459c = null;

        C4811l(Context context, String label, int mode) {
            if (C4804e.f4393b != null) {
                this.f4458b = context.getSharedPreferences(label, 0);
            }
            if (C4804e.f4392a != null) {
                this.f4459c = this.f4458b.edit();
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final int mo46049a(String key, int defaultValue) {
            if (C4804e.f4395d == null || this.f4458b == null) {
                return 0;
            }
            return this.f4458b.getInt(key, 0);
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final long mo46050a(String key, long defaultValue) {
            if (C4804e.f4396e == null || this.f4458b == null) {
                return 0;
            }
            return this.f4458b.getLong(key, 0);
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final String mo46051a(String key, String defaultValue) {
            if (C4804e.f4394c == null || this.f4458b == null) {
                return defaultValue;
            }
            return this.f4458b.getString(key, defaultValue);
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public final void mo46053b(String key, int value) {
            if (C4804e.f4399h != null && this.f4459c != null) {
                this.f4459c.putInt(key, value);
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public final void mo46054b(String key, long value) {
            if (C4804e.f4398g != null && this.f4459c != null) {
                this.f4459c.putLong(key, value);
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public final void mo46055b(String key, String value) {
            if (C4804e.f4397f != null && this.f4459c != null) {
                this.f4459c.putString(key, value);
            }
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final void mo46052a() {
            if (C4804e.f4400i != null && this.f4459c != null) {
                this.f4459c.apply();
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$m */
    static class C4812m {

        /* renamed from: a */
        private static Class<?> f4460a;

        /* renamed from: b */
        private static Method f4461b;

        /* renamed from: c */
        private static Method f4462c = C4787at.m2743a((Class) f4460a, "elapsedRealtime", new Class[0]);

        static {
            Class<?> b = C4787at.m2745b("android.os.SystemClock");
            f4460a = b;
            f4461b = C4787at.m2743a((Class) b, "uptimeMillis", new Class[0]);
        }

        /* renamed from: a */
        static long m2808a() {
            if (f4461b != null) {
                return SystemClock.uptimeMillis();
            }
            return 0;
        }

        /* renamed from: b */
        static long m2809b() {
            if (f4462c != null) {
                return SystemClock.elapsedRealtime();
            }
            return 0;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$n */
    static class C4813n {

        /* renamed from: A */
        private static Method f4463A;

        /* renamed from: B */
        private static Method f4464B;

        /* renamed from: C */
        private static Method f4465C;

        /* renamed from: D */
        private static Method f4466D;

        /* renamed from: E */
        private static Method f4467E;

        /* renamed from: F */
        private static Method f4468F;

        /* renamed from: G */
        private static Method f4469G;

        /* renamed from: H */
        private static Method f4470H;

        /* renamed from: I */
        private static Method f4471I;

        /* renamed from: J */
        private static Method f4472J;

        /* renamed from: K */
        private static Method f4473K;

        /* renamed from: L */
        private static Method f4474L;

        /* renamed from: M */
        private static Method f4475M;

        /* renamed from: N */
        private static Method f4476N;

        /* renamed from: O */
        private static Method f4477O;

        /* renamed from: P */
        private static Method f4478P;

        /* renamed from: Q */
        private static Method f4479Q;

        /* renamed from: R */
        private static Method f4480R;

        /* renamed from: S */
        private static Method f4481S;

        /* renamed from: T */
        private static Method f4482T;

        /* renamed from: U */
        private static Method f4483U;

        /* renamed from: V */
        private static Method f4484V;

        /* renamed from: W */
        private static Method f4485W;

        /* renamed from: X */
        private static Field f4486X;

        /* renamed from: Y */
        private static Field f4487Y;

        /* renamed from: Z */
        private static Field f4488Z;

        /* renamed from: a */
        private static Class<?> f4489a = C4787at.m2745b("android.telephony.TelephonyManager");

        /* renamed from: b */
        private static Class<?> f4490b = C4787at.m2745b("android.telephony.CellIdentityCdma");

        /* renamed from: c */
        private static Class<?> f4491c = C4787at.m2745b("android.telephony.CellIdentityGsm");

        /* renamed from: d */
        private static Class<?> f4492d = C4787at.m2745b("android.telephony.CellIdentityLte");

        /* renamed from: e */
        private static Class<?> f4493e = C4787at.m2745b("android.telephony.CellIdentityWcdma");

        /* renamed from: f */
        private static Class<?> f4494f = C4787at.m2745b("android.telephony.CellInfo");

        /* renamed from: g */
        private static Class<?> f4495g = C4787at.m2745b("android.telephony.CellInfoCdma");

        /* renamed from: h */
        private static Class<?> f4496h = C4787at.m2745b("android.telephony.CellInfoGsm");

        /* renamed from: i */
        private static Class<?> f4497i = C4787at.m2745b("android.telephony.CellInfoLte");

        /* renamed from: j */
        private static Class<?> f4498j = C4787at.m2745b("android.telephony.CellInfoWcdma");

        /* renamed from: k */
        private static Class<?> f4499k = C4787at.m2745b("android.telephony.CellSignalStrength");

        /* renamed from: l */
        private static Class<?> f4500l = C4787at.m2745b("android.telephony.NeighboringCellInfo");

        /* renamed from: m */
        private static Class<?> f4501m = C4787at.m2745b("android.telephony.CellLocation");

        /* renamed from: n */
        private static Class<?> f4502n = C4787at.m2745b("android.telephony.SubscriptionInfo");

        /* renamed from: o */
        private static Class<?> f4503o = C4787at.m2745b("android.telephony.SubscriptionManager");

        /* renamed from: p */
        private static Class<?> f4504p = C4787at.m2745b("android.telephony.cdma.CdmaCellLocation");

        /* renamed from: q */
        private static Class<?> f4505q = C4787at.m2745b("android.telephony.gsm.GsmCellLocation");

        /* renamed from: r */
        private static Method f4506r;

        /* renamed from: s */
        private static Method f4507s;

        /* renamed from: t */
        private static Method f4508t;

        /* renamed from: u */
        private static Method f4509u;

        /* renamed from: v */
        private static Method f4510v = C4787at.m2743a((Class) f4489a, "getCellLocation", new Class[0]);

        /* renamed from: w */
        private static Method f4511w;

        /* renamed from: x */
        private static Method f4512x;

        /* renamed from: y */
        private static Method f4513y;

        /* renamed from: z */
        private static Method f4514z;

        /* renamed from: a */
        static boolean m2810a() {
            f4507s = C4787at.m2743a((Class) f4489a, "getAllCellInfo", new Class[0]);
            f4484V = C4787at.m2743a((Class) f4494f, "isRegistered", new Class[0]);
            if (f4489a == null || f4499k == null || f4494f == null || f4484V == null || f4507s == null) {
                return false;
            }
            return true;
        }

        /* renamed from: b */
        static boolean m2811b() {
            f4506r = C4787at.m2743a((Class) f4489a, "getNetworkOperator", new Class[0]);
            f4508t = C4787at.m2743a((Class) f4489a, "getNetworkCountryIso", new Class[0]);
            f4509u = C4787at.m2746b(f4489a, "getNetworkOperatorName", new Class[0]);
            if (f4489a == null || f4506r == null || f4508t == null || f4509u == null) {
                return false;
            }
            return true;
        }

        /* renamed from: c */
        static boolean m2812c() {
            f4464B = C4787at.m2743a((Class) f4504p, "getSystemId", new Class[0]);
            f4465C = C4787at.m2743a((Class) f4504p, "getBaseStationId", new Class[0]);
            f4466D = C4787at.m2743a((Class) f4504p, "getBaseStationLatitude", new Class[0]);
            f4467E = C4787at.m2743a((Class) f4504p, "getBaseStationLongitude", new Class[0]);
            if (f4501m == null || f4510v == null || f4464B == null || f4465C == null || f4466D == null || f4467E == null) {
                return false;
            }
            return true;
        }

        /* renamed from: d */
        static boolean m2813d() {
            f4511w = C4787at.m2743a((Class) f4505q, "getLac", new Class[0]);
            f4512x = C4787at.m2743a((Class) f4505q, "getCid", new Class[0]);
            f4513y = C4787at.m2743a((Class) f4505q, "getPsc", new Class[0]);
            if (f4501m == null || f4510v == null || f4512x == null || f4511w == null || f4513y == null) {
                return false;
            }
            return true;
        }

        /* renamed from: e */
        static boolean m2814e() {
            f4514z = C4787at.m2743a((Class) f4500l, "getCid", new Class[0]);
            f4463A = C4787at.m2743a((Class) f4500l, "getRssi", new Class[0]);
            if (f4500l == null || f4514z == null || f4463A == null) {
                return false;
            }
            return true;
        }

        /* renamed from: f */
        static boolean m2815f() {
            f4469G = C4787at.m2743a((Class) f4502n, "getSimSlotIndex", new Class[0]);
            f4470H = C4787at.m2743a((Class) f4502n, "getCarrierName", new Class[0]);
            f4471I = C4787at.m2743a((Class) f4502n, "getDisplayName", new Class[0]);
            f4472J = C4787at.m2743a((Class) f4502n, "getIccId", new Class[0]);
            f4473K = C4787at.m2743a((Class) f4502n, "getNumber", new Class[0]);
            f4474L = C4787at.m2743a((Class) f4502n, "getCountryIso", new Class[0]);
            f4475M = C4787at.m2743a((Class) f4502n, "getDataRoaming", new Class[0]);
            f4468F = C4787at.m2743a((Class) f4503o, "getActiveSubscriptionInfoList", new Class[0]);
            if (f4503o == null || f4502n == null || f4469G == null || f4470H == null || f4471I == null || f4472J == null || f4473K == null || f4474L == null || f4475M == null || f4468F == null) {
                return false;
            }
            return true;
        }

        /* renamed from: g */
        static boolean m2816g() {
            f4482T = C4787at.m2746b(f4498j, "getCellSignalStrength", new Class[0]);
            f4483U = C4787at.m2746b(f4498j, "getCellIdentity", new Class[0]);
            if (f4493e == null || f4482T == null || f4483U == null) {
                return false;
            }
            return true;
        }

        /* renamed from: h */
        static boolean m2817h() {
            f4478P = C4787at.m2746b(f4496h, "getCellSignalStrength", new Class[0]);
            f4479Q = C4787at.m2746b(f4496h, "getCellIdentity", new Class[0]);
            if (f4491c == null || f4478P == null || f4479Q == null) {
                return false;
            }
            return true;
        }

        /* renamed from: i */
        static boolean m2818i() {
            f4480R = C4787at.m2746b(f4497i, "getCellSignalStrength", new Class[0]);
            f4481S = C4787at.m2746b(f4497i, "getCellIdentity", new Class[0]);
            if (f4492d == null || f4480R == null || f4481S == null) {
                return false;
            }
            return true;
        }

        /* renamed from: j */
        static boolean m2819j() {
            f4476N = C4787at.m2746b(f4495g, "getCellSignalStrength", new Class[0]);
            f4477O = C4787at.m2746b(f4495g, "getCellIdentity", new Class[0]);
            if (f4490b == null || f4476N == null || f4477O == null) {
                return false;
            }
            return true;
        }

        /* renamed from: k */
        static boolean m2820k() {
            f4485W = C4787at.m2743a((Class) f4489a, "getDataState", new Class[0]);
            f4486X = C4787at.m2742a((Class) f4489a, "DATA_CONNECTED");
            f4487Y = C4787at.m2742a((Class) f4489a, "DATA_CONNECTING");
            f4488Z = C4787at.m2742a((Class) f4489a, "DATA_SUSPENDED");
            if (f4489a == null || f4485W == null || f4486X == null || f4487Y == null || f4488Z == null) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$o */
    static class C4814o {

        /* renamed from: a */
        private static Class<?> f4515a = C4787at.m2745b("android.webkit.WebView");

        /* renamed from: b */
        private static Class<?> f4516b = C4787at.m2745b("android.webkit.WebViewClient");

        /* renamed from: c */
        private static Class<?> f4517c = C4787at.m2745b("android.webkit.WebSettings");

        /* renamed from: d */
        private static Class<?> f4518d = C4787at.m2745b("android.webkit.WebSettings$PluginState");

        /* renamed from: e */
        private static Class<?> f4519e = C4787at.m2745b("android.webkit.WebChromeClient");

        /* renamed from: f */
        private static Class<?> f4520f;

        /* renamed from: g */
        private static Method f4521g;

        /* renamed from: h */
        private static Method f4522h = C4787at.m2743a((Class) f4515a, "destroy", new Class[0]);

        /* renamed from: i */
        private static Method f4523i = C4787at.m2743a((Class) f4515a, "loadUrl", String.class);

        /* renamed from: j */
        private static Method f4524j = C4787at.m2743a((Class) f4515a, "loadData", String.class, String.class, String.class);

        /* renamed from: k */
        private static Method f4525k = C4787at.m2743a((Class) f4515a, "getSettings", new Class[0]);

        /* renamed from: l */
        private static Method f4526l = C4787at.m2743a((Class) f4515a, "setWebViewClient", f4516b);

        /* renamed from: m */
        private static Method f4527m = C4787at.m2743a((Class) f4515a, "setWebChromeClient", f4519e);

        /* renamed from: n */
        private static Method f4528n = C4787at.m2743a((Class) f4517c, "getUserAgentString", new Class[0]);

        /* renamed from: o */
        private static Method f4529o = C4787at.m2743a((Class) f4517c, "setJavaScriptEnabled", Boolean.TYPE);

        /* renamed from: p */
        private static Field f4530p = C4787at.m2742a((Class) f4518d, "ON");

        static {
            Class<?> b = C4787at.m2745b("android.webkit.JsResult");
            f4520f = b;
            f4521g = C4787at.m2743a((Class) b, "confirm", new Class[0]);
        }

        /* renamed from: a */
        static boolean m2821a() {
            return (f4516b == null || f4519e == null || f4521g == null || f4522h == null || f4523i == null || f4524j == null || f4525k == null || f4526l == null || f4527m == null || f4528n == null || f4529o == null || f4530p == null) ? false : true;
        }
    }

    C4797g() {
    }
}
