package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.utils.Strap;

public class EditProfileDualTextFieldsFragment extends EditProfileTextFieldFragment {
    private static final String ARG_OLD_VALUE_TWO = "second_old_value";
    @BindView
    EditText mEditableFieldTwo;
    private String mOldValueTwo;
    @BindView
    TextView mTitleTextTwo;

    public static EditProfileDualTextFieldsFragment newInstance(ProfileSection section, String oldValueOne, String mOldValueTwo2) {
        EditProfileDualTextFieldsFragment fragment = new EditProfileDualTextFieldsFragment();
        Bundle bundle = getBundle(section, oldValueOne);
        bundle.putString(ARG_OLD_VALUE_TWO, mOldValueTwo2);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOldValueTwo = getArguments().getString(ARG_OLD_VALUE_TWO);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        this.mTitleText.setText(C0880R.string.edit_profile_first_name);
        this.mTitleTextTwo.setText(C0880R.string.edit_profile_last_name);
        this.mTitleTextTwo.setVisibility(0);
        this.mEditableFieldTwo.setVisibility(0);
        this.mEditableFieldTwo.setText(this.mOldValueTwo);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean valueModified() {
        boolean lastNameModified;
        boolean firstNameModified = super.valueModified();
        if (!(this.mOldValueTwo == null && this.mEditableFieldTwo.getText().toString().length() == 0) && !TextUtils.equals(this.mOldValueTwo, this.mEditableFieldTwo.getText().toString())) {
            lastNameModified = true;
        } else {
            lastNameModified = false;
        }
        if (firstNameModified || lastNameModified) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        String firstName = this.mEditableField.getText().toString();
        String lastName = this.mEditableFieldTwo.getText().toString();
        EditProfileAnalytics.trackTextSectionAction(this.mSection, BaseAnalytics.UPDATE, Strap.make().mo11637kv("first_name_word_count", firstName.length()).mo11637kv("last_name_word_count", lastName.length()));
        this.mCallback.doneEditName(firstName, lastName);
    }
}
