package com.airbnb.android.payout.manage;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.PayoutGraph;
import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;
import com.airbnb.android.payout.manage.controllers.EditPayoutEpoxyController;
import com.airbnb.android.payout.manage.controllers.EditPayoutEpoxyController.Listener;
import com.airbnb.android.payout.manage.controllers.ManagePayoutDataController;
import com.airbnb.android.payout.manage.controllers.ManagePayoutDataController.ManagePayoutDataChangedListener;
import com.airbnb.jitney.event.logging.PayoutMethodSelectAction.p192v1.C2547PayoutMethodSelectAction;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class EditPayoutFragment extends AirFragment implements Listener, ManagePayoutDataChangedListener {
    AddPayoutMethodJitneyLogger addPayoutMethodJitneyLogger;
    private ManagePayoutDataController dataController;
    private EditPayoutEpoxyController epoxyController;
    @State
    ArrayList<PaymentInstrument> payoutInstruments;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static EditPayoutFragment newInstance() {
        return new EditPayoutFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PayoutGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_payout_recycler_view_with_toolbar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.epoxyController = new EditPayoutEpoxyController(getActivity(), this);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        this.epoxyController.requestModelBuild();
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.dataController = ((ManagePayoutControllerInterface) getActivity()).getDataController();
        this.dataController.addListener(this);
        if (!this.dataController.hasExistingPayoutMethods()) {
            this.dataController.fetchExistingPayoutMethods();
        } else {
            updateExistingPayoutMethods(this.dataController.getExistingPayoutMethods());
        }
    }

    public void onClickAddNewPayoutMethod() {
        this.addPayoutMethodJitneyLogger.payoutMethodSelect(C2547PayoutMethodSelectAction.AddNewPayout);
        startActivity(PayoutActivityIntents.forSelectPayoutCountry(getActivity()));
        getActivity().finish();
    }

    public void onFetchExistingPayoutMethodSuccess(List<PaymentInstrument> payoutInstruments2) {
        updateExistingPayoutMethods(payoutInstruments2);
    }

    public void onFetchExistingPayoutMethodError(AirRequestNetworkException error) {
        NetworkUtil.tryShowErrorWithSnackbar(getView(), error);
    }

    private void updateExistingPayoutMethods(List<PaymentInstrument> payoutInstruments2) {
        this.payoutInstruments = Lists.newArrayList((Iterable<? extends E>) payoutInstruments2);
        this.epoxyController.setPayoutInstruments(payoutInstruments2);
    }
}
