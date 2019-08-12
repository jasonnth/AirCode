package com.jumio.commons.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.renderscript.Allocation;
import android.renderscript.Allocation.MipmapControl;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import com.jumio.commons.utils.MutableBitmap;
import java.lang.reflect.Array;

public class BlurView extends View {
    private static final int ANIMATION_DURATION = 500;
    private static final int BLUR_RADIUS = 12;
    private static final int BLUR_SCALE = 3;
    private int actionBarHeight = 0;
    private Bitmap bitmap = null;
    private boolean blurOrientationPortrait = false;
    private boolean currentOrientationPortrait = false;
    private boolean isBlurred = false;
    private Matrix matrix = null;
    private RelativeLayout rootRelativeLayout;
    private int textureHeight;
    private Matrix textureMatrix = null;
    private int textureWidth;

    public BlurView(Context context) {
        super(context);
    }

    public BlurView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BlurView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRootRelativeLayout(RelativeLayout rootRelativeLayout2) {
        this.rootRelativeLayout = rootRelativeLayout2;
    }

    public void setActionBarHeight(int height) {
        this.actionBarHeight = height;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.bitmap != null) {
            canvas.drawBitmap(this.bitmap, this.matrix, null);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (this.bitmap == null) {
            return;
        }
        if (this.blurOrientationPortrait == this.currentOrientationPortrait) {
            calculateMatrix(this.textureWidth, this.textureHeight, this.textureMatrix);
        } else {
            calculateMatrix(w, h, null);
        }
    }

    private void calculateMatrix(int w, int h, Matrix frameMatrix) {
        float f = 90.0f;
        if (this.rootRelativeLayout != null && frameMatrix == null) {
            h += this.rootRelativeLayout.getRootView().getHeight() - this.rootRelativeLayout.getHeight();
            if (this.currentOrientationPortrait) {
                h -= this.actionBarHeight;
            }
        }
        this.matrix = new Matrix();
        if (this.blurOrientationPortrait) {
            Matrix matrix2 = this.matrix;
            if (this.currentOrientationPortrait) {
                f = 0.0f;
            }
            matrix2.postRotate(f, 0.0f, 0.0f);
            if (!this.currentOrientationPortrait) {
                this.matrix.postTranslate((float) this.bitmap.getHeight(), 0.0f);
                this.matrix.postScale(((float) w) / ((float) this.bitmap.getHeight()), ((float) h) / ((float) this.bitmap.getWidth()));
            } else {
                this.matrix.postScale(((float) w) / ((float) this.bitmap.getWidth()), ((float) h) / ((float) this.bitmap.getHeight()));
            }
        } else {
            Matrix matrix3 = this.matrix;
            if (!this.currentOrientationPortrait) {
                f = 0.0f;
            }
            matrix3.postRotate(f, 0.0f, 0.0f);
            if (!this.currentOrientationPortrait) {
                this.matrix.postScale(((float) w) / ((float) this.bitmap.getWidth()), ((float) h) / ((float) this.bitmap.getHeight()));
            } else {
                this.matrix.postTranslate((float) this.bitmap.getHeight(), 0.0f);
                this.matrix.postScale(((float) w) / ((float) this.bitmap.getHeight()), ((float) h) / ((float) this.bitmap.getWidth()));
            }
        }
        if (frameMatrix != null) {
            this.matrix.postConcat(frameMatrix);
        }
    }

    public void clearBlur() {
        MutableBitmap.delete(this.bitmap);
        this.bitmap = null;
        invalidate();
        setVisibility(8);
        this.isBlurred = false;
    }

    public void changeRotation(boolean portrait) {
        if (this.isBlurred && this.matrix != null) {
            this.currentOrientationPortrait = portrait;
        }
    }

    public void enableBlur(Bitmap lastFrame, Matrix frameMatrix, boolean portraitBlur) {
        if (!this.isBlurred && lastFrame != null) {
            this.isBlurred = true;
            this.blurOrientationPortrait = portraitBlur;
            this.currentOrientationPortrait = portraitBlur;
            Bitmap smallBitmap = MutableBitmap.createScaledBitmap(lastFrame, lastFrame.getWidth() / 3, lastFrame.getHeight() / 3);
            if (VERSION.SDK_INT > 16) {
                smallBitmap = blurRenderScript(smallBitmap);
            } else {
                blurFallback(smallBitmap);
            }
            this.bitmap = MutableBitmap.copy(smallBitmap);
            this.textureMatrix = frameMatrix;
            this.textureWidth = lastFrame.getWidth();
            this.textureHeight = lastFrame.getHeight();
            calculateMatrix(this.textureWidth, this.textureHeight, this.textureMatrix);
            MutableBitmap.delete(smallBitmap);
        }
    }

    public void showBlur() {
        Animation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeInAnimation.setDuration(500);
        setAnimation(fadeInAnimation);
        setVisibility(0);
    }

    @SuppressLint({"NewApi"})
    private Bitmap blurRenderScript(Bitmap smallBitmap) {
        Bitmap output = Bitmap.createBitmap(smallBitmap.getWidth(), smallBitmap.getHeight(), Config.ARGB_8888);
        RenderScript rs = RenderScript.create(getContext());
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation inAlloc = Allocation.createFromBitmap(rs, smallBitmap, MipmapControl.MIPMAP_NONE, 4);
        Allocation outAlloc = Allocation.createFromBitmap(rs, output);
        script.setRadius(10.0f);
        script.setInput(inAlloc);
        script.forEach(outAlloc);
        outAlloc.copyTo(output);
        MutableBitmap.delete(smallBitmap);
        return output;
    }

