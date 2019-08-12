package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.events.OfficialIDStatusEvent;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment.ProgressDialogListener;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.core.requests.OfficialIdSupportedCountriesRequest;
import com.airbnb.android.core.requests.OfficialIdUploadRequest;
import com.airbnb.android.core.responses.OfficialIdSupportedCountriesResponse;
import com.airbnb.android.core.responses.OfficialIdSupportedCountriesResponse.Country;
import com.airbnb.android.core.responses.OfficialIdSupportedCountriesResponse.Country.Identification;
import com.airbnb.android.core.responses.OfficialIdUploadResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdCountryFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdErrorFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoConfirmationFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdTypeFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdDialogSummaryFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdDialogSummaryFragment.VerifiedIdDialogFragment;
import com.airbnb.android.lib.services.OfficialIdIntentService;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.lib.views.StepProgressBar;
import com.airbnb.android.utils.Strap;
import com.squareup.otto.Subscribe;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class OfficialIdActivity extends AirActivity implements VerifiedIdStrapper, ProgressDialogListener, VerifiedIdDialogFragment {
    private static final String BACK_ID_URI_EXTRA = "back_id_uri";
    private static final String COUNTRY_CODE_EXTRA = "country_code";
    private static final String DIALOG_FRAGMENT_TAG = "dialog";
    public static final String DRIVERS_LICENSE_TYPE = "DRIVING_LICENSE";
    private static final String FRONT_ID_URI_EXTRA = "front_id_uri";
    public static final String ID_TYPE = "ID_CARD";
    public static final String ID_TYPE_EXTRA = "id_type";
    private static final int INVALID_STATE = -1;
    private static final String NUMBER_COMPLETED_STEPS_EXTRA = "number_completed_steps";
    private static final int NUMBER_STEPS_IN_VERIFIED_ID = 6;
    public static final String PASSPORT_TYPE = "PASSPORT";
    private static final String RESERVATION_ID_EXTRA = "reservation_id";
    private static final String STATE_EXTRA = "state";
    private static final String SUPPORTED_COUNTRIES_EXTRA = "supported_countries";
    private static final String SUPPORTED_COUNTRIES_TYPES_EXTRA = "supported_countries_types";
    /* access modifiers changed from: private */
    public static final String TAG = OfficialIdActivity.class.getSimpleName();
    private static final String VERIFICATIONS_EXTRA = "verifications";
    private CircleBadgeView mCircleBadgeView;
    private LinearLayout mConfirmationButtons;
    /* access modifiers changed from: private */
    public ProgressDialogFragment mDialogFragment;
    private String mIdBackUriString;
    private String mIdFrontUriString;
    private String mOfficialIdCountryCode;
    /* access modifiers changed from: private */
    public String mOfficialIdType;
    private boolean mResumed;
    /* access modifiers changed from: private */
    public String mScanReferenceId;
    private OfficialIdState mState;
    /* access modifiers changed from: private */
    public ArrayList<String> mSupportedCountries;
    /* access modifiers changed from: private */
    public HashMap<String, List<Identification>> mTypesForSupportedCountries;
    private VerificationRequirements mVerificationRequirements;
    private long reservationId;

    private enum OfficialIdState {
        COUNTRY,
        ID_TYPE,
        FRONT_PHOTO,
        FRONT_CONFIRM,
        BACK_PHOTO,
        BACK_CONFIRM,
        UPLOAD,
        ERROR;

        public OfficialIdState getPrevious() {
            return ordinal() == 0 ? COUNTRY : values()[ordinal() - 1];
        }
    }

    public static Intent intentForVerifiedId(Context context, int numberCompletedSteps, long reservationId2, VerificationRequirements verifications) {
        Intent intent = new Intent(context, OfficialIdActivity.class);
        intent.putExtra(NUMBER_COMPLETED_STEPS_EXTRA, numberCompletedSteps);
        intent.putExtra("reservation_id", reservationId2);
        intent.putExtra("verifications", verifications);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mResumed = true;
        setContentView(C0880R.layout.activity_official_id);
        setSupportActionBar((Toolbar) findViewById(C0880R.C0882id.toolbar));
        this.mCircleBadgeView = (CircleBadgeView) findViewById(C0880R.C0882id.circle_badge_view);
        this.mCircleBadgeView.initializeAsInactiveBadge();
        this.mConfirmationButtons = (LinearLayout) findViewById(C0880R.C0882id.id_confirmation_buttons);
        setupActionBar(C0880R.string.verified_id_offline_title, new Object[0]);
        initProgressBar();
        if (savedInstanceState != null) {
            int stateOrdinal = savedInstanceState.getInt("state", -1);
            this.mState = stateOrdinal > -1 ? OfficialIdState.values()[stateOrdinal] : null;
            this.mOfficialIdCountryCode = savedInstanceState.getString("country_code");
            this.mOfficialIdType = savedInstanceState.getString(ID_TYPE_EXTRA);
            this.mIdFrontUriString = savedInstanceState.getString(FRONT_ID_URI_EXTRA);
            this.mIdBackUriString = savedInstanceState.getString(BACK_ID_URI_EXTRA);
            this.mSupportedCountries = savedInstanceState.getStringArrayList(SUPPORTED_COUNTRIES_EXTRA);
            this.mTypesForSupportedCountries = (HashMap) savedInstanceState.getSerializable(SUPPORTED_COUNTRIES_TYPES_EXTRA);
            this.reservationId = savedInstanceState.getLong("reservation_id");
            this.mVerificationRequirements = (VerificationRequirements) savedInstanceState.getParcelable("verifications");
            this.mDialogFragment = (ProgressDialogFragment) getSupportFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG);
        } else {
            this.reservationId = getIntent().getLongExtra("reservation_id", -1);
            this.mVerificationRequirements = (VerificationRequirements) getIntent().getParcelableExtra("verifications");
            this.mState = OfficialIdState.COUNTRY;
            setOfficialIdCountryCode(Locale.getDefault().getCountry());
            new OfficialIdSupportedCountriesRequest(new NonResubscribableRequestListener<OfficialIdSupportedCountriesResponse>() {
                public void onResponse(OfficialIdSupportedCountriesResponse response) {
                    OfficialIdActivity.this.mSupportedCountries = new ArrayList();
                    OfficialIdActivity.this.mTypesForSupportedCountries = new HashMap(response.countries.size());
                    for (Country country : response.countries) {
                        OfficialIdActivity.this.mSupportedCountries.add(country.code);
                        OfficialIdActivity.this.mTypesForSupportedCountries.put(country.code, country.identifications);
                    }
                    Fragment fragment = OfficialIdActivity.this.getSupportFragmentManager().findFragmentById(C0880R.C0882id.frag_official_id);
                    if (fragment instanceof OfficialIdCountryFragment) {
                        ((OfficialIdCountryFragment) fragment).enableInteraction(true);
                    }
                    Log.i(OfficialIdActivity.TAG, "Supported Countries: " + OfficialIdActivity.this.mSupportedCountries.toString());
                }

                public void onErrorResponse(AirRequestNetworkException error) {
                    Log.e(OfficialIdActivity.TAG, error.toString());
                }
            }).execute(this.requestManager);
            goToCountry();
        }
        setupConfirmationButtons();
        this.bus.register(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.bus.unregister(this);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("state", this.mState.ordinal());
        outState.putString(ID_TYPE_EXTRA, this.mOfficialIdType);
        outState.putString("country_code", this.mOfficialIdCountryCode);
        outState.putString(FRONT_ID_URI_EXTRA, this.mIdFrontUriString);
        outState.putString(BACK_ID_URI_EXTRA, this.mIdBackUriString);
        outState.putStringArrayList(SUPPORTED_COUNTRIES_EXTRA, this.mSupportedCountries);
        outState.putSerializable(SUPPORTED_COUNTRIES_TYPES_EXTRA, this.mTypesForSupportedCountries);
        outState.putLong("reservation_id", this.reservationId);
        outState.putParcelable("verifications", this.mVerificationRequirements);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mResumed = false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mResumed = true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0880R.C0883menu.verified_id_summary, menu);
        menu.findItem(C0880R.C0882id.verified_id_summary_item).setVisible(true);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.verified_id_summary_item) {
            return super.onOptionsItemSelected(item);
        }
        showChecklist();
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
        VerifiedIdAnalytics.trackBackPressed(getVerifiedIdAnalyticsStrap());
        this.mState = this.mState.getPrevious();
    }

    private void showFragment(Fragment f) {
        Fragment prev = getSupportFragmentManager().findFragmentById(C0880R.C0882id.frag_official_id);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(C0880R.anim.fragment_enter, C0880R.anim.fragment_exit, C0880R.anim.fragment_enter_pop, C0880R.anim.fragment_exit_pop);
        if (prev == null || (prev instanceof OfficialIdErrorFragment)) {
            getSupportFragmentManager().popBackStack((String) null, 1);
        } else {
            ft.addToBackStack(f.getTag());
        }
        ft.replace(C0880R.C0882id.frag_official_id, f);
        ft.commit();
    }

    public void goToCountry() {
        this.mCircleBadgeView.initializeAsInactiveBadge();
        this.mState = OfficialIdState.COUNTRY;
        showFragment(OfficialIdCountryFragment.newInstance());
    }

    public void goToType() {
        this.mState = OfficialIdState.ID_TYPE;
        showFragment(OfficialIdTypeFragment.newInstance());
    }

    public void goToPhotoSelectionFront() {
        this.mState = OfficialIdState.FRONT_PHOTO;
        showFragment(OfficialIdPhotoSelectionFragment.newInstance());
    }

    public void goToPhotoConfirmFront() {
        this.mState = OfficialIdState.FRONT_CONFIRM;
        showFragment(OfficialIdPhotoConfirmationFragment.newInstance());
    }

    public void goToPhotoSelectionBack() {
        if (this.mOfficialIdType.equals("PASSPORT")) {
            goToUpload();
            return;
        }
        this.mState = OfficialIdState.BACK_PHOTO;
        showFragment(OfficialIdPhotoSelectionFragment.newInstance());
    }

    public void goToPhotoConfirmBack() {
        this.mState = OfficialIdState.BACK_CONFIRM;
        showFragment(OfficialIdPhotoConfirmationFragment.newInstance());
    }

    public void goToUpload() {
        this.mState = OfficialIdState.UPLOAD;
        uploadId();
    }

    public void goToError() {
        this.mState = OfficialIdState.ERROR;
        if (!isFinishing()) {
            showFragment(OfficialIdErrorFragment.newInstance());
        }
    }

    public void doneWithUpload() {
        if (this.mDialogFragment != null) {
            this.mDialogFragment.dismiss();
        }
        Intent resultData = new Intent();
        resultData.putExtra(ID_TYPE_EXTRA, getOfficialIdTypeDisplayedString());
        setResult(-1, resultData);
        finish();
    }

    private void uploadId() {
        uploadImages(new File(this.mIdFrontUriString), !TextUtils.isEmpty(this.mIdBackUriString) ? new File(this.mIdBackUriString) : null);
    }

    private void uploadImages(File frontIdImage, File backIdImage) {
        new OfficialIdUploadRequest(frontIdImage, backIdImage, this.mOfficialIdCountryCode, this.mOfficialIdType, new NonResubscribableRequestListener<OfficialIdUploadResponse>() {
            public void onResponse(OfficialIdUploadResponse response) {
                Log.i(OfficialIdActivity.TAG, "Successful upload: " + OfficialIdActivity.this.mOfficialIdType);
                if (response.isSuccess()) {
                    OfficialIdActivity.this.mScanReferenceId = response.scanReference;
                    OfficialIdActivity.this.verifyUploadedIdStatus();
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                Log.e(OfficialIdActivity.TAG, "Did not upload successfully");
                OfficialIdActivity.this.mDialogFragment.dismiss();
                OfficialIdActivity.this.displayError();
                NetworkUtil.toastGenericNetworkError(OfficialIdActivity.this);
            }
        }).execute(this.requestManager);
        VerifiedIdAnalytics.trackOfflineUploadPhotoView(getVerifiedIdAnalyticsStrap());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        this.mDialogFragment = ProgressDialogFragment.newInstance(getString(C0880R.string.verified_id_offline_verifying_id_type, new Object[]{getOfficialIdTypeDisplayedString().toLowerCase(Locale.getDefault())}), null);
        this.mDialogFragment.setProgressDialogListener(this);
        this.mDialogFragment.show(ft, DIALOG_FRAGMENT_TAG);
    }

    public ArrayList<String> getSupportedCountries() {
        return this.mSupportedCountries;
    }

    public HashMap<String, List<Identification>> getTypesForSupportedCountries() {
        return this.mTypesForSupportedCountries;
    }

    public String getOfficialIdCountryCode() {
        return this.mOfficialIdCountryCode;
    }

    public void setOfficialIdCountryCode(String officialIdCountryCode) {
        this.mOfficialIdCountryCode = officialIdCountryCode;
    }

    public String getOfficialIdType() {
        return this.mOfficialIdType;
    }

    public void setOfficialIdType(String officialIdType) {
        this.mOfficialIdType = officialIdType;
    }

    public String getOfficialIdTypeDisplayedString() {
        String str = this.mOfficialIdType;
        char c = 65535;
        switch (str.hashCode()) {
            case -1895130188:
                if (str.equals(ID_TYPE)) {
                    c = 2;
                    break;
                }
                break;
            case -1808062583:
                if (str.equals(DRIVERS_LICENSE_TYPE)) {
                    c = 1;
                    break;
                }
                break;
            case 1999404050:
                if (str.equals("PASSPORT")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return getString(C0880R.string.verified_id_offline_passport);
            case 1:
                return getString(C0880R.string.verified_id_offline_drivers_license);
            case 2:
                return getString(C0880R.string.verified_id_offline_id_card);
            default:
                throw new IllegalStateException("Need valid current Id Type: " + this.mOfficialIdType);
        }
    }

    public String getIdFrontUriString() {
        return this.mIdFrontUriString;
    }

    public void setIdFrontUriString(String idFrontUriString) {
        this.mIdFrontUriString = idFrontUriString;
    }

    public String getIdBackUriString() {
        return this.mIdBackUriString;
    }

    public void setIdBackUriString(String idBackUriString) {
        this.mIdBackUriString = idBackUriString;
    }

    private void initProgressBar() {
        StepProgressBar progressBar = (StepProgressBar) findViewById(C0880R.C0882id.step_progress_bar);
        progressBar.setVisibility(0);
        progressBar.setCaption(C0880R.string.verified_id_spb_scan_official_id);
        progressBar.initializeView(6, getIntent().getIntExtra(NUMBER_COMPLETED_STEPS_EXTRA, 0));
    }

    public void onProgressCompleted() {
        if (this.mDialogFragment != null) {
            this.mDialogFragment.dismiss();
        }
        Intent resultData = new Intent();
        resultData.putExtra(ID_TYPE_EXTRA, getOfficialIdTypeDisplayedString());
        setResult(-1, resultData);
        finish();
    }

    public void onProgressCancelled() {
    }

    public boolean isCapturingFrontId() {
        return this.mState == OfficialIdState.FRONT_PHOTO || this.mState == OfficialIdState.FRONT_CONFIRM;
    }

    public String getReservationId() {
        if (this.reservationId != -1) {
            return String.valueOf(this.reservationId);
        }
        return null;
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", getReservationId());
    }

    /* access modifiers changed from: private */
    public void verifyUploadedIdStatus() {
        startService(OfficialIdIntentService.intentForOfficialId(this, this.mScanReferenceId));
    }

    @Subscribe
    public void onVerfifiedUploadedIDStatus(OfficialIDStatusEvent event) {
        if (this.mResumed) {
            if (this.mDialogFragment != null) {
                this.mDialogFragment.dismiss();
            }
            if (event.isSuccess()) {
                doneWithUpload();
            } else {
                displayError();
            }
        }
    }

    /* access modifiers changed from: private */
    public void displayError() {
        this.mCircleBadgeView.animateActivation(false);
        goToError();
    }

    public void showChecklist() {
        VerifiedIdDialogSummaryFragment.newInstance(this.mVerificationRequirements).show(getSupportFragmentManager(), (String) null);
    }

    public void showConfirmationButtons(boolean show) {
        this.mConfirmationButtons.setVisibility(show ? 0 : 4);
    }

    private void setupConfirmationButtons() {
        ((Button) findViewById(C0880R.C0882id.official_id_back_button)).setOnClickListener(OfficialIdActivity$$Lambda$1.lambdaFactory$(this));
        ((Button) findViewById(C0880R.C0882id.official_id_looks_good_button)).setOnClickListener(OfficialIdActivity$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupConfirmationButtons$0(OfficialIdActivity officialIdActivity, View view) {
        if (officialIdActivity.isCapturingFrontId()) {
            VerifiedIdAnalytics.trackOfflinePhotoFrontConfirmChangePhoto(officialIdActivity.getVerifiedIdAnalyticsStrap());
            officialIdActivity.setIdFrontUriString(null);
        } else {
            VerifiedIdAnalytics.trackOfflinePhotoBackConfirmChangePhoto(officialIdActivity.getVerifiedIdAnalyticsStrap());
            officialIdActivity.setIdBackUriString(null);
        }
        officialIdActivity.onBackPressed();
    }

    static /* synthetic */ void lambda$setupConfirmationButtons$1(OfficialIdActivity officialIdActivity, View view) {
        if (officialIdActivity.isCapturingFrontId()) {
            VerifiedIdAnalytics.trackOfflinePhotoFrontConfirmApprovePhoto(officialIdActivity.getVerifiedIdAnalyticsStrap());
            officialIdActivity.goToPhotoSelectionBack();
            return;
        }
        VerifiedIdAnalytics.trackOfflinePhotoBackConfirmApprovePhoto(officialIdActivity.getVerifiedIdAnalyticsStrap());
        officialIdActivity.goToUpload();
    }
}
