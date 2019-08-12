package com.airbnb.android.core.fragments;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.AnimatedLoadingOverlay;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.TextUtil;

public class ProgressDialogFragment extends AirDialogFragment {
    private static final String ARG_DUAL_BUTTON_QUESTION = "dual_button_question";
    private static final String ARG_FINISHED_COMPLETE_IMAGE = "finished_image";
    private static final String ARG_FINISHED_DELAY = "finished_delay";
    private static final String ARG_FINISHED_SUBTITLE = "finished_subtitle";
    private static final String ARG_FINISHED_TITLE = "finished_title";
    private static final String ARG_IN_FINISHED_STATE = "in_finished_state";
    private static final String ARG_SEND_PROGRESS_COMPLETE = "progress_complete";
    private static final String ARG_SUBTITLE = "subtitle";
    private static final String ARG_TITLE = "title";
    public static final String EXTRA_CLICK_DISMISS = "click_cancel";
    public static final String EXTRA_CLICK_NEGATIVE_BUTTON = "click_negative_button";
    public static final String EXTRA_CLICK_POSITIVE_BUTTON = "click_positive_button";
    public static final String EXTRA_ON_PROGRESS_COMPLETE = "on_progress_complete";
    public static final int NO_AUTO_FINISHED_DELAY = -1;
    public static final int REQUEST_CODE_PROGRESS_DIALOG = 401;
    private ImageView mCompleteImage;
    private TextView mDescription;
    private ProgressDialogListener mDialogListener;
    @BindView
    LinearLayout mDualButtonsContainer;
    private int mFinishedCompleteImage;
    private int mFinishedDelayMillis;
    private String mFinishedSubtitle;
    private String mFinishedTitle;
    private Handler mHandler;
    private boolean mInFinishedState;
    @BindView
    TextView mNegativeButton;
    @BindView
    TextView mPositiveButton;
    @BindView
    LinearLayout mProgressDialogContainer;
    @BindView
    TextView mQuestion;
    private boolean mSendProgressCompleteOnResume;
    private AnimatedLoadingOverlay mSmooth;
    private TextView mTitle;

    public interface ProgressDialogListener {
        void onProgressCancelled();

        void onProgressCompleted();
    }

    public static ProgressDialogFragment newInstance(Context context, int titleRes, int subtitleRes) {
        String title;
        String subtitle = null;
        if (titleRes > 0) {
            title = context.getString(titleRes);
        } else {
            title = null;
        }
        if (subtitleRes > 0) {
            subtitle = context.getString(subtitleRes);
        }
        return newInstance(title, subtitle);
    }

    public static ProgressDialogFragment newInstance(String title, String subtitle) {
        ProgressDialogFragment f = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString(ARG_SUBTITLE, subtitle);
        f.setArguments(args);
        return f;
    }

    public static ProgressDialogFragment newInstance(Context context, int titleRes, int subtitleRes, int dualButtonQuestionRes) {
        ProgressDialogFragment dialogFrag = newInstance(context, titleRes, subtitleRes);
        if (dualButtonQuestionRes > 0) {
            dialogFrag.getArguments().putString(ARG_DUAL_BUTTON_QUESTION, context.getString(dualButtonQuestionRes));
        }
        return dialogFrag;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(1, C0716R.C0719style.ProgressDialog);
        View view = LayoutInflater.from(getActivity()).inflate(C0716R.layout.progress_dialog_fragment, null);
        ButterKnife.bind(this, view);
        this.mSmooth = (AnimatedLoadingOverlay) view.findViewById(C0716R.C0718id.img_loading_indicator);
        this.mSmooth.playAnimation();
        this.mTitle = (TextView) view.findViewById(C0716R.C0718id.txt_title);
        this.mDescription = (TextView) view.findViewById(C0716R.C0718id.txt_description);
        this.mCompleteImage = (ImageView) view.findViewById(C0716R.C0718id.img_complete);
        updateText(this.mTitle, getArguments().getString("title"));
        updateText(this.mDescription, getArguments().getString(ARG_SUBTITLE));
        if (savedInstanceState != null) {
            this.mInFinishedState = savedInstanceState.getBoolean(ARG_IN_FINISHED_STATE, false);
            if (this.mInFinishedState) {
                this.mFinishedTitle = savedInstanceState.getString(ARG_FINISHED_TITLE);
                this.mFinishedSubtitle = savedInstanceState.getString(ARG_FINISHED_SUBTITLE);
                this.mFinishedCompleteImage = savedInstanceState.getInt(ARG_FINISHED_COMPLETE_IMAGE, -1);
                this.mFinishedDelayMillis = savedInstanceState.getInt(ARG_FINISHED_DELAY, -1);
                showCompleteState();
            }
            this.mSendProgressCompleteOnResume = savedInstanceState.getBoolean(ARG_SEND_PROGRESS_COMPLETE, false);
        }
        return new Builder(getActivity()).setView(view).create();
    }

    public ProgressDialogFragment setProgressDialogListener(ProgressDialogListener listener) {
        this.mDialogListener = listener;
        return this;
    }