    private void blurFallback(Bitmap smallBitmap) {
        int w = smallBitmap.getWidth();
        int h = smallBitmap.getHeight();
        int[] pix = new int[(w * h)];
        smallBitmap.getPixels(pix, 0, w, 0, 0, w, h);
        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int[] r = new int[wh];
        int[] g = new int[wh];
        int[] b = new int[wh];
        int[] vmin = new int[Math.max(w, h)];
        int divsum = (26 >> 1) * 13;
        int[] dv = new int[43264];
        for (int i = 0; i < 43264; i++) {
            dv[i] = i / divsum;
        }
        int yi = 0;
        int yw = 0;
        int[][] stack = (int[][]) Array.newInstance(Integer.TYPE, new int[]{25, 3});
        for (int y = 0; y < h; y++) {
            int bsum = 0;
            int gsum = 0;
            int rsum = 0;
            int boutsum = 0;
            int goutsum = 0;
            int routsum = 0;
            int binsum = 0;
            int ginsum = 0;
            int rinsum = 0;
            for (int i2 = -12; i2 <= 12; i2++) {
                int p = pix[Math.min(wm, Math.max(i2, 0)) + yi];
                int[] sir = stack[i2 + 12];
                sir[0] = (16711680 & p) >> 16;
                sir[1] = (65280 & p) >> 8;
                sir[2] = p & 255;
                int rbs = 13 - Math.abs(i2);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i2 > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            int stackpointer = 12;
            for (int x = 0; x < w; x++) {
                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];
                int rsum2 = rsum - routsum;
                int gsum2 = gsum - goutsum;
                int bsum2 = bsum - boutsum;
                int[] sir2 = stack[((stackpointer - 12) + 25) % 25];
                int routsum2 = routsum - sir2[0];
                int goutsum2 = goutsum - sir2[1];
                int boutsum2 = boutsum - sir2[2];
                if (y == 0) {
                    vmin[x] = Math.min(x + 12 + 1, wm);
                }
                int p2 = pix[vmin[x] + yw];
                sir2[0] = (16711680 & p2) >> 16;
                sir2[1] = (65280 & p2) >> 8;
                sir2[2] = p2 & 255;
                int rinsum2 = rinsum + sir2[0];
                int ginsum2 = ginsum + sir2[1];
                int binsum2 = binsum + sir2[2];
                rsum = rsum2 + rinsum2;
                gsum = gsum2 + ginsum2;
                bsum = bsum2 + binsum2;
                stackpointer = (stackpointer + 1) % 25;
                int[] sir3 = stack[stackpointer % 25];
                routsum = routsum2 + sir3[0];
                goutsum = goutsum2 + sir3[1];
                boutsum = boutsum2 + sir3[2];
                rinsum = rinsum2 - sir3[0];
                ginsum = ginsum2 - sir3[1];
                binsum = binsum2 - sir3[2];
                yi++;
            }
            yw += w;
        }
        for (int x2 = 0; x2 < w; x2++) {
            int bsum3 = 0;
            int gsum3 = 0;
            int rsum3 = 0;
            int boutsum3 = 0;
            int goutsum3 = 0;
            int routsum3 = 0;
            int binsum3 = 0;
            int ginsum3 = 0;
            int rinsum3 = 0;
            int yp = w * -12;
            for (int i3 = -12; i3 <= 12; i3++) {
                int yi2 = Math.max(0, yp) + x2;
                int[] sir4 = stack[i3 + 12];
                sir4[0] = r[yi2];
                sir4[1] = g[yi2];
                sir4[2] = b[yi2];
                int rbs2 = 13 - Math.abs(i3);
                rsum3 += r[yi2] * rbs2;
                gsum3 += g[yi2] * rbs2;
                bsum3 += b[yi2] * rbs2;
                if (i3 > 0) {
                    rinsum3 += sir4[0];
                    ginsum3 += sir4[1];
                    binsum3 += sir4[2];
                } else {
                    routsum3 += sir4[0];
                    goutsum3 += sir4[1];
                    boutsum3 += sir4[2];
                }
                if (i3 < hm) {
                    yp += w;
                }
            }
            int yi3 = x2;
            int stackpointer2 = 12;
            for (int y2 = 0; y2 < h; y2++) {
                pix[yi3] = (-16777216 & pix[yi3]) | (dv[rsum3] << 16) | (dv[gsum3] << 8) | dv[bsum3];
                int rsum4 = rsum3 - routsum3;
                int gsum4 = gsum3 - goutsum3;
                int bsum4 = bsum3 - boutsum3;
                int[] sir5 = stack[((stackpointer2 - 12) + 25) % 25];
                int routsum4 = routsum3 - sir5[0];
                int goutsum4 = goutsum3 - sir5[1];
                int boutsum4 = boutsum3 - sir5[2];
                if (x2 == 0) {
                    vmin[y2] = Math.min(y2 + 13, hm) * w;
                }
                int p3 = x2 + vmin[y2];
                sir5[0] = r[p3];
                sir5[1] = g[p3];
                sir5[2] = b[p3];
                int rinsum4 = rinsum3 + sir5[0];
                int ginsum4 = ginsum3 + sir5[1];
                int binsum4 = binsum3 + sir5[2];
                rsum3 = rsum4 + rinsum4;
                gsum3 = gsum4 + ginsum4;
                bsum3 = bsum4 + binsum4;
                stackpointer2 = (stackpointer2 + 1) % 25;
                int[] sir6 = stack[stackpointer2];
                routsum3 = routsum4 + sir6[0];
                goutsum3 = goutsum4 + sir6[1];
                boutsum3 = boutsum4 + sir6[2];
                rinsum3 = rinsum4 - sir6[0];
                ginsum3 = ginsum4 - sir6[1];
                binsum3 = binsum4 - sir6[2];
                yi3 += w;
            }
        }
        smallBitmap.setPixels(pix, 0, w, 0, 0, w, h);
    }
}
