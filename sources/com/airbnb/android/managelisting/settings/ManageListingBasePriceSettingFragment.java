package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.BasePriceAdapter;
import com.airbnb.android.listing.adapters.BasePriceAdapter.ValidSettingsListener;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingBasePriceSettingFragment extends ManageListingBaseFragment {
    private BasePriceAdapter adapter;
    private final ValidSettingsListener listener = new ValidSettingsListener() {
        public void settingsAreValid(boolean valid) {
            if (ManageListingBasePriceSettingFragment.this.saveButton != null) {
                ManageListingBasePriceSettingFragment.this.saveButton.setEnabled(valid);
            }
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingBasePriceSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingBasePriceSettingFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingBasePriceSettingFragment create() {
        return new ManageListingBasePriceSettingFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingBasePriceSettingFragment manageListingBasePriceSettingFragment, SimpleListingResponse response) {
        manageListingBasePriceSettingFragment.saveButton.setState(State.Success);
        manageListingBasePriceSettingFragment.controller.actionExecutor.invalidateData();
        manageListingBasePriceSettingFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingBasePriceSettingFragment manageListingBasePriceSettingFragment, AirRequestNetworkException e) {
        manageListingBasePriceSettingFragment.adapter.setInputEnabled(true);
        manageListingBasePriceSettingFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingBasePriceSettingFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new BasePriceAdapter(ListingDisplayMode.ML, getContext(), this.controller.getListing(), this.listener, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        setHasOptionsMenu(true);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.saveButton.setState(State.Loading);
        this.adapter.setInputEnabled(false);
        UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_PRICE_KEY, Integer.valueOf(this.adapter.getPrice())).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
