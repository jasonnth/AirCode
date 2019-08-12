package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.PaidAmenityJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.requests.DeletePaidAmenityRequest;
import com.airbnb.android.lib.paidamenities.requests.FetchAllPaidAmenitiesRequest;
import com.airbnb.android.lib.paidamenities.requests.FetchAllPaidAmenitiesRequest.Format;
import com.airbnb.android.lib.paidamenities.responses.FetchAllPaidAmenitiesResponse;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityResponse;
import com.airbnb.android.lib.paidamenities.utils.PaidAmenityUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import p032rx.Observer;

public class HostAmenityListFragment extends AirFragment {
    private static final int REQUEST_CODE_DELETE_SERVICE = 233;
    private HostAmenityListAdapter adapter;
    final RequestListener<PaidAmenityResponse> deletePaidAmenityRequestListener = new C0699RL().onResponse(HostAmenityListFragment$$Lambda$3.lambdaFactory$(this)).onError(HostAmenityListFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<FetchAllPaidAmenitiesResponse> fetchAllPaidAmenitiesRequestListener = new C0699RL().onResponse(HostAmenityListFragment$$Lambda$1.lambdaFactory$(this)).onError(HostAmenityListFragment$$Lambda$2.lambdaFactory$(this)).build();
    private Listener listener;
    @State
    long listingId;
    @State
    ArrayList<PaidAmenity> paidAmenities;
    PaidAmenityJitneyLogger paidAmenityJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public PaidAmenity selectedAmenity;
    @BindView
    AirToolbar toolbar;

    public class HostAmenityListAdapter extends AirEpoxyAdapter {
        private final LinkActionRowEpoxyModel_ addAServiceRowEpoxyModel = new LinkActionRowEpoxyModel_();
        private final Map<Long, StandardRowEpoxyModel_> amenityIdToRowEpoxyModel = Maps.newHashMap();
        private final DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel = new DocumentMarqueeEpoxyModel_();
        private final LoadingRowEpoxyModel_ loadingRowEpoxyModel = new LoadingRowEpoxyModel_();

        public HostAmenityListAdapter() {
            configureDocumentMarquee();
            if (HostAmenityListFragment.this.paidAmenities == null || HostAmenityListFragment.this.paidAmenities.isEmpty()) {
                this.models.add(this.loadingRowEpoxyModel);
                return;
            }
            configurePaidAmenities();
            configureAddAServiceLinkRow();
        }

        public void setAmenities() {
            removeModel(this.loadingRowEpoxyModel);
            configurePaidAmenities();
            configureAddAServiceLinkRow();
        }

        private void configureAddAServiceLinkRow() {
            this.addAServiceRowEpoxyModel.textRes(C0880R.string.host_amenities_add_another_service_link).showDivider(false).clickListener(HostAmenityListFragment$HostAmenityListAdapter$$Lambda$1.lambdaFactory$(this));
            this.models.add(this.addAServiceRowEpoxyModel);
        }

        private void configurePaidAmenities() {
            Iterator it = HostAmenityListFragment.this.paidAmenities.iterator();
            while (it.hasNext()) {
                addPaidAmenity((PaidAmenity) it.next());
            }
        }

        private void configureDocumentMarquee() {
            this.documentMarqueeEpoxyModel.titleRes(C0880R.string.host_amenities_list_title);
            this.models.add(this.documentMarqueeEpoxyModel);
        }

        private StandardRowEpoxyModel_ amenityToRowEpoxyModel(PaidAmenity amenity) {
            return new StandardRowEpoxyModel_().title((CharSequence) amenity.getTitle()).subtitle((CharSequence) PaidAmenityUtils.getFormattedPrice(HostAmenityListFragment.this.getContext(), amenity.getLocalizedTotalPrice())).longClickListener(HostAmenityListFragment$HostAmenityListAdapter$$Lambda$2.lambdaFactory$(this, amenity));
        }

        static /* synthetic */ boolean lambda$amenityToRowEpoxyModel$1(HostAmenityListAdapter hostAmenityListAdapter, PaidAmenity amenity, View v) {
            HostAmenityListFragment.this.selectedAmenity = amenity;
            DeleteAmenityDialog.newInstance(HostAmenityListFragment.REQUEST_CODE_DELETE_SERVICE).show(HostAmenityListFragment.this.getFragmentManager(), (String) null);
            HostAmenityListFragment.this.paidAmenityJitneyLogger.logHostAmendClickDelete(HostAmenityListFragment.this.selectedAmenity.getId());
            return true;
        }

        private void addPaidAmenity(PaidAmenity amenity) {
            StandardRowEpoxyModel_ row = amenityToRowEpoxyModel(amenity);
            this.models.add(row);
            this.amenityIdToRowEpoxyModel.put(Long.valueOf(amenity.getId()), row);
        }

        /* access modifiers changed from: private */
        public void deletePaidAmenityRowModel(long amenityId) {
            Check.notNull((StandardRowEpoxyModel_) this.amenityIdToRowEpoxyModel.get(Long.valueOf(amenityId)));
            removeModel((EpoxyModel) this.amenityIdToRowEpoxyModel.get(Long.valueOf(amenityId)));
        }
    }

    public interface Listener {
        void onExit(boolean z);
    }

    public static HostAmenityListFragment newInstanceWithListingId(long listingId2) {
        return (HostAmenityListFragment) ((FragmentBundleBuilder) FragmentBundler.make(new HostAmenityListFragment()).putLong("listing_id", listingId2)).build();
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
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setNavigationOnClickListener(HostAmenityListFragment$$Lambda$5.lambdaFactory$(this));
        this.adapter = new HostAmenityListAdapter();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        if (this.paidAmenities == null) {
            this.listingId = getArguments().getLong("listing_id");
            fetchAvailableHostAmenities();
        }
        return view;
    }

    public void onDetach() {
        this.listener = null;
        super.onDetach();
    }

    private void fetchAvailableHostAmenities() {
        FetchAllPaidAmenitiesRequest.forListingId(this.listingId, Format.ListView).withListener((Observer) this.fetchAllPaidAmenitiesRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void createNewAmenity() {
        startActivity(PaidAmenityIntents.createAmenitiesWithListingIdIntent(getContext(), this.listingId));
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void deletePaidAmenity(PaidAmenity paidAmenity) {
        this.paidAmenities.remove(paidAmenity);
        this.adapter.deletePaidAmenityRowModel(paidAmenity.getId());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_DELETE_SERVICE) {
            DeletePaidAmenityRequest.forPaidAmenityId(this.selectedAmenity.getId()).withListener((Observer) this.deletePaidAmenityRequestListener).execute(this.requestManager);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    static /* synthetic */ void lambda$new$1(HostAmenityListFragment hostAmenityListFragment, FetchAllPaidAmenitiesResponse response) {
        if (hostAmenityListFragment.paidAmenities == null) {
            hostAmenityListFragment.paidAmenities = Lists.newArrayList();
        }
        hostAmenityListFragment.paidAmenities.addAll(response.paidAmenities);
        hostAmenityListFragment.adapter.setAmenities();
    }
}
