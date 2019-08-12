package p005cn.jpush.android.util;

import android.content.Context;
import android.os.Build.VERSION;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.JPushConstants.PushService;
import p005cn.jpush.android.api.JPushSA;

/* renamed from: cn.jpush.android.util.ReportUtils */
public class ReportUtils {
    private static final String HISTORY_PATH = "jpush_stat_cache_history.json";
    private static final int LIMITED_LOG_SIZE = 204800;
    private static final String TAG = ReportUtils.class.getSimpleName();
    public static JSONObject historyFileCache = null;
    private static Object history_file_lock = new Object();

    private static String getBodyGzipMd5Str(String body) {
        String md5 = null;
        try {
            byte[] bodyData = body.getBytes(JPushConstants.ENCODING_UTF_8);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            OutputStream zipper = new GZIPOutputStream(bos);
            zipper.write(bodyData);
            zipper.close();
            byte[] zip = bos.toByteArray();
            bos.close();
            return AndroidUtil.getBytesMD5(zip);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return md5;
        } catch (IOException e2) {
            e2.printStackTrace();
            return md5;
        }
    }

    public static String tokenGenerator(String body, int version) {
        if (StringUtils.isEmpty(body)) {
            Logger.m1416d(TAG, " body is null,generate report token failed");
            return null;
        }
        if (version != 1 && version == 2) {
            body = getBodyGzipMd5Str(body);
        }
        long uid = Configs.getUid();
        if (uid == 0) {
            Logger.m1416d(TAG, " miss uid,generate report token failed");
            return null;
        }
        String token = AndroidUtil.getMD5Utf8(uid + AndroidUtil.getMD5Utf8(Configs.getPassword()) + body);
        if (!StringUtils.isEmpty(token)) {
            return uid + ":" + token;
        }
        return null;
    }

    public static String tokenGenerator() {
        long uid = Configs.getUid();
        if (uid == 0) {
            Logger.m1416d(TAG, " miss uid, generate report token failed");
            return null;
        }
        String token = AndroidUtil.getMD5Utf8(uid + AndroidUtil.getMD5Utf8(Configs.getPassword()));
        if (!StringUtils.isEmpty(token)) {
            return uid + ":" + token;
        }
        return null;
    }

    public static String getBasicAuthorization(String authorization) {
        String auth = null;
        try {
            return Base64.encodeToString(authorization.getBytes(), 10);
        } catch (Exception e) {
            Logger.m1420e("getBasicAuthorization", "basic authorization encode failed");
            return auth;
        }
    }

    public static ArrayList<JSONArray> logPartitions(JSONArray content, int limit) {
        JSONArray temp;
        ArrayList<JSONArray> log_slices = new ArrayList<>();
        int size = 0;
        int cur_size = 0;
        JSONArray temp2 = new JSONArray();
        if (content != null && content.length() > 0) {
            int i = content.length() - 1;
            JSONArray temp3 = temp2;
            while (true) {
                if (i >= 0) {
                    JSONObject item = content.optJSONObject(i);
                    if (item != null) {
                        try {
                            int item_size = item.toString().getBytes(JPushConstants.ENCODING_UTF_8).length;
                            cur_size += item_size;
                            if (cur_size > LIMITED_LOG_SIZE && i > 1) {
                                if (i <= 1) {
                                    if (i == 1) {
                                        temp3.put(item);
                                        log_slices.add(temp3);
                                        JSONArray jSONArray = temp3;
                                        break;
                                    }
                                } else {
                                    JSONArray jSONArray2 = temp3;
                                    break;
                                }
                            }
                            if (size + item_size > limit) {
                                size = item_size;
                                log_slices.add(temp3);
                                temp = new JSONArray();
                                try {
                                    temp.put(item);
                                } catch (UnsupportedEncodingException e) {
                                    e = e;
                                }
                                i--;
                                temp3 = temp;
                            } else {
                                size += item_size;
                                temp3.put(item);
                            }
                        } catch (UnsupportedEncodingException e2) {
                            e = e2;
                            temp = temp3;
                            Logger.m1420e(TAG, e.getMessage());
                            i--;
                            temp3 = temp;
                        }
                    }
                    temp = temp3;
                    i--;
                    temp3 = temp;
                } else {
                    if (temp3 != null && temp3.length() > 0) {
                        log_slices.add(temp3);
                    }
                    JSONArray jSONArray3 = temp3;
                }
            }
        }
        return log_slices;
    }

