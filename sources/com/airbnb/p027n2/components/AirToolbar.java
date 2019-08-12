package com.airbnb.p027n2.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.view.menu.ActionMenuItemView;
import android.support.p002v7.widget.ActionMenuView;
import android.support.p002v7.widget.AppCompatImageButton;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.Toolbar;
import android.support.p002v7.widget.Toolbar.OnMenuItemClickListener;
import android.support.p002v7.widget.ToolbarWidgetWrapper;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.ToolbarCoordinator;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.AirToolbar */
public class AirToolbar extends Toolbar {
    private static final String TAG = AirToolbar.class.getSimpleName();
    private Paint badgePaint;
    private int dividerHeight;
    /* access modifiers changed from: private */
    public int foregroundColor;
    private int foregroundColorDark;
    private int foregroundColorLight;
    private boolean hasCreatedMenu;
    private final SparseIntArray iconColors = new SparseIntArray();
    private int menuRes;
    /* access modifiers changed from: private */
    public MenuTransitionNameCallback menuTransitionNameCallback;
    private AppCompatImageButton navigationButton;
    private float navigationIconBadgeRadius;
    private int opaqueBackgroundColor;
    private int scrollWithId;
    private boolean showNavigationIconBadge;
    private AirTextView subtitleView;
    private int themeBackgroundColor;
    private int themeForegroundColor;
    private View titleSubtitleRootView;
    private AirTextView titleView;
    private ToolbarCoordinator toolbarCoordinator;
    private Paint toolbarDividerPaint;
    private int transparentBackgroundColor;

    /* renamed from: com.airbnb.n2.components.AirToolbar$DebouncedOnMenuItemClickListener */
    private static final class DebouncedOnMenuItemClickListener implements OnMenuItemClickListener {
        private final OnMenuItemClickListener clickListener;
        private long lastClickTime;

        DebouncedOnMenuItemClickListener(OnMenuItemClickListener clickListener2) {
            this.clickListener = clickListener2;
        }

        public boolean onMenuItemClick(MenuItem item) {
            long now = System.currentTimeMillis();
            if (now - this.lastClickTime < 1000) {
                return true;
            }
            this.lastClickTime = now;
            return this.clickListener.onMenuItemClick(item);
        }
    }

    /* renamed from: com.airbnb.n2.components.AirToolbar$MenuTransitionNameCallback */
    public interface MenuTransitionNameCallback {
        String getTransitionNameForMenuItem(int i);
    }

    /* renamed from: com.airbnb.n2.components.AirToolbar$TintableMenuItem */
    public interface TintableMenuItem {
        void setForegroundColor(int i);
    }

    public AirToolbar(Context context) {
        super(context);
        init(null);
    }

