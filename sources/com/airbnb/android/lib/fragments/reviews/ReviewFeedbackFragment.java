package com.airbnb.android.lib.fragments.reviews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.events.ReviewUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.reviews.ReviewFeedbackActivity;
import com.airbnb.android.lib.activities.reviews.ReviewRatingsActivity;
import com.airbnb.android.lib.activities.reviews.ReviewSummaryActivity;
import com.airbnb.android.lib.analytics.ReviewsAnalytics;
import com.airbnb.android.lib.fragments.ReviewInfoDialogFragment;
import com.airbnb.android.lib.fragments.managelisting.TooltipDialogFragment;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.squareup.otto.Subscribe;

public class ReviewFeedbackFragment extends AirFragment {
    private static final int DIALOG_REQ_CONFIRM_QUIT = 9010;
    private static final String KEY_EDIT_FLAG = "edit";
    private static final String KEY_REVIEW = "review";
    private static final int MIN_CHARS_IN_REVIEW_FOR_PROMPT = 20;
    private boolean mDoubleBlindFtueSeen;
    private boolean mEditMode;
    private FeedbackField mFieldToEdit;
    private final OnGlobalLayoutListener mGlobalLayoutListener = ReviewFeedbackFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    TextView mGuestName;
    @BindView
    FrameLayout mHeader;
    @BindView
    AirImageView mHeaderBackground;
    @BindView
    HaloImageView mHeaderImage;
    private boolean mIsSoftKeyboardShown;
    @BindView
    TextView mListingName;
    @BindView
    Button mNextButton;
    @BindView
    TextView mPrivateFeedbackDescription;
    @BindView
    EditText mPrivateFeedbackEditText;
    @BindView
    EditText mPrivateFeedbackEditTextTwo;
    @BindView
    TextView mPrivateFeedbackTitle;
    @BindView
    TextView mPublicFeedbackDescription;
    @BindView
    EditText mPublicFeedbackEditText;
    @BindView
    TextView mPublicFeedbackTitle;
    @BindView
    TextView mReservationDates;
    private Review mReview;
    @BindView
    HaloImageView mRevieweeImage;
    @BindView
    HaloImageView mReviewerImage;
    private boolean mToolTipSeen;
    @BindView
    ImageView mTooltip;
    private final TextWatcher publicFeedbackTextWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            ReviewFeedbackFragment.this.mNextButton.setEnabled(!TextUtils.isEmpty(s.toString().trim()));
        }
    };

    private enum FeedbackField {
        Public,
        Private
    }

    public static ReviewFeedbackFragment newInstance(Review review) {
        ReviewFeedbackFragment f = new ReviewFeedbackFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        f.setArguments(bundle);
        return f;
    }

    public static ReviewFeedbackFragment newInstanceEditPublicFeedback(Review review) {
        ReviewFeedbackFragment f = newInstance(review);
        Bundle bundle = f.getArguments();
        bundle.putInt(KEY_EDIT_FLAG, FeedbackField.Public.ordinal());
        f.setArguments(bundle);
        return f;
    }

    public static ReviewFeedbackFragment newInstanceEditPrivateFeedback(Review review) {
        ReviewFeedbackFragment f = newInstance(review);
        Bundle bundle = f.getArguments();
        bundle.putInt(KEY_EDIT_FLAG, FeedbackField.Private.ordinal());
        f.setArguments(bundle);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_review_feedback, null);
        bindViews(view);
        this.mEditMode = getArguments().containsKey(KEY_EDIT_FLAG);
        if (this.mEditMode) {
            this.mFieldToEdit = FeedbackField.values()[getArguments().getInt(KEY_EDIT_FLAG)];
        } else {
            ReviewsAnalytics.trackFeedbackSection(this.mReview);
        }
        initializeViews();
        return view;
    }

    public void onResume() {
        super.onResume();
        loadFields();
        if (this.mEditMode) {
            EditText fieldToFocus = this.mFieldToEdit == FeedbackField.Public ? this.mPublicFeedbackEditText : this.mPrivateFeedbackEditText;
            fieldToFocus.requestFocus();
            fieldToFocus.setSelection(fieldToFocus.length());
        }
    }

    public void onPause() {
        super.onPause();
        saveFeedback();
    }

    private void onSoftKeyboardChanged(boolean shown) {
        if (shown != this.mIsSoftKeyboardShown) {
            this.mIsSoftKeyboardShown = shown;
            this.mHeader.setVisibility(shown ? 8 : 0);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBus.register(this);
        this.mToolTipSeen = this.mPreferences.getSharedPreferences().getBoolean(AirbnbConstants.PREFS_SEEN_REVIEW_TOOLTIP, false);
        this.mDoubleBlindFtueSeen = this.mPreferences.getSharedPreferences().getBoolean(AirbnbConstants.PREFS_SEEN_DOUBLE_BLIND_REVIEW_FTUE, false);
        this.mReview = (Review) getArguments().getParcelable("review");
    }

    public void onStart() {
        super.onStart();
        if (this.mReview.isSubmitted()) {
            getActivity().finish();
        }
        if (!this.mDoubleBlindFtueSeen) {
            showDoubleBlindFtue();
        } else {
            showTooltipIfNecessary();
        }
        if (!this.mDoubleBlindFtueSeen) {
            this.mDoubleBlindFtueSeen = true;
            Editor editor = this.mPreferences.getSharedPreferences().edit();
            editor.putBoolean(AirbnbConstants.PREFS_SEEN_REVIEW_TOOLTIP, true);
            editor.putBoolean(AirbnbConstants.PREFS_SEEN_DOUBLE_BLIND_REVIEW_FTUE, true);
            editor.apply();
        }
        getView().getViewTreeObserver().addOnGlobalLayoutListener(this.mGlobalLayoutListener);
    }

    public void onStop() {
        super.onStop();
        getView().getViewTreeObserver().removeOnGlobalLayoutListener(this.mGlobalLayoutListener);
    }

    static /* synthetic */ void lambda$new$0(ReviewFeedbackFragment reviewFeedbackFragment) {
        View rootView = reviewFeedbackFragment.getView();
        AppCompatActivity activity = reviewFeedbackFragment.getAppCompatActivity();
        if (activity != null && rootView != null) {
            reviewFeedbackFragment.onSoftKeyboardChanged(KeyboardUtils.isKeyboardUp(activity, rootView));
        }
    }

    public void onDestroyView() {
        this.mPublicFeedbackEditText.removeTextChangedListener(this.publicFeedbackTextWatcher);
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    private void loadFields() {
        User reviewee = this.mReview.getRecipient();
        String revieweePic = reviewee.getPictureUrl();
        this.mHeaderImage.setImageUrl(revieweePic);
        this.mRevieweeImage.setImageUrl(revieweePic);
        this.mReviewerImage.setImageUrl(this.mAccountManager.getCurrentUser().getPictureUrl());
        this.mGuestName.setText(reviewee.getFirstName());
        this.mReservationDates.setText(DateHelper.getStringDateSpan((Context) getActivity(), this.mReview.getReservation().getStartDate(), this.mReview.getReservation().getReservedNightsCount()));
        this.mListingName.setText(this.mReview.getListingName());
        updateFeedbackField(this.mReview.getPublicFeedback(), this.mPublicFeedbackEditText);
        updateFeedbackField(this.mReview.getPrivateFeedback(), this.mPrivateFeedbackEditText);
        updateFeedbackField(this.mReview.getPrivateFeedbackTwo(), this.mPrivateFeedbackEditTextTwo);
    }

    private void initializeViews() {
        this.mHeader.setOnClickListener(ReviewFeedbackFragment$$Lambda$2.lambdaFactory$(this));
        Listing listing = this.mReview.getReservation() != null ? this.mReview.getReservation().getListing() : null;
        if (listing != null) {
            this.mHeaderBackground.setImageUrl(listing.getPictureUrl());
        } else {
            this.mHeaderBackground.setImageResource(C0880R.C0881drawable.hh_default_photo);
        }
        this.mPublicFeedbackEditText.addTextChangedListener(this.publicFeedbackTextWatcher);
        if (this.mReview.isGuestReviewingHost()) {
            this.mPublicFeedbackTitle.setText(C0880R.string.review_public_feedback_title_reviewing_host);
            this.mPublicFeedbackDescription.setText(C0880R.string.review_public_feedback_description_reviewing_host);
            this.mPrivateFeedbackTitle.setText(getString(C0880R.string.review_private_feedback_title_reviewing_host));
            this.mPrivateFeedbackDescription.setText(getString(C0880R.string.review_private_feedback_description_reviewing_host));
            this.mPrivateFeedbackEditText.setHint(C0880R.string.review_private_feedback_prompt_one);
            this.mPrivateFeedbackEditTextTwo.setHint(C0880R.string.review_private_feedback_prompt_two);
            this.mPrivateFeedbackEditTextTwo.setVisibility(0);
        } else {
            this.mPublicFeedbackTitle.setText(C0880R.string.review_public_feedback_title_reviewing_guest);
            this.mPublicFeedbackDescription.setText(C0880R.string.review_public_feedback_description_reviewing_guest);
            this.mPrivateFeedbackTitle.setText(getString(C0880R.string.review_private_feedback_title_reviewing_guest));
            this.mPrivateFeedbackDescription.setText(getString(C0880R.string.review_private_feedback_description_reviewing_guest));
        }
        this.mTooltip.setOnClickListener(ReviewFeedbackFragment$$Lambda$3.lambdaFactory$(this));
        this.mNextButton.setOnClickListener(ReviewFeedbackFragment$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initializeViews$3(ReviewFeedbackFragment reviewFeedbackFragment, View v) {
        if (reviewFeedbackFragment.mPublicFeedbackEditText.hasFocus()) {
            reviewFeedbackFragment.mPrivateFeedbackEditText.requestFocus();
            reviewFeedbackFragment.mPrivateFeedbackEditText.setSelection(reviewFeedbackFragment.mPrivateFeedbackEditText.length());
        } else if (!reviewFeedbackFragment.mPrivateFeedbackEditText.hasFocus() || !reviewFeedbackFragment.mReview.isGuestReviewingHost()) {
            reviewFeedbackFragment.saveFeedback();
            if (reviewFeedbackFragment.mEditMode) {
                reviewFeedbackFragment.getActivity().finish();
                reviewFeedbackFragment.startActivity(ReviewSummaryActivity.intentForReview(reviewFeedbackFragment.getActivity(), reviewFeedbackFragment.mReview));
                return;
            }
            reviewFeedbackFragment.startActivity(ReviewRatingsActivity.intentForReview(reviewFeedbackFragment.getActivity(), reviewFeedbackFragment.mReview));
        } else {
            reviewFeedbackFragment.mPrivateFeedbackEditTextTwo.requestFocus();
            reviewFeedbackFragment.mPrivateFeedbackEditTextTwo.setSelection(reviewFeedbackFragment.mPrivateFeedbackEditTextTwo.length());
        }
    }

    private void saveFeedback() {
        this.mReview.setPublicFeedback(this.mPublicFeedbackEditText.getText().toString().trim());
        this.mReview.setPrivateFeedback(this.mPrivateFeedbackEditText.getText().toString().trim());
        this.mReview.setPrivateFeedbackTwo(this.mPrivateFeedbackEditTextTwo.getText().toString().trim());
        this.mBus.post(new ReviewUpdatedEvent(this.mReview));
    }

    private void showTooltipIfNecessary() {
        if (!this.mToolTipSeen) {
            showTooltip();
            this.mToolTipSeen = true;
            this.mPreferences.getSharedPreferences().edit().putBoolean(AirbnbConstants.PREFS_SEEN_REVIEW_TOOLTIP, true).apply();
        }
    }

    /* access modifiers changed from: private */
    public void showTooltip() {
        TooltipDialogFragment.newInstance(C0880R.string.review_tooltip_title, C0880R.string.review_tooltip_content).show(getChildFragmentManager(), "tooltip");
    }

    private void showDoubleBlindFtue() {
        ReviewsAnalytics.trackDoubleBlindFtue(this.mReview);
        ReviewInfoDialogFragment.newInstance(C0880R.string.review_double_blind_ftue_title, this.mReview.isGuestReviewingHost() ? C0880R.string.review_double_blind_ftue_body_reviewing_host : C0880R.string.review_double_blind_ftue_body_reviewing_guest, C0880R.string.review_write_a_review, 0, this).show(getFragmentManager(), (String) null);
    }

    private static void updateFeedbackField(String feedback, EditText editText) {
        editText.setText(feedback);
        if (!TextUtils.isEmpty(feedback)) {
            editText.setSelection(feedback.length());
        }
    }

    @Subscribe
    public void reviewUpdated(ReviewUpdatedEvent update) {
        this.mReview = update.review;
        if (isResumed()) {
            loadFields();
        }
    }

    public void finishReviewFeedbackActivity() {
        int charCount = this.mPublicFeedbackEditText.getText().length() + this.mPrivateFeedbackEditText.getText().length() + this.mPrivateFeedbackEditTextTwo.getText().length();
        if (this.mEditMode || charCount <= 20) {
            ((ReviewFeedbackActivity) getActivity()).navigateUp();
        } else {
            ZenDialog.builder().withTitle(C0880R.string.are_you_sure_exit_write_review_title).withBodyText(C0880R.string.are_you_sure_exit_write_review).withDualButton(C0880R.string.cancel, 0, C0880R.string.exit, (int) DIALOG_REQ_CONFIRM_QUIT, (Fragment) this).create().show(getFragmentManager(), (String) null);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_CONFIRM_QUIT) {
            ((ReviewFeedbackActivity) getActivity()).navigateUp();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
