package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.DotsCounter */
public class DotsCounter extends LinearLayout {
    private int mMarginPx;
    private int mNumDots;
    private Paint mPaint;
    private int mSelected;
    private int mSelectedAlpha;
    private int mSelectedColor;
    private int mUnselectedAlpha;
    private int mUnselectedColor;

    public DotsCounter(Context context) {
        super(context);
    }

    public DotsCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray aPhoto = getContext().obtainStyledAttributes(attrs, R.styleable.n2_DotsCounter);
        this.mMarginPx = (int) (aPhoto.getDimension(R.styleable.n2_DotsCounter_n2_dotMargin, 0.0f) / 2.0f);
        this.mSelectedColor = aPhoto.getColor(R.styleable.n2_DotsCounter_n2_selectedColor, getResources().getColor(17170443));
        this.mUnselectedColor = aPhoto.getColor(R.styleable.n2_DotsCounter_n2_unselectedColor, getResources().getColor(R.color.n2_c_gray_3));
        this.mSelectedAlpha = aPhoto.getInteger(R.styleable.n2_DotsCounter_n2_selectedAlpha, 255);
        this.mUnselectedAlpha = aPhoto.getInteger(R.styleable.n2_DotsCounter_n2_unselectedAlpha, 200);
        this.mPaint = new Paint();
        aPhoto.recycle();
    }

    /* access modifiers changed from: private */
    public void setupDots() {
        boolean z;
        if (this.mNumDots != 0) {
            removeAllViews();
            int height = getHeight();
            if (height > 0) {
                for (int i = 0; i < this.mNumDots; i++) {
                    if (this.mSelected == i) {
                        z = true;
                    } else {
                        z = false;
                    }
                    View imageView = getDot(z);
                    LayoutParams layout = new LayoutParams(height, height);
                    layout.setMargins(this.mMarginPx, 0, this.mMarginPx, 0);
                    imageView.setLayoutParams(layout);
                    addView(imageView);
                }
            }
        }
    }

    private View getDot(boolean selected) {
        ImageView image = new ImageView(getContext());
        int height = getHeight();
        Bitmap bitmap = Bitmap.createBitmap(height, height, Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(selected ? this.mSelectedColor : this.mUnselectedColor);
        this.mPaint.setAlpha(selected ? this.mSelectedAlpha : this.mUnselectedAlpha);
        int radius = height / 2;
        c.drawCircle((float) radius, (float) radius, (float) radius, this.mPaint);
        image.setBackground(new BitmapDrawable(getResources(), bitmap));
        return image;
    }

    public void setNumDots(int dots) {
        this.mNumDots = dots;
        post(DotsCounter$$Lambda$1.lambdaFactory$(this));
    }

    public void setSelectedDot(int selected) {
        if (this.mSelected != selected) {
            this.mSelected = selected;
            setupDots();
        }
    }

    public int getSelectedPosition() {
        return this.mSelected;
    }
}
