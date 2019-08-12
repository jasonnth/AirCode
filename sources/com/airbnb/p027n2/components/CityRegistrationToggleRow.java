package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.CityRegistrationToggleRow */
public class CityRegistrationToggleRow extends RelativeLayout implements Checkable, DividerView {
    private OnCheckChangedListener checkChangedListener;
    @BindView
    AirImageView checkboxView;
    private boolean checked;
    @BindView
    View divider;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    /* renamed from: com.airbnb.n2.components.CityRegistrationToggleRow$OnCheckChangedListener */
    public interface OnCheckChangedListener {
        void onCheckChanged(CityRegistrationToggleRow cityRegistrationToggleRow, boolean z);
    }

    public CityRegistrationToggleRow(Context context) {
        super(context);
        init();
    }

    public CityRegistrationToggleRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CityRegistrationToggleRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_city_registration_toggle_row, this);
        ButterKnife.bind((View) this);
        super.setOnClickListener(CityRegistrationToggleRow$$Lambda$1.lambdaFactory$(this));
        setChecked(this.checked);
    }

    public void setOnClickListener(OnClickListener l) {
        if (l != null) {
            throw new IllegalStateException("Click listeners not supported on toggle row. Use a check changed listener.");
        }
    }

    public void setCheckChangedListener(OnCheckChangedListener checkChangedListener2) {
        this.checkChangedListener = checkChangedListener2;
    }

    public void setChecked(boolean checked2) {
        boolean changed = this.checked != checked2;
        this.checked = checked2;
        this.checkboxView.setImageDrawableCompat(checked2 ? R.drawable.n2_ic_checkbox_checked : R.drawable.n2_ic_checkbox_unchecked);
        if (this.checkChangedListener != null && changed) {
            this.checkChangedListener.onCheckChanged(this, checked2);
        }
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void toggle() {
        setChecked(!this.checked);
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

    public static void mock(CityRegistrationToggleRow view) {
        view.setTitle((CharSequence) "Title");
        view.setSubtitleText((CharSequence) "Subtitle");
    }
}
