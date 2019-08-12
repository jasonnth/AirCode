package com.airbnb.p027n2.collections;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.Collections;
import java.util.List;

/* renamed from: com.airbnb.n2.collections.BannerContainer */
public class BannerContainer extends FrameLayout {
    private BannerAdapter adapter;
    @BindView
    Carousel imageCarousel;
    private final OnSnapToPositionListener snapToPositionListener = new OnSnapToPositionListener() {
        public void onSnappedToPosition(int position, boolean userSwipedLeft, boolean autoScroll) {
            BannerViewHolder currentHolder = (BannerViewHolder) BannerContainer.this.imageCarousel.findViewHolderForAdapterPosition(position);
            BannerViewHolder leftHolder = (BannerViewHolder) BannerContainer.this.imageCarousel.findViewHolderForAdapterPosition(position - 1);
            BannerViewHolder rightHolder = (BannerViewHolder) BannerContainer.this.imageCarousel.findViewHolderForAdapterPosition(position + 1);
            if (currentHolder != null && !currentHolder.videoView.isPlaying()) {
                currentHolder.videoView.start();
            }
            if (leftHolder != null) {
                leftHolder.videoView.pause();
            }
            if (rightHolder != null) {
                rightHolder.videoView.pause();
            }
        }
    };

    /* renamed from: com.airbnb.n2.collections.BannerContainer$BannerAdapter */
    static class BannerAdapter extends Adapter<BannerViewHolder> {
        private List<BannerItem> items = Collections.emptyList();
        private BannerClickListener listener;
        /* access modifiers changed from: private */
        public final int paddingInPx;
        /* access modifiers changed from: private */
        public final int screenWidth;

        /* renamed from: com.airbnb.n2.collections.BannerContainer$BannerAdapter$BannerViewHolder */
        class BannerViewHolder extends ViewHolder {
            private final AirImageView image = ((AirImageView) this.itemView.findViewById(R.id.image));
            private final AirTextView subText = ((AirTextView) this.itemView.findViewById(R.id.subtext));
            private final AirTextView title = ((AirTextView) this.itemView.findViewById(R.id.title_text));
            /* access modifiers changed from: private */
            public final AirVideoView videoView = ((AirVideoView) this.itemView.findViewById(R.id.video_view));

            public BannerViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_banner, parent, false));
                this.videoView.setReleaseOnDetachFromWindow(false);
                adjustLayOutParams(this.itemView);
            }

            public void bind(BannerItem item, OnClickListener listener) {
                boolean hasVideoUrl = !TextUtils.isEmpty(item.videoUrl);
                ViewLibUtils.setVisibleIf(this.videoView, hasVideoUrl);
                ViewLibUtils.setGoneIf(this.image, hasVideoUrl);
                if (hasVideoUrl) {
                    this.videoView.setSrc(item.videoUrl);
                    this.videoView.setOnPreparedListener(BannerContainer$BannerAdapter$BannerViewHolder$$Lambda$1.lambdaFactory$(this));
                }
                this.image.setImageUrl(item.imageUrl);
                this.title.setText(item.title);
                this.subText.setText(item.subText);
                this.itemView.setOnClickListener(listener);
            }

            static /* synthetic */ void lambda$bind$0(BannerViewHolder bannerViewHolder) {
                if (bannerViewHolder.isChildFullyVisibleWidthWise(bannerViewHolder.itemView)) {
                    bannerViewHolder.videoView.start();
                }
            }

            private boolean isChildFullyVisibleWidthWise(View child) {
                return child.getLeft() > 0 && child.getRight() < BannerAdapter.this.screenWidth;
            }

            private void adjustLayOutParams(View view) {
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                params.width = (BannerAdapter.this.screenWidth - (BannerAdapter.this.paddingInPx * 2)) - ((params.leftMargin + params.rightMargin) * 2);
                view.setLayoutParams(params);
            }

            public void unbind() {
                this.image.clear();
            }
        }

        public BannerAdapter(Context context) {
            setHasStableIds(true);
            this.screenWidth = ViewLibUtils.getScreenWidth(context);
            this.paddingInPx = context.getResources().getDimensionPixelOffset(R.dimen.n2_horizontal_padding_tiny_half);
        }

        public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BannerViewHolder(parent);
        }

        public void onBindViewHolder(BannerViewHolder holder, int position) {
            int adjustedPosition = getAdjustedPosition(position);
            holder.bind((BannerItem) this.items.get(adjustedPosition), BannerContainer$BannerAdapter$$Lambda$1.lambdaFactory$(this, adjustedPosition));
        }

        public void setItems(List<BannerItem> urlsList) {
            this.items = urlsList;
            notifyDataSetChanged();
        }

        public int getItemCount() {
            if (shouldShowInfiniteCarousel()) {
                return Integer.MAX_VALUE;
            }
            return this.items.size();
        }

        public long getItemId(int position) {
            return (long) ((BannerItem) this.items.get(getAdjustedPosition(position))).hashCode();
        }

        public void onViewRecycled(BannerViewHolder holder) {
            holder.unbind();
        }

        public void setListener(BannerClickListener listener2) {
            this.listener = listener2;
        }

        /* access modifiers changed from: private */
        public boolean shouldShowInfiniteCarousel() {
            return this.items.size() >= 3;
        }

        private int getAdjustedPosition(int position) {
            return shouldShowInfiniteCarousel() ? position % this.items.size() : position;
        }
    }

    /* renamed from: com.airbnb.n2.collections.BannerContainer$BannerClickListener */
    public interface BannerClickListener {
        void onBannerClicked(int i, View view, int i2);
    }

    /* renamed from: com.airbnb.n2.collections.BannerContainer$BannerItem */
    public static class BannerItem {
        String imageUrl;
        String subText;
        String title;
        String videoUrl;

        public BannerItem(String imageUrl2, String videoUrl2, String title2, String subText2) {
            this.imageUrl = imageUrl2;
            this.videoUrl = videoUrl2;
            this.title = title2;
            this.subText = subText2;
        }
    }

    public BannerContainer(Context context) {
        super(context);
        init();
    }

    public BannerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BannerContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_banner_container, this);
        ButterKnife.bind((View) this);
        this.adapter = new BannerAdapter(getContext());
        this.imageCarousel.setHasFixedSize(true);
        this.imageCarousel.setAdapter(this.adapter);
        this.imageCarousel.setSnapToPositionListener(this.snapToPositionListener);
    }

    public void setCarouselItems(List<BannerItem> items) {
        this.adapter.setItems(items);
        if (this.adapter.shouldShowInfiniteCarousel()) {
            this.imageCarousel.getLayoutManager().scrollToPosition(1073741823);
        }
    }

    public void setBannerClickListener(BannerClickListener listener) {
        this.adapter.setListener(listener);
    }
}