    public AirToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AirToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.transparentBackgroundColor = ContextCompat.getColor(getContext(), R.color.n2_action_bar_transparent_background);
        this.opaqueBackgroundColor = ContextCompat.getColor(getContext(), R.color.n2_action_bar_opaque_background);
        this.foregroundColorLight = ContextCompat.getColor(getContext(), R.color.n2_action_bar_foreground_light);
        this.foregroundColorDark = ContextCompat.getColor(getContext(), R.color.n2_action_bar_foreground_dark);
        this.navigationIconBadgeRadius = getContext().getResources().getDimension(R.dimen.n2_air_tool_bar_badge_radius);
        if (VERSION.SDK_INT < 21) {
            this.toolbarDividerPaint = new Paint();
            this.toolbarDividerPaint.setStyle(Style.FILL);
            this.toolbarDividerPaint.setColor(ContextCompat.getColor(getContext(), R.color.n2_divider_color));
            this.dividerHeight = getResources().getDimensionPixelSize(R.dimen.n2_divider_height);
        }
        this.badgePaint = new Paint();
        this.badgePaint.setStyle(Style.FILL);
        this.badgePaint.setColor(ContextCompat.getColor(getContext(), R.color.n2_babu));
        bindViews();
        setupAttributes(attrs);
    }

    @SuppressLint({"RestrictedApi"})
    private void bindViews() {
        this.titleSubtitleRootView = LayoutInflater.from(getContext()).inflate(R.layout.n2_toolbar_views, this, false);
        addView(this.titleSubtitleRootView);
        this.titleView = (AirTextView) ViewLibUtils.findById(this.titleSubtitleRootView, R.id.title);
        this.subtitleView = (AirTextView) ViewLibUtils.findById(this.titleSubtitleRootView, R.id.subtitle);
        ToolbarWidgetWrapper toolbarWidgetWrapper = new ToolbarWidgetWrapper(this, false);
        toolbarWidgetWrapper.setCustomView(this.titleSubtitleRootView);
        toolbarWidgetWrapper.setHomeButtonEnabled(true);
    }

    @SuppressLint({"ResourceType"})
    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_AirToolbar);
        CharSequence title = ta.getText(R.styleable.n2_AirToolbar_n2_titleText);
        CharSequence subtitle = ta.getText(R.styleable.n2_AirToolbar_n2_subtitleText);
        this.menuRes = ta.getResourceId(R.styleable.n2_AirToolbar_n2_menu, 0);
        int navigationIconType = ta.getInt(R.styleable.n2_AirToolbar_n2_navigationIcon, 1);
        int theme = ta.getColor(R.styleable.n2_AirToolbar_n2_theme, 1);
        this.scrollWithId = ta.getResourceId(R.styleable.n2_AirToolbar_n2_scrollWith, 0);
        setTitle(title);
        setSubtitle(subtitle);
        setNavigationIcon(navigationIconType);
        setTheme(theme);
        if (ta.getBoolean(R.styleable.n2_AirToolbar_n2_isSharedElement, true)) {
            ViewCompat.setTransitionName(this, "toolbar");
        }
        ta.recycle();
    }

    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        return onCreateOptionsMenu(this.menuRes, menu, inflater);
    }

    public boolean onCreateOptionsMenu(int menuRes2, Menu menu, MenuInflater inflater) {
        this.hasCreatedMenu = true;
        menu.clear();
        this.iconColors.clear();
        if (menuRes2 != 0) {
            inflater.inflate(menuRes2, menu);
            refreshForegroundColor();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        scrollWith(this.scrollWithId);
    }

    public void setMenuRes(int menuRes2) {
        if (this.hasCreatedMenu) {
            throw new IllegalStateException("You must set the menu res before onCreateOptionsMenu is called!");
        }
        this.menuRes = menuRes2;
    }

    public CharSequence getTitle() {
        return this.titleView.getText();
    }

    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    public void setTitle(CharSequence title) {
        this.titleView.setText(title);
        this.titleView.requestLayout();
    }

    public void setTitleTextAppearance(Context context, int resId) {
        this.titleView.setTextAppearance(context, resId);
        setForegroundColor(this.titleView.getCurrentTextColor());
    }

    public void setTitleTextColor(int color) {
        this.titleView.setTextColor(color);
    }

    public CharSequence getSubtitle() {
        return this.subtitleView.getText();
    }

    public void setSubtitle(int resId) {
        setSubtitle(getContext().getText(resId));
    }

    public void setSubtitle(CharSequence subtitle) {
        ViewLibUtils.setVisibleIf(this.subtitleView, !TextUtils.isEmpty(subtitle));
        this.subtitleView.setText(subtitle);
    }

    public void setSubtitleTextAppearance(Context context, int resId) {
        this.subtitleView.setTextAppearance(context, resId);
    }

    public void setSubtitleTextColor(int color) {
        this.subtitleView.setTextColor(color);
    }

    public void refreshForegroundColor() {
        setForegroundColor(this.foregroundColor);
    }

    public void addView(View child, LayoutParams params) {
        super.addView(child, params);
        if (child instanceof ActionMenuView) {
            ((ActionMenuView) child).setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
                public void onChildViewAdded(View parent, View child) {
                    if ((child instanceof ActionMenuItemView) && AirToolbar.this.foregroundColor != 0) {
                        AirToolbar.this.setActionMenuItemViewColor((ActionMenuItemView) child, AirToolbar.this.foregroundColor);
                    }
                    if (AirToolbar.this.menuTransitionNameCallback != null) {
                        ViewCompat.setTransitionName(child, AirToolbar.this.menuTransitionNameCallback.getTransitionNameForMenuItem(child.getId()));
                    }
                }

                public void onChildViewRemoved(View parent, View child) {
                }
            });
        } else if (child instanceof AppCompatImageButton) {
            this.navigationButton = (AppCompatImageButton) child;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.dividerHeight > 0) {
            this.toolbarDividerPaint.setAlpha(Color.alpha(getBackgroundColor()));
            canvas.drawRect(0.0f, (float) (canvas.getHeight() - this.dividerHeight), (float) canvas.getWidth(), (float) canvas.getHeight(), this.toolbarDividerPaint);
        }
        if (this.showNavigationIconBadge) {
            drawNavigationBadge(canvas);
        }
    }

    private void drawNavigationBadge(Canvas canvas) {
        if (this.navigationButton != null) {
            float drawableWidthOffset = ((float) this.navigationButton.getDrawable().getIntrinsicWidth()) * 0.5f;
            canvas.drawCircle(((float) this.navigationButton.getLeft()) + (((float) this.navigationButton.getWidth()) * 0.5f) + drawableWidthOffset, (((float) this.navigationButton.getTop()) + (((float) this.navigationButton.getHeight()) * 0.5f)) - (((float) this.navigationButton.getDrawable().getIntrinsicHeight()) * 0.5f), this.navigationIconBadgeRadius, this.badgePaint);
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        super.setOnMenuItemClickListener(new DebouncedOnMenuItemClickListener(listener));
    }

    public int getBackgroundColor() {
        Drawable backgroundColor = getBackground();
        if (backgroundColor instanceof ColorDrawable) {
            return ((ColorDrawable) backgroundColor).getColor();
        }
        Log.w(TAG, "Toolbar's background should be a ColorDrawable!");
        return -1;
    }

    public int getThemeForegroundColor() {
        return this.themeForegroundColor;
    }

    public int getThemeBackgroundColor() {
        return this.themeBackgroundColor;
    }

    public Toolbar setForegroundColor(int color) {
        if (color != 0) {
            this.foregroundColor = color;
            setTitleTextColor(color);
            setSubtitleTextColor(color);
            Drawable newIcon = tintDrawableIfNecessary(getNavigationIcon(), color);
            if (newIcon != null) {
                setNavigationIcon(newIcon);
            }
            Drawable newIcon2 = tintDrawableIfNecessary(getOverflowIcon(), color);
            if (newIcon2 != null) {
                setOverflowIcon(newIcon2);
            }
            setForegroundColorOnView(this, color);
            postInvalidate();
        }
        return this;
    }

    private void setForegroundColorOnView(View view, int color) {
        if (view instanceof ActionMenuItemView) {
            setActionMenuItemViewColor((ActionMenuItemView) view, color);
        } else if (view instanceof TintableMenuItem) {
            ((TintableMenuItem) view).setForegroundColor(color);
        } else if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setForegroundColorOnView(((ViewGroup) view).getChildAt(i), color);
            }
        }
    }

    public void setEllipsizeTitleInMiddle() {
        this.titleView.setEllipsize(TruncateAt.MIDDLE);
        if (VERSION.SDK_INT >= 21) {
            this.titleView.setLetterSpacing(0.0f);
        }
    }

    /* access modifiers changed from: private */
    public void setActionMenuItemViewColor(ActionMenuItemView itemView, int color) {
        itemView.setTextColor(color);
        itemView.setAllCaps(false);
        Drawable newIcon = tintDrawableIfNecessary(itemView.getCompoundDrawables()[0], color);
        if (newIcon != null) {
            itemView.setIcon(newIcon);
        }
    }

    private Drawable tintDrawableIfNecessary(Drawable drawable, int color) {
        if (drawable == null || Integer.valueOf(this.iconColors.get(drawable.hashCode())).intValue() == color) {
            return null;
        }
        Drawable coloredDrawable = ColorizedDrawable.mutateDrawableWithColor(drawable, color);
        coloredDrawable.invalidateSelf();
        this.iconColors.put(coloredDrawable.hashCode(), color);
        return coloredDrawable;
    }

    public void setMenuTransitionNameCallback(MenuTransitionNameCallback callback) {
        this.menuTransitionNameCallback = callback;
    }

    public void setTheme(int theme) {
        switch (theme) {
            case 1:
                this.themeBackgroundColor = this.opaqueBackgroundColor;
                this.themeForegroundColor = this.foregroundColorDark;
                this.badgePaint.setColor(ContextCompat.getColor(getContext(), R.color.n2_babu));
                break;
            case 2:
                this.themeBackgroundColor = this.transparentBackgroundColor;
                this.themeForegroundColor = this.foregroundColorLight;
                this.badgePaint.setColor(this.foregroundColorLight);
                break;
            case 3:
                this.themeBackgroundColor = this.transparentBackgroundColor;
                this.themeForegroundColor = this.foregroundColorDark;
                this.badgePaint.setColor(ContextCompat.getColor(getContext(), R.color.n2_babu));
                break;
            default:
                throw new IllegalArgumentException("Unknown theme " + theme);
        }
        if (this.toolbarCoordinator != null) {
            this.toolbarCoordinator.notifyThemeChanged();
            return;
        }
        setForegroundColor(this.themeForegroundColor);
        setBackgroundColor(this.themeBackgroundColor);
    }

    public void setNavigationIcon(int navigationIcon) {
        switch (navigationIcon) {
            case 0:
                super.setNavigationIcon((Drawable) null);
                break;
            case 1:
                super.setNavigationIcon(R.drawable.n2_ic_arrow_back_black);
                break;
            case 2:
                super.setNavigationIcon(R.drawable.n2_ic_x_black);
                break;
            case 3:
                super.setNavigationIcon(R.drawable.n2_ic_menu_black);
                break;
            default:
                throw new IllegalStateException("Unknown navigation icon type " + navigationIcon);
        }
        setForegroundColor(this.foregroundColor);
    }

    public void scrollWith(int resId) {
        if (resId != 0 && getParent() != null) {
            View scrollableView = null;
            ViewGroup parent = (ViewGroup) getParent();
            while (scrollableView == null && parent != null) {
                scrollableView = parent.findViewById(resId);
                parent = parent.getParent() instanceof ViewGroup ? (ViewGroup) parent.getParent() : null;
            }
            if (scrollableView == null) {
                throw new IllegalArgumentException("Unable to find scrollable view " + resId + ". Only RecyclerViews are supported for now.");
            } else if (scrollableView instanceof RecyclerView) {
                scrollWith((RecyclerView) scrollableView);
            } else if (scrollableView instanceof VerboseScrollView) {
                scrollWith((Scrollable<?>) (VerboseScrollView) scrollableView);
            } else {
                throw new IllegalArgumentException("Scollable view must be a RecyclerView or VerboseScrollView.");
            }
        }
    }

    public void scrollWith(RecyclerView recyclerView) {
        if (this.toolbarCoordinator != null) {
            this.toolbarCoordinator.stop();
        }
        this.toolbarCoordinator = ToolbarCoordinator.attachToRecyclerView(this, recyclerView);
    }

    public void scrollWith(Scrollable<?> scrollable) {
        if (this.toolbarCoordinator != null) {
            this.toolbarCoordinator.stop();
        }
        this.toolbarCoordinator = ToolbarCoordinator.attachToScrollable(this, scrollable);
    }

    public void setOnTitleClickListener(OnClickListener clickListener) {
        this.titleSubtitleRootView.setOnClickListener(clickListener);
        this.titleView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.n2_ic_chevron_down, 0);
    }

    public void stopScrolling() {
        if (this.toolbarCoordinator != null) {
            this.toolbarCoordinator.stop();
            this.toolbarCoordinator = null;
        }
    }

    public static void mockBack(AirToolbar toolbar) {
        toolbar.setTitle((CharSequence) "Title");
        toolbar.setNavigationIcon(1);
    }

    public static void mockX(AirToolbar toolbar) {
        toolbar.setTitle((CharSequence) "Title");
        toolbar.setNavigationIcon(2);
    }

    public static void mockMenu(AirToolbar toolbar) {
        toolbar.setTitle((CharSequence) "Title");
        toolbar.setNavigationIcon(3);
    }

    public static void mockSubtitle(AirToolbar toolbar) {
        toolbar.setTitle((CharSequence) "Title");
        toolbar.setSubtitle((CharSequence) "Subtitle");
    }

    public static void mockNoTitle(AirToolbar toolbar) {
        toolbar.setNavigationIcon(1);
    }

    public static void mockCaret(AirToolbar toolbar) {
        toolbar.setTitle((CharSequence) "Title");
        toolbar.setNavigationIcon(1);
        toolbar.setOnTitleClickListener(AirToolbar$$Lambda$1.lambdaFactory$());
    }
}
