package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class RequestAmenityFragment extends BasePurchaseAmenityFragment {
    public static final String ARG_PAID_AMENITIES = "paid_amenities";
    public static final String TAG = RequestAmenityFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public List<PaidAmenity> paidAmenities;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public class RequestAmenityAdapter extends AirEpoxyAdapter {
        private final DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();

        public RequestAmenityAdapter() {
            this.documentMarqueeModel.titleRes(C0880R.string.purchase_amenities_request_services_title);
            this.models.add(this.documentMarqueeModel);
            setUpAvailableAmenities();
        }

        private void setUpAvailableAmenities() {
            for (PaidAmenity amenity : RequestAmenityFragment.this.paidAmenities) {
                this.models.add(paidAmenityToRowEpoxyModel(amenity));
            }
        }

        private StandardRowEpoxyModel_ paidAmenityToRowEpoxyModel(PaidAmenity paidAmenity) {
            return new StandardRowEpoxyModel_().title((CharSequence) paidAmenity.getTitle()).infoText((CharSequence) paidAmenity.getDescription()).disclosure().clickListener(RequestAmenityFragment$RequestAmenityAdapter$$Lambda$1.lambdaFactory$(this, paidAmenity));
        }

        static /* synthetic */ void lambda$paidAmenityToRowEpoxyModel$0(RequestAmenityAdapter requestAmenityAdapter, PaidAmenity paidAmenity, View v) {
            RequestAmenityFragment.this.paidAmenityJitneyLogger.logGuestAddClickService();
            RequestAmenityFragment.this.navigationController.doneWithChooseAmenityToRequest(paidAmenity);
        }
    }

    public static RequestAmenityFragment newInstanceForAmenities(ArrayList<PaidAmenity> paidAmenities2) {
        return (RequestAmenityFragment) ((FragmentBundleBuilder) FragmentBundler.make(new RequestAmenityFragment()).putParcelableArrayList(ARG_PAID_AMENITIES, paidAmenities2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.paidAmenities = Lists.newArrayList((Iterable<? extends E>) getArguments().getParcelableArrayList(ARG_PAID_AMENITIES));
        this.recyclerView.setAdapter(new RequestAmenityAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }
}
