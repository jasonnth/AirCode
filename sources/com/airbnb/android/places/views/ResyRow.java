package com.airbnb.android.places.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.adapters.CarouselAdapter;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.ResyController.ResyTimeSlotClickListener;
import com.airbnb.android.places.viewmodels.ResyTimeSlotEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ResyRow extends LinearLayout implements DividerView {
    @BindView
    LinearLayout actionView;
    private final CarouselAdapter adapter = new CarouselAdapter();
    @BindView
    View changeButton;
    @BindView
    View emptyState;
    @BindView
    View loadingView;
    private ResyTimeSlotClickListener resyTimeSlotClickListener;
    @BindView
    View sectionDivider;
    @BindView
    Carousel timesCarousel;
    @BindView
    AirTextView titleView;

    public ResyRow(Context context) {
        super(context);
        init();
    }

    public ResyRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResyRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), C7627R.layout.view_resy_row, this);
        ButterKnife.bind((View) this);
        this.timesCarousel.setAdapter(this.adapter);
    }

    public void setTitle(CharSequence title, OnClickListener listener) {
        if (!TextUtils.isEmpty(title)) {
            this.titleView.setText(title);
        } else {
            this.titleView.setText(C7627R.string.resy_available);
        }
        this.actionView.setOnClickListener(listener);
        ViewLibUtils.setVisibleIf(this.changeButton, listener != null);
    }

    public void setupTimeSlots(List<RestaurantAvailability> timeSlots, RestaurantAvailability selectedTime, boolean isLoading) {
        boolean z;
        boolean z2 = true;
        boolean isEmpty = ListUtils.isEmpty((Collection<?>) timeSlots);
        List<ResyTimeSlotEpoxyModel_> timeSlotModels = Collections.emptyList();
        if (!isLoading && !isEmpty) {
            timeSlotModels = FluentIterable.from((Iterable<E>) timeSlots).transform(ResyRow$$Lambda$1.lambdaFactory$(this, selectedTime != null ? selectedTime.getId() : -1)).toList();
            ((ResyTimeSlotEpoxyModel_) timeSlotModels.get(timeSlotModels.size() - 1)).isLast(true);
        }
        this.adapter.setItems(timeSlotModels);
        Carousel carousel = this.timesCarousel;
        if (isEmpty || isLoading) {
            z = false;
        } else {
            z = true;
        }
        ViewLibUtils.setVisibleIf(carousel, z);
        View view = this.emptyState;
        if (!isEmpty || isLoading) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(view, z2);
        ViewLibUtils.setVisibleIf(this.loadingView, isLoading);
    }

    /* access modifiers changed from: private */
    public ResyTimeSlotEpoxyModel_ getTimeSlotModel(RestaurantAvailability timeSlot, int selectedId) {
        return new ResyTimeSlotEpoxyModel_().title(timeSlot.getTimeString(getContext())).subtitle(timeSlot.getTableType()).isSelected(timeSlot.getId() == selectedId).clickListener(ResyRow$$Lambda$2.lambdaFactory$(this, timeSlot)).m6475id((long) timeSlot.getId());
    }

    public void setTimeSlotClickListener(ResyTimeSlotClickListener listener) {
        this.resyTimeSlotClickListener = listener;
    }

    /* access modifiers changed from: private */
    public void onTimeSlotClick(RestaurantAvailability timeSlot) {
        if (this.resyTimeSlotClickListener != null) {
            this.resyTimeSlotClickListener.onClick(timeSlot);
        }
    }

    public void showDivider(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }
}
