package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import android.os.Handler;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.SupportPhoneNumber;
import com.airbnb.android.core.requests.SupportPhoneNumbersRequest;
import com.airbnb.android.core.responses.SupportPhoneNumbersResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.SupportNumbersAdapter;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class LateCancellationFragment extends AirFragment {
    private static final int LOADING_DISPLAY_DELAY = 1000;
    private SupportNumbersAdapter adapter;
    private Handler handler = new Handler();
    /* access modifiers changed from: 0000 */
    @State
    public boolean isLoading;
    final RequestListener<SupportPhoneNumbersResponse> listener = new C0699RL().onComplete(LateCancellationFragment$$Lambda$1.lambdaFactory$(this)).onResponse(LateCancellationFragment$$Lambda$2.lambdaFactory$(this)).onError(LateCancellationFragment$$Lambda$3.lambdaFactory$(this)).build();
    @State
    ArrayList<SupportPhoneNumber> phoneNumbers;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$1(LateCancellationFragment lateCancellationFragment, SupportPhoneNumbersResponse response) {
        lateCancellationFragment.phoneNumbers = response.numbers;
        lateCancellationFragment.adapter.showSupportNumbers(response.numbers);
    }

    static /* synthetic */ void lambda$new$3(LateCancellationFragment lateCancellationFragment, AirRequestNetworkException e) {
        lateCancellationFragment.adapter.showFallbackNumber();
        NetworkUtil.tryShowRetryableErrorWithSnackbar(lateCancellationFragment.getView(), (NetworkException) e, LateCancellationFragment$$Lambda$6.lambdaFactory$(lateCancellationFragment));
    }

    public static LateCancellationFragment newInstance() {
        return new LateCancellationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_cancellation_late, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new SupportNumbersAdapter(getContext());
        this.recyclerView.setAdapter(this.adapter);
        if (savedInstanceState == null) {
            requestInternationalNumbers();
        } else if (this.phoneNumbers != null) {
            this.adapter.showSupportNumbers(this.phoneNumbers);
        } else if (this.isLoading) {
            this.adapter.showDefaultNumberAndLoading();
        } else {
            this.adapter.showFallbackNumber();
            NetworkUtil.tryShowRetryableErrorWithSnackbar(view, LateCancellationFragment$$Lambda$4.lambdaFactory$(this));
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void requestInternationalNumbers() {
        this.isLoading = true;
        this.phoneNumbers = null;
        this.handler.postDelayed(LateCancellationFragment$$Lambda$5.lambdaFactory$(this), 1000);
        new SupportPhoneNumbersRequest().withListener((Observer) this.listener).singleResponse().execute(this.requestManager);
    }

    static /* synthetic */ void lambda$requestInternationalNumbers$5(LateCancellationFragment lateCancellationFragment) {
        if (lateCancellationFragment.isLoading && lateCancellationFragment.phoneNumbers == null) {
            lateCancellationFragment.adapter.showDefaultNumberAndLoading();
        }
    }
}
