package com.appboy.p028ui.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* renamed from: com.appboy.ui.actions.ViewAction */
public class ViewAction implements IAction {
    private final Intent mIntent = new Intent("android.intent.action.VIEW");

    public ViewAction(Uri uri, Bundle extras) {
        this.mIntent.setData(uri);
        if (extras != null) {
            this.mIntent.putExtras(extras);
        }
    }

    public void execute(Context context) {
        if (this.mIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(this.mIntent);
        }
    }
}
