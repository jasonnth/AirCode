package p005cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.PushService;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.data.BasicEntity;
import p005cn.jpush.android.data.DbHelper;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.PkEntity;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.DirectoryUtils;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.service.PushReceiver */
public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "PushReceiver";
    public static Entity failedDowndLoadMSG = null;

    public void onReceive(Context context, Intent intent) {
        Context finalContext = context;
        if (intent == null) {
            Logger.m1434ww(TAG, "Received null intent broadcast. Give up processing.");
            return;
        }
        String str = "";
        try {
            String theAction = intent.getAction();
            Logger.m1418dd(TAG, "onReceive - " + theAction);
            if (JPush.init(context.getApplicationContext())) {
                if (theAction.equals("android.intent.action.BOOT_COMPLETED")) {
                    DirectoryUtils.deleteMsgData(context);
                    ServiceInterface.rtcWithDelayTime(finalContext, 500);
                    return;
                }
                if (theAction.equals("android.intent.action.PACKAGE_ADDED")) {
                    String str2 = "";
                    String dataString = intent.getDataString();
                    if (dataString == null) {
                        Logger.m1434ww(TAG, theAction + ": Get no data from intent.");
                    } else if (dataString.trim().length() <= 8 || !dataString.startsWith("package:")) {
                        Logger.m1434ww(TAG, "Get wrong data string from intent: " + dataString);
                    } else {
                        String packageName = dataString.substring(8);
                        String obj = DbHelper.delUpTableForAppId(context, packageName);
                        ReportHelper.reportOperation(context, StatusCode.buildPackageAddedReportContent(packageName));
                        try {
                            if (!TextUtils.isEmpty(obj)) {
                                String[] objs = obj.split(",");
                                if (objs.length >= 1) {
                                    String msgId = objs[0];
                                    ReportHelper.reportMsgResult(msgId, 1002, context);
                                    String mainActivity = "";
                                    if (objs.length >= 2) {
                                        mainActivity = objs[1];
                                    }
                                    String override_id = "";
                                    if (objs.length >= 3) {
                                        override_id = objs[2];
                                    }
                                    Logger.m1420e(TAG, override_id);
                                    if (!mainActivity.equals(PkEntity.MAIN_ACTIVITY_CONST_NOT_AUTO_RUN) && !AndroidUtil.startNewPK(context, packageName, mainActivity)) {
                                        Logger.m1416d(TAG, "Failed to ist.");
                                        ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TPPE_INVALID_PARAM, context);
                                    }
                                    if (!StringUtils.isEmpty(override_id)) {
                                        msgId = override_id;
                                    }
                                    NotificationHelper.cancelAllNotification(context, msgId);
                                }
                            }
                        } catch (Exception e) {
                            Logger.m1420e(TAG, e.getMessage());
                        }
                    }
                } else {
                    if (theAction.equals("android.intent.action.PACKAGE_REMOVED")) {
                        String str3 = "";
                        String dataString2 = intent.getDataString();
                        if (dataString2 == null) {
                            Logger.m1434ww(TAG, theAction + ": Get no data from intent.");
                        } else if (dataString2.trim().length() <= 8 || !dataString2.startsWith("package:")) {
                            Logger.m1434ww(TAG, "Get wrong data string from intent: " + dataString2);
                        } else {
                            ReportHelper.reportOperation(context, StatusCode.buildPackageRemovedReportContent(dataString2.substring(8)));
                        }
                    } else {
                        if (theAction.equals("android.intent.action.USER_PRESENT")) {
                            ServiceInterface.rtc(finalContext);
                            return;
                        }
                        if (theAction.equals(p005cn.jpush.android.JPushConstants.PushReceiver.INTENT_NOTIFICATION_INSTALL_CLICKED)) {
                            Entity entity = (Entity) intent.getSerializableExtra("body");
                            if (entity != null && entity.isMsgTypeAAndUpdate()) {
                                ReportHelper.reportMsgResult(entity.messageId, StatusCode.RESULT_TYPE_NOTIFICATION_INSTALL_CLICKED, context);
                                PkEntity aEntity = (PkEntity) entity;
                                Intent install = new Intent();
                                install.addFlags(268435456);
                                install.setAction("android.intent.action.VIEW");
                                install.setDataAndType(Uri.fromFile(new File(aEntity._aSavedPath)), JPushConstants.A_MIME);
                                context.startActivity(install);
                                return;
                            }
                            return;
                        }
                        if (theAction.startsWith(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY)) {
                            int noti_type = intent.getIntExtra(Entity.KEY_NOTIFICATION_TYPE, 0);
                            Logger.m1416d(TAG, "ACTION_NOTIFICATION_RECEIVED_PROXY   notification_type = " + noti_type);
                            if (noti_type == 0) {
                                if (ServiceInterface.isServiceStoped(finalContext)) {
                                    Logger.m1424i(TAG, "Service is stoped, give up all the message");
                                    return;
                                } else if (!AndroidUtil.isInValidPushTime(finalContext)) {
                                    Logger.m1424i(TAG, "push is closed，Intercept the message");
                                    return;
                                }
                            }
                            String message = intent.getStringExtra("message");
                            if (StringUtils.isEmpty(message)) {
                                Logger.m1434ww(TAG, "Got an empty notification, don't show it!");
                                return;
                            }
                            String sendId = intent.getStringExtra(JPushConstants.SENDER_ID);
                            BasicEntity basicEntity = ProtocolHelper.preParseOriginalMsgMessage(finalContext, message, intent.getStringExtra(JPushConstants.APP_ID), sendId, intent.getStringExtra("msg_id"));
                            if (basicEntity == null) {
                                Logger.m1432w(TAG, "Transform notification content to BasicEntity failed!");
                                return;
                            }
                            basicEntity.isDeveloperMessage = true;
                            ProtocolHelper.parseMsgMessage(finalContext, basicEntity);
                            abortBroadcast();
                            return;
                        }
                        if (theAction.startsWith(p005cn.jpush.android.JPushConstants.PushReceiver.INTENT_NOTIFICATION_OPENED_PROXY)) {
                            if (intent.getBooleanExtra("debug_notification", false)) {
                                String activity = intent.getStringExtra("activity");
                                int type = intent.getIntExtra("type", -1);
                                if (type == -1) {
                                    String toastText = intent.getStringExtra("toastText");
                                    Toast toast = Toast.makeText(context, activity, 1);
                                    View view = toast.getView();
                                    if (view instanceof LinearLayout) {
                                        View firstChild = ((LinearLayout) view).getChildAt(0);
                                        if (firstChild instanceof TextView) {
                                            TextView toastTv = (TextView) firstChild;
                                            if (!StringUtils.isEmpty(toastText)) {
                                                toastTv.setText(toastText);
                                            }
                                            toastTv.setTextSize(13.0f);
                                        }
                                    }
                                    toast.show();
                                } else if (!StringUtils.isEmpty(activity)) {
                                    String prefix = "JPush 建议集成 SDK 时加上统计代码以评估推送效果。 \n\n检测到在\n";
                                    String suffix1 = "\n的\nonResume()\n";
                                    String suffix2 = "方法中没有调用";
                                    String suffix3 = "\nJPushInterface.onResume()";
                                    switch (type) {
                                        case 1:
                                            suffix1 = "\n的\nonPause()\n";
                                            suffix2 = "方法中没有调用";
                                            suffix3 = "\nJPushInterface.onPause()";
                                            break;
                                        case 2:
                                            suffix1 = "\n的\nonResume()\nonPause()\n";
                                            suffix2 = "方法中没有调用";
                                            suffix3 = "\nJPushInterface.onResume()\nJPushInterface.onPause()";
                                            break;
                                    }
                                    SpannableStringBuilder span = getSpan(activity, prefix, suffix1, suffix2, suffix3);
                                    Toast toast2 = Toast.makeText(context, activity, 1);
                                    View view2 = toast2.getView();
                                    if (view2 instanceof LinearLayout) {
                                        View firstChild2 = ((LinearLayout) view2).getChildAt(0);
                                        if (firstChild2 instanceof TextView) {
                                            TextView toastTv2 = (TextView) firstChild2;
                                            if (span != null) {
                                                toastTv2.setText(span);
                                            }
                                            toastTv2.setTextSize(13.0f);
                                        }
                                    }
                                    toast2.show();
                                }
                            } else {
                                String pushId = intent.getStringExtra(JPushInterface.EXTRA_MSG_ID);
                                if (!StringUtils.isEmpty(pushId)) {
                                    String strNType = intent.getStringExtra(JPushInterface.EXTRA_NOTI_TYPE);
                                    Logger.m1416d(TAG, "strNType = " + strNType);
                                    boolean z = false;
                                    if (strNType != null && "1".equals(strNType)) {
                                        z = true;
                                    }
                                    if (true != z) {
                                        ReportHelper.reportMsgResult(pushId, 1000, context);
                                    }
                                }
                                if (!AndroidUtil.hasReceiverIntentFilter(context, JPushInterface.ACTION_NOTIFICATION_OPENED, true)) {
                                    Logger.m1418dd(TAG, "No ACTION_NOTIFICATION_OPENED defined in manifest, open the default main activity");
                                    AndroidUtil.startMainActivity(finalContext);
                                    return;
                                }
                                Intent intent2 = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
                                intent2.putExtras(intent.getExtras());
                                String toAppPackage = intent.getStringExtra(PushService.PARAM_APP);
                                if (StringUtils.isEmpty(toAppPackage)) {
                                    toAppPackage = context.getPackageName();
                                }
                                intent2.addCategory(toAppPackage);
                                Logger.m1424i(TAG, "Send broadcast to app: " + toAppPackage);
                                context.sendBroadcast(intent2, toAppPackage + JPushConstants.PUSH_MESSAGE_PERMISSION_POSTFIX);
                            }
                        } else {
                            if (theAction.equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                                if (networkInfo == null) {
                                    Logger.m1432w(TAG, "Not found networkInfo");
                                    return;
                                }
                                Logger.m1416d(TAG, "Connection state changed to - " + networkInfo.toString());
                                if (2 == networkInfo.getType() || 3 == networkInfo.getType()) {
                                    Logger.m1416d(TAG, "MMS or SUPL network state change, to do nothing!");
                                    return;
                                }
                                boolean isConnected = false;
                                if (intent.getBooleanExtra("noConnectivity", false)) {
                                    Logger.m1416d(TAG, "No any network is connected");
                                    DownloadControl.isNetworkAvailable = false;
                                    ServiceInterface.networkDisconnected(finalContext);
                                } else if (State.CONNECTED == networkInfo.getState()) {
                                    Logger.m1416d(TAG, "Network is connected.");
                                    ServiceInterface.networkConnected(finalContext);
                                    isConnected = true;
                                    DownloadControl.isNetworkAvailable = true;
                                    if (DownloadService.hasDownladTask()) {
                                        DownloadService.startDownloadTasks(finalContext);
                                    }
                                } else if (State.DISCONNECTED == networkInfo.getState()) {
                                    Logger.m1416d(TAG, "Network is disconnected.");
                                    DownloadControl.isNetworkAvailable = false;
                                    ServiceInterface.networkDisconnected(finalContext);
                                } else {
                                    Logger.m1416d(TAG, "other network state - " + networkInfo.getState() + ". Do nothing.");
                                }
                                AndroidUtil.sendNetworkChangedToIM(context, isConnected);
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e2) {
            Logger.m1434ww(TAG, "Received no action intent broadcast. Give up processing.");
        }
    }

    private SpannableStringBuilder getSpan(String activity, String prex, String suf1, String suf2, String suf3) {
        StringBuilder content = new StringBuilder();
        content.append(prex).append(activity).append(suf1).append(suf2).append(suf3).append("\n\n本提示只在开发打包状态下出现,发布打包不会出现.");
        SpannableStringBuilder span = new SpannableStringBuilder(content);
        int start = prex.length();
        int end = start + activity.length();
        span.setSpan(new ForegroundColorSpan(-13527041), start, end, 33);
        int start2 = end + 2;
        int end2 = (suf1.length() + start2) - 2;
        span.setSpan(new ForegroundColorSpan(-13527041), start2, end2, 33);
        int start3 = end2 + suf2.length();
        span.setSpan(new ForegroundColorSpan(-13527041), start3, start3 + suf3.length(), 33);
        return span;
    }
}