    public static JSONArray sortJSONArray(JSONArray arr) {
        long start = System.nanoTime();
        int i = 0;
        while (i < arr.length()) {
            try {
                long l = Long.parseLong(arr.getJSONObject(i).get(JPushReportInterface.ITIME).toString());
                for (int j = i + 1; j < arr.length(); j++) {
                    if (l < Long.parseLong(arr.getJSONObject(j).get(JPushReportInterface.ITIME).toString())) {
                        JSONObject jObject = arr.getJSONObject(j);
                        arr.put(j, arr.getJSONObject(i));
                        arr.put(i, jObject);
                    }
                }
                i++;
            } catch (Exception e) {
            }
        }
        Logger.m1416d(TAG, "sorting log content spent time:" + ((System.nanoTime() - start) / 1000000) + "ms");
        return arr;
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private static void giveupAllSend(Context context, JSONObject container) {
        historyFileCache = container;
        writeLogFile(context, HISTORY_PATH, container);
    }

    private static void clearHistotyFileContent(Context context) {
        historyFileCache = null;
        writeLogFile(context, HISTORY_PATH, null);
    }

    public static void rearrangeHistoryFile(Context context, int currentLogSize) {
        if (historyFileCache != null) {
            JSONObject mHistoryFileCache = historyFileCache;
            if (currentLogSize >= LIMITED_LOG_SIZE) {
                clearHistotyFileContent(context);
                return;
            }
            int cacheSize = 0;
            try {
                cacheSize = mHistoryFileCache.toString().getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
            }
            int exceedSize = (currentLogSize + cacheSize) - LIMITED_LOG_SIZE;
            if (exceedSize > 0) {
                JSONArray content = mHistoryFileCache.optJSONArray("content");
                if (content != null && content.length() > 0) {
                    int count = 0;
                    try {
                        JSONArray temp = new JSONArray();
                        for (int i = 0; i < content.length(); i++) {
                            JSONObject item = content.getJSONObject(i);
                            if (item != null) {
                                if (count >= exceedSize) {
                                    temp.put(item);
                                }
                                count += item.toString().getBytes("utf-8").length;
                            }
                        }
                        if (temp.length() > 0) {
                            mHistoryFileCache.put("content", temp);
                        } else {
                            mHistoryFileCache = null;
                        }
                        historyFileCache = mHistoryFileCache;
                        writeLogFile(context, HISTORY_PATH, mHistoryFileCache);
                    } catch (UnsupportedEncodingException | JSONException e2) {
                    }
                }
            }
        }
    }

    private static void onSliceSendFailed(Context context, JSONObject container, ArrayList<JSONArray> logs_temp) {
        if (logs_temp == null || logs_temp.size() <= 0) {
            clearHistotyFileContent(context);
        }
        JSONArray temp = new JSONArray();
        for (int i = 0; i < logs_temp.size(); i++) {
            JSONArray arr = (JSONArray) logs_temp.get(i);
            for (int j = 0; j < arr.length(); j++) {
                if (arr.optJSONObject(j) != null) {
                    temp.put(arr.optJSONObject(j));
                }
            }
        }
        try {
            container.put("content", temp);
        } catch (JSONException e) {
        }
        historyFileCache = container;
        writeLogFile(context, HISTORY_PATH, container);
    }

    private static void removeSlice(Context context, JSONObject container, JSONArray slice, ArrayList<JSONArray> logs_temp) {
        if (logs_temp != null) {
            if (logs_temp.size() == 1) {
                clearHistotyFileContent(context);
            } else if (slice != null && logs_temp.size() > 1) {
                logs_temp.remove(slice);
                JSONArray temp = new JSONArray();
                for (int i = 0; i < logs_temp.size(); i++) {
                    JSONArray arr = (JSONArray) logs_temp.get(i);
                    for (int j = 0; j < arr.length(); j++) {
                        if (arr.optJSONObject(j) != null) {
                            temp.put(arr.optJSONObject(j));
                        }
                    }
                }
                try {
                    container.put("content", temp);
                } catch (JSONException e) {
                }
                historyFileCache = container;
                writeLogFile(context, HISTORY_PATH, container);
            }
        }
    }

    public static boolean wrapContainerInfo(JSONObject container, Context context) throws Exception {
        container.put(PushService.PARAM_PLATFORM, "a");
        long uid = Configs.getUid();
        if (uid == 0) {
            Logger.m1420e(TAG, "miss uid when wrap container info");
            return false;
        }
        container.put(JPushReportInterface.UID, uid);
        String appKey = AndroidUtil.getAppKey(context);
        if (StringUtils.isEmpty(appKey)) {
            Logger.m1420e(TAG, "miss app_key when wrap container info");
            return false;
        }
        container.put("app_key", appKey);
        container.put(JPushReportInterface.SDK_VER, JPushConstants.SDK_VERSION);
        return true;
    }

    public static boolean writeLogFile(Context context, String file_name, JSONObject logJsonObject) {
        if (StringUtils.isEmpty(file_name)) {
            Logger.m1416d(TAG, "file_name is null , give up save ");
            return false;
        }
        String fileName = file_name.equals(HISTORY_PATH) ? "history_file" : "current_session_file";
        if (context == null) {
            Logger.m1416d(TAG, "context is null , give up save " + fileName);
            return false;
        }
        synchronized (history_file_lock) {
            String logJsonString = "";
            if (logJsonObject != null) {
                logJsonString = logJsonObject.toString();
                if (file_name.equals(HISTORY_PATH)) {
                    try {
                        Logger.m1428v(TAG, "save log in writeHistoryLog:\n" + logJsonObject.toString(1));
                    } catch (Exception e) {
                        Logger.m1433w(TAG, "save log in writeHistoryLog", e);
                    }
                }
            }
            FileOutputStream fos = null;
            try {
                fos = context.openFileOutput(file_name, 0);
                fos.write(logJsonString.getBytes(JPushConstants.ENCODING_UTF_8));
            } catch (FileNotFoundException e2) {
                Logger.m1416d(TAG, "can't open " + fileName + " outputStream, give up save :" + e2.getMessage());
                return false;
            } catch (UnsupportedEncodingException e3) {
                Logger.m1416d(TAG, "can't encoding " + fileName + " , give up save :" + e3.getMessage());
                return false;
            } catch (IOException e4) {
                Logger.m1416d(TAG, "can't write " + fileName + " , give up save :" + e4.getMessage());
                return false;
            } catch (NullPointerException e5) {
                Logger.m1416d(TAG, "Filepath error of [" + fileName + "] , give up save :" + e5.getMessage());
                return false;
            } finally {
                close(fos);
            }
        }
        return true;
    }

    public static JSONObject readLog(Context context, String file_name) {
        if (file_name == null || file_name.length() <= 0) {
            Logger.m1416d(TAG, "file_name is null , give up read ");
            return null;
        }
        String fileName = file_name.equals(HISTORY_PATH) ? "history_file" : "current_session_file";
        if (context == null) {
            Logger.m1416d(TAG, "context is null , give up read " + fileName);
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(file_name);
            byte[] buffer = new byte[(fis.available() + 1)];
            fis.read(buffer);
            close(fis);
            try {
                String history_json = new String(buffer, JPushConstants.ENCODING_UTF_8);
                if (!StringUtils.isEmpty(history_json)) {
                    return new JSONObject(history_json);
                }
                Logger.m1416d(TAG, fileName + " is null, return null");
                return null;
            } catch (UnsupportedEncodingException e) {
                Logger.m1416d(TAG, "can't encoding " + fileName + ", give up read :" + e.getMessage());
                return null;
            } catch (JSONException e2) {
                Logger.m1416d(TAG, "can't build " + fileName + " into JsonObject, give up read :" + e2.getMessage());
                return null;
            }
        } catch (FileNotFoundException e3) {
            Logger.m1416d(TAG, "can't open " + fileName + " inputStream, give up read  :" + e3.getMessage());
            close(fis);
            return null;
        } catch (IOException e4) {
            Logger.m1416d(TAG, "can't read " + fileName + ", give up read :" + e4.getMessage());
            close(fis);
            return null;
        } catch (Throwable th) {
            close(fis);
            throw th;
        }
    }

    public static JSONObject getHistoryCache(Context context, String file_name) {
        if (historyFileCache == null) {
            historyFileCache = readLog(context, file_name);
        }
        return historyFileCache;
    }

    public static long[] getXBytes(Context ctx) throws Exception {
        if (VERSION.SDK_INT < 8) {
            return null;
        }
        Class<?> clazz = Class.forName("android.net.TrafficStats");
        Method getUidRxBytes = clazz.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
        Method getUidTxBytes = clazz.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
        int i = ctx.getApplicationInfo().uid;
        if (i == -1) {
            return null;
        }
        return new long[]{((Long) getUidRxBytes.invoke(null, new Object[]{Integer.valueOf(i)})).longValue(), ((Long) getUidTxBytes.invoke(null, new Object[]{Integer.valueOf(i)})).longValue()};
    }

    public static JSONObject getBasicContainer(Context context) throws Exception {
        JSONObject container = new JSONObject();
        wrapContainerInfo(container, context);
        container.put("content", new JSONArray());
        return container;
    }

    public static void addItemIntoContent(JSONObject container, Object obj) {
        JSONArray content;
        try {
            content = container.getJSONArray("content");
        } catch (JSONException e) {
            content = new JSONArray();
        }
        content.put(obj);
        try {
            container.put("content", content);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void clearReportLogFile(Context context) {
        Logger.m1416d(TAG, "clearReportLogFile with appkey changed ");
        writeLogFile(context, JPushSA.CACHE_PATH, null);
        clearHistotyFileContent(context);
    }
}
