package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.airbnb.p027n2.transitions.TransitionName;
import com.airbnb.p027n2.utils.DebouncedOnClickListener;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.facebook.internal.AnalyticsEvents;
import java.util.Collections;
import java.util.List;

/* renamed from: com.airbnb.n2.components.HomeMarquee */
public class HomeMarquee extends RelativeLayout {
    private final ImageCarouselAdapter adapter = new ImageCarouselAdapter();
    @BindView
    Carousel imageCarousel;
    @BindView
    AirTextView marqueeTitleView;
    @BindView
    AirTextView numReviewsView;
    @BindView
    View reviewAndStarsContainer;
    @BindView
    RatingBar starRating;

    /* renamed from: com.airbnb.n2.components.HomeMarquee$HomeMarqueeCarouselItem */
    public static class HomeMarqueeCarouselItem {
        /* access modifiers changed from: private */
        public final Image image;

        public HomeMarqueeCarouselItem(String url, String base64Preview) {
            this(new SimpleImage(url, base64Preview));
        }

        public HomeMarqueeCarouselItem(Image image2) {
            this.image = image2;
        }
    }

    /* renamed from: com.airbnb.n2.components.HomeMarquee$ImageCarouselAdapter */
    static class ImageCarouselAdapter extends Adapter<ImageCarouselViewHolder> {
        private List<HomeMarqueeCarouselItem> items = Collections.emptyList();
        private ImageCarouselItemClickListener listener;
        private long transitionNameId;

        /* renamed from: com.airbnb.n2.components.HomeMarquee$ImageCarouselAdapter$ImageCarouselViewHolder */
        static class ImageCarouselViewHolder extends ViewHolder {
            /* access modifiers changed from: private */
            public final AirImageView image = ((AirImageView) this.itemView.findViewById(R.id.img_listing_photo));
            private final int screenWidth = ViewLibUtils.getScreenWidth(this.itemView.getContext());

            public ImageCarouselViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_image_carousel_card, parent, false));
            }

            public void bind(HomeMarqueeCarouselItem item, final int position, long transitionNameId, final ImageCarouselItemClickListener listener) {
                this.itemView.getLayoutParams().width = this.screenWidth;
                this.image.setImage(item.image);
                ViewCompat.setTransitionName(this.image, TransitionName.toString("listing", transitionNameId, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, (long) position));
                this.itemView.setOnClickListener(new DebouncedOnClickListener() {
                    public void onClickDebounced(View v) {
                        if (listener != null) {
                            listener.onImageCarouselItemClicked(ImageCarouselViewHolder.this.image, position);
                        }
                    }
                });
            }

            public void unbind() {
                this.image.clear();
            }
        }

        public ImageCarouselAdapter() {
            setHasStableIds(true);
        }

        public ImageCarouselViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageCarouselViewHolder(parent);
        }

        public void onBindViewHolder(ImageCarouselViewHolder holder, int position) {
            holder.bind((HomeMarqueeCarouselItem) this.items.get(position), position, this.transitionNameId, this.listener);
        }

        public void setItems(long transitionNameId2, List<HomeMarqueeCarouselItem> urlsList) {
            this.transitionNameId = transitionNameId2;
            this.items = urlsList;
            notifyDataSetChanged();
        }

        public int getItemCount() {
            return this.items.size();
        }

        public void setListener(ImageCarouselItemClickListener listener2) {
            this.listener = listener2;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public void onViewRecycled(ImageCarouselViewHolder holder) {
            holder.unbind();
        }
    }

    /* renamed from: com.airbnb.n2.components.HomeMarquee$ImageCarouselItemClickListener */
    public interface ImageCarouselItemClickListener {
        void onImageCarouselItemClicked(ImageView imageView, int i);
    }

    public HomeMarquee(Context context) {
        super(context);
        init(null);
    }

    public HomeMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HomeMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_home_marquee, this);
        ButterKnife.bind((View) this);
        this.imageCarousel.setHasFixedSize(true);
        this.imageCarousel.setAdapter(this.adapter);
        setupAttrs(attrs);
    }

    private void setupAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_HomeMarquee, 0, 0);
        String marqueeTitle = ta.getString(R.styleable.n2_HomeMarquee_n2_marqueeTitleText);
        float numStars = ta.getFloat(R.styleable.n2_HomeMarquee_n2_numStars, 0.0f);
        int numReviews = ta.getInt(R.styleable.n2_HomeMarquee_n2_numReviews, 0);
        String reviewsText = ta.getString(R.styleable.n2_HomeMarquee_n2_reviewsText);
        setMarqueeTitle(marqueeTitle);
        setupStarRatingAndReviews(numStars, numReviews, reviewsText);
        ta.recycle();
    }

    public void setCarouselItems(long transitionNameId, List<HomeMarqueeCarouselItem> items) {
        this.adapter.setItems(transitionNameId, items);
    }

    public void scrollToCarouselPosition(int position) {
        this.imageCarousel.scrollToPosition(position);
    }

    public void setMarqueeTitle(CharSequence title) {
        ViewLibUtils.setVisibleIf(this.marqueeTitleView, !TextUtils.isEmpty(title));
        this.marqueeTitleView.setText(title);
    }

    public void setReviewsClickListener(OnClickListener listener) {
        this.reviewAndStarsContainer.setOnClickListener(listener);
    }

    public void setImageCarouselClickListener(ImageCarouselItemClickListener listener) {
        this.adapter.setListener(listener);
    }

    public void setSnapToPositionListener(OnSnapToPositionListener snapToPositionListener) {
        this.imageCarousel.setSnapToPositionListener(snapToPositionListener);
    }

    public void cancelAutoScrolling() {
        this.imageCarousel.cancelAutoScrolling();
    }

    public void setupStarRatingAndReviews(float numStars, int numReviews, CharSequence reviewsText) {
        boolean shouldShowStars = false;
        if (numStars <= 0.0f || numReviews <= 0) {
            this.reviewAndStarsContainer.setVisibility(8);
            return;
        }
        this.reviewAndStarsContainer.setVisibility(0);
        if (numReviews >= 3) {
            shouldShowStars = true;
        }
        ViewLibUtils.setVisibleIf(this.starRating, shouldShowStars);
        this.starRating.setRating(numStars);
        AirTextView airTextView = this.numReviewsView;
        if (shouldShowStars) {
            reviewsText = String.valueOf(numReviews);
        }
        airTextView.setText(reviewsText);
    }

    public void showStarRatingAndReviews(boolean show) {
        ViewLibUtils.setVisibleIf(this.reviewAndStarsContainer, show);
    }

    public static void mock(HomeMarquee marquee) {
        marquee.setMarqueeTitle("Title");
    }

    public static void mockWithRating(HomeMarquee marquee) {
        marquee.setMarqueeTitle("Title");
        marquee.setupStarRatingAndReviews(4.0f, 33, "33");
    }
}
