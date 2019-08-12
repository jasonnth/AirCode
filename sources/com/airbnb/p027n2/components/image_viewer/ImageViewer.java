package com.airbnb.p027n2.components.image_viewer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.MotionEventCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.ViewDragHelper;
import android.support.p000v4.widget.ViewDragHelper.Callback;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.collections.CarouselLayoutManager;
import com.airbnb.p027n2.primitives.LoadingDrawable;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.airbnb.p027n2.transitions.TransitionName;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.internal.AnalyticsEvents;
import java.util.Collections;
import java.util.List;

/* renamed from: com.airbnb.n2.components.image_viewer.ImageViewer */
public class ImageViewer extends Carousel implements RequestListener<String, Bitmap> {
    /* access modifiers changed from: private */
    public ImageViewerAdapter adapter;
    /* access modifiers changed from: private */
    public ViewDragHelper dragHelper;
    private boolean hasCalledFirstImageLoadedListener;
    private boolean hasLoadedImage;
    private CarouselLayoutManager layoutManager;
    private OnFirstImageLoadedListener onFirstImageLoadedListener;
    /* access modifiers changed from: private */
    public OnViewDragCallback viewDragCallback;

    /* renamed from: com.airbnb.n2.components.image_viewer.ImageViewer$ImageViewerAdapter */
    private class ImageViewerAdapter extends Adapter<ImageViewerViewHolder> {
        private float dragPercentage;
        private List<ImageViewerData> imageData;
        private long transitionNameId;
        private String transitionNameType;

        private ImageViewerAdapter() {
            this.imageData = Collections.emptyList();
        }

        /* access modifiers changed from: 0000 */
        public void setData(String transitionNameType2, long transitionNameId2, List<ImageViewerData> imageData2) {
            this.transitionNameType = transitionNameType2;
            this.transitionNameId = transitionNameId2;
            this.imageData = imageData2;
            notifyDataSetChanged();
        }

        /* access modifiers changed from: 0000 */
        public void setDragPercentage(float dragPercentage2) {
            this.dragPercentage = dragPercentage2;
        }

        public ImageViewerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageViewerViewHolder(parent);
        }

        public void onBindViewHolder(ImageViewerViewHolder holder, int position) {
            holder.bind((ImageViewerData) this.imageData.get(position), position, this.imageData.size(), this.transitionNameType, this.transitionNameId, ImageViewer.this, this.dragPercentage);
        }

