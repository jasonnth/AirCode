package com.airbnb.p027n2.primitives;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.ButterKnife;
import com.airbnb.p027n2.utils.TextViewUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.primitives.ExpandableTextView */
public class ExpandableTextView extends AirTextView implements OnClickListener {
    private static final MovementMethod CLICKABLE_SPAN_MOVEMENT_METHOD = new LinkMovementMethodWithoutScrolling();
    private Boolean contentFits;
    private CharSequence contentText;
    /* access modifiers changed from: private */
    public ValueAnimator currentAnimator;
    private boolean expandable;
    private boolean expanded;
    private OnClickListener externalClickListener;
    private int maxLines = Integer.MAX_VALUE;
    private CharSequence readMoreText;
    @BindColor
    int readMoreTextColor;

    /* renamed from: com.airbnb.n2.primitives.ExpandableTextView$LinkMovementMethodWithoutScrolling */
    private static class LinkMovementMethodWithoutScrolling extends LinkMovementMethod {
        private LinkMovementMethodWithoutScrolling() {
        }

        public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
            int action = event.getAction();
            if (action == 1 || action == 0) {
                int x = (((int) event.getX()) - widget.getTotalPaddingLeft()) + widget.getScrollX();
                int y = (((int) event.getY()) - widget.getTotalPaddingTop()) + widget.getScrollY();
                Layout layout = widget.getLayout();
                int off = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
                ClickableSpan[] link = (ClickableSpan[]) buffer.getSpans(off, off, ClickableSpan.class);
                if (link.length == 0) {
                    Selection.removeSelection(buffer);
                } else if (action == 1) {
                    link[0].onClick(widget);
                    return true;
                } else {
                    Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                    return true;
                }
            }
            return false;
        }
    }

    public ExpandableTextView(Context context) {
        super(context);
        init(null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        ButterKnife.bind((View) this);
        setExpandable(true);
        Paris.style(this).apply(attrs);
        super.setOnClickListener(this);
    }

    public void setExpandable(boolean expandable2) {
        this.expandable = expandable2;
        this.expanded = expandable2 && this.expanded;
        setEllipsize(expandable2 ? null : TruncateAt.END);
        invalidateText();
    }

    public boolean getExpandable() {
        return this.expandable;
    }

    public void setMaxLines(int maxLines2) {
        if (maxLines2 != this.maxLines) {
            this.maxLines = maxLines2;
            invalidateText();
        }
    }

    public void clearMaxLines() {
        setMaxLines(Integer.MAX_VALUE);
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    public void setContentText(int textRes) {
        setContentText((CharSequence) getResources().getString(textRes));
    }

    public void setContentText(CharSequence contentText2) {
        if (!TextUtils.equals(contentText2, this.contentText)) {
            this.contentText = contentText2;
            invalidateText();
        }
    }

    public CharSequence getContentText() {
        return this.contentText;
    }

    public void setReadMoreText(CharSequence readMoreText2) {
        if (!TextUtils.equals(readMoreText2, this.readMoreText)) {
            this.readMoreText = readMoreText2;
            invalidateText();
        }
    }

    public CharSequence getReadMoreText() {
        return this.readMoreText;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.externalClickListener = listener;
    }

    private void invalidateText() {
        MovementMethod movementMethod;
        super.setMaxLines(this.expanded ? Integer.MAX_VALUE : this.maxLines);
        if (hasClickableSpans(this.contentText)) {
            movementMethod = CLICKABLE_SPAN_MOVEMENT_METHOD;
        } else {
            movementMethod = null;
        }
        setMovementMethod(movementMethod);
        this.contentFits = null;
        setText(this.contentText);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean clickShouldExpandOrCollapse;
        boolean clickShouldGoToExternalListener;
        boolean z;
        boolean z2 = false;
        super.onLayout(changed, left, top, right, bottom);
        Layout layout = getLayout();
        if (layout != null) {
            if (this.contentFits == null) {
                if (this.expanded || layout.getLineCount() > super.getMaxLines()) {
                    z = false;
                } else {
                    z = true;
                }
                this.contentFits = Boolean.valueOf(z);
            }
            if (layout.getLineCount() > super.getMaxLines() && this.expandable) {
                TextViewUtils.addReadMoreTextIfNeeded(this, this.readMoreText, this.readMoreTextColor, true);
            }
            if (!this.expandable || this.contentFits.booleanValue()) {
                clickShouldExpandOrCollapse = false;
            } else {
                clickShouldExpandOrCollapse = true;
            }
            if (clickShouldExpandOrCollapse || this.externalClickListener == null) {
                clickShouldGoToExternalListener = false;
            } else {
                clickShouldGoToExternalListener = true;
            }
            if (clickShouldExpandOrCollapse || clickShouldGoToExternalListener) {
                z2 = true;
            }
            setClickable(z2);
        }
    }

    public void onClick(View v) {
        if ((this.contentFits == null || !this.contentFits.booleanValue()) && this.expandable) {
            if (this.currentAnimator != null) {
                return;
            }
            if (this.expanded) {
                collapse();
            } else {
                expand();
            }
        } else if (this.externalClickListener != null) {
            this.externalClickListener.onClick(v);
        }
    }

    public void expand() {
        expand(true);
    }

    public void expand(boolean animate) {
        if (this.expandable && !this.expanded) {
            this.expanded = true;
            invalidateText();
            if (animate) {
                animateHeightChange();
            }
        }
    }

    public void collapse() {
        collapse(true);
    }

    public void collapse(boolean animate) {
        if (this.expanded) {
            this.expanded = false;
            invalidateText();
            if (animate) {
                animateHeightChange();
            }
        }
    }

    private void animateHeightChange() {
        int currentHeight = getHeight();
        measure(MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), -2);
        this.currentAnimator = ValueAnimator.ofInt(new int[]{currentHeight, getMeasuredHeight()});
        this.currentAnimator.addUpdateListener(ExpandableTextView$$Lambda$1.lambdaFactory$(this));
        this.currentAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                ExpandableTextView.this.currentAnimator = null;
                LayoutParams lp = ExpandableTextView.this.getLayoutParams();
                lp.height = -2;
                ExpandableTextView.this.setLayoutParams(lp);
            }
        });
        this.currentAnimator.start();
    }

    static /* synthetic */ void lambda$animateHeightChange$0(ExpandableTextView expandableTextView, ValueAnimator animation) {
        LayoutParams lp = expandableTextView.getLayoutParams();
        lp.height = ((Integer) animation.getAnimatedValue()).intValue();
        expandableTextView.setLayoutParams(lp);
    }

    private static boolean hasClickableSpans(CharSequence text) {
        if (!(text instanceof Spannable)) {
            return false;
        }
        if (((ClickableSpan[]) ((Spannable) text).getSpans(0, text.length(), ClickableSpan.class)).length > 0) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        CharSequence text = getText();
        MovementMethod movementMethod = getMovementMethod();
        if (movementMethod != CLICKABLE_SPAN_MOVEMENT_METHOD || !(text instanceof Spannable) || !movementMethod.onTouchEvent(this, (Spannable) text, event)) {
            return super.onTouchEvent(event);
        }
        return true;
    }
}
