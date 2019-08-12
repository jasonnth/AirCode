package com.appboy.p028ui.inappmessage.factories;

import android.content.res.Resources;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.p028ui.inappmessage.IInAppMessageAnimationFactory;
import com.appboy.p028ui.support.AnimationUtils;

/* renamed from: com.appboy.ui.inappmessage.factories.AppboyInAppMessageAnimationFactory */
public class AppboyInAppMessageAnimationFactory implements IInAppMessageAnimationFactory {
    private final int mShortAnimationDurationMillis = Resources.getSystem().getInteger(17694720);

    public Animation getOpeningAnimation(IInAppMessage inAppMessage) {
        if (!(inAppMessage instanceof InAppMessageSlideup)) {
            return AnimationUtils.setAnimationParams(new AlphaAnimation(0.0f, 1.0f), (long) this.mShortAnimationDurationMillis, true);
        }
        if (((InAppMessageSlideup) inAppMessage).getSlideFrom() == SlideFrom.TOP) {
            return AnimationUtils.createVerticalAnimation(-1.0f, 0.0f, (long) this.mShortAnimationDurationMillis, false);
        }
        return AnimationUtils.createVerticalAnimation(1.0f, 0.0f, (long) this.mShortAnimationDurationMillis, false);
    }

    public Animation getClosingAnimation(IInAppMessage inAppMessage) {
        if (!(inAppMessage instanceof InAppMessageSlideup)) {
            return AnimationUtils.setAnimationParams(new AlphaAnimation(1.0f, 0.0f), (long) this.mShortAnimationDurationMillis, false);
        }
        if (((InAppMessageSlideup) inAppMessage).getSlideFrom() == SlideFrom.TOP) {
            return AnimationUtils.createVerticalAnimation(0.0f, -1.0f, (long) this.mShortAnimationDurationMillis, false);
        }
        return AnimationUtils.createVerticalAnimation(0.0f, 1.0f, (long) this.mShortAnimationDurationMillis, false);
    }
}
