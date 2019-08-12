package com.airbnb.paris;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt {
    public static final void setPadding(View $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.setPadding(px, px, px, px);
    }

    public static final void setPaddingBottom(View $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.setPadding($receiver.getPaddingLeft(), $receiver.getPaddingTop(), $receiver.getPaddingRight(), px);
    }

    public static final void setPaddingLeft(View $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.setPadding(px, $receiver.getPaddingTop(), $receiver.getPaddingRight(), $receiver.getPaddingBottom());
    }

    public static final void setPaddingRight(View $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.setPadding($receiver.getPaddingLeft(), $receiver.getPaddingTop(), px, $receiver.getPaddingBottom());
    }

    public static final void setPaddingTop(View $receiver, int px) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.setPadding($receiver.getPaddingLeft(), px, $receiver.getPaddingRight(), $receiver.getPaddingBottom());
    }
}
