package com.airbnb.android.lib.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class ExpiredOauthTokenActivity_ViewBinding implements Unbinder {
    private ExpiredOauthTokenActivity target;
    private View view2131755415;

    public ExpiredOauthTokenActivity_ViewBinding(ExpiredOauthTokenActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ExpiredOauthTokenActivity_ViewBinding(final ExpiredOauthTokenActivity target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.sign_in_button, "method 'startLogin'");
        this.view2131755415 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.startLogin();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755415.setOnClickListener(null);
        this.view2131755415 = null;
    }
}
