package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.net.Uri;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.concurrent.atomic.AtomicBoolean;

public class RNSVGImageShadowNode extends RNSVGPathShadowNode {
    private String mAlign;

    /* renamed from: mH */
    private String f1382mH;
    private float mImageRatio;
    /* access modifiers changed from: private */
    public AtomicBoolean mLoading = new AtomicBoolean(false);
    private int mMeetOrSlice;
    private Uri mUri;

    /* renamed from: mW */
    private String f1383mW;

    /* renamed from: mX */
    private String f1384mX;

    /* renamed from: mY */
    private String f1385mY;

    @ReactProp(name = "x")
    public void setX(String x) {
        this.f1384mX = x;
        markUpdated();
    }

    @ReactProp(name = "y")
    public void setY(String y) {
        this.f1385mY = y;
        markUpdated();
    }

    @ReactProp(name = "width")
    public void setWidth(String width) {
        this.f1383mW = width;
        markUpdated();
    }

    @ReactProp(name = "height")
    public void seHeight(String height) {
        this.f1382mH = height;
        markUpdated();
    }

    @ReactProp(name = "src")
    public void setSrc(ReadableMap src) {
        if (src != null) {
            String uriString = src.getString("uri");
            if (uriString != null && !uriString.isEmpty()) {
                this.mImageRatio = ((float) src.getInt("width")) / ((float) src.getInt("height"));
                this.mUri = Uri.parse(uriString);
            }
        }
    }

    @ReactProp(name = "align")
    public void setAlign(String align) {
        this.mAlign = align;
        markUpdated();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int meetOrSlice) {
        this.mMeetOrSlice = meetOrSlice;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        this.mPath = new Path();
        this.mPath.addRect(new RectF(getRect()), Direction.CW);
        if (!this.mLoading.get()) {
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(this.mUri).build();
            if (Fresco.getImagePipeline().isInBitmapMemoryCache(request)) {
                tryRender(request, canvas, paint, this.mOpacity * opacity);
            } else {
                loadBitmap(request, canvas, paint);
            }
        }
    }

    private void loadBitmap(ImageRequest request, Canvas canvas, Paint paint) {
        Fresco.getImagePipeline().fetchDecodedImage(request, getThemedContext()).subscribe(new BaseBitmapDataSubscriber() {
            public void onNewResultImpl(Bitmap bitmap) {
                RNSVGImageShadowNode.this.mLoading.set(false);
                RNSVGImageShadowNode.this.getSvgShadowNode().drawOutput();
            }

            public void onFailureImpl(DataSource dataSource) {
                RNSVGImageShadowNode.this.mLoading.set(false);
                FLog.m1850w(ReactConstants.TAG, dataSource.getFailureCause(), "RNSVG: fetchDecodedImage failed!", new Object[0]);
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    private Rect getRect() {
        float x = PropHelper.fromPercentageToFloat(this.f1384mX, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float y = PropHelper.fromPercentageToFloat(this.f1385mY, (float) this.mCanvasHeight, 0.0f, this.mScale);
        return new Rect((int) x, (int) y, (int) (x + PropHelper.fromPercentageToFloat(this.f1383mW, (float) this.mCanvasWidth, 0.0f, this.mScale)), (int) (y + PropHelper.fromPercentageToFloat(this.f1382mH, (float) this.mCanvasHeight, 0.0f, this.mScale)));
    }

    private void doRender(Canvas canvas, Paint paint, Bitmap bitmap, float opacity) {
        RectF renderRect;
        int count = saveAndSetupCanvas(canvas);
        canvas.concat(this.mMatrix);
        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha((int) (255.0f * opacity));
        Rect rect = getRect();
        float rectWidth = (float) rect.width();
        float rectHeight = (float) rect.height();
        float rectX = (float) rect.left;
        float rectY = (float) rect.top;
        float rectRatio = rectWidth / rectHeight;
        if (this.mImageRatio == rectRatio) {
            renderRect = new RectF(rect);
        } else if (this.mImageRatio < rectRatio) {
            renderRect = new RectF(0.0f, 0.0f, (float) ((int) (this.mImageRatio * rectHeight)), (float) ((int) rectHeight));
        } else {
            renderRect = new RectF(0.0f, 0.0f, (float) ((int) rectWidth), (float) ((int) (rectWidth / this.mImageRatio)));
        }
        RNSVGViewBoxShadowNode viewBox = new RNSVGViewBoxShadowNode();
        viewBox.setMinX(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        viewBox.setMinY(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        viewBox.setVbWidth((renderRect.width() / this.mScale) + "");
        viewBox.setVbHeight((renderRect.height() / this.mScale) + "");
        viewBox.setWidth((rectWidth / this.mScale) + "");
        viewBox.setHeight((rectHeight / this.mScale) + "");
        viewBox.setAlign(this.mAlign);
        viewBox.setMeetOrSlice(this.mMeetOrSlice);
        viewBox.setupDimensions(new Rect(0, 0, (int) rectWidth, (int) rectHeight));
        viewBox.getTransform().mapRect(renderRect);
        Matrix translation = new Matrix();
        translation.postTranslate(rectX, rectY);
        translation.mapRect(renderRect);
        Path clip = new Path();
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            clip.setFillType(FillType.INVERSE_EVEN_ODD);
            Path inverseWindingPath = new Path();
            inverseWindingPath.setFillType(FillType.INVERSE_WINDING);
            inverseWindingPath.addPath(this.mPath);
            inverseWindingPath.addPath(clipPath);
            Path evenOddPath = new Path();
            evenOddPath.setFillType(FillType.EVEN_ODD);
            evenOddPath.addPath(this.mPath);
            evenOddPath.addPath(clipPath);
            canvas.clipPath(evenOddPath, Op.DIFFERENCE);
            canvas.clipPath(inverseWindingPath, Op.DIFFERENCE);
        } else {
            canvas.clipPath(this.mPath, Op.REPLACE);
        }
        canvas.drawBitmap(bitmap, null, renderRect, alphaPaint);
        restoreCanvas(canvas, count);
        markUpdateSeen();
    }

    private void tryRender(ImageRequest request, Canvas canvas, Paint paint, float opacity) {
        DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline().fetchImageFromBitmapCache(request, getThemedContext());
        try {
            CloseableReference<CloseableImage> imageReference = (CloseableReference) dataSource.getResult();
            if (imageReference != null) {
                try {
                    if (imageReference.get() instanceof CloseableBitmap) {
                        Bitmap bitmap = ((CloseableBitmap) imageReference.get()).getUnderlyingBitmap();
                        if (bitmap != null) {
                            doRender(canvas, paint, bitmap, opacity);
                        }
                    }
                    CloseableReference.closeSafely(imageReference);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                } catch (Throwable th) {
                    CloseableReference.closeSafely(imageReference);
                    throw th;
                }
            }
            dataSource.close();
        } catch (Exception e2) {
            try {
                throw new IllegalStateException(e2);
            } catch (Throwable th2) {
                dataSource.close();
                throw th2;
            }
        }
    }
}
