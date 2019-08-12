package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.ToggleView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.Icepick;
import icepick.State;

/* renamed from: com.airbnb.n2.components.ToggleActionRow */
public final class ToggleActionRow extends BaseDividerComponent implements Checkable {
    @State
    boolean checked;
    private OnCheckedChangeListener checkedChangedListener;
    private OnClickListener clickListener;
    @BindView
    AirTextView label;
    private boolean radio;
    private boolean readOnly;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;
    @BindView
    ToggleView toggle;

    /* renamed from: com.airbnb.n2.components.ToggleActionRow$OnCheckedChangeListener */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z);
    }

    public ToggleActionRow(Context context) {
        super(context);
    }

    public ToggleActionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToggleActionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_toggle_action_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        setupAttributes(attrs);
        super.setOnClickListener(ToggleActionRow$$Lambda$1.lambdaFactory$(this));
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ToggleActionRow, 0, 0);
        setTitle((CharSequence) a.getString(R.styleable.n2_ToggleActionRow_n2_titleText));
        setSubtitle((CharSequence) a.getString(R.styleable.n2_ToggleActionRow_n2_subtitleText));
        setLabel((CharSequence) a.getString(R.styleable.n2_ToggleActionRow_n2_labelText));
        a.recycle();
    }

    public Parcelable onSaveInstanceState() {
        this.checked = isChecked();
        return Icepick.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
        setChecked(this.checked);
    }

    /* access modifiers changed from: private */
    public void onClick() {
        if (!this.readOnly && (!this.radio || !isChecked())) {
            toggle();
            if (this.checkedChangedListener != null) {
                this.checkedChangedListener.onCheckedChanged(this, isChecked());
            }
        }
        if (this.clickListener != null) {
            this.clickListener.onClick(this);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener checkedChangedListener2) {
        this.checkedChangedListener = checkedChangedListener2;
    }

    public void setOnClickListener(OnClickListener clickListener2) {
        this.clickListener = clickListener2;
    }

    public void setChecked(boolean checked2) {
        this.toggle.setChecked(checked2);
    }

    public boolean isChecked() {
        return this.toggle.isChecked();
    }

    public void toggle() {
        this.toggle.toggle();
    }

    public void radio(boolean radio2) {
        this.radio = radio2;
    }

    public void readOnly(boolean readOnly2) {
        this.readOnly = readOnly2;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text);
    }

    public void setSubtitle(int stringId) {
        setSubtitle((CharSequence) getResources().getString(stringId));
    }

    @Deprecated
    public void setLabel(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.label, text);
    }

    @Deprecated
    public void setLabel(int stringId) {
        setLabel((CharSequence) getResources().getString(stringId));
    }

    public static void mock(ToggleActionRow view) {
        view.setTitle((CharSequence) "Toggle action row");
    }

    public static void mockSubtitleChecked(ToggleActionRow view) {
        view.setTitle((CharSequence) "Toggle action row");
        view.setSubtitle((CharSequence) "Optional subtitle");
        view.setChecked(true);
    }

    public static void mockRadio(ToggleActionRow view) {
        view.setTitle((CharSequence) "Toggle action row");
        view.radio(true);
    }

    public static void mockReadOnly(ToggleActionRow view) {
        view.setTitle((CharSequence) "Toggle action row");
        view.readOnly(true);
    }
}
