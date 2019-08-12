package com.airbnb.android.lib.china5a.fragments;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.collections.ProfilePhotoSheet;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class PhotoVerificationFragment_ViewBinding implements Unbinder {
    private PhotoVerificationFragment target;
    private View view2131755340;
    private View view2131755341;
    private View view2131755342;
    private View view2131755343;
    private View view2131755971;
    private View view2131755974;

    public PhotoVerificationFragment_ViewBinding(final PhotoVerificationFragment target2, View source) {
        this.target = target2;
        target2.photoSheet = (ProfilePhotoSheet) Utils.findRequiredViewAsType(source, C0880R.C0882id.profile_photo_sheet, "field 'photoSheet'", ProfilePhotoSheet.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.upload_photo_btn, "field 'uploadButton' and method 'onClickUpload'");
        target2.uploadButton = (AirButton) Utils.castView(view, C0880R.C0882id.upload_photo_btn, "field 'uploadButton'", AirButton.class);
        this.view2131755974 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUpload();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.next_btn, "field 'nextButton' and method 'onClickNext'");
        target2.nextButton = (AirButton) Utils.castView(view2, C0880R.C0882id.next_btn, "field 'nextButton'", AirButton.class);
        this.view2131755971 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNext();
            }
        });
        target2.photoSelectionLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.choiceLayout, "field 'photoSelectionLayout'", ViewGroup.class);
        target2.choiceInfoText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.choiceInfoText, "field 'choiceInfoText'", AirTextView.class);
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.choiceOneText, "field 'facebookButton' and method 'onClickFacebookLink'");
        target2.facebookButton = (LinkActionRow) Utils.castView(view3, C0880R.C0882id.choiceOneText, "field 'facebookButton'", LinkActionRow.class);
        this.view2131755340 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickFacebookLink();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.choiceTwoText, "field 'takePhotoButton' and method 'onClickCameraLink'");
        target2.takePhotoButton = (LinkActionRow) Utils.castView(view4, C0880R.C0882id.choiceTwoText, "field 'takePhotoButton'", LinkActionRow.class);
        this.view2131755341 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickCameraLink();
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.choiceThreeText, "field 'albumButton' and method 'onClickAlbumLink'");
        target2.albumButton = (LinkActionRow) Utils.castView(view5, C0880R.C0882id.choiceThreeText, "field 'albumButton'", LinkActionRow.class);
        this.view2131755342 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickAlbumLink();
            }
        });
        View view6 = Utils.findRequiredView(source, C0880R.C0882id.choiceCancelText, "field 'cancelButton' and method 'onClickChoiceCancel'");
        target2.cancelButton = (LinkActionRow) Utils.castView(view6, C0880R.C0882id.choiceCancelText, "field 'cancelButton'", LinkActionRow.class);
        this.view2131755343 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickChoiceCancel();
            }
        });
    }

    public void unbind() {
        PhotoVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.photoSheet = null;
        target2.uploadButton = null;
        target2.nextButton = null;
        target2.photoSelectionLayout = null;
        target2.choiceInfoText = null;
        target2.facebookButton = null;
        target2.takePhotoButton = null;
        target2.albumButton = null;
        target2.cancelButton = null;
        this.view2131755974.setOnClickListener(null);
        this.view2131755974 = null;
        this.view2131755971.setOnClickListener(null);
        this.view2131755971 = null;
        this.view2131755340.setOnClickListener(null);
        this.view2131755340 = null;
        this.view2131755341.setOnClickListener(null);
        this.view2131755341 = null;
        this.view2131755342.setOnClickListener(null);
        this.view2131755342 = null;
        this.view2131755343.setOnClickListener(null);
        this.view2131755343 = null;
    }
}
