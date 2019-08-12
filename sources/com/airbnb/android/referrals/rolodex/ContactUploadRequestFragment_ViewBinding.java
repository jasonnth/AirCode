package com.airbnb.android.referrals.rolodex;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.referrals.C1532R;

public class ContactUploadRequestFragment_ViewBinding implements Unbinder {
    private ContactUploadRequestFragment target;
    private View view2131755477;
    private View view2131755478;

    public ContactUploadRequestFragment_ViewBinding(final ContactUploadRequestFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C1532R.C1534id.sync_button, "method 'onSyncButtonClick'");
        this.view2131755478 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSyncButtonClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C1532R.C1534id.skip_button, "method 'onSkipButtonClick'");
        this.view2131755477 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSkipButtonClick();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755478.setOnClickListener(null);
        this.view2131755478 = null;
        this.view2131755477.setOnClickListener(null);
        this.view2131755477 = null;
    }
}
