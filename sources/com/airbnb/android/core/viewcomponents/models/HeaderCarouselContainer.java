package com.airbnb.android.core.viewcomponents.models;

import android.support.p002v7.widget.RecyclerView.LayoutManager;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.view.View.OnClickListener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class HeaderCarouselContainer<A extends AirEpoxyAdapter> {
    private final DeprecatedCarouselEpoxyModel<A> carouselEpoxyModel = new DeprecatedCarouselEpoxyModel_();
    private final List<EpoxyModel<?>> models = ImmutableList.m1286of(this.sectionHeaderEpoxyModel, this.carouselEpoxyModel);
    private final SectionHeaderEpoxyModel_ sectionHeaderEpoxyModel = new SectionHeaderEpoxyModel_();

    public HeaderCarouselContainer<A> title(CharSequence string) {
        this.sectionHeaderEpoxyModel.title(string);
        return this;
    }

    public HeaderCarouselContainer<A> title(int stringRes) {
        this.sectionHeaderEpoxyModel.titleRes(stringRes);
        return this;
    }

    public HeaderCarouselContainer<A> description(CharSequence string) {
        this.sectionHeaderEpoxyModel.description(string);
        return this;
    }

    public HeaderCarouselContainer<A> description(int stringRes) {
        this.sectionHeaderEpoxyModel.descriptionRes(stringRes);
        return this;
    }

    public HeaderCarouselContainer<A> buttonText(CharSequence string) {
        this.sectionHeaderEpoxyModel.buttonText(string);
        return this;
    }

    public HeaderCarouselContainer<A> buttonText(int stringRes) {
        this.sectionHeaderEpoxyModel.buttonTextRes(stringRes);
        return this;
    }

    public HeaderCarouselContainer<A> buttonOnClickListener(OnClickListener listener) {
        this.sectionHeaderEpoxyModel.buttonOnClickListener(listener);
        return this;
    }

    public HeaderCarouselContainer<A> withDivider(boolean showDivider) {
        this.sectionHeaderEpoxyModel.showDivider(showDivider);
        return this;
    }

    public HeaderCarouselContainer<A> adapter(A adapter) {
        this.carouselEpoxyModel.adapter(adapter);
        return this;
    }

    public HeaderCarouselContainer<A> layoutManager(LayoutManager layoutManager) {
        this.carouselEpoxyModel.layoutManager(layoutManager);
        return this;
    }

    public HeaderCarouselContainer<A> forMicroCards(boolean forMicroCards) {
        this.carouselEpoxyModel.forMicroCards(forMicroCards);
        return this;
    }

    public HeaderCarouselContainer<A> carouselId(long id) {
        this.carouselEpoxyModel.m4502id(id);
        return this;
    }

    public A adapter() {
        return (AirEpoxyAdapter) this.carouselEpoxyModel.adapter();
    }

    public HeaderCarouselContainer<A> viewPool(RecycledViewPool recycledViewPool) {
        this.carouselEpoxyModel.viewPool(recycledViewPool);
        return this;
    }

    public SectionHeaderEpoxyModel getHeaderEpoxyModel() {
        return this.sectionHeaderEpoxyModel;
    }

    public DeprecatedCarouselEpoxyModel<A> getCarouselEpoxyModel() {
        return this.carouselEpoxyModel;
    }

    public List<EpoxyModel<?>> getEpoxyModels() {
        return this.models;
    }
}
