package com.miteksystems.misnap.imaging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PreviewFrameConverter {
    public static Bitmap convertPreviewFrameToBitmap(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static ByteBuffer convertBitmapToRgbaArray(Bitmap bitmap) {
        ByteBuffer order = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4).order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(order);
        order.rewind();
        return order;
    }
}
