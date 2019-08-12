package com.airbnb.android.lib.activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class HelpCenterActivity_ViewBinding implements Unbinder {
    private HelpCenterActivity target;

    public HelpCenterActivity_ViewBinding(HelpCenterActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public HelpCenterActivity_ViewBinding(HelpCenterActivity target2, View source) {
        this.target = target2;
        target2.mPhoneNumberContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.container_view, "field 'mPhoneNumberContainer'", LinearLayout.class);
        target2.mWebsiteLink = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.offline_visit_help_website, "field 'mWebsiteLink'", TextView.class);
        target2.mOtherCountryText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.offline_cant_find_country_text, "field 'mOtherCountryText'", TextView.class);
    }

    public void unbind() {
        HelpCenterActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mPhoneNumberContainer = null;
        target2.mWebsiteLink = null;
        target2.mOtherCountryText = null;
    }
}
