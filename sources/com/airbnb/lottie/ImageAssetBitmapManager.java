package com.airbnb.lottie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable.Callback;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Assert;

class ImageAssetBitmapManager {
    private ImageAssetDelegate assetDelegate;
    private final Map<String, Bitmap> bitmaps = new HashMap();
    private final Context context;
    private final Map<String, LottieImageAsset> imageAssets;
    private String imagesFolder;

    ImageAssetBitmapManager(Callback callback, String imagesFolder2, ImageAssetDelegate assetDelegate2, Map<String, LottieImageAsset> imageAssets2) {
        Assert.assertNotNull(callback);
        this.imagesFolder = imagesFolder2;
        if (!TextUtils.isEmpty(imagesFolder2) && this.imagesFolder.charAt(this.imagesFolder.length() - 1) != '/') {
            this.imagesFolder += '/';
        }
        if (!(callback instanceof View)) {
            Log.w("LOTTIE", "LottieDrawable must be inside of a view for images to work.");
            this.imageAssets = new HashMap();
            this.context = null;
            return;
        }
        this.context = ((View) callback).getContext();
        this.imageAssets = imageAssets2;
        setAssetDelegate(assetDelegate2);
    }

    /* access modifiers changed from: 0000 */
    public void setAssetDelegate(ImageAssetDelegate assetDelegate2) {
        this.assetDelegate = assetDelegate2;
    }

    /* access modifiers changed from: 0000 */
    public Bitmap bitmapForId(String id) {
        Bitmap bitmap = (Bitmap) this.bitmaps.get(id);
        if (bitmap == null) {
            LottieImageAsset imageAsset = (LottieImageAsset) this.imageAssets.get(id);
            if (imageAsset == null) {
                return null;
            }
            if (this.assetDelegate != null) {
                Bitmap bitmap2 = this.assetDelegate.fetchBitmap(imageAsset);
                this.bitmaps.put(id, bitmap2);
                return bitmap2;
            }
            try {
                if (TextUtils.isEmpty(this.imagesFolder)) {
                    throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
                }
                InputStream is = this.context.getAssets().open(this.imagesFolder + imageAsset.getFileName());
                Options opts = new Options();
                opts.inScaled = true;
                opts.inDensity = 160;
                bitmap = BitmapFactory.decodeStream(is, null, opts);
                this.bitmaps.put(id, bitmap);
            } catch (IOException e) {
                Log.w("LOTTIE", "Unable to open asset.", e);
                return null;
            }
        }
        return bitmap;
    }

    /* access modifiers changed from: 0000 */
    public void recycleBitmaps() {
        Iterator<Entry<String, Bitmap>> it = this.bitmaps.entrySet().iterator();
        while (it.hasNext()) {
            ((Bitmap) ((Entry) it.next()).getValue()).recycle();
            it.remove();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean hasSameContext(Context context2) {
        return (context2 == null && this.context == null) || (context2 != null && this.context.equals(context2));
    }
}
