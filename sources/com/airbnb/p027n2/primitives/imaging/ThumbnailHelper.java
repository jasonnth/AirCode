package com.airbnb.p027n2.primitives.imaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.bumptech.glide.request.target.Target;

/* renamed from: com.airbnb.n2.primitives.imaging.ThumbnailHelper */
class ThumbnailHelper {
    private int arrayPosition;
    private final ImageData[] images;

    /* renamed from: com.airbnb.n2.primitives.imaging.ThumbnailHelper$ImageData */
    static class ImageData {
        long imageId;
        Point size;
        String url;

        public ImageData(long imageId2, String url2, Point size2) {
            this.imageId = imageId2;
            this.url = url2;
            this.size = size2;
        }
    }

    public ThumbnailHelper(Context context) {
        this.images = new ImageData[(ViewLibUtils.isTabletScreen(context) ? 50 : 25)];
    }

    /* access modifiers changed from: 0000 */
    public void setImageDetails(Image image, String url, Target<Bitmap> target) {
        target.getSize(ThumbnailHelper$$Lambda$1.lambdaFactory$(this, image, url));
    }

    static /* synthetic */ void lambda$setImageDetails$0(ThumbnailHelper thumbnailHelper, Image image, String url, int width, int height) {
        thumbnailHelper.images[thumbnailHelper.arrayPosition] = new ImageData(image.getId(), url, new Point(width, height));
        thumbnailHelper.arrayPosition++;
        if (thumbnailHelper.arrayPosition >= thumbnailHelper.images.length) {
            thumbnailHelper.arrayPosition = 0;
        }
    }

    public ImageData getImageDetails(Image image) {
        for (int i = 0; i < this.images.length; i++) {
            int index = (this.arrayPosition - i) - 1;
            if (index < 0) {
                index += this.images.length;
            }
            ImageData imageData = this.images[index];
            if (imageData != null && imageData.imageId == image.getId()) {
                return imageData;
            }
        }
        return null;
    }
}