    public void onProgressComplete(String title, String subtitle, int completeImage, int delayMillis) {
        if (getActivity() != null) {
            this.mInFinishedState = true;
            this.mFinishedTitle = title;
            this.mFinishedSubtitle = subtitle;
            this.mFinishedCompleteImage = completeImage;
            this.mFinishedDelayMillis = delayMillis;
            showCompleteState();
        }
    }

    public boolean isProgressComplete() {
        return this.mInFinishedState;
    }

    private void setupDualButtonsIfNeeded() {
        String questionText = getArguments() != null ? getArguments().getString(ARG_DUAL_BUTTON_QUESTION) : null;
        if (!TextUtils.isEmpty(questionText)) {
            this.mQuestion.setText(TextUtil.makeBold(questionText, questionText));
            this.mQuestion.setVisibility(0);
            this.mProgressDialogContainer.setPadding(0, (int) getResources().getDimension(C0716R.dimen.progress_dialog_padding), 0, 0);
            this.mDualButtonsContainer.setVisibility(0);
            this.mPositiveButton.setText(C0716R.string.yes);
            this.mNegativeButton.setText(C0716R.string.cancel);
            OnClickListener onClick = ProgressDialogFragment$$Lambda$1.lambdaFactory$(this);
            this.mPositiveButton.setOnClickListener(onClick);
            this.mNegativeButton.setOnClickListener(onClick);
        }
    }

    static /* synthetic */ void lambda$setupDualButtonsIfNeeded$0(ProgressDialogFragment progressDialogFragment, View v) {
        Intent intent = new Intent();
        progressDialogFragment.dismiss();
        if (v.getId() == C0716R.C0718id.positive_button) {
            intent.putExtra(EXTRA_CLICK_POSITIVE_BUTTON, true);
            progressDialogFragment.getTargetFragment().onActivityResult(401, -1, intent);
        } else if (v.getId() == C0716R.C0718id.negative_button) {
            intent.putExtra(EXTRA_CLICK_NEGATIVE_BUTTON, true);
            progressDialogFragment.getTargetFragment().onActivityResult(401, -1, intent);
        }
    }

    public void onProgressComplete(int titleRes, int subtitleRes, int completeImage, int delayMillis) {
        onProgressComplete(getString(titleRes), subtitleRes != 0 ? getString(subtitleRes) : "", completeImage, delayMillis);
    }

    private void showCompleteState() {
        updateText(this.mTitle, this.mFinishedTitle);
        updateText(this.mDescription, this.mFinishedSubtitle);
        showCompleteImage(this.mFinishedCompleteImage);
        setupDualButtonsIfNeeded();
        if (this.mFinishedDelayMillis != -1) {
            this.mHandler = new Handler();
            this.mHandler.postDelayed(ProgressDialogFragment$$Lambda$2.lambdaFactory$(this), (long) this.mFinishedDelayMillis);
            return;
        }
        sendOnProgressComplete(false);
    }

    public void onPause() {
        super.onPause();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mSendProgressCompleteOnResume = true;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mSendProgressCompleteOnResume) {
            sendOnProgressComplete(true);
            this.mSendProgressCompleteOnResume = false;
        }
    }

    /* access modifiers changed from: private */
    public void sendOnProgressComplete(boolean shouldDismiss) {
        if (shouldDismiss) {
            dismiss();
        }
        if (this.mDialogListener != null) {
            this.mDialogListener.onProgressCompleted();
        }
        if (getTargetFragment() != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_ON_PROGRESS_COMPLETE, true);
            getTargetFragment().onActivityResult(401, -1, intent);
        }
    }

    private void showCompleteImage(int completeImage) {
        this.mSmooth.setVisibility(8);
        this.mCompleteImage.setVisibility(0);
        this.mCompleteImage.setImageDrawable(ColorizedDrawable.forIdWithColor(getActivity(), completeImage, C0716R.color.c_green));
    }

    public void updateDescription(String description) {
        this.mDescription.setVisibility(TextUtils.isEmpty(description) ? 8 : 0);
        this.mDescription.setText(description);
    }

    private static void updateText(TextView textView, String text) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(text);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ARG_IN_FINISHED_STATE, this.mInFinishedState);
        if (this.mInFinishedState) {
            outState.putString(ARG_FINISHED_TITLE, this.mFinishedTitle);
            outState.putString(ARG_FINISHED_SUBTITLE, this.mFinishedSubtitle);
            outState.putInt(ARG_FINISHED_COMPLETE_IMAGE, this.mFinishedCompleteImage);
            outState.putInt(ARG_FINISHED_DELAY, this.mFinishedDelayMillis);
        }
        outState.putBoolean(ARG_SEND_PROGRESS_COMPLETE, this.mSendProgressCompleteOnResume);
    }

    public void cancel() {
        if (getDialog() != null) {
            getDialog().cancel();
        }
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (this.mDialogListener != null) {
            this.mDialogListener.onProgressCancelled();
        }
        if (getTargetFragment() != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_CLICK_DISMISS, true);
            getTargetFragment().onActivityResult(401, -1, intent);
        }
    }

    public void dismiss() {
        if (isResumed()) {
            super.dismiss();
        }
    }
}
