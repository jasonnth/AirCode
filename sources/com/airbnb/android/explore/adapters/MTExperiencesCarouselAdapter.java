package com.airbnb.android.explore.adapters;

import com.airbnb.android.core.viewcomponents.SimpleAirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import java.util.Collections;
import java.util.List;

public class MTExperiencesCarouselAdapter extends SimpleAirEpoxyAdapter {
    private PaginationListener paginationListener;
    private LoadingRowEpoxyModel_ paginationModel;

    public interface PaginationListener {
        void onPaginationModelBound();
    }

    public MTExperiencesCarouselAdapter(EpoxyModel<?> loadingEpoxyModel) {
        super(false);
        setFilterDuplicates(true);
        setModels(Collections.singletonList(loadingEpoxyModel));
    }

    public EpoxyModel<?> getItemAt(int position) {
        return (EpoxyModel) this.models.get(position);
    }

    public void setPaginationModel(LoadingRowEpoxyModel_ paginationModel2) {
        this.paginationModel = paginationModel2;
    }

    public void setPaginationListener(PaginationListener paginationListener2) {
        this.paginationListener = paginationListener2;
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position, List<Object> payloads) {
        super.onModelBound(holder, model, position, payloads);
        if (this.paginationModel != null && this.paginationListener != null && this.paginationModel == model) {
            this.paginationListener.onPaginationModelBound();
        }
    }
}
