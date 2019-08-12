package com.airbnb.android.core.views.guestpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class GuestsPickerSwitchRowWhite extends BaseDividerComponent implements SwitchRowInterface {
    @BindView
    AirTextView description;
    @BindView
    GuestsPickerSwitchWhite switchView;
    @BindView
    AirTextView title;

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

    public GuestsPickerSwitchRowWhite(Context context) {
        super(context);
    }

    public GuestsPickerSwitchRowWhite(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuestsPickerSwitchRowWhite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return C0716R.layout.guests_picker_switch_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, C0716R.styleable.GuestsPickerSwitchRowWhite);
        if (ta != null) {
            int titleId = ta.getResourceId(C0716R.styleable.GuestsPickerSwitchRowWhite_titleRes, 0);
            int descriptionId = ta.getResourceId(C0716R.styleable.GuestsPickerSwitchRowWhite_descriptionRes, 0);
            if (titleId != 0) {
                setTitle(titleId);
            }
            if (descriptionId != 0) {
                setDescription(descriptionId);
            }
            ta.recycle();
        }
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
            this.switchView.setOnCheckedChangeListener(GuestsPickerSwitchRowWhite$$Lambda$1.lambdaFactory$(this, listener));
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
}
