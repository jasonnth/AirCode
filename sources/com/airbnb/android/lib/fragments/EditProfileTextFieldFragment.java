package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.utils.TextUtil;
import com.jumio.p311nv.data.NVStrings;

public class EditProfileTextFieldFragment extends BaseEditProfileSectionFragment {
    private static final String ARG_OLD_VALUE = "old_value";
    private static final String ARG_SECTION = "profile_section";
    private static final String CONFIRM_SAVE_DIALOG = "confirm_save_dialog";
    private static final int REQUEST_CODE_CONFIRM_CANCEL = 3002;
    private static final int REQUEST_CODE_CONFIRM_SAVE = 3001;
    private static final int TOGGLE_KEYBOARD_DELAY = 500;
    @BindView
    TextView mDescriptionText;
    @BindView
    EditText mEditableField;
    private String mOldValue;
    private final Runnable mOpenKeyboardRunnable = EditProfileTextFieldFragment$$Lambda$1.lambdaFactory$(this);
    protected ProfileSection mSection;
    @BindView
    TextView mTitleText;
    @BindView
    View mTooltip;

    public static EditProfileTextFieldFragment newInstance(ProfileSection section, String oldValue) {
        EditProfileTextFieldFragment fragment = new EditProfileTextFieldFragment();
        fragment.setArguments(getBundle(section, oldValue));
        return fragment;
    }

    protected static Bundle getBundle(ProfileSection section, String oldValue) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_SECTION, section);
        bundle.putString(ARG_OLD_VALUE, oldValue);
        return bundle;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOldValue = getArguments().getString(ARG_OLD_VALUE);
        this.mSection = (ProfileSection) getArguments().getParcelable(ARG_SECTION);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_text_edit_field, container, false);
        bindViews(view);
        this.mTooltip.setVisibility(8);
        this.mTitleText.setText(this.mSection.getTitleId());
        this.mEditableField.setText(this.mOldValue);
        if (this.mSection.hasHint()) {
            this.mEditableField.setHint(this.mSection.getHintId());
        }
        if (this.mSection == ProfileSection.Email) {
            this.mEditableField.setInputType(32);
        }
        if (this.mSection.hasDescription()) {
            this.mDescriptionText.setVisibility(0);
            this.mDescriptionText.setText(this.mSection.getDescriptionId());
        }
        return view;
    }

    public void onStart() {
        super.onStart();
        setActionBarEditModeEnabled(true);
    }

    public void onStop() {
        super.onStop();
        setActionBarEditModeEnabled(false);
    }

    public void onResume() {
        super.onResume();
        this.mEditableField.requestFocus();
        getView().postDelayed(this.mOpenKeyboardRunnable, 500);
    }

    public void onPause() {
        super.onPause();
        this.mCallback.unregisterOnBackListener();
        getView().removeCallbacks(this.mOpenKeyboardRunnable);
    }

    public boolean onBackPressed() {
        if (!valueModified()) {
            return false;
        }
        ZenDialog.builder().withTitle(this.mSection.getTitleId()).withBodyText(C0880R.string.ml_text_edit_update_body).withDualButton(C0880R.string.discard, 3002, C0880R.string.save, 3001, (Fragment) this).create().show(getFragmentManager(), CONFIRM_SAVE_DIALOG);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean valueModified() {
        return !(this.mOldValue == null && this.mEditableField.getText().toString().length() == 0) && !TextUtils.equals(this.mOldValue, this.mEditableField.getText().toString());
    }

    private void exit(boolean save) {
        if (getActivity() != null) {
            KeyboardUtils.dismissSoftKeyboard(getActivity(), this.mEditableField);
        }
        saveValueOrGoBack(save);
    }

    private void saveValueOrGoBack(boolean save) {
        if (save) {
            onSave();
            return;
        }
        EditProfileAnalytics.trackTextSectionAction(this.mSection, NVStrings.BACK, null);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        String newValue = this.mEditableField.getText().toString();
        EditProfileAnalytics.trackTextSectionAction(this.mSection, BaseAnalytics.UPDATE, Strap.make().mo11637kv("word_count", newValue.length()));
        this.mCallback.doneEdit(this.mSection, newValue);
    }

    private void setActionBarEditModeEnabled(boolean enabled) {
        ActionBar actionbar = getActionBar();
        if (enabled) {
            actionbar.setCustomView(C0880R.layout.editor_actionbar_layout);
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setDisplayShowCustomEnabled(true);
            actionbar.setDisplayShowTitleEnabled(false);
            actionbar.setDisplayShowHomeEnabled(false);
            actionbar.getCustomView().setOnClickListener(EditProfileTextFieldFragment$$Lambda$2.lambdaFactory$(this));
            return;
        }
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowCustomEnabled(false);
        actionbar.setDisplayShowTitleEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
    }

    static /* synthetic */ void lambda$setActionBarEditModeEnabled$1(EditProfileTextFieldFragment editProfileTextFieldFragment, View view) {
        if (editProfileTextFieldFragment.mSection != ProfileSection.Email) {
            editProfileTextFieldFragment.exit(true);
        } else if (TextUtil.isValidEmail(editProfileTextFieldFragment.mEditableField.getText())) {
            editProfileTextFieldFragment.exit(true);
        } else {
            Toast.makeText(editProfileTextFieldFragment.getActivity(), C0880R.string.edit_profile_invalid_email, 0).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 3001:
                exit(true);
                return;
            case 3002:
                exit(false);
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }
}
