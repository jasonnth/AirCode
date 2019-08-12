package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.identity.AccountVerificationOfflineIdController.MisnapUploadListener;
import com.airbnb.android.misnap.MiSnapIdentityCaptureActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.ImagePreviewRow;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.SheetMarquee;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import icepick.State;

public class AccountVerificationMiSnapIDCaptureFragment extends BaseAccountVerificationFragment {
    private static final String ARG_COUNTRY_CODE = "country_code";
    private static final String ARG_GOVERNMENT_ID_TYPE = "government_id_type";
    @BindView
    PrimaryButton applyButton;
    @State
    String backPhotoPath;
    @BindView
    ImagePreviewRow backRow;
    @State
    String capturedBarcode;
    @State
    String countryCode;
    @State
    String frontPhotoPath;
    @BindView
    ImagePreviewRow frontRow;
    @State
    GovernmentIdType governmentIdType;
    private MisnapUploadListener misnapUploadListener = new MisnapUploadListener() {
        public void uploadComplete() {
            AccountVerificationMiSnapIDCaptureFragment.this.frontPhotoPath = null;
            AccountVerificationMiSnapIDCaptureFragment.this.backPhotoPath = null;
            AccountVerificationMiSnapIDCaptureFragment.this.frontRow.setImageUrl(AccountVerificationMiSnapIDCaptureFragment.this.frontPhotoPath, null);
            AccountVerificationMiSnapIDCaptureFragment.this.backRow.setImageUrl(AccountVerificationMiSnapIDCaptureFragment.this.backPhotoPath, null);
            AccountVerificationMiSnapIDCaptureFragment.this.capturedBarcode = null;
            AccountVerificationMiSnapIDCaptureFragment.this.updateViews();
        }
    };
    @BindView
    SheetMarquee sheetMarquee;

    public static AccountVerificationMiSnapIDCaptureFragment newInstance(String countryCode2, GovernmentIdType governmentIdType2) {
        return (AccountVerificationMiSnapIDCaptureFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AccountVerificationMiSnapIDCaptureFragment()).putString("country_code", countryCode2)).putSerializable(ARG_GOVERNMENT_ID_TYPE, governmentIdType2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controller.initOfflineIdController(savedInstanceState, this, this.requestManager, this.navigationAnalytics);
        this.controller.getOfflineIdController().setMisnapUploadListener(this.misnapUploadListener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_license, container, false);
        bindViews(view);
        this.countryCode = getArguments().getString("country_code");
        this.governmentIdType = (GovernmentIdType) getArguments().getSerializable(ARG_GOVERNMENT_ID_TYPE);
        setTexts();
        this.frontRow.setOnClickListener(AccountVerificationMiSnapIDCaptureFragment$$Lambda$1.lambdaFactory$(this, getFrontDocType(this.governmentIdType)));
        this.frontRow.setImageUrl(this.frontPhotoPath, null);
        String backDocType = getBackDocType(this.countryCode, this.governmentIdType);
        if (backDocType != null) {
            this.backRow.setOnClickListener(AccountVerificationMiSnapIDCaptureFragment$$Lambda$2.lambdaFactory$(this, backDocType));
            this.backRow.setImageUrl(this.backPhotoPath, null);
        } else {
            this.backRow.setVisibility(8);
        }
        this.controller.getOfflineIdController().setCountryCode(this.countryCode);
        this.controller.getOfflineIdController().setGovernmentIdType(this.governmentIdType);
        this.applyButton.setOnClickListener(AccountVerificationMiSnapIDCaptureFragment$$Lambda$3.lambdaFactory$(this));
        updateViews();
        this.controller.getOfflineIdController().setView(view);
        return view;
    }

    private void setTexts() {
        switch (this.governmentIdType) {
            case VISA:
                this.sheetMarquee.setTitle(C6533R.string.verified_id_offline_visa);
                this.sheetMarquee.setSubtitle(C6533R.string.scan_id_front_desc);
                return;
            case PASSPORT:
                this.sheetMarquee.setTitle(C6533R.string.verified_id_offline_passport);
                this.sheetMarquee.setSubtitle(C6533R.string.scan_id_front_desc);
                return;
            case DRIVING_LICENSE:
                this.sheetMarquee.setTitle(C6533R.string.verified_id_offline_drivers_license);
                this.sheetMarquee.setSubtitle(C6533R.string.scan_id_front_and_back_desc);
                return;
            case ID_CARD:
                this.sheetMarquee.setTitle(C6533R.string.verified_id_offline_id_card);
                this.sheetMarquee.setSubtitle(C6533R.string.scan_id_front_and_back_desc);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void updateViews() {
        ViewUtils.setVisibleIf((View) this.applyButton, !TextUtils.isEmpty(this.frontPhotoPath) && (!TextUtils.isEmpty(this.backPhotoPath) || this.backRow.getVisibility() != 0));
        this.frontRow.setSubtitleText(TextUtils.isEmpty(this.frontPhotoPath) ? C6533R.string.scan_id_front_take : C6533R.string.scan_id_retake);
        if (this.backRow.getVisibility() == 0) {
            this.backRow.setSubtitleText(TextUtils.isEmpty(this.backPhotoPath) ? C6533R.string.scan_id_back_take : C6533R.string.scan_id_retake);
        }
    }

    /* access modifiers changed from: protected */
    public int getAirToolbarTheme() {
        return 3;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == -1) {
            if (data.getBooleanExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_IS_BACK_PHOTO, false)) {
                this.backPhotoPath = data.getStringExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_RESULT_FILE);
                this.backRow.setImageUrl(this.backPhotoPath, null);
                this.capturedBarcode = data.getStringExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_BARCODE);
            } else {
                this.frontPhotoPath = data.getStringExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_RESULT_FILE);
                this.frontRow.setImageUrl(this.frontPhotoPath, null);
            }
            updateViews();
        }
    }

    public void onResume() {
        super.onResume();
        this.controller.getOfflineIdController().onResume();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.getOfflineIdController().onSaveInstanceState(outState);
    }

    private String getFrontDocType(GovernmentIdType idType) {
        if (idType == GovernmentIdType.DRIVING_LICENSE || idType == GovernmentIdType.ID_CARD) {
            return MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE;
        }
        return "PASSPORT";
    }

    private String getBackDocType(String countryCode2, GovernmentIdType idType) {
        switch (idType) {
            case DRIVING_LICENSE:
                if ("USA".equals(countryCode2)) {
                    return "PDF417";
                }
                return MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE;
            case ID_CARD:
                return MiSnapApiConstants.PARAMETER_DOCTYPE_DRIVER_LICENSE;
            default:
                return null;
        }
    }
}
