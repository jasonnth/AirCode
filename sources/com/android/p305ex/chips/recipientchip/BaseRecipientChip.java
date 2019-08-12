package com.android.p305ex.chips.recipientchip;

import com.android.p305ex.chips.RecipientEntry;

/* renamed from: com.android.ex.chips.recipientchip.BaseRecipientChip */
interface BaseRecipientChip {
    long getContactId();

    long getDataId();

    Long getDirectoryId();

    CharSequence getDisplay();

    RecipientEntry getEntry();

    String getLookupKey();

    CharSequence getOriginalText();

    CharSequence getValue();

    boolean isSelected();

    void setOriginalText(String str);

    void setSelected(boolean z);
}
