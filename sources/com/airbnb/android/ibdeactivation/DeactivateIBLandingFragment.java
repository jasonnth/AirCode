package com.airbnb.android.ibdeactivation;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.enums.DeactivateIBReason;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.io.Serializable;

public class DeactivateIBLandingFragment extends AirFragment {
    private static final String LISTING_KEY = "listing";
    private static final String REASON_KEY = "deactivate_reason";
    @BindView
    View containerView;
    @State
    Listing listing;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public interface DeactivateIBNavigator {
        void onDeactivateReasonSelected(DeactivateIBReason deactivateIBReason);
    }

    public static DeactivateIBLandingFragment newInstanceForListing(Listing listing2) {
        return (DeactivateIBLandingFragment) ((FragmentBundleBuilder) FragmentBundler.make(new DeactivateIBLandingFragment()).putParcelable("listing", listing2)).build();
    }

    public static DeactivateIBLandingFragment newInstanceForReason(Listing listing2, DeactivateIBReason reason) {
        return (DeactivateIBLandingFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new DeactivateIBLandingFragment()).putSerializable(REASON_KEY, (Serializable) Check.notNull(reason))).putParcelable("listing", listing2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6454R.layout.fragment_deactivate_ib_landing, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        DeactivateIBReason reason = null;
        if (getArguments() != null && getArguments().containsKey(REASON_KEY)) {
            reason = (DeactivateIBReason) getArguments().getSerializable(REASON_KEY);
        }
        if (reason == null || reason == DeactivateIBReason.AirbnbRequirements) {
            this.containerView.setVisibility(8);
            if (reason == DeactivateIBReason.AirbnbRequirements) {
                this.toolbar.setNavigationIcon(2);
                this.toolbar.setNavigationOnClickListener(DeactivateIBLandingFragment$$Lambda$1.lambdaFactory$(this));
            }
        }
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setVerticalScrollBarEnabled(false);
        this.recyclerView.setAdapter(new DeactivateIBReasonsAdapter(getDeactivateIBActivity().getNavigator(), reason));
        if (savedInstanceState == null) {
            DeactivateIBAnalytics.trackReasonPageLoad(reason);
        }
        this.listing = (Listing) getArguments().getParcelable("listing");
        return view;
    }

    private DeactivateIBActivity getDeactivateIBActivity() {
        Check.state(getActivity() instanceof DeactivateIBActivity);
        return (DeactivateIBActivity) getActivity();
    }

    @OnClick
    public void clickCancel() {
        DeactivateIBAnalytics.trackKeepIBOnClick(Long.valueOf(this.listing.getId()), Long.valueOf(this.listing.getHost().getId()));
        getActivity().setResult(0);
        getActivity().finish();
    }

    @OnClick
    public void onDeactivateClicked() {
        DeactivateIBAnalytics.trackCloseModal(Long.valueOf(this.listing.getId()), Long.valueOf(this.listing.getHost().getId()));
        getDeactivateIBActivity().showTellUsMoreFragment();
    }
}
