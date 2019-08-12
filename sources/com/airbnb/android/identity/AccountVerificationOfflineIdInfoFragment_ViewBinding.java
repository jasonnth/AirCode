package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class AccountVerificationOfflineIdInfoFragment_ViewBinding implements Unbinder {
    private AccountVerificationOfflineIdInfoFragment target;
    private View view2131756111;
    private View view2131756112;
    private View view2131756113;
    private View view2131756114;

    public AccountVerificationOfflineIdInfoFragment_ViewBinding(final AccountVerificationOfflineIdInfoFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.offline_id_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, C6533R.C6535id.offline_id_image, "field 'image'", AirImageView.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_secondary_button, "field 'secondaryButton' and method 'onClickDocs'");
        target2.secondaryButton = (AirButton) Utils.castView(view, C6533R.C6535id.sheet_bottom_secondary_button, "field 'secondaryButton'", AirButton.class);
        this.view2131756111 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDocs();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_secondary_button_white, "field 'secondaryButtonWhite' and method 'onClickDocsWhite'");
        target2.secondaryButtonWhite = (AirButton) Utils.castView(view2, C6533R.C6535id.sheet_bottom_secondary_button_white, "field 'secondaryButtonWhite'", AirButton.class);
        this.view2131756112 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDocsWhite();
            }
        });
        View view3 = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_primary_button, "field 'primaryButton' and method 'onContinueClicked'");
        target2.primaryButton = (AirButton) Utils.castView(view3, C6533R.C6535id.sheet_bottom_primary_button, "field 'primaryButton'", AirButton.class);
        this.view2131756113 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClicked();
            }
        });
        View view4 = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_next_button, "field 'nextButton' and method 'onNextClicked'");
        target2.nextButton = (AirButton) Utils.castView(view4, C6533R.C6535id.sheet_bottom_next_button, "field 'nextButton'", AirButton.class);
        this.view2131756114 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClicked();
            }
        });
    }

    public void unbind() {
        AccountVerificationOfflineIdInfoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.sheetMarquee = null;
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
    }
}
