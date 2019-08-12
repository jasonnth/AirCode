package com.facebook.react.views.toolbar;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.support.p002v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.airmapview.AirMapInterface;
import com.facebook.react.C3704R;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.toolbar.events.ToolbarClickEvent;
import java.util.Map;

public class ReactToolbarManager extends ViewGroupManager<ReactToolbar> {
    private static final String REACT_CLASS = "ToolbarAndroid";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ReactToolbar createViewInstance(ThemedReactContext reactContext) {
        return new ReactToolbar(reactContext);
    }

    @ReactProp(name = "logo")
    public void setLogo(ReactToolbar view, ReadableMap logo) {
        view.setLogoSource(logo);
    }

    @ReactProp(name = "navIcon")
    public void setNavIcon(ReactToolbar view, ReadableMap navIcon) {
        view.setNavIconSource(navIcon);
    }

    @ReactProp(name = "overflowIcon")
    public void setOverflowIcon(ReactToolbar view, ReadableMap overflowIcon) {
        view.setOverflowIconSource(overflowIcon);
    }

    @ReactProp(name = "rtl")
    public void setRtl(ReactToolbar view, boolean rtl) {
        view.setLayoutDirection(rtl ? 1 : 0);
    }

    @ReactProp(name = "subtitle")
    public void setSubtitle(ReactToolbar view, String subtitle) {
        view.setSubtitle((CharSequence) subtitle);
    }

    @ReactProp(customType = "Color", name = "subtitleColor")
    public void setSubtitleColor(ReactToolbar view, Integer subtitleColor) {
        int[] defaultColors = getDefaultColors(view.getContext());
        if (subtitleColor != null) {
            view.setSubtitleTextColor(subtitleColor.intValue());
        } else {
            view.setSubtitleTextColor(defaultColors[1]);
        }
    }

    @ReactProp(name = "title")
    public void setTitle(ReactToolbar view, String title) {
        view.setTitle((CharSequence) title);
    }

    @ReactProp(customType = "Color", name = "titleColor")
    public void setTitleColor(ReactToolbar view, Integer titleColor) {
        int[] defaultColors = getDefaultColors(view.getContext());
        if (titleColor != null) {
            view.setTitleTextColor(titleColor.intValue());
        } else {
            view.setTitleTextColor(defaultColors[0]);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetStart")
    public void setContentInsetStart(ReactToolbar view, float insetStart) {
        int inset;
        if (Float.isNaN(insetStart)) {
            inset = getDefaultContentInsets(view.getContext())[0];
        } else {
            inset = Math.round(PixelUtil.toPixelFromDIP(insetStart));
        }
        view.setContentInsetsRelative(inset, view.getContentInsetEnd());
    }

    @ReactProp(defaultFloat = Float.NaN, name = "contentInsetEnd")
    public void setContentInsetEnd(ReactToolbar view, float insetEnd) {
        int inset;
        if (Float.isNaN(insetEnd)) {
            inset = getDefaultContentInsets(view.getContext())[1];
        } else {
            inset = Math.round(PixelUtil.toPixelFromDIP(insetEnd));
        }
        view.setContentInsetsRelative(view.getContentInsetStart(), inset);
    }

    @ReactProp(name = "nativeActions")
    public void setActions(ReactToolbar view, ReadableArray actions) {
        view.setActions(actions);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext reactContext, final ReactToolbar view) {
        final EventDispatcher mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        view.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mEventDispatcher.dispatchEvent(new ToolbarClickEvent(view.getId(), -1));
            }
        });
        view.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                mEventDispatcher.dispatchEvent(new ToolbarClickEvent(view.getId(), menuItem.getOrder()));
                return true;
            }
        });
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.m1882of("ShowAsAction", MapBuilder.m1884of("never", Integer.valueOf(0), "always", Integer.valueOf(2), "ifRoom", Integer.valueOf(1)));
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    private int[] getDefaultContentInsets(Context context) {
        Theme theme = context.getTheme();
        TypedArray toolbarStyle = null;
        TypedArray contentInsets = null;
        try {
            toolbarStyle = theme.obtainStyledAttributes(new int[]{C3704R.attr.toolbarStyle});
            contentInsets = theme.obtainStyledAttributes(toolbarStyle.getResourceId(0, 0), new int[]{C3704R.attr.contentInsetStart, C3704R.attr.contentInsetEnd});
            return new int[]{contentInsets.getDimensionPixelSize(0, 0), contentInsets.getDimensionPixelSize(1, 0)};
        } finally {
            recycleQuietly(toolbarStyle);
            recycleQuietly(contentInsets);
        }
    }

    private static int[] getDefaultColors(Context context) {
        Theme theme = context.getTheme();
        TypedArray toolbarStyle = null;
        TypedArray textAppearances = null;
        TypedArray titleTextAppearance = null;
        TypedArray subtitleTextAppearance = null;
        try {
            toolbarStyle = theme.obtainStyledAttributes(new int[]{C3704R.attr.toolbarStyle});
            textAppearances = theme.obtainStyledAttributes(toolbarStyle.getResourceId(0, 0), new int[]{C3704R.attr.titleTextAppearance, C3704R.attr.subtitleTextAppearance});
            int titleTextAppearanceResId = textAppearances.getResourceId(0, 0);
            int subtitleTextAppearanceResId = textAppearances.getResourceId(1, 0);
            titleTextAppearance = theme.obtainStyledAttributes(titleTextAppearanceResId, new int[]{16842904});
            subtitleTextAppearance = theme.obtainStyledAttributes(subtitleTextAppearanceResId, new int[]{16842904});
            return new int[]{titleTextAppearance.getColor(0, AirMapInterface.CIRCLE_BORDER_COLOR), subtitleTextAppearance.getColor(0, AirMapInterface.CIRCLE_BORDER_COLOR)};
        } finally {
            recycleQuietly(toolbarStyle);
            recycleQuietly(textAppearances);
            recycleQuietly(titleTextAppearance);
            recycleQuietly(subtitleTextAppearance);
        }
    }

    private static void recycleQuietly(TypedArray style) {
        if (style != null) {
            style.recycle();
        }
    }
}
