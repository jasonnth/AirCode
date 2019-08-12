package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.ImageRow */
public final class ImageRow extends BaseDividerComponent {
    @BindView
    AirImageView image;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public ImageRow(Context context) {
        super(context);
    }

    public ImageRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_image_row;
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitle, text, true);
    }

    public void setSubtitle(int textRes) {
        setSubtitle((CharSequence) getResources().getString(textRes));
    }

    public void setImage(int drawableRes) {
        this.image.setImageResource(drawableRes);
    }

    public void setImage(Image image2) {
        this.image.setImage(image2);
    }

    public static void mockTitle(ImageRow row) {
        row.setTitle((CharSequence) "Title");
        row.setImage((Image) new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg"));
    }

    public static void mockLongTitle(ImageRow row) {
        row.setTitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        row.setImage((Image) new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg"));
    }

    public static void mockWithSubtitle(ImageRow row) {
        row.setTitle((CharSequence) "Title");
        row.setImage((Image) new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg"));
        row.setSubtitle((CharSequence) "Optional subtitle");
        row.setOnClickListener(ImageRow$$Lambda$1.lambdaFactory$());
    }

    public static void mockWithRichSubtitle(ImageRow row) {
        row.setTitle((CharSequence) "Title");
        row.setImage((Image) new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg"));
        row.setSubtitle(new AirTextBuilder(row.getContext()).append("Subtitle supports rich text - ").appendBold("bold text, ").appendItalic("italic text, ").appendLink("and inline links", ImageRow$$Lambda$2.lambdaFactory$()).build());
        row.setOnClickListener(ImageRow$$Lambda$3.lambdaFactory$());
    }

    public static void mockWithLongSubtitle(ImageRow row) {
        row.setTitle((CharSequence) "Title");
        row.setImage((Image) new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg"));
        row.setSubtitle((CharSequence) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eros non justo accumsan ullamcorper. Duis pellentesque sem at facilisis mattis. Morbi pellentesque ligula vitae aliquam sagittis.");
    }
}
