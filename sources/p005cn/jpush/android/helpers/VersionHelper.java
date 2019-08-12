package p005cn.jpush.android.helpers;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.DeviceInfoUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.helpers.VersionHelper */
public class VersionHelper {
    private static final String TAG = "VersionHelper";

    public static void handleUpgrade(Context context) {
        Logger.m1416d(TAG, "action - handleUpgrade");
        String curVersion = ServiceInterface.getVersion();
        String oldVersion = DeviceInfoUtil.getVersion(context);
        if (StringUtils.isEmpty(oldVersion)) {
            oldVersion = Configs.getSdkVersion();
        }
        Logger.m1428v(TAG, "sdk version - curVersion:" + curVersion + ",oldVersion:" + oldVersion);
        if (StringUtils.isEmpty(oldVersion)) {
            Logger.m1416d(TAG, "It's a new app, first installed");
        } else if (!curVersion.equals(oldVersion) && !curVersion.startsWith("1.") && oldVersion.startsWith("1.")) {
            DeviceInfoUtil.setVersion(context, curVersion);
            onUpgradeFromVersionOne(context);
        }
        Configs.setSdkVersion(curVersion);
    }

    private static void onUpgradeFromVersionOne(Context context) {
        Logger.m1416d(TAG, "action - onUpgrade");
        copyRegisterUserInfo(context);
        Configs.copyDataOnUpgradeFromVersionOne(context);
    }

    private static void onUpgrade(Context context) {
    }

    private static synchronized boolean copyRegisterUserInfo(Context context) {
        boolean z;
        synchronized (VersionHelper.class) {
            Logger.m1416d(TAG, "Action - copyRegisterUserInfo");
            long uid = 0;
            String password = "";
            String registrationID = "";
            try {
                byte[] idArray = new byte[8];
                FileInputStream fis = context.openFileInput("PrefsFile");
                fis.read(idArray, 0, 8);
                uid = 0;
                for (int i = 0; i < 8; i++) {
                    uid = (uid << 8) + ((long) (idArray[i] & 255));
                }
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int c = fis.read();
                    if (c == -1) {
                        break;
                    }
                    sb.append((char) c);
                }
                fis.close();
                password = sb.toString();
            } catch (FileNotFoundException e) {
                Logger.m1416d(TAG, "No saved user info.");
                uid = 0;
            } catch (IOException e2) {
                Logger.m1417d(TAG, "", e2);
            }
            if (uid == 0) {
                z = false;
            } else {
                if (StringUtils.isEmpty(registrationID)) {
                    registrationID = Configs.getRegistrationId();
                }
                if (StringUtils.isEmpty(registrationID)) {
                    Logger.m1416d(TAG, "No RegistrationID.");
                    z = false;
                } else {
                    Configs.setRegisteredUserInfo(uid, password, registrationID, AndroidUtil.getDeviceId(context), JPush.APP_KEY);
                    z = true;
                }
            }
        }
        return z;
    }
}
