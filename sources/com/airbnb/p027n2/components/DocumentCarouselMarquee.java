package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.LinearLeftSnapHelper;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.DocumentCarouselMarquee */
public class DocumentCarouselMarquee extends LinearLayout {
    @BindView
    RecyclerView carousel;
    @BindView
    LoadingView loadingView;
    @BindView
    AirTextView titleText;

    public DocumentCarouselMarquee(Context context) {
        super(context);
        init(null);
    }

    public DocumentCarouselMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DocumentCarouselMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_document_carousel_marquee, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        setBackgroundResource(R.color.n2_babu);
        setupAttributes(attrs);
        this.carousel.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        new LinearLeftSnapHelper().attachToRecyclerView(this.carousel);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_Marquee);
        setTitle((CharSequence) a.getString(R.styleable.n2_Marquee_n2_titleText));
        a.recycle();
    }

    public void setLoading(boolean loading) {
        ViewLibUtils.setVisibleIf(this.loadingView, loading);
        ViewLibUtils.setGoneIf(this.carousel, loading);
    }

    public void setAdapter(EpoxyAdapter adapter) {
        this.carousel.setAdapter(adapter);
    }

    public void setTitle(CharSequence title) {
        this.titleText.setText(title);
    }

    public void setTitle(int titleRes) {
        this.titleText.setText(titleRes);
    }

    public static void mock(DocumentCarouselMarquee view) {
        view.setTitle((CharSequence) "Title");
    }
}
