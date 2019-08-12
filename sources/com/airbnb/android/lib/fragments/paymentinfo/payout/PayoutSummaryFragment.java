package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.airbnb.android.core.requests.GetExistingPayoutMethodRequest;
import com.airbnb.android.core.responses.GetExistingPayoutMethodResponse;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.EntryMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.List;
import p032rx.Observer;

public class PayoutSummaryFragment extends BasePaymentInfoFragment {
    private PayoutsEpoxyAdapter payoutsAdapter;
    final RequestListener<GetExistingPayoutMethodResponse> payoutsListener = new C0699RL().onResponse(PayoutSummaryFragment$$Lambda$1.lambdaFactory$(this)).onError(PayoutSummaryFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public class PayoutsEpoxyAdapter extends AirEpoxyAdapter {
        private final LoadingRowEpoxyModel loadingRowModel = new LoadingRowEpoxyModel_();
        private final EntryMarqueeEpoxyModel_ marqueeModel = new EntryMarqueeEpoxyModel_();
        private final StandardRowEpoxyModel_ noPayoutMethodRowModel = new StandardRowEpoxyModel_();

        public PayoutsEpoxyAdapter() {
            this.marqueeModel.title(C0880R.string.payout_summary_title);
            this.noPayoutMethodRowModel.title(C0880R.string.payout_summary_no_methods).actionText(C0880R.string.payout_summary_add_methods);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.loadingRowModel});
        }

        public void updateWithPayouts(List<PaymentInstrument> payouts) {
            removeModel(this.loadingRowModel);
            if (payouts.isEmpty()) {
                addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.noPayoutMethodRowModel});
                return;
            }
            for (PaymentInstrument payout : payouts) {
                if (payout.getType() != C6200PaymentInstrumentType.Unknown) {
                    addModels((EpoxyModel<?>[]) new EpoxyModel[]{createPayoutRow(payout)});
                }
            }
        }

        public void setDefaultPayout() {
        }

        public void deletePayout() {
        }

        private StandardRowEpoxyModel_ createPayoutRow(PaymentInstrument instrument) {
            return new StandardRowEpoxyModel_().title((CharSequence) instrument.getTitleText()).subtitle((CharSequence) instrument.getDetailText()).actionText(C0880R.string.payout_summary_edit_method);
        }
    }

    public static PayoutSummaryFragment newInstance() {
        return new PayoutSummaryFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payout_summary, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.payoutsAdapter = new PayoutsEpoxyAdapter();
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.payoutsAdapter);
        GetExistingPayoutMethodRequest.forHostPayouts().withListener((Observer) this.payoutsListener).execute(this.requestManager);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.payout_summary, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_add) {
            return super.onOptionsItemSelected(item);
        }
        getNavigationController().onAddPayout();
        return true;
    }
}
