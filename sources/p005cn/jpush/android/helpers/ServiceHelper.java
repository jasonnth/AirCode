package p005cn.jpush.android.helpers;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.facebook.internal.AnalyticsEvents;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.data.ReportDBHelper;
import p005cn.jpush.android.service.PushProtocol;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.ReportUtils;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.helpers.ServiceHelper */
public class ServiceHelper {
    private static final String TAG = "ServiceHelper";

    private static int inJectInitPush(int fd, String ip, int port) {
        Logger.m1416d(TAG, "socket before: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        int ret = PushProtocol.InitPush((long) fd, ip, port);
        Logger.m1416d(TAG, "socket end: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        return ret;
    }

    public static void fecthTcpDataFromDB(Context context, Queue<String> mRequestQueue) {
        Logger.m1416d(TAG, "Action:fecthTcpDataFromDB");
        Cursor cur = null;
        try {
            Cursor cur2 = ReportDBHelper.fetchAllTCPData(context);
            if (cur2 != null && cur2.getCount() > 0) {
                Logger.m1424i(TAG, "Get  tcp data from DB, the count is: " + cur2.getCount());
                cur2.moveToFirst();
                while (!cur2.isAfterLast()) {
                    int rowId = cur2.getInt(cur2.getColumnIndex(ReportDBHelper.KEY_ID));
                    String data = cur2.getString(cur2.getColumnIndex(ReportDBHelper.KEY_TCP_DATA));
                    if (mRequestQueue.offer(data)) {
                        Logger.m1424i(TAG, "Get tcp data from DB: " + data);
                        ReportDBHelper.deleteTCPData(context, rowId);
                    }
                    cur2.moveToNext();
                }
            }
            if (cur2 != null) {
                cur2.close();
            }
        } catch (Exception e) {
            Logger.m1421e(TAG, "", e);
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            if (cur != null) {
                cur.close();
            }
            throw th;
        }
    }

    public static void saveTcpData(Context context, Queue<String> mRequestQueue) {
        Logger.m1416d(TAG, "Action:saveTcpData");
        while (true) {
            String request = (String) mRequestQueue.poll();
            if (request != null) {
                ReportDBHelper.insertTcp(context, request);
            } else {
                return;
            }
        }
    }

    public static void checkAppkeyChanged(Context context) {
        String registeredAppKey = Configs.getRegisteredAppKey();
        if (!StringUtils.isEmpty(registeredAppKey) && !"null".equals(registeredAppKey) && !JPush.APP_KEY.equalsIgnoreCase(registeredAppKey)) {
            Logger.m1418dd(TAG, "We found the appKey is changed. Will re-register.");
            Configs.clearRegistered();
            ReportUtils.clearReportLogFile(context);
        }
    }

    public static String getRegistrationExtKey(Context context) {
        String deviceId = AndroidUtil.getDeviceId(context);
        String androidId = AndroidUtil.getAndroidId(context);
        String mac = AndroidUtil.getWifiMac(context, " ");
        String imei = AndroidUtil.getImei(context, " ");
        String serial_number = Build.SERIAL;
        if (StringUtils.isEmpty(deviceId)) {
            deviceId = " ";
        }
        if (StringUtils.isEmpty(androidId)) {
            androidId = " ";
        }
        if (StringUtils.isEmpty(mac)) {
            mac = " ";
        }
        if (StringUtils.isEmpty(imei)) {
            imei = " ";
        }
        if (StringUtils.isEmpty(serial_number) || "unknown".equalsIgnoreCase(serial_number)) {
            serial_number = " ";
        }
        Configs.setMainImei(imei);
        Configs.setMainAndroidId(androidId);
        Configs.setMainMac(mac);
        return AndroidUtil.deviceID_Status + "$$" + deviceId + "$$" + imei + "$$" + androidId + "$$" + mac + "$$" + serial_number;
    }

    public static String getRegistrationKey(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String imei = AndroidUtil.getUDID(context);
        String imsi = null;
        if (AndroidUtil.hasPermission(context, "android.permission.READ_PHONE_STATE")) {
            try {
                imsi = telephonyManager.getSubscriberId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (imei == null) {
            imei = " ";
        }
        if (imsi == null) {
            imsi = " ";
        }
        return imei + "$$" + imsi + "$$" + context.getPackageName() + "$$" + JPush.APP_KEY;
    }

    public static String getApkVersion(Context context) {
        try {
            String vname = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (vname.length() <= 30) {
                return vname;
            }
            Logger.m1422ee(TAG, "The versionName is not valid, Please check your AndroidManifest.xml");
            return vname.substring(0, 30);
        } catch (Exception e) {
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    public static boolean isImPushCommand(int imCmd) {
        if (imCmd == 14 || imCmd == 13 || imCmd == 15) {
            return true;
        }
        return false;
    }

    public static void resetPushStatus(Context context) {
        if (Configs.isStopExecuted(context)) {
            Logger.m1416d(TAG, "call stopPush on im-logout-success or im-login-timeout");
            ServiceInterface.stopPush(context, 1);
            Configs.setStopExecuted(context, false);
            return;
        }
        Logger.m1416d(TAG, "push-status is running before im-login called");
    }
}
