package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.requests.UpdateMessageThreadRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class DeclineInquiryFragment extends AirFragment {
    public static final String ARG_LISTING_ID = "listing_id";
    public static final String ARG_THREAD_ID = "thread_id";
    public static final String ARG_USER_NAME = "user_name";
    final RequestListener<BaseResponse> declineInquiryListener = new C0699RL().onResponse(DeclineInquiryFragment$$Lambda$1.lambdaFactory$(this)).onError(DeclineInquiryFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton editButton;
    @BindView
    LinkActionRow editRow;
    @BindView
    PrimaryButton finishButton;
    @State
    long listingId;
    @BindView
    DocumentMarquee marquee;
    @State
    String message;
    @BindView
    TextView reportMessageView;
    @BindView
    SimpleTextRow textRow;
    @State
    long threadId;
    @BindView
    AirToolbar toolbar;
    @State
    String userName;

    static /* synthetic */ void lambda$new$0(DeclineInquiryFragment declineInquiryFragment, BaseResponse data) {
        declineInquiryFragment.finishButton.setNormal();
        declineInquiryFragment.getActivity().setResult(-1);
        declineInquiryFragment.getActivity().finish();
        declineInquiryFragment.startActivity(DeclineInquiryConfirmationFragment.newIntent(declineInquiryFragment.getContext(), declineInquiryFragment.userName));
    }

    static /* synthetic */ void lambda$new$1(DeclineInquiryFragment declineInquiryFragment, AirRequestNetworkException e) {
        declineInquiryFragment.finishButton.setNormal();
        NetworkUtil.toastNetworkError(declineInquiryFragment.getContext(), (NetworkException) e);
    }

    public static Intent newIntent(Context context, long threadId2, long listingId2, String userName2) {
        Bundle bundle = new Bundle();
        bundle.putString("user_name", userName2);
        bundle.putLong("thread_id", threadId2);
        bundle.putLong("listing_id", listingId2);
        Intent intent = AutoAirActivity.intentForFragment(context, DeclineInquiryFragment.class, bundle);
        intent.putExtra(AutoAirActivity.EXTRA_ACTION_BAR, false);
        return intent;
    }

    public static DeclineInquiryFragment newInstance(long threadId2, long listingId2, String userName2) {
        return (DeclineInquiryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new DeclineInquiryFragment()).putLong("thread_id", threadId2)).putLong("listing_id", listingId2)).putString("user_name", userName2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_decline_inquiry, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (isMessageValid(this.message)) {
            showSubmitUI();
        }
        this.threadId = getArguments().getLong("thread_id");
        this.listingId = getArguments().getLong("listing_id");
        this.userName = getArguments().getString("user_name");
        setMarqueeText();
        setTextRowText();
        return view;
    }

    private void setMarqueeText() {
        this.marquee.setTitle((CharSequence) getString(C0880R.string.decline_inquiry_title, this.userName));
    }

    private void setTextRowText() {
        this.textRow.setText((CharSequence) getString(C0880R.string.decline_inquiry_text, this.userName));
    }

    @OnClick
    public void onClickEditRow() {
        showEditSheet();
    }

    @OnClick
    public void onClickEditButton() {
        showEditSheet();
    }

    @OnClick
    public void onClickFinishButton() {
        this.finishButton.setLoading();
        UpdateMessageThreadRequest.forPreApproveOrDecline(this.threadId, this.listingId, this.message, -1, true, this.declineInquiryListener).withListener(this.declineInquiryListener).execute(this.requestManager);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 811) {
            String input = data.getStringExtra("result_extra_input");
            if (isMessageValid(input)) {
                this.message = input;
                showSubmitUI();
            }
        }
    }

    private void showEditSheet() {
        startActivityForResult(TextInputSheetFragment.newIntent(getContext(), this.message), TextInputSheetFragment.REQUEST_CODE_TEXT_INPUT_SHEET);
    }

    private boolean isMessageValid(String s) {
        return s != null && !TextUtils.isEmpty(s.trim());
    }

    private void showSubmitUI() {
        this.reportMessageView.setText(this.message);
        this.editButton.setVisibility(8);
        this.finishButton.setVisibility(0);
        this.editRow.setVisibility(0);
        this.reportMessageView.setVisibility(0);
    }
}
