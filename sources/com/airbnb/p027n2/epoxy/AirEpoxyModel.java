package com.airbnb.p027n2.epoxy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.n2.R;
import com.airbnb.p027n2.N2Context;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.interfaces.DividerView;
import java.util.List;

/* renamed from: com.airbnb.n2.epoxy.AirEpoxyModel */
public abstract class AirEpoxyModel<T extends View> extends EpoxyModel<T> implements AirModel {
    protected NumCarouselItemsShown numCarouselItemsShown;
    protected Boolean showDivider;

    public AirEpoxyModel(long id) {
        super(id);
    }

    public AirEpoxyModel() {
    }

    public void bind(T view) {
        updateDivider(view);
        adjustViewWidth(view);
    }

    public void bind(T view, List<Object> list) {
        updateDivider(view);
        adjustViewWidth(view);
    }

    private void updateDivider(T view) {
        if (view instanceof DividerView) {
            ((DividerView) view).showDivider(this.showDivider != null && this.showDivider.booleanValue());
        } else if (this.showDivider != null) {
            N2Context.instance().graph().mo11971n2().throwOrNotify(new IllegalStateException("View " + view + " must implement DividerView to use showDivider"));
        }
    }

    private void adjustViewWidth(T view) {
        if (this.numCarouselItemsShown != null) {
            LayoutParams params = view.getLayoutParams();
            view.setTag(R.id.n2_epoxy_model_view_initial_width_id, Integer.valueOf(params.width));
            int margin = 0;
            if (params instanceof MarginLayoutParams) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) params;
                margin = (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin) / 2;
            }
            Context context = view.getContext();
            params.width = Carousel.getCarouselCardWidth(context, this.numCarouselItemsShown.forCurrentScreen(context), margin);
            view.requestLayout();
        }
    }

    public void unbind(T view) {
        super.unbind(view);
        restoreInitialViewWidth(view);
    }

    private void restoreInitialViewWidth(T view) {
        if (this.numCarouselItemsShown != null) {
            Object initialWidth = view.getTag(R.id.n2_epoxy_model_view_initial_width_id);
            if (initialWidth instanceof Integer) {
                view.getLayoutParams().width = ((Integer) initialWidth).intValue();
                return;
            }
            N2Context.instance().graph().mo11971n2().throwOrNotify(new IllegalStateException("Expected tag to hold view height but was " + initialWidth));
        }
    }

    public final boolean supportsDividers() {
        return (getDividerViewType() == -1 || getDividerViewType() == 2 || getDividerViewType() == 5) ? false : true;
    }

    public int getDividerViewType() {
        return -1;
    }

    public AirEpoxyModel<T> showDivider(boolean showDivider2) {
        if (!supportsDividers()) {
            N2Context.instance().graph().mo11971n2().throwOrNotify(new IllegalStateException("Model does not support dividers"));
        }
        this.showDivider = Boolean.valueOf(showDivider2);
        return this;
    }

    public final Boolean isShowingDivider() {
        return this.showDivider;
    }

    public AirEpoxyModel<T> numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown2) {
        this.numCarouselItemsShown = numCarouselItemsShown2;
        return this;
    }

    public NumCarouselItemsShown numCarouselItemsShown() {
        return this.numCarouselItemsShown;
    }

    public AirEpoxyModel<T> numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        spanSizeOverride(numItemsInGridRow);
        return this;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return EpoxyUtils.getDefaultSpanForDividerType(getDividerViewType(), totalSpanCount, position, itemCount);
    }

    public AirEpoxyModel<T> reset() {
        super.reset();
        this.showDivider = null;
        return this;
    }

    public boolean canReuseUpdatedView(List<Object> list) {
        return true;
    }

    public boolean isRearrangeable() {
        return false;
    }

    public void onDraggingStateChanged(T t, boolean isDragging) {
    }
}
