package com.airbnb.android.react;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.interfaces.Scrollable;

final /* synthetic */ class AirbnbReactScrollViewManager$$Lambda$1 implements Runnable {
    private final AirbnbReactScrollViewManager arg$1;
    private final AirbnbReactScrollView arg$2;
    private final ReactInterface arg$3;

    private AirbnbReactScrollViewManager$$Lambda$1(AirbnbReactScrollViewManager airbnbReactScrollViewManager, AirbnbReactScrollView airbnbReactScrollView, ReactInterface reactInterface) {
        this.arg$1 = airbnbReactScrollViewManager;
        this.arg$2 = airbnbReactScrollView;
        this.arg$3 = reactInterface;
    }

    public static Runnable lambdaFactory$(AirbnbReactScrollViewManager airbnbReactScrollViewManager, AirbnbReactScrollView airbnbReactScrollView, ReactInterface reactInterface) {
        return new AirbnbReactScrollViewManager$$Lambda$1(airbnbReactScrollViewManager, airbnbReactScrollView, reactInterface);
    }

    public void run() {
        this.arg$2.addOnAttachStateChangeListener(new OnAttachStateChangeListener(this.arg$3, this.arg$2) {
            public void onViewAttachedToWindow(View view) {
                AirToolbar toolbar = component.getToolbar();
                if (toolbar != null) {
                    toolbar.scrollWith((Scrollable<?>) scrollView);
                }
            }

            public void onViewDetachedFromWindow(View view) {
                AirToolbar toolbar = component.getToolbar();
                if (toolbar != null) {
                    toolbar.stopScrolling();
                }
            }
        });
    }
}
