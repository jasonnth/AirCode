package com.airbnb.android.core.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.fragments.CancellationPolicy.CancellationPolicy1;
import com.airbnb.android.core.fragments.CancellationPolicy.Data;
import com.airbnb.android.core.graphql.GraphistClient;
import com.airbnb.android.core.graphql.GraphistResponse;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.GetCancellationPolicyRequest;
import com.airbnb.android.core.responses.GetCancellationPolicyResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.base.Optional;
import icepick.State;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;

public class DLSCancellationPolicyFragment extends AirDialogFragment {
    private static final String ARG_CANCELLATION_POLICY = "cancellation_policy";
    private static final String ARG_RESERVATION = "reservation";
    private static final int DIALOG_CONFIRM_CANCEL = 607;
    @BindView
    PrimaryButton cancelButton;
    @State
    String cancellationPolicy;
    final RequestListener<GetCancellationPolicyResponse> cancellationPolicyRequestListener = new C0699RL().onResponse(DLSCancellationPolicyFragment$$Lambda$1.lambdaFactory$(this)).onError(DLSCancellationPolicyFragment$$Lambda$2.lambdaFactory$(this)).onComplete(DLSCancellationPolicyFragment$$Lambda$3.lambdaFactory$(this)).build();
    @State
    String cancellationPolicyText;
    @BindView
    SimpleTextRow cancellationPolicyTextView;
    @State
    String cancellationPolicyType;
    GraphistClient client;
    final Observer<GraphistResponse<Data>> graphQLCancellationPolicyRequestListener = new Observer<GraphistResponse<Data>>() {
        public void onCompleted() {
        }

        public void onError(Throwable e) {
            DLSCancellationPolicyFragment.this.requestPerformanceLogger.markGraphQLLoadError(e);
            DLSCancellationPolicyFragment.this.fetchCancellationPolicy(DLSCancellationPolicyFragment.this.cancellationPolicy);
        }

        public void onNext(GraphistResponse<Data> response) {
            DLSCancellationPolicyFragment.this.requestPerformanceLogger.markGraphQLLoadReceived(response);
            DLSCancellationPolicyFragment.this.showLoader(false);
            Optional<CancellationPolicy1> cancellationPolicyOptional = DLSCancellationPolicyFragment.this.unwrap(response);
            if (cancellationPolicyOptional.isPresent()) {
                FormattedCancellationPolicy formattedCancellationPolicy = FormattedCancellationPolicy.fromCancellationPolicy((CancellationPolicy1) cancellationPolicyOptional.get());
                DLSCancellationPolicyFragment.this.cancellationPolicyType = formattedCancellationPolicy.title;
                DLSCancellationPolicyFragment.this.cancellationPolicyText = formattedCancellationPolicy.body;
                DLSCancellationPolicyFragment.this.populateLayout();
                return;
            }
            DLSCancellationPolicyFragment.this.fetchCancellationPolicy(DLSCancellationPolicyFragment.this.cancellationPolicy);
        }
    };
    Subscription graphQLDataFetchSubscription = null;
    @BindView
    LoadingView loadingView;
    @BindView
    DocumentMarquee marquee;
    PerformanceLogger performanceLogger;
    RequestPerformanceLogger requestPerformanceLogger;
    @State
    Reservation reservation;
    @BindView
    AirToolbar toolbar;

    static class FormattedCancellationPolicy {
        public final String body;
        public final String title;

        public FormattedCancellationPolicy(String title2, String body2) {
            this.title = title2;
            this.body = body2;
        }

        public static FormattedCancellationPolicy fromCancellationPolicy(CancellationPolicy1 cancellationPolicy) {
            return new FormattedCancellationPolicy(cancellationPolicy.title().isPresent() ? (String) cancellationPolicy.title().get() : "", TextUtils.join("\n\n", cancellationPolicy.body()));
        }
    }

    private static class RequestPerformanceLogger {
        private static final String ERROR_KEY = "error";
        private static final String LOAD_EVENT_GRAPHQL = "cancellation_policy_query_graphql";
        private static final String LOAD_EVENT_REST = "cancellation_policy_query_rest";
        private static final String LOAD_EVENT_VIEW = DLSCancellationPolicyFragment.class.getSimpleName();
        private boolean outgoingGraphQLRequest;
        private boolean outgoingRestRequest;
        private final PerformanceLogger performanceLogger;
        private boolean receivedRestNetworkResponse;

