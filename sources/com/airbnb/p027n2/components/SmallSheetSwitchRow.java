package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.SmallSheetSwitchRow */
public class SmallSheetSwitchRow extends LinearLayout implements DividerView, SwitchRowInterface {
    @BindView
    AirTextView description;
    @BindView
    View dividerView;
    @BindView
    SmallSheetSwitchRowSwitch switchView;
    @BindView
    AirTextView title;

    /* renamed from: com.airbnb.n2.components.SmallSheetSwitchRow$SavedState */
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

    public SmallSheetSwitchRow(Context context) {
        super(context);
        init(null);
    }

    public SmallSheetSwitchRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SmallSheetSwitchRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        getContext().obtainStyledAttributes(attrs, R.styleable.n2_SwitchRow).recycle();
        try {
            inflate(getContext(), R.layout.n2_small_sheet_switch_row, this);
        } catch (Exception e) {
            for (Throwable cause = e; cause != null; cause = cause.getCause()) {
                Log.d("Gabe", "SmallSheetSwitchRow#init\t", cause);
            }
        }
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
        setFocusable(true);
        setClickable(true);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_SmallSheetSwitchRow, 0, 0);
        String titleText = ta.getString(R.styleable.n2_SmallSheetSwitchRow_n2_titleText);
        String descriptionText = ta.getString(R.styleable.n2_SmallSheetSwitchRow_n2_descriptionText);
        showDivider(ta.getBoolean(R.styleable.n2_SmallSheetSwitchRow_n2_showDivider, false));
        setTitle((CharSequence) titleText);
        setDescription((CharSequence) descriptionText);
        ta.recycle();
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
        ViewLibUtils.setVisibleIf(this.description, !TextUtils.isEmpty(text));
        this.description.setText(text);
    }

    public boolean isChecked() {
        return this.switchView.isChecked();
    }

    public void toggle() {
        setChecked(!this.switchView.isChecked());
    }

    public void setChecked(boolean checked) {
        this.switchView.setChecked(checked);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        if (listener == null) {
            this.switchView.setOnCheckedChangeListener(null);
        } else {
            this.switchView.setOnCheckedChangeListener(SmallSheetSwitchRow$$Lambda$1.lambdaFactory$(this, listener));
        }
    }

    public View getView() {
        return this;
    }

    public void showDivider(boolean showDivider) {
        if (this.dividerView != null) {
            ViewLibUtils.setVisibleIf(this.dividerView, showDivider);
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

    public static void mock(SmallSheetSwitchRow view) {
        view.setTitle((CharSequence) "Title");
    }
}
