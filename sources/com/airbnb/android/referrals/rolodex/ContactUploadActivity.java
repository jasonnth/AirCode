package com.airbnb.android.referrals.rolodex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.JellyfishSheetActivity;
import permissions.dispatcher.PermissionUtils;

public class ContactUploadActivity extends JellyfishSheetActivity {
    public static void maybeDoContactUpload(Activity activity) {
        if (PermissionUtils.hasSelfPermissions(activity, "android.permission.READ_CONTACTS")) {
            startUploadService(activity);
            return;
        }
        if (PermissionUtils.shouldShowRequestPermissionRationale(activity, "android.permission.READ_CONTACTS")) {
            activity.startActivity(new Intent(activity, ContactUploadActivity.class));
        }
    }

    public static void startUploadService(Context context) {
        context.startService(ContactUploadIntentService.getIntent(context));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(ContactUploadRequestFragment.getInstance(), false);
    }
}
