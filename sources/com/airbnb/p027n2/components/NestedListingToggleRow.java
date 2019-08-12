package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.NestedListingToggleRow */
public class NestedListingToggleRow extends RelativeLayout implements Checkable, DividerView {
    @BindView
    AirImageView checkboxView;
    private boolean checked;
    @BindView
    View divider;
    @BindView
    AirImageView imageDrawable;
    private OnCheckChangedListener listener;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    /* renamed from: com.airbnb.n2.components.NestedListingToggleRow$OnCheckChangedListener */
    public interface OnCheckChangedListener {
        void onCheckChanged(NestedListingToggleRow nestedListingToggleRow);
    }

    public NestedListingToggleRow(Context context) {
        super(context);
        init(null);
    }

    public NestedListingToggleRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NestedListingToggleRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_nested_listing_toggle_row, this);
        ButterKnife.bind((View) this);
        super.setOnClickListener(NestedListingToggleRow$$Lambda$1.lambdaFactory$(this));
        setChecked(false);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_NestedListingToggleRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_NestedListingToggleRow_n2_titleText);
        String subtitleText2 = a.getString(R.styleable.n2_NestedListingToggleRow_n2_subtitleText);
        Drawable imageDrawable2 = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_NestedListingToggleRow_n2_image);
        setTitle((CharSequence) titleText2);
        setSubtitleText((CharSequence) subtitleText2);
        setImageDrawable(imageDrawable2);
        a.recycle();
    }

    public static void mock(NestedListingToggleRow view) {
        view.setTitle((CharSequence) "The best listing");
        view.setSubtitleText((CharSequence) "Private Room");
        view.setImageDrawable(R.drawable.n2_ic_camera);
    }

    /* access modifiers changed from: private */
    public void onClick() {
        if (this.listener != null) {
            this.listener.onCheckChanged(this);
        }
    }

    public void setCheckChangedListener(OnCheckChangedListener listener2) {
        this.listener = listener2;
    }

    public void setChecked(boolean checked2) {
        this.checked = checked2;
        this.checkboxView.setImageDrawableCompat(checked2 ? R.drawable.n2_ic_radio_button_selected : R.drawable.n2_ic_radio_button_unselected);
    }

    public void toggle() {
        setChecked(!this.checked);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setImageUrl(String imageUrl) {
        this.imageDrawable.setImageUrl(imageUrl);
    }

    public void setImageDrawable(int drawableRes) {
        this.imageDrawable.setImageResource(drawableRes);
    }

    public void setImageDrawable(Drawable drawable) {
        this.imageDrawable.setImageDrawable(drawable);
    }

    public void clearImage() {
        this.imageDrawable.clear();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitleText, !TextUtils.isEmpty(text));
        this.subtitleText.setText(text);
    }

    public void setSubtitleText(int stringId) {
        setSubtitleText((CharSequence) getResources().getString(stringId));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}
