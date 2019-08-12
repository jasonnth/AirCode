package com.airbnb.p027n2.primitives.messaging;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.messaging.MessageItemSender */
public class MessageItemSender extends MessageItem {
    public MessageItemSender(Context context) {
        super(context);
    }

    public MessageItemSender(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageItemSender(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: 0000 */
    public int getLayoutId() {
        return R.layout.n2_message_item_sender;
    }

    /* access modifiers changed from: 0000 */
    public int getBackgroundDrawableId(boolean withTail) {
        return withTail ? R.drawable.n2_message_item_babu_with_tail_selector : R.drawable.n2_message_item_babu_no_tail_selector;
    }
}
