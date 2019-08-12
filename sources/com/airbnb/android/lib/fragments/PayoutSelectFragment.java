package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.PayoutInfoType;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.airbnb.android.core.requests.PayoutInfoTypesRequest;
import com.airbnb.android.core.responses.PayoutInfoTypesResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;
import com.airbnb.android.lib.views.LoaderListView;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class PayoutSelectFragment extends Fragment {
    private static final String SAVED_PAYMENT_METHOD = "payment_method";
    private static final String SELECTED_COUNTRY = "selected_country";
    AirbnbApi mAirbnbApi;
    /* access modifiers changed from: private */
    public LoaderListView mLoaderListView;
    /* access modifiers changed from: private */
    public List<PayoutInfoType> mPaymentMethodResponse;
    private String mSelectedCountry;

    private static class PayoutMethodAdapter extends ArrayAdapter<PayoutInfoType> {
        private final int mResource;

        PayoutMethodAdapter(Context context, int resource, List<PayoutInfoType> objects) {
            super(context, resource, objects);
            this.mResource = resource;
        }

        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(this.mResource, parent, false);
            }
            PayoutInfoType payoutMethod = (PayoutInfoType) getItem(position);
            view.setTag(payoutMethod);
            ((TextView) view.findViewById(C0880R.C0882id.payout_method_title)).setText(payoutMethod.getPayoutMethodText());
            ((TextView) view.findViewById(C0880R.C0882id.payout_method_description)).setText(payoutMethod.getCombinedDetails(view.getContext()));
            return view;
        }
    }

    public static PayoutSelectFragment newInstance(String countryCode) {
        PayoutSelectFragment f = new PayoutSelectFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_COUNTRY, countryCode);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState != null) {
            this.mPaymentMethodResponse = savedInstanceState.getParcelableArrayList(SAVED_PAYMENT_METHOD);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mPaymentMethodResponse != null) {
            outState.putParcelableArrayList(SAVED_PAYMENT_METHOD, new ArrayList(this.mPaymentMethodResponse));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mLoaderListView = (LoaderListView) inflater.inflate(C0880R.layout.fragment_loader_list_view, container, false);
        this.mSelectedCountry = getArguments().getString(SELECTED_COUNTRY);
        if (this.mSelectedCountry == null) {
            throw new IllegalArgumentException("must select a country");
        }
        ListView listView = this.mLoaderListView.getListView();
        listView.setSelector(new ColorDrawable(0));
        listView.setDividerHeight(0);
        listView.setOnItemClickListener(PayoutSelectFragment$$Lambda$1.lambdaFactory$(this));
        listView.addHeaderView(LayoutInflater.from(getActivity()).inflate(C0880R.layout.list_item_payout_select_header, listView, false));
        if (this.mPaymentMethodResponse != null) {
            this.mLoaderListView.finishLoaderImmediate();
            setAdapter(this.mPaymentMethodResponse);
        } else {
            loadSupportedPayoutMethods();
        }
        return this.mLoaderListView;
    }

    static /* synthetic */ void lambda$onCreateView$0(PayoutSelectFragment payoutSelectFragment, AdapterView parent, View view, int position, long rowId) {
        PayoutInfoType payoutInfoType = (PayoutInfoType) view.getTag();
        C6200PaymentInstrumentType paymentType = payoutInfoType.getInfoType();
        if (((LegacyAddPayoutActivity) payoutSelectFragment.getActivity()).isPaymentSupported(paymentType)) {
            ((LegacyAddPayoutActivity) payoutSelectFragment.getActivity()).startAddPayment(paymentType, payoutInfoType.getCurrencies());
        } else {
            ZenDialog.createSingleButtonDialog(C0880R.string.payout_unsupported_payout_title, C0880R.string.payout_unsupported_payout_body, C0880R.string.okay).show(payoutSelectFragment.getFragmentManager(), (String) null);
        }
    }

    private void loadSupportedPayoutMethods() {
        PayoutInfoTypesRequest.forCountry(this.mSelectedCountry).withListener((Observer) new NonResubscribableRequestListener<PayoutInfoTypesResponse>() {
            public void onErrorResponse(AirRequestNetworkException error) {
                NetworkUtil.toastGenericNetworkError(PayoutSelectFragment.this.getActivity());
                PayoutSelectFragment.this.getFragmentManager().popBackStack();
            }

            public void onResponse(PayoutInfoTypesResponse response) {
                if (PayoutSelectFragment.this.getActivity() != null) {
                    PayoutSelectFragment.this.mLoaderListView.finishLoader();
                    PayoutSelectFragment.this.setAdapter(response.payoutInfoTypes);
                    PayoutSelectFragment.this.mPaymentMethodResponse = response.payoutInfoTypes;
                }
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void setAdapter(List<PayoutInfoType> payoutMethods) {
        this.mLoaderListView.getListView().setAdapter(new PayoutMethodAdapter(getActivity(), C0880R.layout.list_item_payout_method, payoutMethods));
    }
}
