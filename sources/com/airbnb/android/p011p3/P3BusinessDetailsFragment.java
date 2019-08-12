package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.CommercialHostInfo;
import com.airbnb.android.core.requests.CommercialHostInfosRequest;
import com.airbnb.android.core.responses.CommercialHostInfoResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.SimpleTextRow;
import icepick.State;
import p032rx.Observer;

/* renamed from: com.airbnb.android.p3.P3BusinessDetailsFragment */
public class P3BusinessDetailsFragment extends AirFragment {
    private static final String ARG_HOST_ID = "host_id";
    @BindView
    LinearLayout businessDetails;
    @BindView
    SimpleTextRow businessDetailsText;
    @State
    CommercialHostInfo commercialHostInfo;
    final RequestListener<CommercialHostInfoResponse> commercialHostInfoRequestListener = new RequestListener<CommercialHostInfoResponse>() {
        public void onResponse(CommercialHostInfoResponse response) {
            P3BusinessDetailsFragment.this.commercialHostInfo = (CommercialHostInfo) response.commercialHostInfos.get(0);
            P3BusinessDetailsFragment.this.showLoader(false);
            P3BusinessDetailsFragment.this.populateLayout();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            P3BusinessDetailsFragment.this.showLoader(false);
            NetworkUtil.tryShowErrorWithSnackbar(P3BusinessDetailsFragment.this.businessDetails, e);
        }
    };
    @State
    long hostId;
    @BindView
    LoadingView loadingView;
    @BindView
    AirToolbar toolbar;

    public static P3BusinessDetailsFragment instanceForHostId(long hostId2) {
        return (P3BusinessDetailsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new P3BusinessDetailsFragment()).putLong(ARG_HOST_ID, hostId2)).build();
    }

    public void showLoader(boolean show) {
        ViewUtils.setVisibleIf((View) this.loadingView, show);
        ViewUtils.setGoneIf(this.businessDetails, show);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_p3_business_details, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.commercialHostInfo == null) {
            this.hostId = getArguments().getLong(ARG_HOST_ID);
            CommercialHostInfosRequest.forUserId(this.hostId).withListener((Observer) this.commercialHostInfoRequestListener).execute(this.requestManager);
            showLoader(true);
        } else {
            populateLayout();
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void populateLayout() {
        this.businessDetailsText.setText((CharSequence) this.commercialHostInfo.getFormattedInfo(getContext()));
    }
}
