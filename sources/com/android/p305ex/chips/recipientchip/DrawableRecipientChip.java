package com.android.p305ex.chips.recipientchip;

import android.graphics.Canvas;
import android.graphics.Rect;

/* renamed from: com.android.ex.chips.recipientchip.DrawableRecipientChip */
public interface DrawableRecipientChip extends BaseRecipientChip {
    void draw(Canvas canvas);

    Rect getBounds();
}
