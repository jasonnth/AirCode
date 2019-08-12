package com.airbnb.android.referrals.rolodex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.referrals.C1532R;

public class ContactUploadRequestFragment extends AirFragment {
    public static ContactUploadRequestFragment getInstance() {
        return new ContactUploadRequestFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1532R.layout.fragment_contact_upload_request, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSyncButtonClick() {
        ContactUploadRequestFragmentPermissionsDispatcher.startContactUploadingWithCheck(this);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSkipButtonClick() {
        getAirActivity().finish();
    }

    /* access modifiers changed from: 0000 */
    public void startContactUploading() {
        ContactUploadActivity.startUploadService(getContext());
        getAirActivity().finish();
    }

    /* access modifiers changed from: 0000 */
    public void onContactsPermissionDenied() {
        getAirActivity().finish();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        ContactUploadRequestFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
