package com.airbnb.android.thread.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.CreateUserBlockRequest;
import com.airbnb.android.core.responses.UserBlockResponse;
import com.airbnb.android.thread.C1729R;
import com.airbnb.android.thread.controllers.ThreadBlockController;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import icepick.State;
import p032rx.Observer;

public class ThreadBlockInfoFragment extends AirFragment {
    public static final String ARG_INFO_TYPE = "ARG_INFO_TYPE";
    public static final String ARG_RECIPIENT_NAME = "ARG_RECIPIENT_NAME";
    private static final int NO_RES = -1;
    final RequestListener<UserBlockResponse> createUserBlockListener = new C0699RL().onResponse(ThreadBlockInfoFragment$$Lambda$1.lambdaFactory$(this)).onError(ThreadBlockInfoFragment$$Lambda$2.lambdaFactory$(this)).onComplete(ThreadBlockInfoFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    FixedDualActionFooter footer;
    @State
    InfoType infoType;
    @BindView
    DocumentMarquee marquee;
    private ThreadBlockController threadBlockController;
    @BindView
    AirToolbar toolbar;

    public enum InfoType {
        ContactUs(C1729R.string.message_block_contact_us_title, C1729R.string.message_block_contact_us_caption, false, true, C1729R.string.contact_airbnb, C1729R.string.cancel),
        FinalBlockConfirm(C1729R.string.message_block_final_confirm_title, C1729R.string.message_block_final_confirm_caption, true, false, C1729R.string.message_block_button_ok, -1),
        InitialBlockConfirm(C1729R.string.message_block_initial_confirm_title, C1729R.string.message_block_initial_confirm_caption, true, true, C1729R.string.message_block_button_block, C1729R.string.action_skip),
        FlagUserConfirm(C1729R.string.message_flag_user_confirm_title, C1729R.string.message_flag_user_confirm_caption, false, false, C1729R.string.message_block_button_ok, -1);
        
        final boolean captionRecipientNameParam;
        final int captionRes;
        final int primaryButtonRes;
        final int secondaryButtonRes;
        final boolean titleRecipientNameParam;
        final int titleRes;

        private InfoType(int titleRes2, int captionRes2, boolean titleRecipientNameParam2, boolean captionRecipientNameParam2, int primaryButtonRes2, int secondaryButtonRes2) {
            this.titleRes = titleRes2;
            this.captionRes = captionRes2;
            this.titleRecipientNameParam = titleRecipientNameParam2;
            this.captionRecipientNameParam = captionRecipientNameParam2;
            this.primaryButtonRes = primaryButtonRes2;
            this.secondaryButtonRes = secondaryButtonRes2;
        }
    }

    public static ThreadBlockInfoFragment create(InfoType infoType2, String recipientName) {
        return (ThreadBlockInfoFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ThreadBlockInfoFragment()).putSerializable(ARG_INFO_TYPE, infoType2)).putString(ARG_RECIPIENT_NAME, recipientName)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.threadBlockController = (ThreadBlockController) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        this.threadBlockController = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1729R.layout.fragment_thread_block_info, container, false);
        bindViews(view);
        this.infoType = (InfoType) getArguments().getSerializable(ARG_INFO_TYPE);
        String recipientName = getArguments().getString(ARG_RECIPIENT_NAME);
        if (this.infoType.titleRecipientNameParam) {
            this.marquee.setTitle((CharSequence) getContext().getString(this.infoType.titleRes, new Object[]{recipientName}));
        } else {
            this.marquee.setTitle(this.infoType.titleRes);
        }
        if (this.infoType.captionRecipientNameParam) {
            this.marquee.setCaption((CharSequence) getContext().getString(this.infoType.captionRes, new Object[]{recipientName}));
        } else {
            this.marquee.setCaption(this.infoType.captionRes);
        }
        this.footer.setButtonText(this.infoType.primaryButtonRes);
        this.footer.setButtonOnClickListener(ThreadBlockInfoFragment$$Lambda$4.lambdaFactory$(this));
        if (this.infoType.secondaryButtonRes != -1) {
            this.footer.setSecondaryButtonText(this.infoType.secondaryButtonRes);
            this.footer.setSecondaryButtonOnClickListener(ThreadBlockInfoFragment$$Lambda$5.lambdaFactory$(this));
        } else {
            this.footer.setSecondaryButtonEnabled(false);
        }
        this.toolbar.setNavigationIcon(1);
        this.toolbar.setNavigationOnClickListener(ThreadBlockInfoFragment$$Lambda$6.lambdaFactory$(this));
        return view;
    }

    /* access modifiers changed from: private */
    public void handlePrimaryButtonClick() {
        switch (this.infoType) {
            case ContactUs:
                WebViewIntentBuilder.startAuthenticatedMobileWebActivity(getActivity(), getString(C1729R.string.contact_cx_url));
                this.threadBlockController.finishOk();
                return;
            case InitialBlockConfirm:
                createUserBlock();
                return;
            case FinalBlockConfirm:
            case FlagUserConfirm:
                this.threadBlockController.finishOk();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void handleSecondaryButtonClick() {
        switch (this.infoType) {
            case ContactUs:
                this.threadBlockController.finishOk();
                return;
            case InitialBlockConfirm:
                this.threadBlockController.showFlagUserConfirmFragment();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void createUserBlock() {
        this.footer.setButtonLoading(true);
        Thread thread = this.threadBlockController.getThread();
        CreateUserBlockRequest.create(thread.getId(), thread.getOtherUser().getId()).withListener((Observer) this.createUserBlockListener).execute(this.requestManager);
    }
}