        public int getItemCount() {
            return this.imageData.size();
        }
    }

    /* renamed from: com.airbnb.n2.components.image_viewer.ImageViewer$ImageViewerData */
    public static class ImageViewerData {
        final String caption;
        final Image image;

        public ImageViewerData(String url) {
            this("", url, null);
        }

        public ImageViewerData(String caption2, String url, String base64Preview) {
            this(caption2, new SimpleImage(url, base64Preview));
        }

        public ImageViewerData(String caption2, Image image2) {
            this.caption = caption2;
            this.image = image2;
        }
    }

    /* renamed from: com.airbnb.n2.components.image_viewer.ImageViewer$ImageViewerViewHolder */
    static class ImageViewerViewHolder extends ViewHolder {
        private final ImageViewerView imageViewerView = ((ImageViewerView) this.itemView);

        ImageViewerViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_image_viewer_item, parent, false));
            int loadingDrawableDimension = parent.getContext().getResources().getDimensionPixelOffset(R.dimen.n2_image_loading_drawable_dimen);
            this.imageViewerView.setPlaceholderDrawable(new LoadingDrawable(loadingDrawableDimension, loadingDrawableDimension));
            this.imageViewerView.setCaptionBackgroundColor(ContextCompat.getColor(this.itemView.getContext(), R.color.n2_black_overlay));
        }

        public void bind(ImageViewerData data, int position, int imageCount, String type, long id, RequestListener<String, Bitmap> requestListener, float dragPercentage) {
            this.imageViewerView.setImage(data.image, requestListener);
            this.imageViewerView.setImageTransitionName(TransitionName.toString(type, id, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, (long) position));
            addCaption(data, position, imageCount);
            this.imageViewerView.fadeContent(dragPercentage > 0.0f);
        }

        private void addCaption(ImageViewerData data, int position, int imageCount) {
            boolean hasCaption;
            int captionLength = 0;
            if (!TextUtils.isEmpty(data.caption)) {
                hasCaption = true;
            } else {
                hasCaption = false;
            }
            if (hasCaption) {
                captionLength = data.caption.length();
            }
            StringBuilder captionBuilder = new StringBuilder(captionLength + 8);
            captionBuilder.append(position + 1);
            captionBuilder.append("/");
            captionBuilder.append(imageCount);
            if (hasCaption) {
                captionBuilder.append(" - ");
                captionBuilder.append(data.caption);
            }
            this.imageViewerView.setCaption(captionBuilder.toString());
        }
    }

    /* renamed from: com.airbnb.n2.components.image_viewer.ImageViewer$OnFirstImageLoadedListener */
    public interface OnFirstImageLoadedListener {
        void onFirstImageLoaded();
    }

    /* renamed from: com.airbnb.n2.components.image_viewer.ImageViewer$OnViewDragCallback */
    public interface OnViewDragCallback {
        void onViewCaptured();

        void onViewDragged(float f);

        void onViewReleased(boolean z);
    }

    public ImageViewer(Context context) {
        super(context);
        init();
    }

    public ImageViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.layoutManager = (CarouselLayoutManager) getLayoutManager();
        this.adapter = new ImageViewerAdapter();
        setAdapter(this.adapter);
        if (ViewLibUtils.isAtLeastLollipop()) {
            setupDragHelper();
        }
    }

    @TargetApi(21)
    private void setupDragHelper() {
        this.dragHelper = ViewDragHelper.create(this, new Callback() {
            private View capturedView;
            private int dragDistanceToFinish;
            private int viewDragY;

            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            public int getViewVerticalDragRange(View child) {
                return Integer.MAX_VALUE;
            }

            public void onViewCaptured(View capturedChild, int activePointerId) {
                this.capturedView = capturedChild;
                this.dragDistanceToFinish = (capturedChild.getHeight() * 2) / 3;
                if (ImageViewer.this.viewDragCallback != null) {
                    ImageViewer.this.viewDragCallback.onViewCaptured();
                }
                ((ImageViewerView) capturedChild).fadeContent(true);
            }

            public boolean tryCaptureView(View child, int pointerId) {
                return this.capturedView != null;
            }

            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                this.capturedView = null;
                if (getDragPercentage() >= 1.0f || Math.abs(yvel) > 600.0f) {
                    if (ImageViewer.this.viewDragCallback != null) {
                        ImageViewer.this.viewDragCallback.onViewReleased(false);
                    }
                    this.viewDragY = 0;
                    return;
                }
                if (ImageViewer.this.viewDragCallback != null) {
                    ImageViewer.this.viewDragCallback.onViewReleased(true);
                }
                ((ImageViewerView) releasedChild).fadeContent(false);
                ImageViewer.this.dragHelper.settleCapturedViewAt(0, 0);
                ImageViewer.this.computeScroll();
            }

            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                this.viewDragY += dy;
                float dragPercentage = getDragPercentage();
                if (ImageViewer.this.viewDragCallback != null) {
                    ImageViewer.this.viewDragCallback.onViewDragged(dragPercentage);
                }
                ImageViewer.this.adapter.setDragPercentage(dragPercentage);
            }

            private float getDragPercentage() {
                return Math.abs(((float) this.viewDragY) / ((float) this.dragDistanceToFinish));
            }
        });
    }

    public void setViewDragCallback(OnViewDragCallback viewDragCallback2) {
        this.viewDragCallback = viewDragCallback2;
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        return (this.dragHelper != null && this.dragHelper.shouldInterceptTouchEvent(e)) || super.onInterceptTouchEvent(e);
    }

    public boolean onTouchEvent(MotionEvent e) {
        if (this.dragHelper == null) {
            return super.onTouchEvent(e);
        }
        this.dragHelper.processTouchEvent(e);
        if ((this.dragHelper.checkTouchSlop(1) || getScrollState() != 0) && this.dragHelper.getViewDragState() == 0) {
            return super.onTouchEvent(e);
        }
        if (!this.dragHelper.checkTouchSlop(2) || this.dragHelper.getViewDragState() != 0) {
            return true;
        }
        View child = this.dragHelper.findTopChildUnder((int) e.getX(), (int) e.getY());
        if (child == null || ViewCompat.canScrollHorizontally(child, this.dragHelper.getTouchSlop())) {
            return true;
        }
        this.dragHelper.captureChildView(child, MotionEventCompat.getPointerId(e, 0));
        return true;
    }

    public void computeScroll() {
        if (this.dragHelper != null && this.dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setData(String transitionNameType, long transitionNameId, List<ImageViewerData> data) {
        this.adapter.setData(transitionNameType, transitionNameId, data);
    }

    public void setOnFirstImageLoadedListener(OnFirstImageLoadedListener listener) {
        if (!this.hasLoadedImage || this.hasCalledFirstImageLoadedListener) {
            this.onFirstImageLoadedListener = listener;
        } else {
            listener.onFirstImageLoaded();
        }
    }

    public int getFirstVisibleItemPosition() {
        return this.layoutManager.findFirstVisibleItemPosition();
    }

    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
        maybeCallFirstImageLoadedListener();
        return false;
    }

    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
        maybeCallFirstImageLoadedListener();
        return false;
    }

    private void maybeCallFirstImageLoadedListener() {
        this.hasLoadedImage = true;
        if (this.onFirstImageLoadedListener != null) {
            this.hasCalledFirstImageLoadedListener = true;
            this.onFirstImageLoadedListener.onFirstImageLoaded();
        }
    }
}
