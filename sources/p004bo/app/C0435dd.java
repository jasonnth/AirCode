package p004bo.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: bo.app.dd */
public final class C0435dd extends SQLiteOpenHelper {

    /* renamed from: a */
    private static final String f333a = AppboyLogger.getAppboyLogTag(C0435dd.class);

    /* renamed from: b */
    private static volatile Map<String, C0435dd> f334b = new HashMap();

    /* renamed from: a */
    public static C0435dd m418a(Context context, String str) {
        String b = m419b(context, str);
        m420c(context, str);
        if (!f334b.containsKey(b)) {
            synchronized (C0435dd.class) {
                if (!f334b.containsKey(b)) {
                    C0435dd ddVar = new C0435dd(context, b);
                    f334b.put(b, ddVar);
                    return ddVar;
                }
            }
        }
        return (C0435dd) f334b.get(b);
    }

    private C0435dd(Context context, String str) {
        super(context, str, null, 2);
    }

    public void onCreate(SQLiteDatabase database) {
        AppboyLogger.m1737i(f333a, String.format("Creating %s table", new Object[]{"ab_sessions"}));
        database.execSQL("CREATE TABLE ab_sessions (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id TEXT UNIQUE NOT NULL, start_time INTEGER NOT NULL, end_time INTEGER, latitude TEXT, longitude TEXT, altitude REAL, accuracy REAL, new_sent INTEGER NOT NULL CHECK(new_sent IN (1, 0)), sealed INTEGER NOT NULL CHECK(sealed IN (1, 0)), endtime_sent INTEGER NOT NULL CHECK(endtime_sent IN (1, 0)));");
        AppboyLogger.m1737i(f333a, String.format("Creating %s table", new Object[]{"ab_events"}));
        database.execSQL("CREATE TABLE ab_events (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id TEXT, event_type TEXT NOT NULL, event_data TEXT NOT NULL, timestamp INTEGER NOT NULL);");
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (newVersion == 2) {
            database.execSQL("DROP TABLE IF EXISTS sessions");
            database.execSQL("DROP TABLE IF EXISTS appboy_events");
            database.execSQL("DROP TABLE IF EXISTS ab_sessions");
            database.execSQL("DROP TABLE IF EXISTS ab_events");
            onCreate(database);
            return;
        }
        AppboyLogger.m1735e(f333a, String.format("Received call to onUpgrade with unknown upgrade version %d.  Please contact Appboy to report this error.", new Object[]{Integer.valueOf(newVersion)}));
    }

    @TargetApi(11)
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        AppboyLogger.m1737i(f333a, String.format("Downgrading storage from version %d to %d. Dropping all current tables.", new Object[]{Integer.valueOf(oldVersion), Integer.valueOf(newVersion)}));
        database.execSQL(String.format("DROP TABLE IF EXISTS %s;", new Object[]{"ab_events"}));
        database.execSQL(String.format("DROP TABLE IF EXISTS %s;", new Object[]{"ab_sessions"}));
        onCreate(database);
    }

    @TargetApi(16)
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        if (VERSION.SDK_INT >= 16) {
            database.setForeignKeyConstraintsEnabled(true);
        } else if (!database.isReadOnly()) {
            database.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    /* renamed from: b */
    static String m419b(Context context, String str) {
        if (StringUtils.isNullOrBlank(str)) {
            return "appboy.db";
        }
        return "appboy.db" + StringUtils.getCacheFileSuffix(context, str);
    }

    /* renamed from: c */
    private static void m420c(Context context, String str) {
        if (!StringUtils.isNullOrBlank(str) && !str.contains(File.separator)) {
            File databasePath = context.getDatabasePath(String.format("%s.%s", new Object[]{"appboy.db", str}));
            if (databasePath.exists()) {
                File databasePath2 = context.getDatabasePath(m419b(context, str));
                File file = new File(databasePath + "-journal");
                if (databasePath2.exists()) {
                    AppboyLogger.m1739w(f333a, "Appboy database file with MD5-hashed name already exists. Deleting the old database.");
                    AppboyLogger.m1739w(f333a, context.deleteDatabase(databasePath.getPath()) ? "Deletion of old database successful" : "Deletion of old database failed");
                    return;
                }
                AppboyLogger.m1737i(f333a, "Old Appboy database present, renaming to MD5-hashed filename.");
                boolean renameTo = databasePath.renameTo(databasePath2);
                boolean renameTo2 = file.renameTo(new File(databasePath2 + "-journal"));
                if (!renameTo || !renameTo2) {
                    String str2 = f333a;
                    String str3 = "Renaming of the old database files not complete. The database file move operation was: %s. The database journal file move operation was: %s";
                    Object[] objArr = new Object[2];
                    objArr[0] = renameTo ? "successful" : "unsuccessful";
                    objArr[1] = renameTo2 ? "successful" : "unsuccessful";
                    AppboyLogger.m1739w(str2, String.format(str3, objArr));
                }
            }
        }
    }
}
