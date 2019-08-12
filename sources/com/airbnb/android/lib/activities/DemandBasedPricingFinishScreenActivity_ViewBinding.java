package com.airbnb.android.lib.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.lottie.LottieAnimationView;

public class DemandBasedPricingFinishScreenActivity_ViewBinding implements Unbinder {
    private DemandBasedPricingFinishScreenActivity target;
    private View view2131755408;
    private View view2131755409;

    public DemandBasedPricingFinishScreenActivity_ViewBinding(DemandBasedPricingFinishScreenActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public DemandBasedPricingFinishScreenActivity_ViewBinding(final DemandBasedPricingFinishScreenActivity target2, View source) {
        this.target = target2;
        target2.lottieView = (LottieAnimationView) Utils.findRequiredViewAsType(source, C0880R.C0882id.dbp_finish_video_layout, "field 'lottieView'", LottieAnimationView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.view_calendar_button, "method 'onViewCalendarClicked'");
        this.view2131755409 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onViewCalendarClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_close, "method 'close'");
        this.view2131755408 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.close();
            }
        });
    }

    public void unbind() {
        DemandBasedPricingFinishScreenActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.lottieView = null;
        this.view2131755409.setOnClickListener(null);
        this.view2131755409 = null;
        this.view2131755408.setOnClickListener(null);
        this.view2131755408 = null;
    }
}
