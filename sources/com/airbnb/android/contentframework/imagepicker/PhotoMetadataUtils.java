package com.airbnb.android.contentframework.imagepicker;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import com.google.android.gms.maps.model.LatLng;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PhotoMetadataUtils {
    private static final String SCHEME_CONTENT = "content";

    public static Point getBitmapSize(ContentResolver resolver, Uri uri) {
        Point point;
        InputStream is = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            is = resolver.openInputStream(uri);
            BitmapFactory.decodeStream(is, null, options);
            point = new Point(options.outWidth, options.outHeight);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e2) {
            point = new Point(0, 0);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
        return point;
    }

    /* JADX INFO: finally extract failed */
    public static String getPath(ContentResolver resolver, Uri uri) {
        if (uri == null) {
            return null;
        }
        if (!"content".equals(uri.getScheme())) {
            return uri.getPath();
        }
        Cursor cursor = null;
        try {
            cursor = resolver.query(uri, new String[]{"_data"}, null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            String string = cursor.getString(cursor.getColumnIndex("_data"));
            if (cursor == null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static LatLng getExifLatLng(String filepath) {
        if (filepath == null) {
            return null;
        }
        try {
            float[] output = new float[2];
            new ExifInterface(filepath).getLatLong(output);
            return new LatLng((double) output[0], (double) output[1]);
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean shouldRotate(String filePath) {
        try {
            int orientation = new ExifInterface(filePath).getAttributeInt("Orientation", -1);
            if (orientation == 6 || orientation == 8) {
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
