package com.android.p305ex.chips.recipientchip;

import android.text.TextUtils;
import com.android.p305ex.chips.RecipientEntry;

/* renamed from: com.android.ex.chips.recipientchip.SimpleRecipientChip */
class SimpleRecipientChip implements BaseRecipientChip {
    private final long mContactId;
    private final long mDataId;
    private final Long mDirectoryId;
    private final CharSequence mDisplay;
    private final RecipientEntry mEntry;
    private final String mLookupKey;
    private CharSequence mOriginalText;
    private boolean mSelected = false;
    private final CharSequence mValue;

    public SimpleRecipientChip(RecipientEntry entry) {
        this.mDisplay = entry.getDisplayName();
        this.mValue = entry.getDestination().trim();
        this.mContactId = entry.getContactId();
        this.mDirectoryId = entry.getDirectoryId();
        this.mLookupKey = entry.getLookupKey();
        this.mDataId = entry.getDataId();
        this.mEntry = entry;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public CharSequence getDisplay() {
        return this.mDisplay;
    }

    public CharSequence getValue() {
        return this.mValue;
    }

    public long getContactId() {
        return this.mContactId;
    }

    public Long getDirectoryId() {
        return this.mDirectoryId;
    }

    public String getLookupKey() {
        return this.mLookupKey;
    }

    public long getDataId() {
        return this.mDataId;
    }

    public RecipientEntry getEntry() {
        return this.mEntry;
    }

    public void setOriginalText(String text) {
        if (TextUtils.isEmpty(text)) {
            this.mOriginalText = text;
        } else {
            this.mOriginalText = text.trim();
        }
    }

    public CharSequence getOriginalText() {
        return !TextUtils.isEmpty(this.mOriginalText) ? this.mOriginalText : this.mEntry.getDestination();
    }

    public String toString() {
        return this.mDisplay + " <" + this.mValue + ">";
    }
}
