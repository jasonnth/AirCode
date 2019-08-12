package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;

public class GuestControlToggleView extends LinearLayout implements DividerView {
    @BindView
    View border;
    @BindView
    ImageButton noButton;
    @BindView
    AirTextView title;
    @BindView
    ImageButton yesButton;

    public GuestControlToggleView(Context context) {
        super(context);
        init();
    }

    public GuestControlToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuestControlToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0880R.layout.guest_control_toggle_row, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setSelection(null);
    }

    public void setTitle(int titleId) {
        this.title.setText(titleId);
    }

    public void setSelection(Boolean selectionValue) {
        boolean z = false;
        if (selectionValue == null) {
            this.yesButton.setSelected(false);
            this.noButton.setSelected(false);
            return;
        }
        this.yesButton.setSelected(selectionValue.booleanValue());
        ImageButton imageButton = this.noButton;
        if (!selectionValue.booleanValue()) {
            z = true;
        }
        imageButton.setSelected(z);
    }

    public void setNoButtonClickListener(OnClickListener listener) {
        this.noButton.setOnClickListener(listener);
    }

    public void setYesButtonClickListener(OnClickListener listener) {
        this.yesButton.setOnClickListener(listener);
    }

    public void showDivider(boolean showDivider) {
        ViewUtils.setVisibleIf(this.border, showDivider);
    }
}
