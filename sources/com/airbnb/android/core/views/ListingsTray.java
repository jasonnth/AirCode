package com.airbnb.android.core.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.paris.Paris;
import java.util.List;

public class ListingsTray extends LinearLayout {
    @BindView
    Carousel carousel;
    @BindView
    SectionHeader header;
    WishListManager wishListManager;

    public ListingsTray(Context context) {
        super(context);
        init(null);
    }

    public ListingsTray(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ListingsTray(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(1);
        inflate(getContext(), C0716R.layout.listings_tray, this);
        ButterKnife.bind((View) this);
        ((CoreGraph) CoreApplication.instance(getContext()).component()).inject(this);
        setupAttributes(attrs);
    }

    public void setup(CharSequence title, List<ListingTrayItem> items, CarouselItemClickListener carouselItemClickListener) {
        this.header.setTitle(title);
        ListingTrayCarouselAdapter adapter = new ListingTrayCarouselAdapter(carouselItemClickListener, getContext());
        adapter.setItems(items);
        this.carousel.swapAdapter(adapter, false);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0716R.styleable.ListingsTray, 0, 0);
        Paris.style(this.header).apply(a.getResourceId(C0716R.styleable.ListingsTray_n2_titleStyle, -1));
        a.recycle();
    }

    public void showHeader(boolean showHeader) {
        ViewUtils.setVisibleIf((View) this.header, showHeader);
    }

    public void subtitle(CharSequence subtitle) {
        this.header.setDescription(subtitle);
    }

    public void setOnSnapToPositionListener(OnSnapToPositionListener listener) {
        this.carousel.setSnapToPositionListener(listener);
    }
}
