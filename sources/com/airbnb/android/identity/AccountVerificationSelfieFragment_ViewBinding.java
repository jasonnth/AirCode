package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class AccountVerificationSelfieFragment_ViewBinding implements Unbinder {
    private AccountVerificationSelfieFragment target;
    private View view2131755611;
    private View view2131756111;
    private View view2131756112;
    private View view2131756113;
    private View view2131756114;

    public AccountVerificationSelfieFragment_ViewBinding(final AccountVerificationSelfieFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.selfieMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.selfie_marquee, "field 'selfieMarquee'", SheetMarquee.class);
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, C6533R.C6535id.selfie_image, "field 'image'", AirImageView.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_secondary_button, "field 'secondaryButton' and method 'onDocClick'");
        target2.secondaryButton = (AirButton) Utils.castView(view, C6533R.C6535id.sheet_bottom_secondary_button, "field 'secondaryButton'", AirButton.class);
        this.view2131756111 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDocClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_secondary_button_white, "field 'secondaryButtonWhite' and method 'onDocClickWhite'");
        target2.secondaryButtonWhite = (AirButton) Utils.castView(view2, C6533R.C6535id.sheet_bottom_secondary_button_white, "field 'secondaryButtonWhite'", AirButton.class);
        this.view2131756112 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDocClickWhite();
            }
        });
        View view3 = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_primary_button, "field 'primaryButton' and method 'onTakePhotoClickedOneDotOne'");
        target2.primaryButton = (AirButton) Utils.castView(view3, C6533R.C6535id.sheet_bottom_primary_button, "field 'primaryButton'", AirButton.class);
        this.view2131756113 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onTakePhotoClickedOneDotOne();
            }
        });
        View view4 = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_next_button, "field 'nextButton' and method 'onTakePhotoClickedNext'");
        target2.nextButton = (AirButton) Utils.castView(view4, C6533R.C6535id.sheet_bottom_next_button, "field 'nextButton'", AirButton.class);
        this.view2131756114 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onTakePhotoClickedNext();
            }
        });
        View view5 = source.findViewById(C6533R.C6535id.account_verification_selfie_take_photo);
        if (view5 != null) {
            this.view2131755611 = view5;
            view5.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View p0) {
                    target2.onTakePhotoClicked();
                }
            });
        }
    }

    public void unbind() {
        AccountVerificationSelfieFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.selfieMarquee = null;
        target2.image = null;
        target2.secondaryButton = null;
        target2.secondaryButtonWhite = null;
        target2.primaryButton = null;
        target2.nextButton = null;
        this.view2131756111.setOnClickListener(null);
        this.view2131756111 = null;
        this.view2131756112.setOnClickListener(null);
        this.view2131756112 = null;
        this.view2131756113.setOnClickListener(null);
        this.view2131756113 = null;
        this.view2131756114.setOnClickListener(null);
        this.view2131756114 = null;
        if (this.view2131755611 != null) {
            this.view2131755611.setOnClickListener(null);
            this.view2131755611 = null;
        }
    }
}
