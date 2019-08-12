package com.airbnb.p027n2.components;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.Collections;
import java.util.List;

/* renamed from: com.airbnb.n2.components.ScratchPhotoCarouselMarquee */
public class ScratchPhotoCarouselMarquee extends LinearLayout {
    private final PhotoCarouselAdapter adapter = new PhotoCarouselAdapter();
    @BindView
    Carousel carousel;

    /* renamed from: com.airbnb.n2.components.ScratchPhotoCarouselMarquee$PhotoCarouselAdapter */
    static class PhotoCarouselAdapter extends Adapter<PhotoViewHolder> {
        private List<String> items = Collections.emptyList();

        /* renamed from: com.airbnb.n2.components.ScratchPhotoCarouselMarquee$PhotoCarouselAdapter$PhotoViewHolder */
        static class PhotoViewHolder extends ViewHolder {
            public PhotoViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_photo_carousel_item, parent, false));
            }

            public void bind(String item) {
                ((AirImageView) this.itemView).setImageUrl(item);
            }

            public void unbind() {
                ((AirImageView) this.itemView).clear();
            }
        }

        public PhotoCarouselAdapter() {
            setHasStableIds(true);
        }

        public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PhotoViewHolder(parent);
        }

        public void onBindViewHolder(PhotoViewHolder holder, int position) {
            holder.bind((String) this.items.get(position));
        }

        public void setItems(List<String> photoUrls) {
            this.items = photoUrls;
            notifyDataSetChanged();
        }

        public int getItemCount() {
            return this.items.size();
        }

        public long getItemId(int position) {
            return (long) ((String) this.items.get(position)).hashCode();
        }

        public void onViewRecycled(PhotoViewHolder holder) {
            holder.unbind();
        }
    }

    public ScratchPhotoCarouselMarquee(Context context) {
        super(context);
        init();
    }

    public ScratchPhotoCarouselMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScratchPhotoCarouselMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_photo_carousel_marquee, this);
        ButterKnife.bind((View) this);
        this.carousel.setHasFixedSize(true);
        this.carousel.setAdapter(this.adapter);
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.adapter.setItems(photoUrls);
    }
}
