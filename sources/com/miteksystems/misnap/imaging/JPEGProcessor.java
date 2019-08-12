package com.miteksystems.misnap.imaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Environment;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import com.miteksystems.misnap.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.jpeg.exifRewrite.ExifRewriter;
import org.apache.sanselan.formats.tiff.constants.ExifTagConstants;
import org.apache.sanselan.formats.tiff.constants.TiffFieldTypeConstants;
import org.apache.sanselan.formats.tiff.write.TiffOutputField;
import org.apache.sanselan.formats.tiff.write.TiffOutputSet;

public class JPEGProcessor {
    private final boolean MISNAP_SAVE_IMAGE_ENABLED = false;

    public byte[] addMibiData(byte[] bArr, String str) {
        if (bArr == null) {
            return bArr;
        }
        TiffOutputSet tiffOutputSet = new TiffOutputSet(77);
        try {
            byte[] encodeValue = ExifTagConstants.EXIF_TAG_USER_COMMENT.encodeValue(TiffFieldTypeConstants.FIELD_TYPE_ASCII, str, tiffOutputSet.byteOrder);
            tiffOutputSet.getOrCreateExifDirectory().add(new TiffOutputField(ExifTagConstants.EXIF_TAG_USER_COMMENT, ExifTagConstants.EXIF_TAG_USER_COMMENT.dataTypes[0], encodeValue.length, encodeValue));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
            new ExifRewriter().updateExifMetadataLossless(bArr, (OutputStream) byteArrayOutputStream, tiffOutputSet);
            bArr = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bArr;
        } catch (ImageWriteException e) {
            e.printStackTrace();
            return bArr;
        } catch (ImageReadException e2) {
            e2.printStackTrace();
            return bArr;
        } catch (IOException e3) {
            e3.printStackTrace();
            return bArr;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0042 A[SYNTHETIC, Splitter:B:24:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] convertYUVtoJPEG(byte[] r9, int r10, int r11, int r12) {
        /*
            r8 = this;
            r6 = 0
            if (r9 != 0) goto L_0x0004
        L_0x0003:
            return r6
        L_0x0004:
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x002c, all -> 0x003e }
            r7.<init>()     // Catch:{ Exception -> 0x002c, all -> 0x003e }
            android.graphics.YuvImage r0 = new android.graphics.YuvImage     // Catch:{ Exception -> 0x0050, all -> 0x004b }
            r2 = 17
            r5 = 0
            r1 = r9
            r3 = r10
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0050, all -> 0x004b }
            android.graphics.Rect r1 = new android.graphics.Rect     // Catch:{ Exception -> 0x0050, all -> 0x004b }
            r2 = 0
            r3 = 0
            r1.<init>(r2, r3, r10, r11)     // Catch:{ Exception -> 0x0050, all -> 0x004b }
            r0.compressToJpeg(r1, r12, r7)     // Catch:{ Exception -> 0x0050, all -> 0x004b }
            byte[] r0 = r7.toByteArray()     // Catch:{ Exception -> 0x0050, all -> 0x004b }
            r7.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0025:
            r6 = r0
            goto L_0x0003
        L_0x0027:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0025
        L_0x002c:
            r0 = move-exception
            r1 = r6
        L_0x002e:
            r0.printStackTrace()     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0036:
            r0 = r6
            goto L_0x0025
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r6
            goto L_0x0025
        L_0x003e:
            r0 = move-exception
            r7 = r6
        L_0x0040:
            if (r7 == 0) goto L_0x0045
            r7.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0045:
            throw r0
        L_0x0046:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0045
        L_0x004b:
            r0 = move-exception
            goto L_0x0040
        L_0x004d:
            r0 = move-exception
            r7 = r1
            goto L_0x0040
        L_0x0050:
            r0 = move-exception
            r1 = r7
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miteksystems.misnap.imaging.JPEGProcessor.convertYUVtoJPEG(byte[], int, int, int):byte[]");
    }

    public byte[] rotateJPEG(byte[] bArr, int i, int i2) {
        Matrix matrix = new Matrix();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray == null) {
            return bArr;
        }
        if (i != 0) {
            matrix.postRotate((float) i, (float) (decodeByteArray.getWidth() / 2), (float) (decodeByteArray.getHeight() / 2));
        }
        Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(CompressFormat.JPEG, i2, byteArrayOutputStream);
        createBitmap.recycle();
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] scaleAndCompress(Context context, byte[] bArr, int i, int i2, int i3) {
        Bitmap bitmap;
        int i4 = MiSnapApiConstants.DEFAULT_IMAGE_HORIZONTAL_PIXELS;
        byte[] bArr2 = null;
        Context applicationContext = context.getApplicationContext();
        if (bArr != null) {
            double d = (double) (((float) i3) / ((float) i2));
            if (0.5625d == d) {
                i4 = i2;
            }
            int i5 = (int) (d * ((double) ((float) i4)));
            try {
                Utils.savePictureSizeInPrefFile(applicationContext, i4, i5);
                Options options = new Options();
                options.inScaled = false;
                options.inPurgeable = false;
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                if (decodeByteArray != null) {
                    if (i4 != i2) {
                        try {
                            bitmap = Bitmap.createScaledBitmap(decodeByteArray, MiSnapApiConstants.DEFAULT_IMAGE_HORIZONTAL_PIXELS, i5, true);
                            decodeByteArray.recycle();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        bitmap = decodeByteArray;
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
                    bitmap.recycle();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return bArr2;
    }

    public boolean saveJPEGImage(byte[] bArr, boolean z) {
        return saveJPEGImage(bArr, z, "");
    }

    public boolean saveJPEGImage(byte[] bArr, boolean z, String str) {
        return false;
    }

    public boolean saveJPEGImage(byte[] bArr, File file) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean saveToFile(byte[] bArr, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException | Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public File getOutputMediaFile(boolean z, String str) {
        File file = null;
        try {
            File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MiSnap");
            if (file2.exists() || file2.mkdirs()) {
                if (!z) {
                    file = new File(file2.getPath(), "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + str + ".jpg");
                } else {
                    file = new File(file2.getPath(), "IMG_MiSnap" + str + ".jpg");
                }
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception e) {
        }
        return file;
    }
}
