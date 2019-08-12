package com.airbnb.android.listyourspacedls.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.utils.ListingPhotoPickerUtil;
import com.airbnb.android.listing.utils.ListingPhotosUtil;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;

public class LYSPhotoStartFragment extends LYSBaseFragment {
    private static final int REQUEST_CODE_SELECT_PICTURE = 12;
    LYSJitneyLogger jitneyLogger;
    PhotoUploadManager photoUploadManager;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new LYSPhotoStartFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_photos_start, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        showTip(C7251R.string.lys_dls_photo_tip, LYSPhotoStartFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        navigateInFlow(LYSStep.Photos);
    }

    @OnClick
    public void onClickAddButton() {
        this.jitneyLogger.logClickToAddPhotos("AddPhotosNow", Long.valueOf(this.controller.getListing().getId()));
        startActivityForResult(ListingPhotoPickerUtil.createPickerIntent(getContext()), 12);
    }

    @OnClick
    public void onClickSkipButton() {
        this.jitneyLogger.logClickToSkipPhotos(Long.valueOf(this.controller.getListing().getId()));
        this.controller.nextStep(LYSStep.PhotoManager);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 12 && resultCode == -1) {
            this.photoUploadManager.uploadImage(ListingPhotosUtil.createPhotoUpload(getContext(), this.controller.getListing(), data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH)));
            this.controller.nextStep(LYSStep.Photos);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: private */
    public void showTipDialog() {
        this.controller.showTipModal(C7251R.string.lys_dls_photo_tip_title, C7251R.string.lys_dls_photo_tip_text, NavigationTag.LYSAddPhotosTip);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSAddPhotos;
    }
}
