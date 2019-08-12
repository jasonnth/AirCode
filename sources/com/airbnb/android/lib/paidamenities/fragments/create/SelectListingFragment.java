package com.airbnb.android.lib.paidamenities.fragments.create;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class SelectListingFragment extends BaseCreateAmenityFragment {
    private static final String ARG_LISTINGS = "arg_listings";
    private SelectListingAdapter adapter;
    /* access modifiers changed from: private */
    public Listener listener;
    @State
    ArrayList<Listing> listings = new ArrayList<>();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        void onSelectListing(Listing listing);
    }

    public class SelectListingAdapter extends AirEpoxyAdapter {
        private final DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();

        public SelectListingAdapter() {
            this.documentMarqueeModel.titleRes(C0880R.string.paid_amenities_select_listing_title);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarqueeModel});
        }

        public void setListings(List<Listing> listings) {
            for (Listing listing : listings) {
                addModel(createListingRow(listing));
            }
        }

        private EpoxyModel<?> createListingRow(Listing listing) {
            return new StandardRowEpoxyModel_().title((CharSequence) listing.getName()).clickListener(SelectListingFragment$SelectListingAdapter$$Lambda$1.lambdaFactory$(this, listing));
        }
    }

    public static SelectListingFragment instanceForListings(ArrayList<Listing> listings2) {
        return (SelectListingFragment) ((FragmentBundleBuilder) FragmentBundler.make(new SelectListingFragment()).putParcelableArrayList(ARG_LISTINGS, listings2)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface.");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new SelectListingAdapter();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        if (this.listings.isEmpty()) {
            this.listings.addAll(getArguments().getParcelableArrayList(ARG_LISTINGS));
        }
        this.adapter.setListings(this.listings);
        return view;
    }
}
