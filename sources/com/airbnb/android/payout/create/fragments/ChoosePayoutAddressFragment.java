package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.create.controllers.ChoosePayoutAddressEpoxyController;
import com.airbnb.android.payout.create.controllers.ChoosePayoutAddressEpoxyController.Listener;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;
import com.google.common.collect.Maps;
import icepick.State;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ChoosePayoutAddressFragment extends BaseAddPayoutMethodFragment implements Listener {
    @State
    HashMap<AirAddress, Boolean> addressSelectionMap;
    @BindView
    FixedFlowActionAdvanceFooter advanceFooter;
    private ChoosePayoutAddressEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ChoosePayoutAddressFragment newInstance() {
        return new ChoosePayoutAddressFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7552R.layout.fragment_payout_recycler_view_with_toolbar_and_advance_button, container, false);
        bindViews(view);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        setToolbar(this.toolbar);
        this.epoxyController = new ChoosePayoutAddressEpoxyController(this);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.advanceFooter.setButtonEnabled(this.dataController.getSelectedPayoutAddress() != null);
        this.advanceFooter.setButtonOnClickListener(addressNextStepClickListener());
        setupUserAddress();
    }

    private void setupUserAddress() {
        if (!this.dataController.hasUserAddresses()) {
            this.dataController.fetchUserAddress();
            return;
        }
        initAddressSelectionMap(this.dataController.getUserAddresses());
        this.epoxyController.setAddresses(this.addressSelectionMap);
    }

    public void onFetchUserAddressSuccess(List<AirAddress> addresses) {
        initAddressSelectionMap(addresses);
        this.epoxyController.setAddresses(this.addressSelectionMap);
    }

    public void onFetchUserAddressError(AirRequestNetworkException error) {
        NetworkUtil.tryShowErrorWithSnackbar(getView(), error);
    }

    private void initAddressSelectionMap(List<AirAddress> addresses) {
        this.addressSelectionMap = Maps.newHashMap();
        for (AirAddress address : addresses) {
            this.addressSelectionMap.put(address, Boolean.valueOf(false));
        }
    }

    public void onAddressCheckChanged(AirAddress address, boolean checked) {
        this.addressSelectionMap.put(address, Boolean.valueOf(checked));
        if (checked) {
            for (Entry<AirAddress, Boolean> entry : this.addressSelectionMap.entrySet()) {
                if (!((AirAddress) entry.getKey()).equals(address)) {
                    entry.setValue(Boolean.valueOf(false));
                }
            }
            this.dataController.setPayoutAddress(address);
            this.advanceFooter.setButtonEnabled(true);
        } else {
            this.advanceFooter.setButtonEnabled(false);
        }
        this.epoxyController.setAddresses(this.addressSelectionMap);
    }

    public void onClickAddNewAddress() {
        logPayoutSetup(C2548PayoutMethodSetupPage.AddressPicker, C2545PayoutMethodAction.EnterNewAddress);
        this.navigationController.navigateToAddNewAddress();
    }

    public void onFetchRedirectUrlSuccess() {
        this.advanceFooter.setButtonLoading(false);
        this.navigationController.doneWithAddPayoutAddress((String) Check.notNull(this.dataController.getRedirectUrl()));
    }

    public void onFetchRedirectUrlError(AirRequestNetworkException error) {
        this.advanceFooter.setButtonLoading(false);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), error);
    }

    private OnClickListener addressNextStepClickListener() {
        return ChoosePayoutAddressFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$addressNextStepClickListener$0(ChoosePayoutAddressFragment choosePayoutAddressFragment, View v) {
        choosePayoutAddressFragment.logPayoutSetup(C2548PayoutMethodSetupPage.AddressPicker, C2545PayoutMethodAction.Next);
        if (choosePayoutAddressFragment.dataController.getSelectedPayoutInfoForm().isURLRedirect()) {
            choosePayoutAddressFragment.advanceFooter.setButtonLoading(true);
            choosePayoutAddressFragment.dataController.fetchRedirectUrl();
            return;
        }
        choosePayoutAddressFragment.navigationController.doneWithAddPayoutAddress();
    }
}
