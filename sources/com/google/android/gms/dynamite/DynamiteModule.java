package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import dalvik.system.PathClassLoader;
import icepick.Icepick;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public final class DynamiteModule {
    private static Boolean zzaRO;
    private static zza zzaRP;
    private static zzb zzaRQ;
    private static final HashMap<String, byte[]> zzaRR = new HashMap<>();
    private static String zzaRS;
    private static final zza zzaRT = new zza() {
        public int zzI(Context context, String str) {
            return DynamiteModule.zzI(context, str);
        }

        public int zzb(Context context, String str, boolean z) throws zza {
            return DynamiteModule.zzb(context, str, z);
        }
    };
    public static final zzb zzaRU = new zzb() {
        public C7687zzb zza(Context context, String str, zza zza) throws zza {
            C7687zzb zzb = new C7687zzb();
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSc != 0) {
                zzb.zzaSd = 1;
            } else {
                zzb.zzaSb = zza.zzI(context, str);
                if (zzb.zzaSb != 0) {
                    zzb.zzaSd = -1;
                }
            }
            return zzb;
        }
    };
    public static final zzb zzaRV = new zzb() {
        public C7687zzb zza(Context context, String str, zza zza) throws zza {
            C7687zzb zzb = new C7687zzb();
            zzb.zzaSb = zza.zzI(context, str);
            if (zzb.zzaSb != 0) {
                zzb.zzaSd = -1;
            } else {
                zzb.zzaSc = zza.zzb(context, str, true);
                if (zzb.zzaSc != 0) {
                    zzb.zzaSd = 1;
                }
            }
            return zzb;
        }
    };
    public static final zzb zzaRW = new zzb() {
        public C7687zzb zza(Context context, String str, zza zza) throws zza {
            C7687zzb zzb = new C7687zzb();
            zzb.zzaSb = zza.zzI(context, str);
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                zzb.zzaSd = 0;
            } else if (zzb.zzaSb >= zzb.zzaSc) {
                zzb.zzaSd = -1;
            } else {
                zzb.zzaSd = 1;
            }
            return zzb;
        }
    };
    public static final zzb zzaRX = new zzb() {
        public C7687zzb zza(Context context, String str, zza zza) throws zza {
            C7687zzb zzb = new C7687zzb();
            zzb.zzaSb = zza.zzI(context, str);
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                zzb.zzaSd = 0;
            } else if (zzb.zzaSc >= zzb.zzaSb) {
                zzb.zzaSd = 1;
            } else {
                zzb.zzaSd = -1;
            }
            return zzb;
        }
    };
    public static final zzb zzaRY = new zzb() {
        public C7687zzb zza(Context context, String str, zza zza) throws zza {
            C7687zzb zzb = new C7687zzb();
            zzb.zzaSb = zza.zzI(context, str);
            if (zzb.zzaSb != 0) {
                zzb.zzaSc = zza.zzb(context, str, false);
            } else {
                zzb.zzaSc = zza.zzb(context, str, true);
            }
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                zzb.zzaSd = 0;
            } else if (zzb.zzaSc >= zzb.zzaSb) {
                zzb.zzaSd = 1;
            } else {
                zzb.zzaSd = -1;
            }
            return zzb;
        }
    };
    private final Context zzaRZ;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    public interface zzb {

        public interface zza {
            int zzI(Context context, String str);

            int zzb(Context context, String str, boolean z) throws zza;
        }

        /* renamed from: com.google.android.gms.dynamite.DynamiteModule$zzb$zzb reason: collision with other inner class name */
        public static class C7687zzb {
            public int zzaSb = 0;
            public int zzaSc = 0;
            public int zzaSd = 0;
        }

        C7687zzb zza(Context context, String str, zza zza2) throws zza;
    }

    private DynamiteModule(Context context) {
        this.zzaRZ = (Context) zzac.zzw(context);
    }

    private static ClassLoader zzBT() {
        return new PathClassLoader(zzaRS, ClassLoader.getSystemClassLoader()) {
            /* access modifiers changed from: protected */
            public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
                if (!str.startsWith(Icepick.JAVA_PREFIX) && !str.startsWith(Icepick.ANDROID_PREFIX)) {
                    try {
                        return findClass(str);
                    } catch (ClassNotFoundException e) {
                    }
                }
                return super.loadClass(str, z);
            }
        };
    }

    public static int zzI(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            String valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            String valueOf2 = String.valueOf("ModuleDescriptor");
            Class loadClass = classLoader.loadClass(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length() + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(".").append(valueOf2).toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf3 = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", new StringBuilder(String.valueOf(valueOf3).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf3).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load module descriptor class: ";
            String valueOf4 = String.valueOf(e2.getMessage());
            Log.e(str2, valueOf4.length() != 0 ? str3.concat(valueOf4) : new String(str3));
            return 0;
        }
    }

    private static DynamiteModule zzK(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        Log.i(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static Context zza(Context context, String str, byte[] bArr, zzb zzb2) {
        try {
            return (Context) zzd.zzF(zzb2.zza(zzd.zzA(context), str, bArr));
        } catch (Exception e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load DynamiteLoader: ";
            String valueOf = String.valueOf(e.toString());
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
    }

    public static DynamiteModule zza(Context context, zzb zzb2, String str) throws zza {
        C7687zzb zza2 = zzb2.zza(context, str, zzaRT);
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza2.zzaSb).append(" and remote module ").append(str).append(":").append(zza2.zzaSc).toString());
        if (zza2.zzaSd == 0 || ((zza2.zzaSd == -1 && zza2.zzaSb == 0) || (zza2.zzaSd == 1 && zza2.zzaSc == 0))) {
            throw new zza("No acceptable module found. Local version is " + zza2.zzaSb + " and remote version is " + zza2.zzaSc + ".");
        } else if (zza2.zzaSd == -1) {
            return zzK(context, str);
        } else {
            if (zza2.zzaSd == 1) {
                try {
                    return zza(context, str, zza2.zzaSc);
                } catch (zza e) {
                    zza zza3 = e;
                    String str2 = "DynamiteModule";
                    String str3 = "Failed to load remote module: ";
                    String valueOf = String.valueOf(zza3.getMessage());
                    Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    if (zza2.zzaSb != 0) {
                        final int i = zza2.zzaSb;
                        if (zzb2.zza(context, str, new zza() {
                            public int zzI(Context context, String str) {
                                return i;
                            }

                            public int zzb(Context context, String str, boolean z) {
                                return 0;
                            }
                        }).zzaSd == -1) {
                            return zzK(context, str);
                        }
                    }
                    throw new zza("Remote load failed. No local fallback found.", zza3);
                }
            } else {
                throw new zza("VersionPolicy returned invalid code:" + zza2.zzaSd);
            }
        }
    }

    private static DynamiteModule zza(Context context, String str, int i) throws zza {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = zzaRO;
        }
        if (bool != null) {
            return bool.booleanValue() ? zzc(context, str, i) : zzb(context, str, i);
        }
        throw new zza("Failed to determine which loading route to use.");
    }

    private static void zza(ClassLoader classLoader) throws zza {
        try {
            zzaRQ = com.google.android.gms.dynamite.zzb.zza.zzcf((IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new zza("Failed to instantiate dynamite loader", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c A[SYNTHETIC, Splitter:B:21:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00ec  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:35:0x0073=Splitter:B:35:0x0073, B:25:0x0044=Splitter:B:25:0x0044} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzb(android.content.Context r6, java.lang.String r7, boolean r8) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)
            java.lang.Boolean r0 = zzaRO     // Catch:{ all -> 0x0076 }
            if (r0 != 0) goto L_0x0035
            android.content.Context r0 = r6.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x009a, IllegalAccessException -> 0x00f7, NoSuchFieldException -> 0x00f5 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x009a, IllegalAccessException -> 0x00f7, NoSuchFieldException -> 0x00f5 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException -> 0x009a, IllegalAccessException -> 0x00f7, NoSuchFieldException -> 0x00f5 }
            java.lang.Class r2 = r0.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x009a, IllegalAccessException -> 0x00f7, NoSuchFieldException -> 0x00f5 }
            java.lang.String r0 = "sClassLoader"
            java.lang.reflect.Field r3 = r2.getDeclaredField(r0)     // Catch:{ ClassNotFoundException -> 0x009a, IllegalAccessException -> 0x00f7, NoSuchFieldException -> 0x00f5 }
            monitor-enter(r2)     // Catch:{ ClassNotFoundException -> 0x009a, IllegalAccessException -> 0x00f7, NoSuchFieldException -> 0x00f5 }
            r0 = 0
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x0097 }
            java.lang.ClassLoader r0 = (java.lang.ClassLoader) r0     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0047
            java.lang.ClassLoader r3 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0097 }
            if (r0 != r3) goto L_0x0041
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0097 }
        L_0x0032:
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
        L_0x0033:
            zzaRO = r0     // Catch:{ all -> 0x0076 }
        L_0x0035:
            monitor-exit(r1)     // Catch:{ all -> 0x0076 }
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x00ec
            int r0 = zzd(r6, r7, r8)     // Catch:{ zza -> 0x00c7 }
        L_0x0040:
            return r0
        L_0x0041:
            zza(r0)     // Catch:{ zza -> 0x00f2 }
        L_0x0044:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0097 }
            goto L_0x0032
        L_0x0047:
            java.lang.String r0 = "com.google.android.gms"
            android.content.Context r4 = r6.getApplicationContext()     // Catch:{ all -> 0x0097 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x0097 }
            boolean r0 = r0.equals(r4)     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0063
            r0 = 0
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0097 }
            r3.set(r0, r4)     // Catch:{ all -> 0x0097 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0097 }
            goto L_0x0032
        L_0x0063:
            int r0 = zzd(r6, r7, r8)     // Catch:{ zza -> 0x008b }
            java.lang.String r4 = zzaRS     // Catch:{ zza -> 0x008b }
            if (r4 == 0) goto L_0x0073
            java.lang.String r4 = zzaRS     // Catch:{ zza -> 0x008b }
            boolean r4 = r4.isEmpty()     // Catch:{ zza -> 0x008b }
            if (r4 == 0) goto L_0x0079
        L_0x0073:
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
            monitor-exit(r1)     // Catch:{ all -> 0x0076 }
            goto L_0x0040
        L_0x0076:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0076 }
            throw r0
        L_0x0079:
            java.lang.ClassLoader r4 = zzBT()     // Catch:{ zza -> 0x008b }
            zza(r4)     // Catch:{ zza -> 0x008b }
            r5 = 0
            r3.set(r5, r4)     // Catch:{ zza -> 0x008b }
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ zza -> 0x008b }
            zzaRO = r4     // Catch:{ zza -> 0x008b }
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
            monitor-exit(r1)     // Catch:{ all -> 0x0076 }
            goto L_0x0040
        L_0x008b:
            r0 = move-exception
            r0 = 0
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0097 }
            r3.set(r0, r4)     // Catch:{ all -> 0x0097 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0097 }
            goto L_0x0032
        L_0x0097:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0097 }
            throw r0     // Catch:{ ClassNotFoundException -> 0x009a, IllegalAccessException -> 0x00f7, NoSuchFieldException -> 0x00f5 }
        L_0x009a:
            r0 = move-exception
        L_0x009b:
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0076 }
            int r3 = r3.length()     // Catch:{ all -> 0x0076 }
            int r3 = r3 + 30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
            r4.<init>(r3)     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = "Failed to load module via V2: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x0076 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x0076 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0076 }
            android.util.Log.w(r2, r0)     // Catch:{ all -> 0x0076 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0076 }
            goto L_0x0033
        L_0x00c7:
            r0 = move-exception
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r2 = "Failed to retrieve remote module version: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r3 = r0.length()
            if (r3 == 0) goto L_0x00e6
            java.lang.String r0 = r2.concat(r0)
        L_0x00e0:
            android.util.Log.w(r1, r0)
            r0 = 0
            goto L_0x0040
        L_0x00e6:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            goto L_0x00e0
        L_0x00ec:
            int r0 = zzc(r6, r7, r8)
            goto L_0x0040
        L_0x00f2:
            r0 = move-exception
            goto L_0x0044
        L_0x00f5:
            r0 = move-exception
            goto L_0x009b
        L_0x00f7:
            r0 = move-exception
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws zza {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zza zzbm = zzbm(context);
        if (zzbm == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            IObjectWrapper zza2 = zzbm.zza(zzd.zzA(context), str, i);
            if (zzd.zzF(zza2) != null) {
                return new DynamiteModule((Context) zzd.zzF(zza2));
            }
            throw new zza("Failed to load remote module.");
        } catch (RemoteException e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.zza zzbm(android.content.Context r6) {
        /*
            r1 = 0
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r2 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r2)
            com.google.android.gms.dynamite.zza r0 = zzaRP     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x000c
            com.google.android.gms.dynamite.zza r0 = zzaRP     // Catch:{ all -> 0x003c }
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
        L_0x000b:
            return r0
        L_0x000c:
            com.google.android.gms.common.zze r0 = com.google.android.gms.common.zze.zzuY()     // Catch:{ all -> 0x003c }
            int r0 = r0.isGooglePlayServicesAvailable(r6)     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0019
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            r0 = r1
            goto L_0x000b
        L_0x0019:
            java.lang.String r0 = "com.google.android.gms"
            r3 = 3
            android.content.Context r0 = r6.createPackageContext(r0, r3)     // Catch:{ Exception -> 0x003f }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ Exception -> 0x003f }
            java.lang.String r3 = "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
            java.lang.Class r0 = r0.loadClass(r3)     // Catch:{ Exception -> 0x003f }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x003f }
            android.os.IBinder r0 = (android.os.IBinder) r0     // Catch:{ Exception -> 0x003f }
            com.google.android.gms.dynamite.zza r0 = com.google.android.gms.dynamite.zza.C7688zza.zzce(r0)     // Catch:{ Exception -> 0x003f }
            if (r0 == 0) goto L_0x005b
            zzaRP = r0     // Catch:{ Exception -> 0x003f }
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            goto L_0x000b
        L_0x003c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            throw r0
        L_0x003f:
            r0 = move-exception
            java.lang.String r3 = "DynamiteModule"
            java.lang.String r4 = "Failed to load IDynamiteLoader from GmsCore: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x003c }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x003c }
            int r5 = r0.length()     // Catch:{ all -> 0x003c }
            if (r5 == 0) goto L_0x005e
            java.lang.String r0 = r4.concat(r0)     // Catch:{ all -> 0x003c }
        L_0x0058:
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x003c }
        L_0x005b:
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            r0 = r1
            goto L_0x000b
        L_0x005e:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x003c }
            r0.<init>(r4)     // Catch:{ all -> 0x003c }
            goto L_0x0058
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzbm(android.content.Context):com.google.android.gms.dynamite.zza");
    }

    private static int zzc(Context context, String str, boolean z) {
        zza zzbm = zzbm(context);
        if (zzbm == null) {
            return 0;
        }
        try {
            return zzbm.zza(zzd.zzA(context), str, z);
        } catch (RemoteException e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws zza {
        byte[] bArr;
        zzb zzb2;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            bArr = (byte[]) zzaRR.get(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString());
            zzb2 = zzaRQ;
        }
        if (bArr == null) {
            throw new zza("Module implementation could not be found.");
        } else if (zzb2 == null) {
            throw new zza("DynamiteLoaderV2 was not cached.");
        } else {
            Context zza2 = zza(context.getApplicationContext(), str, bArr, zzb2);
            if (zza2 != null) {
                return new DynamiteModule(zza2);
            }
            throw new zza("Failed to get module context");
        }
    }

    private static int zzd(Context context, String str, boolean z) throws zza {
        Cursor cursor = null;
        try {
            cursor = zze(context, str, z);
            if (cursor == null || !cursor.moveToFirst()) {
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new zza("Failed to connect to dynamite module ContentResolver.");
            }
            int i = cursor.getInt(0);
            if (i > 0) {
                synchronized (DynamiteModule.class) {
                    zzaRR.put(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString(), Base64.decode(cursor.getString(3), 0));
                    zzaRS = cursor.getString(2);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return i;
        } catch (Exception e) {
            if (e instanceof zza) {
                throw e;
            }
            throw new zza("V2 version check failed", e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static Cursor zze(Context context, String str, boolean z) {
        String str2 = z ? "api_force_staging" : "api";
        String valueOf = String.valueOf("content://com.google.android.gms.chimera/");
        return context.getContentResolver().query(Uri.parse(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str2).length() + String.valueOf(str).length()).append(valueOf).append(str2).append("/").append(str).toString()), null, null, null, null);
    }

    public Context zzBS() {
        return this.zzaRZ;
    }

    public IBinder zzdT(String str) throws zza {
        try {
            return (IBinder) this.zzaRZ.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String str2 = "Failed to instantiate module class: ";
            String valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        }
    }
}
