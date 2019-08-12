package p005cn.jpush.android.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.util.AndroidUtil;

/* renamed from: cn.jpush.android.api.JPushCrashHandler */
public class JPushCrashHandler implements UncaughtExceptionHandler {
    private static final String CRASH_JSON_KEY_COUNT = "count";
    private static final String CRASH_JSON_KEY_CRASHLOGS = "crashlogs";
    private static final String CRASH_JSON_KEY_CRASHTIME = "crashtime";
    private static final String CRASH_JSON_KEY_ITIME = "itime";
    private static final String CRASH_JSON_KEY_MESSAGE = "message";
    private static final String CRASH_JSON_KEY_NETWORTTYPE = "networktype";
    private static final String CRASH_JSON_KEY_STACKTRACE = "stacktrace";
    private static final String CRASH_JSON_KEY_TYPE = "type";
    private static final String CRASH_JSON_KEY_VERSIONCODE = "versioncode";
    private static final String CRASH_JSON_KEY_VERSIONNAME = "versionname";
    public static final String PATH = "jpush_uncaughtexception_file";
    private static JPushCrashHandler instance = new JPushCrashHandler();
    public boolean isInit = false;
    private Context mContext;
    private UncaughtExceptionHandler mDefaultHandler = null;

    private JPushCrashHandler() {
    }

    public static JPushCrashHandler getInstance() {
        return instance;
    }

    public void init(Context ctx) {
        this.mContext = ctx;
        if (this.mDefaultHandler == null) {
            this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.isInit = true;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(ex);
        if (this.mDefaultHandler != this) {
            this.mDefaultHandler.uncaughtException(thread, ex);
        }
        throw new RuntimeException(ex);
    }

    private void handleException(Throwable ex) {
        JSONArray addNewLog = addNewLog(this.mContext, ex, "");
        deleteCrashLog(this.mContext);
    }

    public void handleException(Throwable ex, String extraStr) {
        if (this.isInit && this.mContext != null) {
            JSONArray addNewLog = addNewLog(this.mContext, ex, extraStr);
            deleteCrashLog(this.mContext);
        }
    }

    public static JSONArray getLogs(Context context) {
        if (!new File(context.getFilesDir(), PATH).exists()) {
            return null;
        }
        try {
            FileInputStream fis = context.openFileInput(PATH);
            byte[] buffer = new byte[1024];
            StringBuffer sbf = new StringBuffer();
            while (true) {
                int i = fis.read(buffer);
                if (i == -1) {
                    break;
                }
                sbf.append(new String(buffer, 0, i));
            }
            if (sbf.toString().length() > 0) {
                return new JSONArray(sbf.toString());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static void deleteCrashLog(Context context) {
        if (context != null) {
            File file = new File(context.getFilesDir(), PATH);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private JSONArray generalJSONLog(Context context, JSONArray arr, Throwable ex, String extraStr) {
        JSONObject logObj;
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();
        if (arr == null) {
            arr = new JSONArray();
        }
        String exMessage = extraStr + ex.toString();
        try {
            String[] exArray = exMessage.split(":");
            if (exArray.length > 1) {
                int i = exArray.length - 1;
                while (true) {
                    if (i < 0) {
                        break;
                    } else if (exArray[i].endsWith("Exception") || exArray[i].endsWith("Error")) {
                        exMessage = exArray[i];
                    } else {
                        i--;
                    }
                }
            }
        } catch (NullPointerException | PatternSyntaxException e) {
        }
        int index = 0;
        JSONObject logObj2 = null;
        int i2 = 0;
        while (true) {
            try {
                if (i2 >= arr.length()) {
                    logObj = logObj2;
                    break;
                }
                JSONObject logObj3 = arr.optJSONObject(i2);
                if (logObj3 != null && stackTrace.equals(logObj3.getString(CRASH_JSON_KEY_STACKTRACE))) {
                    index = i2;
                    logObj3.put("count", logObj3.getInt("count") + 1);
                    logObj3.put(CRASH_JSON_KEY_CRASHTIME, System.currentTimeMillis());
                    logObj = logObj3;
                    break;
                }
                logObj2 = null;
                i2++;
            } catch (JSONException e2) {
                return arr;
            } catch (NameNotFoundException e3) {
                return arr;
            }
        }
        if (logObj != null) {
            try {
                JSONArray arr2 = removeJsonArrayByIndex(arr, index);
                arr2.put(logObj);
                JSONObject jSONObject = logObj;
                return arr2;
            } catch (JSONException e4) {
                JSONObject jSONObject2 = logObj;
                return arr;
            } catch (NameNotFoundException e5) {
                JSONObject jSONObject3 = logObj;
                return arr;
            }
        } else {
            JSONObject logObj4 = new JSONObject();
            logObj4.put(CRASH_JSON_KEY_CRASHTIME, System.currentTimeMillis());
            logObj4.put(CRASH_JSON_KEY_STACKTRACE, stackTrace);
            logObj4.put("message", exMessage);
            logObj4.put("count", 1);
            if (!(this.mContext == null && context == null)) {
                logObj4.put(CRASH_JSON_KEY_NETWORTTYPE, AndroidUtil.getNetworkType(context));
            }
            if (this.mContext != null) {
                PackageInfo pi = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 1);
                if (pi != null) {
                    String versionName = pi.versionName == null ? "null" : pi.versionName;
                    String versionCode = pi.versionCode + "";
                    logObj4.put(CRASH_JSON_KEY_VERSIONNAME, versionName);
                    logObj4.put(CRASH_JSON_KEY_VERSIONCODE, versionCode);
                }
            }
            arr.put(logObj4);
            return arr;
        }
    }

    private JSONArray addNewLog(Context context, Throwable ex, String extraString) {
        return generalJSONLog(context, getLogs(context), ex, extraString);
    }

    private JSONArray removeJsonArrayByIndex(JSONArray arr, int index) {
        if (arr == null) {
            return null;
        }
        JSONArray newArr = new JSONArray();
        for (int i = 0; i < arr.length(); i++) {
            if (i != index) {
                try {
                    newArr.put(arr.get(i));
                } catch (JSONException e) {
                }
            }
        }
        return newArr;
    }

    public void reportCrashLog(Context context) {
        if (Configs.isValidRegistered() && this.isInit) {
            JSONArray crashLogItems = getLogs(context);
            if (crashLogItems != null) {
                JSONObject crashLog = new JSONObject();
                try {
                    crashLog.put(CRASH_JSON_KEY_CRASHLOGS, crashLogItems);
                    crashLog.put("itime", Configs.getReportTime());
                    crashLog.put("type", "crash_log");
                    crashLog.put(JPushReportInterface.NETWORK_TYPE, AndroidUtil.getNetworkType(context));
                } catch (JSONException e) {
                }
                deleteCrashLog(context);
            }
        }
    }

    public JSONObject getCrashLog(Context context) {
        JSONObject crashLog = null;
        if (this.isInit) {
            JSONArray crashLogItems = getLogs(context);
            if (crashLogItems != null) {
                crashLog = new JSONObject();
                try {
                    crashLog.put(CRASH_JSON_KEY_CRASHLOGS, crashLogItems);
                    crashLog.put("itime", Configs.getReportTime());
                    crashLog.put("type", "crash_log");
                    crashLog.put(JPushReportInterface.NETWORK_TYPE, AndroidUtil.getNetworkType(context));
                } catch (JSONException e) {
                }
            }
        }
        return crashLog;
    }
}
