package com.airbnb.android.explore.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.adapters.FilterRemovalSuggestionCarouselAdapter;
import com.airbnb.android.explore.adapters.FilterRemovalSuggestionCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.explore.adapters.FilterSuggestionCarouselController;
import com.airbnb.android.explore.adapters.FilterSuggestionCarouselController.CarouselClickListener;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

public class FilterRemovalSuggestionCard extends LinearLayout implements DividerView {
    @BindView
    Carousel carousel;
    @BindView
    AirTextView descriptionView;
    @BindView
    View sectionDivider;
    @BindView
    AirTextView titleView;

    public FilterRemovalSuggestionCard(Context context) {
        super(context);
        init(null);
    }

    public FilterRemovalSuggestionCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FilterRemovalSuggestionCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(1);
        inflate(getContext(), C0857R.layout.filter_removal_suggestion_card, this);
        ButterKnife.bind((View) this);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getContext().getString(stringRes));
    }

    public void setTitle(CharSequence title) {
        ViewLibUtils.setVisibleIf(this.titleView, !TextUtils.isEmpty(title));
        this.titleView.setText(title);
    }

    public void setDescription(int stringRes) {
        setDescription((CharSequence) getContext().getString(stringRes));
    }

    public void setDescription(CharSequence description) {
        ViewLibUtils.setVisibleIf(this.descriptionView, !TextUtils.isEmpty(description));
        this.descriptionView.setText(description);
    }

    public void setup(CharSequence title, CharSequence description, List<FilterRemovalSuggestionItem> items, CarouselItemClickListener carouselItemClickListener) {
        setTitle(title);
        setDescription(description);
        FilterRemovalSuggestionCarouselAdapter adapter = new FilterRemovalSuggestionCarouselAdapter(carouselItemClickListener, getContext());
        adapter.setItems(items);
        this.carousel.swapAdapter(adapter, false);
    }

    public void setupForSuggestions(CharSequence title, List<FilterSuggestionItem> items, CarouselClickListener carouselItemClickListener) {
        setTitle(title);
        FilterSuggestionCarouselController controller = new FilterSuggestionCarouselController(carouselItemClickListener);
        controller.setData(items);
        this.carousel.swapAdapter(controller.getAdapter(), true);
    }

    public void showDivider(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }
}