        public RequestPerformanceLogger(PerformanceLogger performanceLogger2) {
            this.performanceLogger = performanceLogger2;
        }

        /* access modifiers changed from: private */
        public void markRestLoadStarted() {
            this.performanceLogger.markStart(LOAD_EVENT_REST);
            this.receivedRestNetworkResponse = false;
            this.outgoingRestRequest = true;
        }

        /* access modifiers changed from: private */
        public void markRestLoadReceived(BaseResponse baseResponse) {
            this.receivedRestNetworkResponse = (!baseResponse.metadata.isCached()) | this.receivedRestNetworkResponse;
        }

        /* access modifiers changed from: private */
        public void markRestLoadEnded() {
            this.outgoingRestRequest = false;
            if (this.receivedRestNetworkResponse) {
                this.performanceLogger.markCompleted(LOAD_EVENT_REST, null, C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
            } else {
                this.performanceLogger.markCancelled(LOAD_EVENT_REST, Strap.make().mo11639kv("error", "Completed without network response."), C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
            }
        }

        /* access modifiers changed from: private */
        public void markRestLoadError(Throwable throwable) {
            this.performanceLogger.markCancelled(LOAD_EVENT_REST, Strap.make().mo11639kv("error", throwable.getMessage()), C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
        }

        /* access modifiers changed from: private */
        public void markGraphQLLoadStarted() {
            this.outgoingGraphQLRequest = true;
            this.performanceLogger.markStart(LOAD_EVENT_GRAPHQL);
        }

        /* access modifiers changed from: private */
        public void markGraphQLLoadReceived(GraphistResponse<Data> response) {
            this.outgoingGraphQLRequest = false;
            if (response.hasErrors()) {
                this.performanceLogger.markCancelled(LOAD_EVENT_GRAPHQL, Strap.make().mo11639kv("error", response.errorMessage()), C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
            } else if (!response.fromCache()) {
                this.performanceLogger.markCompleted(LOAD_EVENT_GRAPHQL, C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
            }
        }

        /* access modifiers changed from: private */
        public void markGraphQLLoadError(Throwable error) {
            this.performanceLogger.markCancelled(LOAD_EVENT_GRAPHQL, Strap.make().mo11639kv("error", error.getMessage()), C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
        }

        /* access modifiers changed from: private */
        public void clearUnfinishedEvents() {
            if (this.outgoingGraphQLRequest) {
                this.performanceLogger.markCancelled(LOAD_EVENT_GRAPHQL, C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
            }
            if (this.outgoingRestRequest) {
                this.performanceLogger.markCancelled(LOAD_EVENT_REST, C2445NativeMeasurementType.TTI, LOAD_EVENT_VIEW);
            }
        }
    }

    public static DLSCancellationPolicyFragment newInstancePolicyOnly(String cancellationPolicy2) {
        Check.notEmpty(cancellationPolicy2);
        return (DLSCancellationPolicyFragment) ((FragmentBundleBuilder) FragmentBundler.make(new DLSCancellationPolicyFragment()).putString("cancellation_policy", cancellationPolicy2)).build();
    }

    public void showLoader(boolean show) {
        ViewUtils.setVisibleIf((View) this.loadingView, show);
        ViewUtils.setGoneIf(this.cancellationPolicyTextView, show);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_dls_cancellation_policy, container, false);
        bindViews(view);
        ((CoreGraph) CoreApplication.instance(getActivity()).component()).inject(this);
        this.requestPerformanceLogger = new RequestPerformanceLogger(this.performanceLogger);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getArguments().getParcelable("reservation");
            this.cancellationPolicy = getArguments().getString("cancellation_policy");
            if (TextUtils.isEmpty(this.cancellationPolicy)) {
                this.cancellationPolicy = this.reservation.getCancellationPolicyKey();
            }
            if (Trebuchet.launch(TrebuchetKeys.CANCELLATION_POLICY_GRAPHQL, false)) {
                fetchCancellationPolicyWithGraphQL(this.cancellationPolicy);
            } else {
                fetchCancellationPolicy(this.cancellationPolicy);
            }
        }
        populateLayout();
        return view;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.graphQLDataFetchSubscription != null) {
            this.graphQLDataFetchSubscription.unsubscribe();
        }
        this.requestPerformanceLogger.clearUnfinishedEvents();
    }

    /* access modifiers changed from: private */
    public void fetchCancellationPolicy(String cancellationPolicy2) {
        this.requestPerformanceLogger.markRestLoadStarted();
        new GetCancellationPolicyRequest(cancellationPolicy2).withListener((Observer) this.cancellationPolicyRequestListener).doubleResponse().execute(this.requestManager);
        showLoader(true);
    }

    private void fetchCancellationPolicyWithGraphQL(String cancellationPolicy2) {
        this.requestPerformanceLogger.markGraphQLLoadStarted();
        CancellationPolicy query = new CancellationPolicy(cancellationPolicy2);
        showLoader(true);
        this.graphQLDataFetchSubscription = this.client.fetchDoubleRepsonse(query).observeOn(AndroidSchedulers.mainThread()).subscribe(this.graphQLCancellationPolicyRequestListener);
    }

    static /* synthetic */ void lambda$new$0(DLSCancellationPolicyFragment dLSCancellationPolicyFragment, GetCancellationPolicyResponse response) {
        dLSCancellationPolicyFragment.requestPerformanceLogger.markRestLoadReceived(response);
        dLSCancellationPolicyFragment.cancellationPolicyType = response.cancellationPolicy.getFormattedName();
        dLSCancellationPolicyFragment.cancellationPolicyText = response.cancellationPolicy.getPolicyAsString();
        dLSCancellationPolicyFragment.showLoader(false);
        dLSCancellationPolicyFragment.populateLayout();
    }

    static /* synthetic */ void lambda$new$1(DLSCancellationPolicyFragment dLSCancellationPolicyFragment, AirRequestNetworkException e) {
        dLSCancellationPolicyFragment.requestPerformanceLogger.markRestLoadError(e);
        dLSCancellationPolicyFragment.handleLoadError();
    }

    /* access modifiers changed from: private */
    public void populateLayout() {
        boolean z;
        boolean z2 = true;
        this.marquee.setCaption((CharSequence) this.cancellationPolicyType);
        this.cancellationPolicyTextView.setText((CharSequence) this.cancellationPolicyText);
        PrimaryButton primaryButton = this.cancelButton;
        if (this.reservation != null) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(primaryButton, z);
        PrimaryButton primaryButton2 = this.cancelButton;
        if (TextUtils.isEmpty(this.cancellationPolicyText)) {
            z2 = false;
        }
        ViewLibUtils.setEnabledIf(primaryButton2, z2);
    }

    private void handleLoadError() {
        showLoader(false);
        Toast.makeText(getActivity(), C0716R.string.cancellation_policy_request_fail, 0).show();
        getActivity().onBackPressed();
    }

    @OnClick
    public void initiateCancellation() {
        ZenDialog.builder().withBodyText(getString(C0716R.string.cancel_reservation_confirmation_guest_question)).withDualButton(C0716R.string.f1069no, 0, C0716R.string.yes, (int) DIALOG_CONFIRM_CANCEL, (Fragment) this).create().show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_CONFIRM_CANCEL) {
            ((TransparentActionBarActivity) getActivity()).showInitialFragment(DLSCancelReservationFragment.newInstance(this.reservation));
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ListingCancellationPolicy;
    }

    public boolean isLeafDialog() {
        return true;
    }

    /* access modifiers changed from: private */
    public Optional<CancellationPolicy1> unwrap(GraphistResponse<Data> response) {
        CancellationPolicy1 cancellationPolicy2 = null;
        if (response.data().isPresent()) {
            Data data = (Data) response.data().get();
            if (data.CancellationPolicy().isPresent()) {
                cancellationPolicy2 = (CancellationPolicy1) data.CancellationPolicy().get();
            }
        }
        return Optional.fromNullable(cancellationPolicy2);
    }
}
