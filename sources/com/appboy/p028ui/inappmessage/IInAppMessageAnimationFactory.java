package com.appboy.p028ui.inappmessage;

import android.view.animation.Animation;
import com.appboy.models.IInAppMessage;

/* renamed from: com.appboy.ui.inappmessage.IInAppMessageAnimationFactory */
public interface IInAppMessageAnimationFactory {
    Animation getClosingAnimation(IInAppMessage iInAppMessage);

    Animation getOpeningAnimation(IInAppMessage iInAppMessage);
}
