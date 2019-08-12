package com.appboy.p028ui.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.p028ui.support.UriUtils;
import java.util.Map.Entry;

/* renamed from: com.appboy.ui.actions.ActivityAction */
public final class ActivityAction implements IAction {
    private final Intent mIntent;

    public ActivityAction(String packageName, Uri uri, Bundle extras) {
        this(new Intent());
        this.mIntent.setClassName(packageName, uri.getHost());
        if (extras != null) {
            this.mIntent.putExtras(extras);
        }
        for (Entry<String, String> entry : UriUtils.getQueryParameters(uri).entrySet()) {
            this.mIntent.putExtra((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public ActivityAction(Intent intent) {
        this.mIntent = intent;
    }

    public void execute(Context context) {
        if (this.mIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(this.mIntent);
        }
    }
}
