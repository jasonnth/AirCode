package com.airbnb.android.profile_completion.edit_about_me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.requests.EditProfileRequest;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.profile_completion.C7646R;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.utils.SnackbarWrapper;

public class EditAboutMeFragment extends AirFragment {
    @BindView
    AirEditTextView aboutMeEditText;
    public final NonResubscribableRequestListener<UserResponse> editAboutMeListener = new NonResubscribableRequestListener<UserResponse>() {
        public void onResponse(UserResponse data) {
            EditAboutMeFragment.this.aboutMeEditText.setEnabled(true);
            EditAboutMeFragment.this.getActivity().finish();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            EditAboutMeFragment.this.aboutMeEditText.setEnabled(true);
            new SnackbarWrapper().view(EditAboutMeFragment.this.getView()).duration(-1).body(EditAboutMeFragment.this.getString(C7646R.string.edit_profile_failed, EditAboutMeFragment.this.getString(ProfileSection.About.getTitleId()))).buildAndShow();
        }
    };
    @BindView
    AirToolbar toolbar;

    public static EditAboutMeFragment newInstance() {
        return new EditAboutMeFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7646R.layout.fragment_edit_about_me, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        getAirActivity().setOnBackPressedListener(EditAboutMeFragment$$Lambda$1.lambdaFactory$(this));
        getAirActivity().setOnHomePressedListener(EditAboutMeFragment$$Lambda$4.lambdaFactory$(this));
        dismissKeyboard();
        return view;
    }

    /* access modifiers changed from: private */
    public boolean dismissKeyboard() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        return false;
    }

    public void save() {
        this.aboutMeEditText.setEnabled(false);
        new EditProfileRequest(ProfileSection.About, this.aboutMeEditText.getText().toString(), (BaseRequestListener<UserResponse>) this.editAboutMeListener).execute(this.requestManager);
        dismissKeyboard();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C7646R.C7649menu.fragment_edit_about_me, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7646R.C7648id.menu_save) {
            return super.onOptionsItemSelected(item);
        }
        save();
        return true;
    }
}
