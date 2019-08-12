package com.android.p305ex.chips.recipientchip;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.text.style.ReplacementSpan;
import com.android.p305ex.chips.RecipientEntry;

/* renamed from: com.android.ex.chips.recipientchip.InvisibleRecipientChip */
public class InvisibleRecipientChip extends ReplacementSpan implements DrawableRecipientChip {
    private final SimpleRecipientChip mDelegate;

    public InvisibleRecipientChip(RecipientEntry entry) {
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

    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
    }

    public int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        return 0;
    }

    public Rect getBounds() {
        return new Rect(0, 0, 0, 0);
    }

    public void draw(Canvas canvas) {
    }
}
