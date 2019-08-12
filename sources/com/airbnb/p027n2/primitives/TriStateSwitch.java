package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.ThreeWayToggle;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;

/* renamed from: com.airbnb.n2.primitives.TriStateSwitch */
public class TriStateSwitch extends FrameLayout implements ThreeWayToggle {
    @BindView
    TriStateSwitchHalf leftX;
    private OnCheckedChangeListener listener;
    @BindView
    TriStateSwitchHalf rightCheck;
    private ToggleState state = ToggleState.NEITHER;

    /* renamed from: com.airbnb.n2.primitives.TriStateSwitch$OnCheckedChangeListener */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(TriStateSwitch triStateSwitch, ToggleState toggleState);
    }

    /* renamed from: com.airbnb.n2.primitives.TriStateSwitch$TriStateSwitchStyle */
    public enum TriStateSwitchStyle {
        Sheet,
        Outlined
    }

    public TriStateSwitch(Context context) {
        super(context);
        init(null);
    }

    public TriStateSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TriStateSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_TriStateSwitch, 0, 0);
        TriStateSwitchStyle style = TriStateSwitchStyle.values()[ta.getInt(R.styleable.n2_TriStateSwitch_n2_triStateSwitchStyle, 0)];
        ta.recycle();
        inflate(getContext(), style == TriStateSwitchStyle.Sheet ? R.layout.n2_tri_state_switch : R.layout.n2_tri_state_switch_outlined, this);
        ButterKnife.bind((View) this);
        setListeners();
    }

    private void setListeners() {
        this.leftX.setOnChangeListener(TriStateSwitch$$Lambda$1.lambdaFactory$(this));
        this.rightCheck.setOnChangeListener(TriStateSwitch$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setListeners$0(TriStateSwitch triStateSwitch, TriStateSwitchHalf view, boolean isLeftChecked) {
        triStateSwitch.setState(isLeftChecked ? ToggleState.OFF : ToggleState.ON);
    }

    static /* synthetic */ void lambda$setListeners$1(TriStateSwitch triStateSwitch, TriStateSwitchHalf view, boolean isRightChecked) {
        triStateSwitch.setState(isRightChecked ? ToggleState.ON : ToggleState.OFF);
    }

    public void setState(ToggleState state2) {
        boolean z = true;
        if (this.state != state2) {
            this.state = state2;
            this.leftX.setChecked(state2 == ToggleState.OFF);
            TriStateSwitchHalf triStateSwitchHalf = this.rightCheck;
            if (state2 != ToggleState.ON) {
                z = false;
            }
            triStateSwitchHalf.setChecked(z);
            if (this.listener != null) {
                this.listener.onCheckedChanged(this, this.state);
            }
        }
    }

    public ToggleState getState() {
        return this.state;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener2) {
        this.listener = listener2;
    }
}
