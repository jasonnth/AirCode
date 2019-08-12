package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.StaticMapInfo;
import com.airbnb.p027n2.utils.StaticMapInfo.MapType;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/* renamed from: com.airbnb.n2.primitives.StaticMapView */
public class StaticMapView extends AirImageView {
    private final Handler handler = new Handler();
    private boolean keyed;
    public Listener listener;
    private final StaticMapInfo mapInfo = new StaticMapInfo();
    private MapOptions options;

    /* renamed from: com.airbnb.n2.primitives.StaticMapView$Listener */
    public interface Listener {
        void onImageException(Exception exc);
    }

    public StaticMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public StaticMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StaticMapView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        setScaleType(ScaleType.CENTER_CROP);
        setMapType(false, false);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_StaticMapView, 0, 0);
            this.keyed = a.getBoolean(R.styleable.n2_StaticMapView_n2_keyed, false);
            a.recycle();
        }
    }

    public boolean shouldUseARGB8888() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.handler.post(StaticMapView$$Lambda$1.lambdaFactory$(this));
    }

    public void setup(MapOptions options2, Listener listener2) {
        setup(options2, listener2, this.keyed);
    }

    public void setup(MapOptions options2, Listener listener2, boolean keyed2) {
        clear();
        this.options = options2;
        this.listener = listener2;
        this.keyed = keyed2;
        setMapType(options2.isUserInChina(), options2.useDlsMapType());
        this.mapInfo.centerMap(options2.center(), options2.zoom());
        if (options2.marker() != null) {
            this.mapInfo.addMarkerToMap(options2.marker().latLng());
        }
        if (options2.circle() != null) {
            this.mapInfo.addCircleToMap(getContext(), options2.circle().center(), options2.circle().radiusMeters());
        }
        fetchImageIfNeeded();
    }

    public void setMapType(boolean isUserInChina, boolean useDlsMapType) {
        if (isInEditMode()) {
            this.mapInfo.setMapType(MapType.GoogleWeb);
        } else {
            this.mapInfo.setMapType(isUserInChina, useDlsMapType);
        }
    }

    /* access modifiers changed from: private */
    public void fetchImageIfNeeded() {
        if (this.options != null) {
            int width = getWidth();
            int height = getHeight();
            if (width != 0 && height != 0) {
                setImageUrl(this.mapInfo.getStaticMapUrl(getResources(), width, height, this.keyed), new RequestListener<String, Bitmap>() {
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }

                    public boolean onException(Exception exception, String model, Target<Bitmap> target, boolean isFirstResource) {
                        StaticMapView.this.setVisibility(8);
                        StaticMapView.this.listener.onImageException(exception);
                        return true;
                    }
                });
            }
        }
    }

    public void clear() {
        super.clear();
        this.handler.removeCallbacksAndMessages(null);
        this.options = null;
        this.mapInfo.clearMapUrls();
    }
}
