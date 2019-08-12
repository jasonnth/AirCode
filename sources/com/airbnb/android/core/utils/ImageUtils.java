package com.airbnb.android.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.ImageView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.AttachmentImage;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.ImageUtil;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.facebook.common.util.UriUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.p309io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ImageUtils {
    public static final int DEFAULT_PHOTO_CAPTURE_SIZE = 2048;
    private static final int UPLOAD_COMPRESS_QUALITY = 80;
    public static final Map<String, String> imageTypeMapping = ImmutableMap.builder().put("bmp", "image/bmp").put("gif", "image/gif").put("jpeg", "image/jpeg").put("jpg", "image/jpg").put("png", "image/png").put("tiff", "image/tiff").build();

    public static void setImageUrlForUser(HaloImageView imageView, User user) {
        String pictureUrl = user == null ? null : user.getPictureUrl();
        if (TextUtils.isEmpty(pictureUrl)) {
            imageView.setImageDefault();
        } else {
            imageView.setImageUrl(pictureUrl);
        }
    }

    public static String parseListingThumbnailUrl(String mThumbnailUrl) {
        if (mThumbnailUrl == null || !mThumbnailUrl.startsWith(UriUtil.HTTP_SCHEME)) {
            return null;
        }
        return mThumbnailUrl;
    }

    public static Bitmap rotateIfNeeded(Bitmap bitmap, int degrees) {
        if (degrees == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix m = new Matrix();
        m.setRotate((float) degrees, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        try {
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
        } catch (OutOfMemoryError e) {
            CoreApplication.instance().component().memoryUtils().handleCaughtOOM("rotate_bitmap");
            return bitmap;
        }
    }

    public static String compressBitmapForUpload(Bitmap bitmap, int rotationDegrees) {
        if (bitmap != null) {
            try {
                Bitmap bitmap2 = rotateIfNeeded(bitmap, rotationDegrees);
                File file = File.createTempFile("upload" + System.currentTimeMillis(), ".jpg");
                bitmap2.compress(CompressFormat.JPEG, 80, new FileOutputStream(file));
                return file.getAbsolutePath();
            } catch (IOException e) {
            } catch (OutOfMemoryError e2) {
                CoreApplication.instance().component().memoryUtils().handleCaughtOOM("compress_image_bitmap_for_upload");
            }
        }
        return null;
    }

    public static Bitmap scaleBitmap(String filePath, int maxWidth, int maxHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = ImageUtil.getInSampleSize(options.outWidth, options.outHeight, maxWidth, maxHeight, true);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError e) {
            CoreApplication.instance().component().memoryUtils().handleCaughtOOM("scale_bitmap");
            return null;
        }
    }

    public static Bitmap makeCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static void loadImageWithMemoryCheckOnUiThread(MemoryUtils memoryUtils, Context context, int imageResId, ImageView target, int targetWidth, int targetHeight, String errorMessageForOOM) {
        if (!memoryUtils.isLowMemoryDeviceOrOomOccurredInLastWeekProd()) {
            try {
                Options opts = new Options();
                opts.inPreferredConfig = Config.RGB_565;
                opts.outWidth = targetWidth;
                opts.outHeight = targetHeight;
                target.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imageResId, opts));
            } catch (OutOfMemoryError e) {
                memoryUtils.handleCaughtOOM(errorMessageForOOM);
                target.setImageResource(C0716R.color.c_gray_1);
            }
        } else {
            target.setImageResource(C0716R.color.c_gray_1);
        }
    }

    public static AttachmentImage createAttachmentImage(String imagePath) {
        AttachmentImage image = new AttachmentImage();
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(new File(imagePath).getAbsolutePath(), options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        image.setUrl(imagePath);
        image.setHeightPx(imageHeight);
        image.setWidthPx(imageWidth);
        return image;
    }

    public static String getContentTypeFromFilePath(String imagePath) {
        return (String) imageTypeMapping.get(Files.getFileExtension(imagePath));
    }
}
