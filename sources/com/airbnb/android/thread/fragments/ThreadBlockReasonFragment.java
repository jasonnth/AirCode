package com.airbnb.android.thread.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.UserFlagDetail;
import com.airbnb.android.core.requests.CreateUserFlagRequest;
import com.airbnb.android.core.requests.GetUserFlagDetailsRequest;
import com.airbnb.android.core.responses.GetUserFlagDetailsResponse;
import com.airbnb.android.core.responses.UserFlagResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.thread.C1729R;
import com.airbnb.android.thread.controllers.ThreadBlockController;
import com.airbnb.android.thread.controllers.ThreadBlockReasonEpoxyController;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import icepick.State;
import java.util.List;
import p032rx.Observer;

public class ThreadBlockReasonFragment extends AirFragment {
    private static final String ERROR_DETAIL_FLAG_EXISTS = "User flag already exists";
    final RequestListener<UserFlagResponse> createUserFlagListener = new C0699RL().onResponse(ThreadBlockReasonFragment$$Lambda$6.lambdaFactory$(this)).onError(ThreadBlockReasonFragment$$Lambda$7.lambdaFactory$(this)).onComplete(ThreadBlockReasonFragment$$Lambda$8.lambdaFactory$(this)).build();
    @BindView
    FixedDualActionFooter footer;
    @BindView
    AirRecyclerView recyclerView;
    @State
    String selectedReason;
    private ThreadBlockController threadBlockController;
    @BindView
    AirToolbar toolbar;
    final RequestListener<GetUserFlagDetailsResponse> userFlagDetailsRequestListener = new C0699RL().onResponse(ThreadBlockReasonFragment$$Lambda$1.lambdaFactory$(this)).onError(ThreadBlockReasonFragment$$Lambda$4.lambdaFactory$(this)).onComplete(ThreadBlockReasonFragment$$Lambda$5.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$3(ThreadBlockReasonFragment threadBlockReasonFragment, Boolean b) {
        if (threadBlockReasonFragment.footer != null) {
            threadBlockReasonFragment.footer.setButtonLoading(false);
        }
    }

    static /* synthetic */ void lambda$new$6(ThreadBlockReasonFragment threadBlockReasonFragment, AirRequestNetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        if (errorResponse == null || !ERROR_DETAIL_FLAG_EXISTS.equals(errorResponse.errorDetails())) {
            NetworkUtil.tryShowRetryableErrorWithSnackbar(threadBlockReasonFragment.getView(), (NetworkException) e, ThreadBlockReasonFragment$$Lambda$12.lambdaFactory$(threadBlockReasonFragment));
        } else {
            threadBlockReasonFragment.threadBlockController.showThreadBlockFragment();
        }
    }

    static /* synthetic */ void lambda$new$7(ThreadBlockReasonFragment threadBlockReasonFragment, Boolean b) {
        if (threadBlockReasonFragment.footer != null) {
            threadBlockReasonFragment.footer.setButtonLoading(false);
        }
    }

    public static ThreadBlockReasonFragment create() {
        return (ThreadBlockReasonFragment) FragmentBundler.make(new ThreadBlockReasonFragment()).build();
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
        boolean z = false;
        View view = inflater.inflate(C1729R.layout.fragment_thread_block_reason, container, false);
        bindViews(view);
        this.footer.setButtonText(C1729R.string.submit);
        FixedDualActionFooter fixedDualActionFooter = this.footer;
        if (this.selectedReason != null) {
            z = true;
        }
        fixedDualActionFooter.setButtonEnabled(z);
        this.footer.setButtonOnClickListener(ThreadBlockReasonFragment$$Lambda$9.lambdaFactory$(this));
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setNavigationOnClickListener(ThreadBlockReasonFragment$$Lambda$10.lambdaFactory$(this));
        fetchUserFlagDetails();
        return view;
    }

    /* access modifiers changed from: private */
    public void fetchUserFlagDetails() {
        GetUserFlagDetailsRequest.forUserBlock().withListener((Observer) this.userFlagDetailsRequestListener).doubleResponse().execute(this.requestManager);
        this.footer.setButtonLoading(true);
    }

    /* access modifiers changed from: private */
    public void setUserFlagDetails(List<UserFlagDetail> userFlagDetails) {
        this.recyclerView.setEpoxyControllerAndBuildModels(new ThreadBlockReasonEpoxyController(userFlagDetails, this.selectedReason, ThreadBlockReasonFragment$$Lambda$11.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$setUserFlagDetails$10(ThreadBlockReasonFragment threadBlockReasonFragment, String selectedReason2) {
        threadBlockReasonFragment.selectedReason = selectedReason2;
        threadBlockReasonFragment.footer.setButtonEnabled(selectedReason2 != null);
    }

    /* access modifiers changed from: private */
    public void submit() {
        this.footer.setButtonLoading(true);
        long flaggedUserId = this.threadBlockController.getThread().getOtherUser().getId();
        new CreateUserFlagRequest(FlagContent.User, flaggedUserId, flaggedUserId, this.selectedReason).withListener((Observer) this.createUserFlagListener).execute(this.requestManager);
    }
}
