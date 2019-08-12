package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import p005cn.jpush.android.JPushConstants;

public class AirMapMarker extends AirMapFeature {
    private boolean anchorIsSet;
    private float anchorX;
    private float anchorY;
    private boolean calloutAnchorIsSet;
    private float calloutAnchorX;
    private float calloutAnchorY;
    private AirMapCallout calloutView;
    private final Context context;
    /* access modifiers changed from: private */
    public DataSource<CloseableReference<CloseableImage>> dataSource;
    private boolean draggable = false;
    private boolean flat = false;
    private boolean hasCustomMarkerView = false;
    private int height;
    /* access modifiers changed from: private */
    public Bitmap iconBitmap;
    /* access modifiers changed from: private */
    public BitmapDescriptor iconBitmapDescriptor;
    private String identifier;
    private final DraweeHolder<?> logoHolder;
    private final ControllerListener<ImageInfo> mLogoControllerListener = new BaseControllerListener<ImageInfo>() {
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            CloseableReference<CloseableImage> imageReference = null;
            try {
                imageReference = (CloseableReference) AirMapMarker.this.dataSource.getResult();
                if (imageReference != null) {
                    CloseableImage image = (CloseableImage) imageReference.get();
                    if (image != null && (image instanceof CloseableStaticBitmap)) {
                        Bitmap bitmap = ((CloseableStaticBitmap) image).getUnderlyingBitmap();
                        if (bitmap != null) {
                            Bitmap bitmap2 = bitmap.copy(Config.ARGB_8888, true);
                            AirMapMarker.this.iconBitmap = bitmap2;
                            AirMapMarker.this.iconBitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap2);
                        }
                    }
                }
                AirMapMarker.this.update();
            } finally {
                AirMapMarker.this.dataSource.close();
                if (imageReference != null) {
                    CloseableReference.closeSafely(imageReference);
                }
            }
        }
    };
    private Marker marker;
    private float markerHue = 0.0f;
    private MarkerOptions markerOptions;
    private float opacity = 1.0f;
    private LatLng position;
    private float rotation = 0.0f;
    private String snippet;
    private String title;
    private int width;
    private View wrappedCalloutView;
    private int zIndex = 0;

    public AirMapMarker(Context context2) {
        super(context2);
        this.context = context2;
        this.logoHolder = DraweeHolder.create(createDraweeHierarchy(), context2);
        this.logoHolder.onAttach();
    }

    private GenericDraweeHierarchy createDraweeHierarchy() {
        return new GenericDraweeHierarchyBuilder(getResources()).setActualImageScaleType(ScaleType.FIT_CENTER).setFadeDuration(0).build();
    }

    public void setCoordinate(ReadableMap coordinate) {
        this.position = new LatLng(coordinate.getDouble("latitude"), coordinate.getDouble("longitude"));
        if (this.marker != null) {
            this.marker.setPosition(this.position);
        }
        update();
    }

    public void setIdentifier(String identifier2) {
        this.identifier = identifier2;
        update();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setTitle(String title2) {
        this.title = title2;
        if (this.marker != null) {
            this.marker.setTitle(title2);
        }
        update();
    }

    public void setSnippet(String snippet2) {
        this.snippet = snippet2;
        if (this.marker != null) {
            this.marker.setSnippet(snippet2);
        }
        update();
    }

    public void setRotation(float rotation2) {
        this.rotation = rotation2;
        if (this.marker != null) {
            this.marker.setRotation(rotation2);
        }
        update();
    }

    public void setFlat(boolean flat2) {
        this.flat = flat2;
        if (this.marker != null) {
            this.marker.setFlat(flat2);
        }
        update();
    }

    public void setDraggable(boolean draggable2) {
        this.draggable = draggable2;
        if (this.marker != null) {
            this.marker.setDraggable(draggable2);
        }
        update();
    }

    public void setZIndex(int zIndex2) {
        this.zIndex = zIndex2;
        if (this.marker != null) {
            this.marker.setZIndex((float) zIndex2);
        }
        update();
    }

    public void setOpacity(float opacity2) {
        this.opacity = opacity2;
        if (this.marker != null) {
            this.marker.setAlpha(opacity2);
        }
        update();
    }

    public void setMarkerHue(float markerHue2) {
        this.markerHue = markerHue2;
        update();
    }

    public void setAnchor(double x, double y) {
        this.anchorIsSet = true;
        this.anchorX = (float) x;
        this.anchorY = (float) y;
        if (this.marker != null) {
            this.marker.setAnchor(this.anchorX, this.anchorY);
        }
        update();
    }

    public void setCalloutAnchor(double x, double y) {
        this.calloutAnchorIsSet = true;
        this.calloutAnchorX = (float) x;
        this.calloutAnchorY = (float) y;
        if (this.marker != null) {
            this.marker.setInfoWindowAnchor(this.calloutAnchorX, this.calloutAnchorY);
        }
        update();
    }

    public void setImage(String uri) {
        if (uri == null) {
            this.iconBitmapDescriptor = null;
            update();
        } else if (uri.startsWith(JPushConstants.HTTP_PRE) || uri.startsWith("https://") || uri.startsWith("file://")) {
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri)).build();
            this.dataSource = Fresco.getImagePipeline().fetchDecodedImage(imageRequest, this);
            this.logoHolder.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(imageRequest)).setControllerListener(this.mLogoControllerListener)).setOldController(this.logoHolder.getController())).build());
        } else {
            this.iconBitmapDescriptor = getBitmapDescriptorByName(uri);
            update();
        }
    }

    public MarkerOptions getMarkerOptions() {
        if (this.markerOptions == null) {
            this.markerOptions = createMarkerOptions();
        }
        return this.markerOptions;
    }

    public void addView(View child, int index) {
        super.addView(child, index);
        if (!(child instanceof AirMapCallout)) {
            this.hasCustomMarkerView = true;
        }
        update();
    }

    public Object getFeature() {
        return this.marker;
    }

    public void addToMap(GoogleMap map) {
        this.marker = map.addMarker(getMarkerOptions());
    }

    public void removeFromMap(GoogleMap map) {
        this.marker.remove();
        this.marker = null;
    }

    private BitmapDescriptor getIcon() {
        if (this.hasCustomMarkerView) {
            if (this.iconBitmapDescriptor == null) {
                return BitmapDescriptorFactory.fromBitmap(createDrawable());
            }
            Bitmap viewBitmap = createDrawable();
            Bitmap combinedBitmap = Bitmap.createBitmap(Math.max(this.iconBitmap.getWidth(), viewBitmap.getWidth()), Math.max(this.iconBitmap.getHeight(), viewBitmap.getHeight()), this.iconBitmap.getConfig());
            Canvas canvas = new Canvas(combinedBitmap);
            canvas.drawBitmap(this.iconBitmap, 0.0f, 0.0f, null);
            canvas.drawBitmap(viewBitmap, 0.0f, 0.0f, null);
            return BitmapDescriptorFactory.fromBitmap(combinedBitmap);
        } else if (this.iconBitmapDescriptor != null) {
            return this.iconBitmapDescriptor;
        } else {
            return BitmapDescriptorFactory.defaultMarker(this.markerHue);
        }
    }

    private MarkerOptions createMarkerOptions() {
        MarkerOptions options = new MarkerOptions().position(this.position);
        if (this.anchorIsSet) {
            options.anchor(this.anchorX, this.anchorY);
        }
        if (this.calloutAnchorIsSet) {
            options.infoWindowAnchor(this.calloutAnchorX, this.calloutAnchorY);
        }
        options.title(this.title);
        options.snippet(this.snippet);
        options.rotation(this.rotation);
        options.flat(this.flat);
        options.draggable(this.draggable);
        options.zIndex((float) this.zIndex);
        options.alpha(this.opacity);
        options.icon(getIcon());
        return options;
    }

    public void update() {
        if (this.marker != null) {
            this.marker.setIcon(getIcon());
            if (this.anchorIsSet) {
                this.marker.setAnchor(this.anchorX, this.anchorY);
            } else {
                this.marker.setAnchor(0.5f, 1.0f);
            }
            if (this.calloutAnchorIsSet) {
                this.marker.setInfoWindowAnchor(this.calloutAnchorX, this.calloutAnchorY);
            } else {
                this.marker.setInfoWindowAnchor(0.5f, 0.0f);
            }
        }
    }

    public void update(int width2, int height2) {
        this.width = width2;
        this.height = height2;
        update();
    }

    private Bitmap createDrawable() {
        int width2 = this.width <= 0 ? 100 : this.width;
        int height2 = this.height <= 0 ? 100 : this.height;
        buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(width2, height2, Config.ARGB_8888);
        draw(new Canvas(bitmap));
        return bitmap;
    }

    public void setCalloutView(AirMapCallout view) {
        this.calloutView = view;
    }

    public AirMapCallout getCalloutView() {
        return this.calloutView;
    }

    public View getCallout() {
        if (this.calloutView == null) {
            return null;
        }
        if (this.wrappedCalloutView == null) {
            wrapCalloutView();
        }
        if (this.calloutView.getTooltip()) {
            return this.wrappedCalloutView;
        }
        return null;
    }

    public View getInfoContents() {
        if (this.calloutView == null) {
            return null;
        }
        if (this.wrappedCalloutView == null) {
            wrapCalloutView();
        }
        if (!this.calloutView.getTooltip()) {
            return this.wrappedCalloutView;
        }
        return null;
    }

    private void wrapCalloutView() {
        if (this.calloutView != null && this.calloutView.getChildCount() != 0) {
            LinearLayout LL = new LinearLayout(this.context);
            LL.setOrientation(1);
            LL.setLayoutParams(new LayoutParams(this.calloutView.width, this.calloutView.height, 0.0f));
            LinearLayout LL2 = new LinearLayout(this.context);
            LL2.setOrientation(0);
            LL2.setLayoutParams(new LayoutParams(this.calloutView.width, this.calloutView.height, 0.0f));
            LL.addView(LL2);
            LL2.addView(this.calloutView);
            this.wrappedCalloutView = LL;
        }
    }

    private int getDrawableResourceByName(String name) {
        return getResources().getIdentifier(name, "drawable", getContext().getPackageName());
    }

    private BitmapDescriptor getBitmapDescriptorByName(String name) {
        return BitmapDescriptorFactory.fromResource(getDrawableResourceByName(name));
    }
}
