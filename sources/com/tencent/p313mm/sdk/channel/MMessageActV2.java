package com.tencent.p313mm.sdk.channel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.tencent.p313mm.sdk.channel.compatible.MMessageUtil;

/* renamed from: com.tencent.mm.sdk.channel.MMessageActV2 */
public class MMessageActV2 {

    /* renamed from: com.tencent.mm.sdk.channel.MMessageActV2$Args */
    public static class Args {
        public Bundle bundle;
        public String content;
        public int flags = -1;
        public String targetClassName;
        public String targetPkgName;

        public String toString() {
            return "targetPkgName:" + this.targetPkgName + ", targetClassName:" + this.targetClassName + ", content:" + this.content + ", flags:" + this.flags + ", bundle:" + this.bundle;
        }
    }

    public static boolean send(Context context, Args args) {
        if (context == null || args == null) {
            Log.e("MMessageAct", "send fail, invalid argument");
            return false;
        } else if (args.targetPkgName == null || args.targetClassName.length() <= 0) {
            Log.e("MMessageAct", "send fail, invalid targetPkgName, targetPkgName = " + args.targetPkgName);
            return false;
        } else {
            if (args.targetClassName == null || args.targetClassName.length() <= 0) {
                args.targetClassName = args.targetPkgName + ".wxapi.WXEntryActivity";
            }
            Log.d("MMessageAct", "send, targetPkgName = " + args.targetPkgName + ", targetClassName = " + args.targetClassName);
            Intent intent = new Intent();
            intent.setClassName(args.targetPkgName, args.targetClassName);
            if (args.bundle != null) {
                intent.putExtras(args.bundle);
            }
            String senderPkgName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 587268097);
            intent.putExtra("_mmessage_appPackage", senderPkgName);
            intent.putExtra("_mmessage_content", args.content);
            intent.putExtra("_mmessage_checksum", MMessageUtil.genCheckSum(args.content, 587268097, senderPkgName));
            if (args.flags == -1) {
                intent.addFlags(268435456).addFlags(134217728);
            } else {
                intent.setFlags(args.flags);
            }
            try {
                context.startActivity(intent);
                Log.d("MMessageAct", "send mm message, intent=" + intent);
                return true;
            } catch (Exception e) {
                Log.e("MMessageAct", "send fail, ex = %s" + e.getMessage());
                return false;
            }
        }
    }
}
