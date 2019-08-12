package com.airbnb.android.lib.reviews.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.reviews.fragments.WriteFeedbackIntroFragment.FeedbackField;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class WriteFeedbackFragment extends BaseWriteReviewFragment {
    private static final String EXIT_ON_SAVE = "exit_on_save";
    @BindView
    AirEditTextView editText;
    private FeedbackField feedbackField;
    private final Handler handler = new Handler();

    public static WriteFeedbackFragment newInstance(FeedbackField field) {
        return (WriteFeedbackFragment) ((FragmentBundleBuilder) FragmentBundler.make(new WriteFeedbackFragment()).putSerializable(WriteFeedbackIntroFragment.ARG_FEEDBACK_FIELD, field)).build();
    }

    public static WriteFeedbackFragment newInstanceForEdit(FeedbackField field) {
        return (WriteFeedbackFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new WriteFeedbackFragment()).putSerializable(WriteFeedbackIntroFragment.ARG_FEEDBACK_FIELD, field)).putBoolean(EXIT_ON_SAVE, true)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.feedbackField = (FeedbackField) getArguments().getSerializable(WriteFeedbackIntroFragment.ARG_FEEDBACK_FIELD);
        Check.notNull(this.feedbackField);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.save, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment next;
        if (item.getItemId() != C0880R.C0882id.save) {
            return super.onOptionsItemSelected(item);
        }
        String text = this.editText.getText().toString().trim();
        if (this.feedbackField != FeedbackField.PUBLIC || !TextUtils.isEmpty(text)) {
            if (this.feedbackField == FeedbackField.PUBLIC) {
                getReview().setPublicFeedback(text);
                next = WriteFeedbackIntroFragment.newInstance(FeedbackField.PRIVATE);
            } else {
                getReview().setPrivateFeedback(text);
                next = ReviewStarFragment.newInstanceFirstPage(getReview());
            }
            this.wrInterface.saveProgress(this.feedbackField, text);
            if (getArguments().getBoolean(EXIT_ON_SAVE)) {
                this.wrInterface.getSupportFragmentManager().popBackStack();
            } else {
                this.wrInterface.popAndShowFragment(this, next);
            }
            KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
            return true;
        }
        Toast.makeText(getActivity(), C0880R.string.please_write_review, 0).show();
        return true;
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_write_review, null);
        bindViews(view);
        int hintStr = C0880R.string.review_write_a_review;
        if (getReview().getReviewRole() == ReviewRole.Host) {
            hintStr = this.feedbackField == FeedbackField.PUBLIC ? C0880R.string.review_hint_host_write_public_review_for_guest : C0880R.string.review_hint_host_write_private_review_for_guest;
        }
        this.editText.setHint(hintStr);
        this.editText.setText(this.feedbackField == FeedbackField.PUBLIC ? getReview().getPublicFeedback() : getReview().getPrivateFeedback());
        this.editText.requestFocus();
        this.handler.post(WriteFeedbackFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.handler.removeCallbacksAndMessages(null);
    }

    /* access modifiers changed from: 0000 */
    public SheetTheme getTheme() {
        return SheetTheme.WHITE;
    }
}
