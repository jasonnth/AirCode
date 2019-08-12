package com.miteksystems.misnap.analyzer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.params.SDKConstants;
import com.miteksystems.misnap.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: com.miteksystems.misnap.analyzer.b */
public class C4568b extends MiSnapAnalyzer {

    /* renamed from: a */
    private static final String f3652a = C4568b.class.getSimpleName();

    /* renamed from: b */
    private String f3653b;

    /* renamed from: c */
    private byte[] f3654c;

    /* renamed from: d */
    private byte[] f3655d;

    /* renamed from: e */
    private int f3656e;

    /* renamed from: f */
    private int f3657f;

    /* renamed from: g */
    private boolean f3658g;

    public C4568b(Context context, ParameterManager parameterManager) {
        super(context, parameterManager, true);
        this.f3653b = parameterManager.getTestScienceSessionName();
    }

    public boolean init() {
        Log.d(f3652a, "Initializing TestScienceOneDrawableAnalyzer");
        return super.init();
    }

    public void deinit() {
        super.deinit();
        Log.d(f3652a, "Deinit TestScienceOneDrawableAnalyzer");
    }

    public void analyze(IAnalyzeResponse iAnalyzeResponse, byte[] bArr, int i, int i2, int i3) {
        boolean z;
        int i4;
        if (this.f3654c == null && !this.f3658g) {
            int identifier = this.mAppContext.getResources().getIdentifier(this.f3653b, "drawable", this.mAppContext.getPackageName());
            if (identifier != 0) {
                Bitmap decodeResource = BitmapFactory.decodeResource(this.mAppContext.getResources(), identifier);
                this.f3656e = decodeResource.getWidth();
                this.f3657f = decodeResource.getHeight();
                int width = decodeResource.getWidth();
                int height = decodeResource.getHeight();
                int[] iArr = new int[(width * height)];
                decodeResource.getPixels(iArr, 0, width, 0, 0, width, height);
                byte[] bArr2 = new byte[(((width * height) * 3) / 2)];
                int i5 = width * height;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                while (i8 < height) {
                    int i9 = 0;
                    int i10 = i7;
                    int i11 = i6;
                    while (i9 < width) {
                        int i12 = (iArr[i10] & 16711680) >> 16;
                        int i13 = (iArr[i10] & ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK) >> 8;
                        int i14 = (iArr[i10] & 255) >> 0;
                        int i15 = (((((i12 * 66) + (i13 * 129)) + (i14 * 25)) + 128) >> 8) + 16;
                        int i16 = (((((i12 * -38) - (i13 * 74)) + (i14 * 112)) + 128) >> 8) + 128;
                        int i17 = (((((i12 * 112) - (i13 * 94)) - (i14 * 18)) + 128) >> 8) + 128;
                        int i18 = i11 + 1;
                        if (i15 < 0) {
                            i15 = 0;
                        } else if (i15 > 255) {
                            i15 = 255;
                        }
                        bArr2[i11] = (byte) i15;
                        if (i8 % 2 == 0 && i10 % 2 == 0) {
                            int i19 = i5 + 1;
                            int i20 = i17 < 0 ? 0 : i17 > 255 ? 255 : i17;
                            bArr2[i5] = (byte) i20;
                            int i21 = i19 + 1;
                            int i22 = i16 < 0 ? 0 : i16 > 255 ? 255 : i16;
                            bArr2[i19] = (byte) i22;
                            i4 = i21;
                        } else {
                            i4 = i5;
                        }
                        i9++;
                        i10++;
                        i11 = i18;
                        i5 = i4;
                    }
                    i8++;
                    i7 = i10;
                    i6 = i11;
                }
                this.f3654c = bArr2;
                this.f3655d = m2131a(decodeResource);
                decodeResource.recycle();
                z = true;
            } else {
                z = false;
            }
            if (z) {
                Utils.sendMsgToUIFragment(this.mAppContext, SDKConstants.UI_FRAGMENT_SHOW_REPLAY_FRAME, this.f3655d, new int[]{0, 0}, new int[]{this.f3656e - 1, 0}, new int[]{this.f3656e - 1, this.f3657f - 1}, new int[]{0, this.f3657f - 1});
            } else {
                Toast.makeText(this.mAppContext, "WARNING: Could not load injected image!", 1).show();
                this.f3658g = true;
            }
        }
        if (!this.f3658g) {
            super.analyze(iAnalyzeResponse, this.f3654c, this.f3656e, this.f3657f, 4);
        } else {
            iAnalyzeResponse.onAnalyzeFail(1, null);
        }
    }

    /* renamed from: a */
    private static byte[] m2131a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}
