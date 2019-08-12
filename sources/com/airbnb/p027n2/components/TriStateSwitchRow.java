package com.airbnb.p027n2.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.interfaces.ThreeWayToggle;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.TriStateSwitch;
import com.airbnb.p027n2.primitives.TriStateSwitch.OnCheckedChangeListener;
import com.airbnb.p027n2.primitives.TriStateSwitch.TriStateSwitchStyle;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.TriStateSwitchRow */
public class TriStateSwitchRow extends LinearLayout implements DividerView, ThreeWayToggle {
    @BindView
    AirTextView description;
    @BindView
    View dividerView;
    @BindView
    AirTextView title;
    @BindView
    TriStateSwitch triStateSwitchSelector;

    /* renamed from: com.airbnb.n2.components.TriStateSwitchRow$SavedState */
    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        ToggleState state;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.state = (ToggleState) in.readSerializable();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeSerializable(this.state);
        }
    }

    public TriStateSwitchRow(Context context) {
        super(context);
        init(null);
    }

    public TriStateSwitchRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TriStateSwitchRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(21)
    public TriStateSwitchRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_TriStateSwitchRow);
        TriStateSwitchStyle style = TriStateSwitchStyle.values()[ta.getInt(R.styleable.n2_TriStateSwitchRow_n2_triStateSwitchStyle, 0)];
        ta.recycle();
        inflate(getContext(), style == TriStateSwitchStyle.Sheet ? R.layout.n2_tri_state_switch_row : R.layout.n2_tri_state_switch_row_outlined, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(style == TriStateSwitchStyle.Sheet ? 0 : 1);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_TriStateSwitchRow);
        String titleText = ta.getString(R.styleable.n2_TriStateSwitchRow_n2_titleText);
        String descriptionText = ta.getString(R.styleable.n2_TriStateSwitchRow_n2_descriptionText);
        setTitle((CharSequence) titleText);
        setDescription(descriptionText);
        showDivider(true);
        ta.recycle();
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setDescription(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.description, !TextUtils.isEmpty(text));
        this.description.setText(text);
    }

    public void setState(ToggleState state) {
        this.triStateSwitchSelector.setState(state);
    }

    public ToggleState getState() {
        return this.triStateSwitchSelector.getState();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.triStateSwitchSelector.setOnCheckedChangeListener(listener);
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.state = getState();
        return ss;
    }

    public void onRestoreInstanceState(Parcelable savedState) {
        SavedState ss = (SavedState) savedState;
        super.onRestoreInstanceState(ss.getSuperState());
        setState(ss.state);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.triStateSwitchSelector.setEnabled(enabled);
    }

    public void showDivider(boolean showDivider) {
        if (this.dividerView != null) {
            ViewLibUtils.setVisibleIf(this.dividerView, showDivider);
        }
    }
}
