package com.airbnb.android.identity;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.ProfilePhotoSheet;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class AccountVerificationProfilePhoto_ViewBinding implements Unbinder {
    private AccountVerificationProfilePhoto target;
    private View view2131755303;
    private View view2131755304;
    private View view2131755305;
    private View view2131755306;

    public AccountVerificationProfilePhoto_ViewBinding(AccountVerificationProfilePhoto target2) {
        this(target2, target2);
    }

    public AccountVerificationProfilePhoto_ViewBinding(final AccountVerificationProfilePhoto target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.profilePhotoSheet = (ProfilePhotoSheet) Utils.findRequiredViewAsType(source, C6533R.C6535id.verification_profile_photo_sheet, "field 'profilePhotoSheet'", ProfilePhotoSheet.class);
        target2.bottomToolbar = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_toolbar_container, "field 'bottomToolbar'");
        target2.photoSelectionLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C6533R.C6535id.choiceLayout, "field 'photoSelectionLayout'", ViewGroup.class);
        target2.choiceInfoText = (AirTextView) Utils.findRequiredViewAsType(source, C6533R.C6535id.choiceInfoText, "field 'choiceInfoText'", AirTextView.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.choiceOneText, "field 'facebookButton' and method 'onClickFacebookLink'");
        target2.facebookButton = (LinkActionRow) Utils.castView(view, C6533R.C6535id.choiceOneText, "field 'facebookButton'", LinkActionRow.class);
        this.view2131755303 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickFacebookLink();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.choiceTwoText, "field 'takePhotoButton' and method 'onClickCameraLink'");
        target2.takePhotoButton = (LinkActionRow) Utils.castView(view2, C6533R.C6535id.choiceTwoText, "field 'takePhotoButton'", LinkActionRow.class);
        this.view2131755304 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickCameraLink();
            }
        });
        View view3 = Utils.findRequiredView(source, C6533R.C6535id.choiceThreeText, "field 'albumButton' and method 'onClickAlbumLink'");
        target2.albumButton = (LinkActionRow) Utils.castView(view3, C6533R.C6535id.choiceThreeText, "field 'albumButton'", LinkActionRow.class);
        this.view2131755305 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickAlbumLink();
            }
        });
        View view4 = Utils.findRequiredView(source, C6533R.C6535id.choiceCancelText, "field 'cancelButton' and method 'onClickChoiceCancel'");
        target2.cancelButton = (LinkActionRow) Utils.castView(view4, C6533R.C6535id.choiceCancelText, "field 'cancelButton'", LinkActionRow.class);
        this.view2131755306 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickChoiceCancel();
            }
        });
        target2.secondaryButton = (AirButton) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_bottom_secondary_button, "field 'secondaryButton'", AirButton.class);
        target2.primaryButton = (AirButton) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_bottom_primary_button, "field 'primaryButton'", AirButton.class);
        target2.secondaryWhiteButton = (AirButton) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_bottom_secondary_button_white, "field 'secondaryWhiteButton'", AirButton.class);
        target2.primaryWhiteButton = (AirButton) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_bottom_next_button, "field 'primaryWhiteButton'", AirButton.class);
    }

    public void unbind() {
        AccountVerificationProfilePhoto target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.profilePhotoSheet = null;
        target2.bottomToolbar = null;
        target2.photoSelectionLayout = null;
        target2.choiceInfoText = null;
        target2.facebookButton = null;
        target2.takePhotoButton = null;
        target2.albumButton = null;
        target2.cancelButton = null;
        target2.secondaryButton = null;
        target2.primaryButton = null;
        target2.secondaryWhiteButton = null;
        target2.primaryWhiteButton = null;
        this.view2131755303.setOnClickListener(null);
        this.view2131755303 = null;
        this.view2131755304.setOnClickListener(null);
        this.view2131755304 = null;
        this.view2131755305.setOnClickListener(null);
        this.view2131755305 = null;
        this.view2131755306.setOnClickListener(null);
        this.view2131755306 = null;
    }
}
