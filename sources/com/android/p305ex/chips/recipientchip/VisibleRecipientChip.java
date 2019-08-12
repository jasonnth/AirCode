package com.android.p305ex.chips.recipientchip;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import com.android.p305ex.chips.RecipientEntry;

/* renamed from: com.android.ex.chips.recipientchip.VisibleRecipientChip */
public class VisibleRecipientChip extends ImageSpan implements DrawableRecipientChip {
    private final SimpleRecipientChip mDelegate;

    public VisibleRecipientChip(Drawable drawable, RecipientEntry entry, int verticalAlignment) {
        super(drawable, verticalAlignment);
        this.mDelegate = new SimpleRecipientChip(entry);
    }

    public void setSelected(boolean selected) {
        this.mDelegate.setSelected(selected);
    }

    public boolean isSelected() {
        return this.mDelegate.isSelected();
    }

    public CharSequence getDisplay() {
        return this.mDelegate.getDisplay();
    }

    public CharSequence getValue() {
        return this.mDelegate.getValue();
    }

    public long getContactId() {
        return this.mDelegate.getContactId();
    }

    public Long getDirectoryId() {
        return this.mDelegate.getDirectoryId();
    }

    public String getLookupKey() {
        return this.mDelegate.getLookupKey();
    }

    public long getDataId() {
        return this.mDelegate.getDataId();
    }

    public RecipientEntry getEntry() {
        return this.mDelegate.getEntry();
    }

    public void setOriginalText(String text) {
        this.mDelegate.setOriginalText(text);
    }

    public CharSequence getOriginalText() {
        return this.mDelegate.getOriginalText();
    }

    public Rect getBounds() {
        return getDrawable().getBounds();
    }

    public void draw(Canvas canvas) {
        getDrawable().draw(canvas);
    }

    public String toString() {
        return this.mDelegate.toString();
    }
}
