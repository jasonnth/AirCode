package com.airbnb.android.lib.fragments.find;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.p338n2.ListingSelectionView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;

public class ListingSelectionFragment extends AirFragment {
    private static final String ARG_SELECTED_LISTING = "selected_listing";
    private static final String ARG_USER = "user";
    private Listener listener;
    final RequestListener<ListingResponse> listingRequestListener = new RequestListener<ListingResponse>() {
        public void onResponse(ListingResponse response) {
            ListingSelectionFragment.this.listings = new ArrayList<>(response.getListings());
            ListingSelectionFragment.this.setupSelection();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            NetworkUtil.tryShowErrorWithSnackbar(ListingSelectionFragment.this.getView(), e);
        }
    };
    @State
    ArrayList<Listing> listings;
    @BindView
    View loader;
    @State
    Listing selectedListing;
    @BindView
    ListingSelectionView selectionView;
    @BindView
    AirToolbar toolbar;
    @State
    User user;

    public interface Listener {
        void listingChanged(Listing listing);
    }

    public static ListingSelectionFragment newInstance(User u, Listing selectedListing2) {
        return (ListingSelectionFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ListingSelectionFragment()).putParcelable("user", u)).putParcelable(ARG_SELECTED_LISTING, selectedListing2)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_listing_selection, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.user = (User) getArguments().getParcelable("user");
            this.selectedListing = (Listing) getArguments().getParcelable(ARG_SELECTED_LISTING);
            this.loader.setVisibility(0);
            ListingRequest.forMySpaces(this.user.getId(), true, this.listingRequestListener).execute(this.requestManager);
        } else {
            setupSelection();
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void setupSelection() {
        this.selectionView.initialize(this.listings);
        this.selectionView.setSelectedListing(this.selectedListing);
        this.loader.setVisibility(8);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSaveClicked() {
        this.listener.listingChanged(this.selectionView.getSelectedListing());
        getFragmentManager().popBackStack();
    }
}
