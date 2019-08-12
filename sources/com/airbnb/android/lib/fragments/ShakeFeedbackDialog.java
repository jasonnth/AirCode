package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import com.airbnb.android.apprater.AppRaterAnalytics;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.ShakeFeedbackDialogIntents;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListenerImpl;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.internal.bugreporter.DebugDumps;
import com.airbnb.android.internal.bugreporter.DebugEmailUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.facebook.internal.ServerProtocol;
import com.google.common.collect.Lists;
import java.util.List;

public class ShakeFeedbackDialog extends ZenDialog {
    private static final String AIREVENT_SHAKE_FEEDBACK = "shake_feedback";
    private static final String EMAIL_SUBJECT = "Android app Feedback";
    private static final String KEY_PROMPT_DISABLE_SHAKE_FEEDBACK = "prompt_disable_shake_feedback";
    private static final int MINIMUM_FEEDBACK_TITLE_LENGTH = 4;
    private static final int REQUEST_CODE_SEND_FEEDBACK = 1201;
    private static final int REQ_CODE_CANCEL_DISABLE = 1202;
    private static final int REQ_CODE_DISABLE = 1203;
    @BindView
    EditText description;
    private String emailRecipient;
    @BindView
    Spinner feedbackType;
    @BindView
    TextView positiveButton;
    AirbnbPreferences preferences;
    private SharedPreferences sharedPreferences;
    SharedPrefsHelper sharedPrefsHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        this.emailRecipient = getArguments().getString(ShakeFeedbackDialogIntents.KEY_EMAIL);
        this.sharedPreferences = this.preferences.getGlobalSharedPreferences();
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("page", AIREVENT_SHAKE_FEEDBACK).mo11639kv("action", ServerProtocol.DIALOG_PARAM_DISPLAY));
        this.positiveButton.setEnabled(false);
        return rootView;
    }

    @OnTextChanged
    public void onDescriptionChanged() {
        onViewChanged();
    }

    @OnItemSelected
    public void onFeedbackTypeChanged() {
        onViewChanged();
    }

    private void onViewChanged() {
        boolean valid = this.feedbackType.getSelectedItemPosition() != -1 && this.description.getText().toString().length() > 4;
        this.positiveButton.setEnabled(valid);
        this.positiveButton.setTextColor(ContextCompat.getColor(getContext(), valid ? C0880R.color.c_rausch : C0880R.color.c_foggy));
    }

    private void sendFeedback(String title, String description2) {
        List<String> paths;
        String path = DebugDumps.createLogcatFile(getContext());
        if (path != null) {
            paths = Lists.newArrayList((E[]) new String[]{path});
        } else {
            paths = Lists.newArrayList();
        }
        getActivity().startActivityForResult(DebugEmailUtil.createEmailIntent(getContext(), this.emailRecipient, EMAIL_SUBJECT, title, description2, this.sharedPrefsHelper.isGuestMode(), paths), REQUEST_CODE_SEND_FEEDBACK);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == REQ_CODE_DISABLE) {
            this.sharedPreferences.edit().putBoolean(ShakeFeedbackSensorListenerImpl.KEY_SHAKE_FEEDBACK, false).apply();
        }
    }

    /* access modifiers changed from: protected */
    public void clickLeftButton(int requestCodeLeft) {
        if (shouldPromptDisableShake()) {
            promptForDisableShakeFeedback();
            setPromptDisableShake(false);
        } else if (!hasPromptDisableShakeKey()) {
            setPromptDisableShake(true);
        }
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("page", AIREVENT_SHAKE_FEEDBACK).mo11639kv("action", AppRaterAnalytics.DISMISS));
    }

    private void promptForDisableShakeFeedback() {
        ZenDialog.builder().withTitle(C0880R.string.disable_shake_feedback_title).withBodyText(C0880R.string.disable_shake_feedback_message).withDualButton(17039360, (int) REQ_CODE_CANCEL_DISABLE, C0880R.string.disable_shake_feedback_disable, (int) REQ_CODE_DISABLE, (Fragment) this).create().show(getFragmentManager(), "disable_dialog");
    }

    private boolean shouldPromptDisableShake() {
        return this.sharedPreferences.getBoolean(KEY_PROMPT_DISABLE_SHAKE_FEEDBACK, false);
    }

    private boolean hasPromptDisableShakeKey() {
        return this.sharedPreferences.contains(KEY_PROMPT_DISABLE_SHAKE_FEEDBACK);
    }

    private void setPromptDisableShake(boolean enabled) {
        this.sharedPreferences.edit().putBoolean(KEY_PROMPT_DISABLE_SHAKE_FEEDBACK, enabled).apply();
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("page", AIREVENT_SHAKE_FEEDBACK).mo11639kv("action", "send_feedback"));
        sendFeedback((String) this.feedbackType.getSelectedItem(), this.description.getText().toString());
    }
}
