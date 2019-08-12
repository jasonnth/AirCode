package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class NotificationSettingsFragment_ViewBinding implements Unbinder {
    private NotificationSettingsFragment target;

    public NotificationSettingsFragment_ViewBinding(NotificationSettingsFragment target2, View source) {
        this.target = target2;
        target2.pushSettingsLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.push_notification_settings, "field 'pushSettingsLayout'", LinearLayout.class);
        target2.smsSettingsLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.sms_notification_settings, "field 'smsSettingsLayout'", LinearLayout.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        NotificationSettingsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.pushSettingsLayout = null;
        target2.smsSettingsLayout = null;
        target2.toolbar = null;
    }
}
