package com.appboy.p028ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.appboy.Appboy;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;

/* renamed from: com.appboy.ui.AppboyFeedbackFragment */
public class AppboyFeedbackFragment extends Fragment {
    private Button mCancelButton;
    private OnClickListener mCancelListener;
    /* access modifiers changed from: private */
    public EditText mEmailEditText;
    /* access modifiers changed from: private */
    public boolean mErrorMessageShown;
    /* access modifiers changed from: private */
    public FeedbackFinishedListener mFeedbackFinishedListener;
    /* access modifiers changed from: private */
    public CheckBox mIsBugCheckBox;
    /* access modifiers changed from: private */
    public EditText mMessageEditText;
    private int mOriginalSoftInputMode;
    private Button mSendButton;
    private TextWatcher mSendButtonWatcher;
    private OnClickListener mSendListener;

    /* renamed from: com.appboy.ui.AppboyFeedbackFragment$FeedbackFinishedListener */
    public interface FeedbackFinishedListener {
        String beforeFeedbackSubmitted(String str);

        void onFeedbackFinished(FeedbackResult feedbackResult);
    }

    /* renamed from: com.appboy.ui.AppboyFeedbackFragment$FeedbackResult */
    public enum FeedbackResult {
        SENT,
        CANCELLED,
        ERROR
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mSendButtonWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence sequence, int start, int before, int count) {
            }

            public void afterTextChanged(Editable sequence) {
                if (AppboyFeedbackFragment.this.mErrorMessageShown) {
                    AppboyFeedbackFragment.this.ensureSendButton();
                }
            }
        };
        this.mCancelListener = new OnClickListener() {
            public void onClick(View view) {
                AppboyFeedbackFragment.this.hideSoftKeyboard();
                if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                    AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(FeedbackResult.CANCELLED);
                }
                AppboyFeedbackFragment.this.clearData();
            }
        };
        this.mSendListener = new OnClickListener() {
            public void onClick(View view) {
                if (AppboyFeedbackFragment.this.ensureSendButton()) {
                    AppboyFeedbackFragment.this.hideSoftKeyboard();
                    boolean isBug = AppboyFeedbackFragment.this.mIsBugCheckBox.isChecked();
                    String message = AppboyFeedbackFragment.this.mMessageEditText.getText().toString();
                    String email = AppboyFeedbackFragment.this.mEmailEditText.getText().toString();
                    if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                        message = AppboyFeedbackFragment.this.mFeedbackFinishedListener.beforeFeedbackSubmitted(message);
                    }
                    boolean result = Appboy.getInstance(AppboyFeedbackFragment.this.getActivity()).submitFeedback(email, message, isBug);
                    if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                        AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(result ? FeedbackResult.SENT : FeedbackResult.ERROR);
                    }
                    AppboyFeedbackFragment.this.clearData();
                    return;
                }
                AppboyFeedbackFragment.this.mErrorMessageShown = true;
            }
        };
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.com_appboy_feedback, container, false);
        this.mCancelButton = (Button) view.findViewById(R.id.com_appboy_feedback_cancel);
        this.mSendButton = (Button) view.findViewById(R.id.com_appboy_feedback_send);
        this.mIsBugCheckBox = (CheckBox) view.findViewById(R.id.com_appboy_feedback_is_bug);
        this.mMessageEditText = (EditText) view.findViewById(R.id.com_appboy_feedback_message);
        this.mEmailEditText = (EditText) view.findViewById(R.id.com_appboy_feedback_email);
        this.mMessageEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mCancelButton.setOnClickListener(this.mCancelListener);
        this.mSendButton.setOnClickListener(this.mSendListener);
        return view;
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getActivity()).logFeedbackDisplayed();
        Activity activity = getActivity();
        Window window = activity.getWindow();
        this.mOriginalSoftInputMode = window.getAttributes().softInputMode;
        window.setSoftInputMode(16);
        Appboy.getInstance(activity).logFeedbackDisplayed();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMessageEditText.removeTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.removeTextChangedListener(this.mSendButtonWatcher);
    }

    private boolean validatedMessage() {
        boolean validMessage = this.mMessageEditText.getText() != null && !StringUtils.isNullOrBlank(this.mMessageEditText.getText().toString());
        if (validMessage) {
            this.mMessageEditText.setError(null);
        } else {
            this.mMessageEditText.setError(getResources().getString(R.string.com_appboy_feedback_form_invalid_message));
        }
        return validMessage;
    }

    private boolean validatedEmail() {
        boolean validEmail;
        boolean blankEmail;
        if (this.mEmailEditText.getText() == null || StringUtils.isNullOrBlank(this.mEmailEditText.getText().toString()) || !ValidationUtils.isValidEmailAddress(this.mEmailEditText.getText().toString())) {
            validEmail = false;
        } else {
            validEmail = true;
        }
        if (this.mEmailEditText.getText() == null || !StringUtils.isNullOrBlank(this.mEmailEditText.getText().toString())) {
            blankEmail = false;
        } else {
            blankEmail = true;
        }
        if (validEmail) {
            this.mEmailEditText.setError(null);
        } else if (blankEmail) {
            this.mEmailEditText.setError(getResources().getString(R.string.com_appboy_feedback_form_empty_email));
        } else {
            this.mEmailEditText.setError(getResources().getString(R.string.com_appboy_feedback_form_invalid_email));
        }
        return validEmail;
    }

    /* access modifiers changed from: private */
    public boolean ensureSendButton() {
        return validatedMessage() & validatedEmail();
    }

    /* access modifiers changed from: private */
    public void clearData() {
        this.mEmailEditText.setText("");
        this.mMessageEditText.setText("");
        this.mIsBugCheckBox.setChecked(false);
        this.mErrorMessageShown = false;
        this.mEmailEditText.setError(null);
        this.mMessageEditText.setError(null);
    }

    /* access modifiers changed from: private */
    public void hideSoftKeyboard() {
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(this.mOriginalSoftInputMode);
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
