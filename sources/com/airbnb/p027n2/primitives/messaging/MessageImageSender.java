package com.airbnb.p027n2.primitives.messaging;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.messaging.MessageImageSender */
public class MessageImageSender extends MessageImage {
    public MessageImageSender(Context context) {
        super(context);
    }

    public MessageImageSender(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageImageSender(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: 0000 */
    public int getLayoutId() {
        return R.layout.n2_message_image_sender;
    }
}
