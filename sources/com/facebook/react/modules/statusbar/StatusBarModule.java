package com.facebook.react.modules.statusbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import java.util.Map;

@ReactModule(name = "StatusBarManager")
public class StatusBarModule extends ReactContextBaseJavaModule {
    private static final String HEIGHT_KEY = "HEIGHT";

    public StatusBarModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "StatusBarManager";
    }

    public Map<String, Object> getConstants() {
        Context context = getReactApplicationContext();
        int heightResId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return MapBuilder.m1882of(HEIGHT_KEY, Float.valueOf(heightResId > 0 ? PixelUtil.toDIPFromPixel((float) context.getResources().getDimensionPixelSize(heightResId)) : 0.0f));
    }

    @ReactMethod
    public void setColor(final int color, final boolean animated) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.m1847w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                @TargetApi(21)
                public void run() {
                    if (animated) {
                        int curColor = activity.getWindow().getStatusBarColor();
                        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(curColor), Integer.valueOf(color)});
                        colorAnimation.addUpdateListener(new AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator animator) {
                                activity.getWindow().setStatusBarColor(((Integer) animator.getAnimatedValue()).intValue());
                            }
                        });
                        colorAnimation.setDuration(300).setStartDelay(0);
                        colorAnimation.start();
                        return;
                    }
                    activity.getWindow().setStatusBarColor(color);
                }
            });
        }
    }

    @ReactMethod
    public void setTranslucent(final boolean translucent) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.m1847w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                @TargetApi(21)
                public void run() {
                    View decorView = activity.getWindow().getDecorView();
                    if (translucent) {
                        decorView.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
                            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                                return defaultInsets.replaceSystemWindowInsets(defaultInsets.getSystemWindowInsetLeft(), 0, defaultInsets.getSystemWindowInsetRight(), defaultInsets.getSystemWindowInsetBottom());
                            }
                        });
                    } else {
                        decorView.setOnApplyWindowInsetsListener(null);
                    }
                    ViewCompat.requestApplyInsets(decorView);
                }
            });
        }
    }

    @ReactMethod
    public void setHidden(final boolean hidden) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.m1847w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    if (hidden) {
                        activity.getWindow().addFlags(1024);
                        activity.getWindow().clearFlags(2048);
                        return;
                    }
                    activity.getWindow().addFlags(2048);
                    activity.getWindow().clearFlags(1024);
                }
            });
        }
    }

    @ReactMethod
    public void setStyle(final String style) {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.m1847w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 23) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                @TargetApi(23)
                public void run() {
                    activity.getWindow().getDecorView().setSystemUiVisibility(style.equals("dark-content") ? 8192 : 0);
                }
            });
        }
    }
}
