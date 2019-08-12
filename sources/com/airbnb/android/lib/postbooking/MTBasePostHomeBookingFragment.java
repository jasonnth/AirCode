package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.responses.PostHomeBookingResponse;
import com.airbnb.android.core.responses.PostHomeBookingResponse.PHB;
import com.airbnb.android.explore.requests.PostHomeBookingRequest;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public abstract class MTBasePostHomeBookingFragment extends PostBookingBaseFragment {
    protected static final String ARG_RESERVATION_ID = "arg_reservation_id";
    @BindView
    FixedDualActionFooter footer;
    final RequestListener<PostHomeBookingResponse> phbRequestListener = new C0699RL().onResponse(MTBasePostHomeBookingFragment$$Lambda$1.lambdaFactory$(this)).onError(MTBasePostHomeBookingFragment$$Lambda$2.lambdaFactory$()).build();

    /* access modifiers changed from: protected */
    public abstract int getLayoutRes();

    /* access modifiers changed from: protected */
    public abstract void initViews(View view);

    /* access modifiers changed from: protected */
    public abstract void onResponseLoaded(PHB phb);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        bindViews(view);
        setupFooter();
        initViews(view);
        PostHomeBookingRequest.newInstance(this.postBookingFlowController.getReservation().getConfirmationCode()).doubleResponse().withListener(this.phbRequestListener).execute(this.requestManager);
        return view;
    }

    private void setupFooter() {
        this.footer.setButtonEnabled(true);
        this.footer.setButtonText((CharSequence) getResources().getString(C0880R.string.post_home_booking_see_more));
        this.footer.setSecondaryButtonText((CharSequence) getResources().getString(C0880R.string.post_home_booking_not_now));
        this.footer.setButtonOnClickListener(MTBasePostHomeBookingFragment$$Lambda$3.lambdaFactory$(this));
        this.footer.setSecondaryButtonOnClickListener(MTBasePostHomeBookingFragment$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$new$3(AirRequestNetworkException e) {
    }
}
