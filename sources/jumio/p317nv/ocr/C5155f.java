package jumio.p317nv.ocr;

import com.jumio.clientlib.impl.livenessAndTM.ImgWarp;
import com.jumio.clientlib.impl.livenessAndTM.PixelFormatType;
import com.jumio.commons.camera.CameraManager.Size;
import com.jumio.commons.log.Log;

/* renamed from: jumio.nv.ocr.f */
/* compiled from: TemplateMatcherImageProcessor */
public class C5155f {
    /* renamed from: a */
    public static float[] m3945a(C5145a aVar, float f) {
        float[] e = aVar.mo47275e();
        float f2 = (((e[0] + e[2]) + e[6]) + e[4]) / 4.0f;
        float f3 = (((e[1] + e[3]) + e[7]) + e[5]) / 4.0f;
        float f4 = e[0] - f2;
        float f5 = e[1] - f3;
        float f6 = e[2] - f2;
        float f7 = e[3] - f3;
        float f8 = e[6] - f2;
        float f9 = e[7] - f3;
        float f10 = e[4] - f2;
        float f11 = e[5] - f3;
        double sqrt = Math.sqrt((double) ((f4 * f4) + (f5 * f5)));
        double sqrt2 = Math.sqrt((double) ((f6 * f6) + (f7 * f7)));
        double sqrt3 = Math.sqrt((double) ((f8 * f8) + (f9 * f9)));
        double sqrt4 = Math.sqrt((double) ((f10 * f10) + (f11 * f11)));
        float f12 = (float) (sqrt * ((double) f) * ((double) ((float) (((double) f5) / sqrt))));
        float f13 = (float) (((double) ((float) (((double) f6) / sqrt2))) * ((double) f) * sqrt2);
        float f14 = (float) (((double) ((float) (((double) f7) / sqrt2))) * sqrt2 * ((double) f));
        float f15 = (float) (((double) ((float) (((double) f8) / sqrt3))) * ((double) f) * sqrt3);
        float f16 = (float) (((double) ((float) (((double) f9) / sqrt3))) * ((double) f) * sqrt3);
        float f17 = (float) (((double) ((float) (((double) f10) / sqrt4))) * ((double) f) * sqrt4);
        float f18 = (float) (((double) ((float) (((double) f11) / sqrt4))) * ((double) f) * sqrt4);
        e[0] = ((float) (((double) ((float) (((double) f4) / sqrt))) * ((double) f) * sqrt)) + f2;
        e[1] = f12 + f3;
        e[2] = f2 + f13;
        e[3] = f3 + f14;
        e[6] = f2 + f15;
        e[7] = f3 + f16;
        e[4] = f2 + f17;
        e[5] = f3 + f18;
        return e;
    }

    /* renamed from: a */
    public static byte[] m3944a(byte[] bArr, boolean z, C5145a aVar) {
        float[] e = aVar.mo47275e();
        int n = z ? (int) aVar.mo47285o() : (int) aVar.mo47284n();
        int o = z ? (int) aVar.mo47284n() : (int) aVar.mo47285o();
        float w = ((float) aVar.mo47293w()) / ((float) aVar.mo47295x());
        int width = aVar.mo47286p().width();
        return m3943a(bArr, n, o, e, width, (int) (((float) width) / w), aVar);
    }

    /* renamed from: a */
    public static byte[] m3942a(byte[] bArr, float f, Size size, boolean z, C5145a aVar) {
        float[] a = m3945a(aVar, f);
        int n = z ? (int) aVar.mo47285o() : (int) aVar.mo47284n();
        int o = z ? (int) aVar.mo47284n() : (int) aVar.mo47285o();
        float w = ((float) aVar.mo47293w()) / ((float) aVar.mo47295x());
        size.width = (int) (((float) aVar.mo47286p().width()) * f);
        size.height = (int) (((float) size.width) / w);
        return m3943a(bArr, n, o, a, size.width, size.height, aVar);
    }

    /* renamed from: a */
    public static byte[] m3943a(byte[] bArr, int i, int i2, float[] fArr, int i3, int i4, C5145a aVar) {
        long currentTimeMillis = System.currentTimeMillis();
        byte[] bArr2 = new byte[(i4 * i3 * 3)];
        byte[] bArr3 = bArr;
        int i5 = i;
        int i6 = i2;
        ImgWarp.warp(bArr3, (long) i, i5, i6, PixelFormatType.PIXEL_FORMAT_YUV420_NV21, fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6], fArr[7], bArr2, i3, i4);
        Log.m1909d("TemplateMatcher", "Image warping took " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return bArr2;
    }
}
