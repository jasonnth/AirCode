package com.tencent.p313mm.sdk.channel.compatible;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/* renamed from: com.tencent.mm.sdk.channel.compatible.MMessage */
public class MMessage {

    /* renamed from: com.tencent.mm.sdk.channel.compatible.MMessage$Args */
    public static class Args {
        public String action;
        public Bundle bundle;
        public String content;
        public String targetPkg;
    }

    public static boolean send(Context context, Args args) {
        if (context == null || args == null) {
            Log.e("MMessage", "send fail, invalid argument");
            return false;
        } else if (args.action == null || args.action.length() <= 0) {
            Log.e("MMessage", "send fail, action is null");
            return false;
        } else {
            String perm = null;
            if (args.targetPkg != null && args.targetPkg.length() > 0) {
                perm = args.targetPkg + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(args.action);
            if (args.bundle != null) {
                intent.putExtras(args.bundle);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 587268097);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", args.content);
            intent.putExtra("_mmessage_checksum", MMessageUtil.genCheckSum(args.content, 587268097, packageName));
            context.sendBroadcast(intent, perm);
            Log.d("MMessage", "send mm message, intent=" + intent + ", perm=" + perm);
            return true;
        }
    }
}
