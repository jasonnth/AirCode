package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.events.ProfileUpdatedEvent;
import com.airbnb.android.core.interfaces.EditProfileInterface;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.EditProfileRequest;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.lib.fragments.ChinaResidencyDialog;
import com.airbnb.android.lib.fragments.EditProfileDualTextFieldsFragment;
import com.airbnb.android.lib.fragments.EditProfileFragment;
import com.airbnb.android.lib.fragments.EditProfileTextFieldFragment;
import com.airbnb.android.lib.fragments.ProfilePhoneNumbersFragment;
import com.airbnb.android.lib.utils.UserProfileUtils;
import com.airbnb.android.utils.AirbnbConstants;
import p032rx.Observer;

public class EditProfileActivity extends SolitAirActivity implements EditProfileInterface {
    private static final String TAG = EditProfileActivity.class.getSimpleName();
    @BindView
    Toolbar activityToolbar;
    private OnBackListener mOnBackListener;
    final RequestListener<UserResponse> userRequestListenerForProfile = new C0699RL().onResponse(EditProfileActivity$$Lambda$1.lambdaFactory$(this)).onError(EditProfileActivity$$Lambda$2.lambdaFactory$(this)).build();

    public static Intent intentForDefault(Context context) {
        return new Intent(context, EditProfileActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            EditProfileAnalytics.trackAction("impression", EditProfileAnalytics.EDIT_PROFILE, null);
            showLoader(true);
            UserRequest.newRequestForMobileProfiles(this.accountManager.getCurrentUser().getId()).withListener((Observer) this.userRequestListenerForProfile).execute(this.requestManager);
        }
    }

    private void showFragment(Fragment f) {
        super.showFragment(f, true);
        if (f instanceof OnBackListener) {
            setOnBackListener((OnBackListener) f);
        }
    }

    static /* synthetic */ void lambda$new$0(EditProfileActivity editProfileActivity, UserResponse response) {
        editProfileActivity.showLoader(false);
        editProfileActivity.showFragment(EditProfileFragment.newInstance(response.user), false);
    }

    static /* synthetic */ void lambda$new$1(EditProfileActivity editProfileActivity, AirRequestNetworkException e) {
        editProfileActivity.showLoader(false);
        NetworkUtil.toastGenericNetworkError(editProfileActivity);
    }

    private void setOnBackListener(OnBackListener f) {
        this.mOnBackListener = f;
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void onBackPressed() {
        if (this.mOnBackListener == null || !this.mOnBackListener.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void onProfileSectionSelected(ProfileSection section) {
        EditProfileAnalytics.trackTextSectionAction(section, "click", null);
        showFragment(EditProfileTextFieldFragment.newInstance(section, UserProfileUtils.getValueForEdit(this.accountManager.getCurrentUser(), section)));
    }

    public void onNameSectionSelected() {
        User currentUser = this.accountManager.getCurrentUser();
        showFragment(EditProfileDualTextFieldsFragment.newInstance(ProfileSection.Name, currentUser.getFirstName(), currentUser.getLastName()));
    }

    public void onPhoneNumbersSelected() {
        showFragment(ProfilePhoneNumbersFragment.newInstance());
    }

    public void unregisterOnBackListener() {
        this.mOnBackListener = null;
    }

    public void doneEdit(ProfileSection section, Object newValue) {
        updateUserProfile(section, (String) newValue);
        getSupportFragmentManager().popBackStack();
    }

    public void doneEditName(String firstName, String lastName) {
        new EditProfileRequest(firstName, lastName, (BaseRequestListener<UserResponse>) new NonResubscribableRequestListener<UserResponse>() {
            public void onResponse(UserResponse response) {
                if (UserProfileUtils.updateCurrentUser(EditProfileActivity.this.accountManager.getCurrentUser(), response)) {
                    EditProfileActivity.this.onProfileUpdated(ProfileSection.Name);
                }
                EditProfileActivity.this.alertUpdateCountryToChinaIfNecessary(response);
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                Toast.makeText(EditProfileActivity.this, EditProfileActivity.this.getString(C0880R.string.edit_profile_failed, new Object[]{EditProfileActivity.this.getString(ProfileSection.Name.getTitleId())}), 0).show();
            }
        }).execute(NetworkUtil.singleFireExecutor());
        getSupportFragmentManager().popBackStack();
    }

    private void updateUserProfile(final ProfileSection section, String newValue) {
        new EditProfileRequest(section, newValue, (BaseRequestListener<UserResponse>) new NonResubscribableRequestListener<UserResponse>() {
            public void onResponse(UserResponse response) {
                if (UserProfileUtils.updateCurrentUser(EditProfileActivity.this.accountManager.getCurrentUser(), response)) {
                    EditProfileActivity.this.onProfileUpdated(section);
                }
                EditProfileActivity.this.alertUpdateCountryToChinaIfNecessary(response);
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                Toast.makeText(EditProfileActivity.this, EditProfileActivity.this.getString(C0880R.string.edit_profile_failed, new Object[]{EditProfileActivity.this.getString(section.getTitleId())}), 0).show();
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void alertUpdateCountryToChinaIfNecessary(UserResponse response) {
        User user = response.user;
        if (AirbnbConstants.COUNTRY_CODE_CHINA.equalsIgnoreCase(user.getCountry()) && !AirbnbConstants.COUNTRY_CODE_CHINA.equalsIgnoreCase(user.getPreviousCountry())) {
            ChinaResidencyDialog.newInstance().show(getSupportFragmentManager(), (String) null);
        }
    }

    /* access modifiers changed from: private */
    public void onProfileUpdated(ProfileSection section) {
        Fragment f = getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
        if (!(f == null || f.getActivity() == null || !(f instanceof EditProfileFragment))) {
            ((EditProfileFragment) f).onProfileUpdated(section);
        }
        this.bus.post(new ProfileUpdatedEvent(section));
    }

    public Toolbar getActivityToolbar() {
        return this.activityToolbar;
    }
}
