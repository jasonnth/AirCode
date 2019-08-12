package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar.TintableMenuItem;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.primitives.AirBadgableMenuActionView */
public class AirBadgableMenuActionView extends FrameLayout implements TintableMenuItem {
    private ImageView badge;
    private ImageView icon;
    private int toolbarForegroundColor;

    public AirBadgableMenuActionView(Context context) {
        super(context);
        init(null);
    }

    public AirBadgableMenuActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AirBadgableMenuActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Context context = getContext();
        inflate(getContext(), R.layout.n2_badgable_menu_action_view, this);
        this.icon = (ImageView) findViewById(R.id.icon);
        this.badge = (ImageView) findViewById(R.id.badge);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_AirBadgableMenuActionView);
        Drawable drawable = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_AirBadgableMenuActionView_n2_icon);
        boolean showBadge = a.getBoolean(R.styleable.n2_AirBadgableMenuActionView_n2_showBadge, false);
        a.recycle();
        setIcon(drawable);
        showBadge(showBadge);
    }

    public void showBadge(boolean show) {
        ViewLibUtils.setVisibleIf(this.badge, show);
    }

    private Drawable getIcon() {
        return this.icon.getDrawable();
    }

    private void setIcon(Drawable drawable) {
        this.icon.setImageDrawable(drawable);
    }

    public void setForegroundColor(int color) {
        if (color != this.toolbarForegroundColor) {
            setIcon(ColorizedDrawable.mutateDrawableWithColor(getIcon(), color));
        }
    }
}
