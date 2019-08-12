package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class OnlineIdChildFragment_ViewBinding implements Unbinder {
    private OnlineIdChildFragment target;

    public OnlineIdChildFragment_ViewBinding(OnlineIdChildFragment target2, View source) {
        this.target = target2;
        target2.mFacebookButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.facebook_button, "field 'mFacebookButton'", Button.class);
        target2.mLinkedInButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.linkedin_button, "field 'mLinkedInButton'", Button.class);
        target2.mGoogleButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.google_button, "field 'mGoogleButton'", Button.class);
        target2.mWeiboButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.weibo_button, "field 'mWeiboButton'", Button.class);
    }

    public void unbind() {
        OnlineIdChildFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mFacebookButton = null;
        target2.mLinkedInButton = null;
        target2.mGoogleButton = null;
        target2.mWeiboButton = null;
    }
}
