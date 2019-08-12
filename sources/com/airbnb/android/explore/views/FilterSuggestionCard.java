package com.airbnb.android.explore.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.adapters.FilterSuggestionCarouselController;
import com.airbnb.android.explore.adapters.FilterSuggestionCarouselController.CarouselClickListener;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

public class FilterSuggestionCard extends LinearLayout {
    @BindView
    Carousel carousel;
    @BindView
    AirTextView titleView;

    public FilterSuggestionCard(Context context) {
        super(context);
        init(null);
    }

    public FilterSuggestionCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FilterSuggestionCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(1);
        inflate(getContext(), C0857R.layout.filter_suggestion_card, this);
        ButterKnife.bind((View) this);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getContext().getString(stringRes));
    }

    public void setTitle(CharSequence title) {
        ViewLibUtils.setVisibleIf(this.titleView, !TextUtils.isEmpty(title));
        this.titleView.setText(title);
    }

    public void setup(CharSequence title, List<FilterSuggestionItem> items, CarouselClickListener carouselItemClickListener) {
        setTitle(title);
        FilterSuggestionCarouselController controller = new FilterSuggestionCarouselController(carouselItemClickListener);
        controller.setData(items);
        this.carousel.swapAdapter(controller.getAdapter(), true);
    }
}
