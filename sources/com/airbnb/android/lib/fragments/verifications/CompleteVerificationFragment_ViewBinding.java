package com.airbnb.android.lib.fragments.verifications;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class CompleteVerificationFragment_ViewBinding implements Unbinder {
    private CompleteVerificationFragment target;
    private View view2131756872;

    public CompleteVerificationFragment_ViewBinding(final CompleteVerificationFragment target2, View source) {
        this.target = target2;
        target2.bookingSubHeader = (TextView) Utils.findOptionalViewAsType(source, C0880R.C0882id.txt_complete_verification_subheader, "field 'bookingSubHeader'", TextView.class);
        target2.contentContainer = Utils.findRequiredView(source, C0880R.C0882id.container_content, "field 'contentContainer'");
        target2.backgroundImageView = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.img_background, "field 'backgroundImageView'", ImageView.class);
        target2.gradientImage = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.img_gradient, "field 'gradientImage'", ImageView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_complete_verifications, "method 'clickCompleteButton'");
        this.view2131756872 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickCompleteButton();
            }
        });
    }

    public void unbind() {
        CompleteVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.bookingSubHeader = null;
        target2.contentContainer = null;
        target2.backgroundImageView = null;
        target2.gradientImage = null;
        this.view2131756872.setOnClickListener(null);
        this.view2131756872 = null;
    }
}
