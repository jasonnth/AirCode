package com.airbnb.android.fixit.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.core.requests.UpdateFixItItemRequest;
import com.airbnb.android.core.responses.FixItItemResponse;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.fixit.C6380R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import icepick.State;
import p032rx.Observer;

public class FixItItemCommentFragment extends FixItBaseFragment {
    private static final String ARG_FIX_IT_ITEM = "fix_it_item";
    private static final int NUM_CHAR_MAX_LENGTH = 140;
    private static final int NUM_CHAR_MIN_LENGTH = 1;
    @BindView
    AirEditTextPageView editTextPage;
    final RequestListener<FixItItemResponse> fixItItemListener = new C0699RL().onResponse(FixItItemCommentFragment$$Lambda$1.lambdaFactory$(this)).onError(FixItItemCommentFragment$$Lambda$2.lambdaFactory$(this)).onComplete(FixItItemCommentFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    FixedDualActionFooter footer;
    @State
    String hostComment;
    private FixItItem item;
    @BindView
    AirToolbar toolbar;

    public static FixItItemCommentFragment create(FixItItem item2) {
        return (FixItItemCommentFragment) ((FragmentBundleBuilder) FragmentBundler.make(new FixItItemCommentFragment()).putParcelable(ARG_FIX_IT_ITEM, item2)).build();
    }

    static /* synthetic */ void lambda$new$0(FixItItemCommentFragment fixItItemCommentFragment, FixItItemResponse response) {
        fixItItemCommentFragment.dataController.setItem(response.item);
        fixItItemCommentFragment.getFragmentManager().popBackStack();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.item = (FixItItem) getArguments().getParcelable(ARG_FIX_IT_ITEM);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6380R.layout.fragment_fix_it_item_comment, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        initEditTextPage();
        initFooter();
        return view;
    }

    public void onResume() {
        super.onResume();
        this.editTextPage.requestFocusAndKeyboard();
    }

    public void onSaveInstanceState(Bundle outState) {
        this.hostComment = this.editTextPage.getText().toString();
        super.onSaveInstanceState(outState);
    }

    private void initEditTextPage() {
        this.editTextPage.setTitle(C6380R.string.comment_title);
        this.editTextPage.setHint(C6380R.string.comment_hint);
        this.editTextPage.setText(!TextUtils.isEmpty(this.item.getHostComment()) ? this.item.getHostComment() : this.hostComment);
        this.editTextPage.setListener(FixItItemCommentFragment$$Lambda$4.lambdaFactory$(this));
        this.editTextPage.setMaxLength(140);
        this.editTextPage.setMinLength(1);
    }

    private void initFooter() {
        this.footer.setButtonText(C6380R.string.comment_action_save);
        this.footer.setButtonOnClickListener(FixItItemCommentFragment$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void enableSaveButton(boolean enabled) {
        this.footer.setButtonEnabled(enabled);
    }

    /* access modifiers changed from: private */
    public void onSave() {
        this.footer.setButtonLoading(true);
        UpdateFixItItemRequest.forHostComment(this.item.getId(), this.editTextPage.getText().toString()).withListener((Observer) this.fixItItemListener).execute(this.requestManager);
    }
}
