package com.airbnb.android.lib.paidamenities.fragments.create;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.PaidAmenityCategory;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.requests.GetPaidAmenityTypesRequest;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityTypesResponse;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class SelectAmenityTypeFragment extends BaseCreateAmenityFragment {
    private SelectAmenityTypeAdapter adapter;
    /* access modifiers changed from: private */
    public Listener listener;
    @State
    ArrayList<PaidAmenityCategory> paidAmenityCategories = new ArrayList<>();
    final RequestListener<PaidAmenityTypesResponse> paidAmenityCategoriesResponseRequestListener = new C0699RL().onResponse(SelectAmenityTypeFragment$$Lambda$1.lambdaFactory$(this)).onError(SelectAmenityTypeFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        void onSelectAmenityType(PaidAmenityCategory paidAmenityCategory);
    }

    public class SelectAmenityTypeAdapter extends AirEpoxyAdapter {
        private final DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        private final LoadingRowEpoxyModel loadingRowEpoxyModel = new LoadingRowEpoxyModel_();

        public SelectAmenityTypeAdapter() {
            this.documentMarqueeModel.titleRes(C0880R.string.paid_amenities_select_amenity_type_title);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarqueeModel, this.loadingRowEpoxyModel});
        }

        public void setPaidAmenityCategories(List<PaidAmenityCategory> paidAmenityCategories) {
            removeModel(this.loadingRowEpoxyModel);
            for (PaidAmenityCategory category : paidAmenityCategories) {
                buildAndAddPaidAmenityDisplayTypeRowModel(category);
            }
        }

        private void buildAndAddPaidAmenityDisplayTypeRowModel(PaidAmenityCategory paidAmenityCategory) {
            addModel(new StandardRowEpoxyModel_().titleRes(paidAmenityCategory.getAmenityServerType().getTitleRes()).clickListener(SelectAmenityTypeFragment$SelectAmenityTypeAdapter$$Lambda$1.lambdaFactory$(this, paidAmenityCategory)).disclosure());
        }
    }

    public static SelectAmenityTypeFragment newInstance() {
        return new SelectAmenityTypeFragment();
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
        this.adapter = new SelectAmenityTypeAdapter();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        if (this.paidAmenityCategories.isEmpty()) {
            GetPaidAmenityTypesRequest.forDefault().withListener((Observer) this.paidAmenityCategoriesResponseRequestListener).execute(this.requestManager);
        } else {
            this.adapter.setPaidAmenityCategories(this.paidAmenityCategories);
        }
        return view;
    }

    static /* synthetic */ void lambda$new$0(SelectAmenityTypeFragment selectAmenityTypeFragment, PaidAmenityTypesResponse response) {
        selectAmenityTypeFragment.paidAmenityCategories.addAll(response.paidAmenityCategories);
        selectAmenityTypeFragment.adapter.setPaidAmenityCategories(selectAmenityTypeFragment.paidAmenityCategories);
    }
}
