package com.airbnb.android.sharing.p033ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.graphics.Palette;
import android.support.p002v7.graphics.Palette.Swatch;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/* renamed from: com.airbnb.android.sharing.ui.SharePreview */
public class SharePreview extends RelativeLayout {
    @BindView
    AirImageView image;
    @BindView
    TextView title;

    public SharePreview(Context context) {
        super(context);
        init(context, null);
    }

    public SharePreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SharePreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), C0921R.layout.share_preview, this);
        ButterKnife.bind((View) this);
        this.title.setVisibility(8);
        setMinimumHeight((int) (((double) context.getResources().getDisplayMetrics().heightPixels) * 0.6d));
        int[] colors = {ContextCompat.getColor(context, C0921R.color.c_foggy), ContextCompat.getColor(context, C0921R.color.c_hof)};
        GradientDrawable gradient = new GradientDrawable();
        gradient.setColors(colors);
        setBackground(gradient);
    }

    public void setImagePath(final String imagePath) {
        new AsyncTask<Void, Void, Bitmap>() {
            /* access modifiers changed from: protected */
            public Bitmap doInBackground(Void[] objects) {
                return BitmapFactory.decodeFile(imagePath);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Bitmap bitmap) {
                SharePreview.this.image.setImageBitmap(bitmap);
                SharePreview.this.setBackgroundColors(bitmap);
            }
        }.execute(new Void[0]);
    }

    public void setImageUrl(String url) {
        this.image.setImageUrl(url, new RequestListener<String, Bitmap>() {
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                SharePreview.this.setBackgroundColors(resource);
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void setBackgroundColors(Bitmap resource) {
        Palette.from(resource).generate(SharePreview$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setBackgroundColors$0(SharePreview sharePreview, Palette palette) {
        Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
        if (darkMutedSwatch != null && darkVibrantSwatch != null) {
            ((GradientDrawable) sharePreview.getBackground()).setColors(new int[]{darkVibrantSwatch.getRgb(), darkMutedSwatch.getRgb()});
        }
    }

    public void setTitle(String title2) {
        if (!TextUtils.isEmpty(title2)) {
            this.title.setText(title2);
            this.title.setVisibility(0);
        }
    }
}
