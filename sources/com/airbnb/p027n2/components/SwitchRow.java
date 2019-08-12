package com.airbnb.p027n2.components;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;
import com.airbnb.p027n2.primitives.AirSwitch;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.SwitchRow */
public class SwitchRow extends BaseDividerComponent implements SwitchRowInterface {
    @BindView
    AirTextView description;
    @BindView
    AirSwitch switchView;
    @BindView
    AirTextView title;

    /* renamed from: com.airbnb.n2.components.SwitchRow$SavedState */
    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean checked;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.checked = ((Boolean) in.readValue(null)).booleanValue();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(Boolean.valueOf(this.checked));
        }
    }

    public SwitchRow(Context context) {
        super(context);
    }

    public SwitchRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_switch_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
        setClickable(true);
    }

    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public void setTitle(int titleRes) {
        setTitle((CharSequence) getResources().getString(titleRes));
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setDescription(int descriptionRes) {
        setDescription((CharSequence) descriptionRes == 0 ? "" : getResources().getString(descriptionRes));
    }

    public void setDescription(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.description, text);
    }

    public boolean isChecked() {
        return this.switchView.isChecked();
    }

    public void toggle() {
        setChecked(!this.switchView.isChecked());
    }

    public void setChecked(boolean checked) {
        setChecked(checked, true);
    }

    public void setChecked(boolean checked, boolean shouldAnimate) {
        this.switchView.setChecked(checked, shouldAnimate);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        if (listener == null) {
            this.switchView.setOnCheckedChangeListener(null);
        } else {
            this.switchView.setOnCheckedChangeListener(SwitchRow$$Lambda$1.lambdaFactory$(this, listener));
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.checked = isChecked();
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setChecked(ss.checked);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.switchView.setEnabled(enabled);
    }

    public View getView() {
        return this;
    }

    public static void mock(SwitchRow row) {
        row.setTitle((CharSequence) "Switch row");
    }

    public static void mockPlus(SwitchRow row) {
        row.setTitle((CharSequence) "Switch row +");
        row.setDescription((CharSequence) "Optional subtitle");
        row.setChecked(true);
    }

    public static void mockSheet(SwitchRow row) {
        row.setTitle((CharSequence) "Switch row");
        Paris.style(row).applySheet();
        row.setChecked(true);
    }

    public static void mockPlusSheet(SwitchRow row) {
        row.setTitle((CharSequence) "Switch row +");
        row.setDescription((CharSequence) "Optional subtitle");
        Paris.style(row).applySheet();
    }

    public static void mockPlusOutline(SwitchRow row) {
        row.setTitle((CharSequence) "Switch row +");
        row.setDescription((CharSequence) "Optional subtitle");
        Paris.style(row).apply(R.style.n2_SwitchRow_Outline);
    }
}
