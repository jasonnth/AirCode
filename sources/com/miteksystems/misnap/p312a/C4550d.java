package com.miteksystems.misnap.p312a;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApiConstants;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.utils.Utils;
import java.util.List;

/* renamed from: com.miteksystems.misnap.a.d */
public final class C4550d {
    /* renamed from: a */
    public static void m2092a(Context context, Camera camera, int i) {
        int a = m2089a(context, i);
        camera.setDisplayOrientation(a);
        camera.getParameters().setRotation(a);
    }

    /* renamed from: a */
    public static int m2089a(Context context, int i) {
        int i2 = 0;
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        switch (Utils.getDeviceCurrentOrientation(context)) {
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS;
                break;
        }
        if (cameraInfo.facing == 1) {
            return (360 - ((i2 + cameraInfo.orientation) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT)) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT;
        }
        return ((cameraInfo.orientation - i2) + FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT;
    }

    /* renamed from: a */
    public static Size m2090a(int i, int i2, ParameterManager parameterManager, Camera camera, List<Size> list, boolean z, boolean z2, C4552f fVar) {
        Size size;
        int i3;
        Size size2;
        Size size3;
        double d;
        if (z) {
            C4549c b = fVar.mo44333b();
            camera.getClass();
            return new Size(camera, b.f3602a, b.f3603b);
        } else if (z2) {
            C4549c c = fVar.mo44334c();
            camera.getClass();
            return new Size(camera, c.f3602a, c.f3603b);
        } else if (list == null || list.size() == 0) {
            return null;
        } else {
            double d2 = ((double) i) / ((double) i2);
            Size size4 = null;
            double d3 = Double.MAX_VALUE;
            for (Size size5 : list) {
                double d4 = ((double) size5.width) / ((double) size5.height);
                int i4 = size5.width * size5.height;
                if ((d2 != d4 || ((d2 != d3 || i4 < 307200) && (d2 == d3 || i4 < 307200))) && (Math.abs(d2 - d4) >= Math.abs(d2 - d3) || i4 < 307200)) {
                    double d5 = d3;
                    size3 = size4;
                    d = d5;
                } else {
                    size3 = size5;
                    d = d4;
                }
                size4 = size3;
                d3 = d;
            }
            if (size4 == null) {
                Size size6 = size4;
                int i5 = 307200;
                for (Size size7 : list) {
                    if (size7.width <= i && size7.height <= i2) {
                        int i6 = size7.width * size7.height;
                        if (size6 == null || ((size7.width > size6.width || size7.height > size6.height) && i6 > i5)) {
                            size2 = size7;
                            i3 = i6;
                            size6 = size2;
                            i5 = i3;
                        }
                    }
                    i3 = i5;
                    size2 = size6;
                    size6 = size2;
                    i5 = i3;
                }
                size = size6;
            } else {
                size = size4;
            }
            if (size != null) {
                if (600 <= size.height || !parameterManager.isCurrentModeVideo()) {
                    new StringBuilder("preview size ").append(size.width).append("x").append(size.height);
                    return size;
                }
                new StringBuilder("warning: previewFrame size (").append(size.width).append("x").append(size.height).append(") insufficient for AllowVideoFrames");
                return null;
            } else if (list.size() == 1) {
                return (Size) list.get(0);
            } else {
                return size;
            }
        }
    }

    /* renamed from: a */
    public static Size m2091a(List<Size> list, Size size, int i, float f) {
        Size size2;
        Size size3;
        double d;
        Size size4;
        double d2;
        Size size5 = null;
        if (list == null || size == null || size.height == 0) {
            return null;
        }
        if (list.contains(size)) {
            return size;
        }
        double d3 = Double.MAX_VALUE;
        double d4 = ((double) size.width) / ((double) size.height);
        int i2 = size.width;
        for (Size size6 : list) {
            double abs = (double) Math.abs(size6.width - i2);
            double d5 = ((double) size6.width) / ((double) size6.height);
            if (abs > d3 || size6.width < i2 || ((double) size6.height) < 0.5625d * ((double) i2) || Math.abs(d5 - d4) > ((double) f)) {
                size4 = size2;
                d2 = d3;
            } else {
                double d6 = abs;
                size4 = size6;
                d2 = d6;
            }
            d3 = d2;
            size5 = size4;
        }
        if (size2 == null) {
            if (list == null) {
                size2 = null;
            } else {
                size2 = null;
                double d7 = Double.MAX_VALUE;
                for (Size size7 : list) {
                    double abs2 = (double) Math.abs(size7.width - i);
                    if (abs2 > d7 || size7.width < i || ((double) size7.height) < 0.5625d * ((double) i)) {
                        size3 = size2;
                        d = d7;
                    } else {
                        double d8 = abs2;
                        size3 = size7;
                        d = d8;
                    }
                    d7 = d;
                    size2 = size3;
                }
            }
        }
        return size2;
    }
}
