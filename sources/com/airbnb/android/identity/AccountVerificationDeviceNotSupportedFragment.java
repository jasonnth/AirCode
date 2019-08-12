package com.airbnb.android.identity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.CameraHelper;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import icepick.State;

public class AccountVerificationDeviceNotSupportedFragment extends BaseAccountVerificationFragment {
    @BindView
    AirTextView bodyView;
    @BindView
    AirImageView iconView;
    @State
    Mode mode;
    @BindView
    AirTextView titleView;

    public enum Mode {
        NoCamera(C6533R.C6534drawable.camera_icon, C6533R.string.account_verification_not_supported_no_camera_title, C6533R.string.account_verification_not_supported_no_camera_body, NavigationTag.VerificationNoCameraDetected),
        NoJumio(C6533R.C6534drawable.belo_icon, C6533R.string.account_verification_not_supported_not_compatible_title, C6533R.string.account_verification_not_supported_not_compatible_body, NavigationTag.VerificationNoJumioSupport);
        
        public final int bodyText;
        public final int iconDrawable;
        public final int titleText;
        public final NavigationTag trackingTag;

        private Mode(int iconDrawable2, int titleText2, int bodyText2, NavigationTag trackingTag2) {
            this.iconDrawable = iconDrawable2;
            this.titleText = titleText2;
            this.bodyText = bodyText2;
            this.trackingTag = trackingTag2;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_device_not_supported, container, false);
        bindViews(view);
        if (!CameraHelper.isCameraAvailable(getContext())) {
            this.mode = Mode.NoCamera;
        } else {
            this.mode = Mode.NoJumio;
        }
        this.iconView.setImageResource(this.mode.iconDrawable);
        this.titleView.setText(this.mode.titleText);
        this.bodyView.setText(this.mode.bodyText);
        this.controller.getIdentityJitneyLogger().logImpression(getVerificationFlow(), this.controller.getUser(), null, Page.device_not_supported);
        return view;
    }

    public void onResume() {
        super.onResume();
        this.controller.setState(SheetState.Error);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onConfirmClick() {
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), null, Page.device_not_supported, Element.navigation_button_finish);
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public int getNavigationIcon() {
        return 0;
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.mode.trackingTag;
    }
}
