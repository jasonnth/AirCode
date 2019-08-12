package com.airbnb.p027n2.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.CalendarBubblePopUp */
public class CalendarBubblePopUp extends LinearLayout {
    /* access modifiers changed from: private */
    public int bubbleCornerRadius;
    private int bubbleHorizontalPadding;
    private int bubbleVerticalPadding;
    @BindView
    AirImageView closeIcon;
    @BindView
    AirTextView message;
    /* access modifiers changed from: private */
    public int pointerHeight;
    private int pointerWidth;

    public CalendarBubblePopUp(Context context) {
        super(context);
        init(context);
    }

    public CalendarBubblePopUp(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarBubblePopUp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        super.dispatchTouchEvent(event);
        return true;
    }

    private void init(Context context) {
        inflate(context, R.layout.n2_calendar_pop_up, this);
        ButterKnife.bind((View) this);
        setOrientation(0);
        setLayoutParams(new LayoutParams(getResources().getDimensionPixelSize(R.dimen.n2_calendar_pop_up_width), -2));
        initDimens();
    }

    private void initDimens() {
        Resources r = getResources();
        this.pointerWidth = r.getDimensionPixelSize(R.dimen.n2_calendar_pop_up_pointer_width);
        this.pointerHeight = r.getDimensionPixelSize(R.dimen.n2_calendar_pop_up_pointer_height);
        this.bubbleCornerRadius = r.getDimensionPixelSize(R.dimen.n2_calendar_pop_up_box_corner_radius);
        this.bubbleHorizontalPadding = r.getDimensionPixelSize(R.dimen.n2_calendar_pop_up_horizontal_padding);
        this.bubbleVerticalPadding = r.getDimensionPixelSize(R.dimen.n2_vertical_padding_small);
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public void onSizeChanged(final int w, final int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (ViewLibUtils.isAtLeastLollipop()) {
            setOutlineProvider(new ViewOutlineProvider() {
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, w, h - CalendarBubblePopUp.this.pointerHeight, (float) CalendarBubblePopUp.this.bubbleCornerRadius);
                }
            });
        }
    }

    public void setCloseIconOnClickListener(OnClickListener listener) {
        this.closeIcon.setOnClickListener(listener);
    }

    public void setText(String text) {
        this.message.setText(text);
    }

    public void setPointerPosition(int position) {
        BubbleDrawable bubbleDrawable = new BubbleDrawable();
        bubbleDrawable.setFillColor(-1);
        bubbleDrawable.setPointerWidth(this.pointerWidth);
        bubbleDrawable.setPointerHeight(this.pointerHeight);
        bubbleDrawable.setCornerRadius((float) this.bubbleCornerRadius);
        bubbleDrawable.setPadding(this.bubbleHorizontalPadding, this.bubbleVerticalPadding, this.bubbleHorizontalPadding, this.bubbleVerticalPadding);
        bubbleDrawable.setPointerCenter(position);
        setBackground(bubbleDrawable);
    }

    public static void mock(CalendarBubblePopUp view) {
        view.setText("Katie requires a minimum stay of 2 nights.");
        view.setPointerPosition(135);
    }
}
