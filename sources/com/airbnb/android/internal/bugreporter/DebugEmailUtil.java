package com.airbnb.android.internal.bugreporter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.p000v4.content.FileProvider;
import android.text.Html;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.internal.C6574R;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import java.util.Locale;

public class DebugEmailUtil {
    public static Intent createEmailIntent(Context context, String emailRecipient, String subject, String title, String description, boolean isGuestMode, List<String> paths) {
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE").setType("text/html").putExtra("android.intent.extra.EMAIL", new String[]{emailRecipient}).putExtra("android.intent.extra.SUBJECT", subject).putExtra("android.intent.extra.TEXT", createBody(context, isGuestMode, title, description)).addFlags(1);
        if (!paths.isEmpty()) {
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", Lists.newArrayList((Iterable<? extends E>) FluentIterable.from((Iterable<E>) paths).transform(DebugEmailUtil$$Lambda$1.lambdaFactory$(context))));
        }
        ResolveInfo gmailApp = findGmailApp(context, intent);
        if (gmailApp == null) {
            return Intent.createChooser(intent, context.getString(C6574R.string.feedback_select_gmail_to_send));
        }
        intent.setClassName(gmailApp.activityInfo.packageName, gmailApp.activityInfo.name);
        return intent;
    }

    private static String createBody(Context context, boolean isGuestMode, String title, String description) {
        CoreGraph coreGraph = (CoreGraph) CoreApplication.instance(context).component();
        StringBuilder body = new StringBuilder();
        if (!TextUtils.isEmpty(description)) {
            appendHtmlLine(body, "<b>Section:</b>");
            appendHtmlLine(body, title);
            appendHtmlLine(body, "<b>Description:</b>");
            appendHtmlLine(body, description);
            appendHtmlLine(body, "<br />");
        }
        appendHtmlLine(body, "<b>Version:</b> ", BuildHelper.versionName());
        appendHtmlLine(body, "<b>Version Code:</b> ", String.valueOf(BuildHelper.versionCode()));
        User user = coreGraph.accountManager().getCurrentUser();
        if (user != null) {
            appendHtmlLine(body, "<b>User:</b> ", Long.toString(user.getId()));
        }
        appendHtmlLine(body, "<b>Activity:</b> ", context.getClass().getSimpleName());
        appendHtmlLine(body, "<b>OS Version:</b> ", VERSION.RELEASE, " (SDK ", Integer.toString(VERSION.SDK_INT), ")");
        appendHtmlLine(body, "<b>Manufacturer:</b> ", Build.MANUFACTURER);
        appendHtmlLine(body, "<b>Device:</b> ", Build.DEVICE);
        appendHtmlLine(body, "<b>Model:</b> ", Build.MODEL);
        appendHtmlLine(body, "<b>Network:</b> ", coreGraph.networkMonitor().getNetworkClass().description);
        appendHtmlLine(body, "<b>Language:</b> ", Locale.getDefault().getDisplayName());
        String[] strArr = new String[2];
        strArr[0] = "<b>Mode:</b> ";
        strArr[1] = isGuestMode ? "Guest" : "Host";
        appendHtmlLine(body, strArr);
        return Html.fromHtml(body.toString()).toString();
    }

    private static void appendHtmlLine(StringBuilder builder, String... content) {
        for (String s : content) {
            builder.append(s);
        }
        builder.append("<br />");
    }

    /* access modifiers changed from: private */
    public static Uri makeExternalUri(Context context, String path) {
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(path));
    }

    private static ResolveInfo findGmailApp(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        for (ResolveInfo info : packageManager.queryIntentActivities(intent, 0)) {
            if (!info.activityInfo.packageName.endsWith(".gm")) {
                if (info.activityInfo.name.toLowerCase().contains("gmail")) {
                }
            }
            return info;
        }
        return null;
    }
}
