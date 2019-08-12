package com.airbnb.p027n2.primitives.messaging;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.messaging.MessageItemReceiver */
public class MessageItemReceiver extends MessageItem {
    public MessageItemReceiver(Context context) {
        super(context);
    }

    public MessageItemReceiver(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageItemReceiver(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: 0000 */
    public int getLayoutId() {
        return R.layout.n2_message_item_receiver;
    }

    /* access modifiers changed from: 0000 */
    public int getBackgroundDrawableId(boolean withTail) {
        return withTail ? R.drawable.n2_message_item_gray_with_tail_selector : R.drawable.n2_message_item_gray_no_tail_selector;
    }
}
