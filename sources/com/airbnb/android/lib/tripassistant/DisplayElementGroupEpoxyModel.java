package com.airbnb.android.lib.tripassistant;

import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.models.HelpThreadDisplayElement;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.viewcomponents.models.MessageItemEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.tripassistant.HelpThreadAdapter.HelpThreadAdapterListener;
import com.airbnb.android.lib.tripassistant.MessagePhotosAdapter.PhotoType;
import com.airbnb.p027n2.primitives.messaging.MessageItem;
import com.airbnb.p027n2.primitives.messaging.MessageItem.MessageItemURLClickHandler;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.Collections;
import java.util.List;
import p030in.uncod.android.bypass.Bypass;

class DisplayElementGroupEpoxyModel extends MessageItemEpoxyModel_ {
    private final Bypass bypass;
    private final List<HelpThreadDisplayElement> displayElements;
    private boolean hasSetMessage;
    /* access modifiers changed from: private */
    public HelpThreadAdapterListener linkClickListener;
    private MessageItemURLClickHandler messageItemURLClickHandler;
    private MessagePhotosAdapter photoAdapter;
    private AirDateTime time;

    public DisplayElementGroupEpoxyModel(HelpThreadIssue issue, HelpThreadNode node, List<HelpThreadDisplayElement> displayElements2, Bypass bypass2) {
        this.displayElements = displayElements2;
        this.bypass = bypass2;
        withTail(false);
        m5146id(generateModelId(issue, node, displayElements2));
    }

    static long generateModelId(HelpThreadIssue issue, HelpThreadNode node, List<HelpThreadDisplayElement> elements) {
        long id = node.getId() + (issue.getId() * 486187739);
        for (HelpThreadDisplayElement element : elements) {
            id += (element.getId() * 486187739) + (((long) element.getClass().getName().hashCode()) * 486187739);
        }
        return id;
    }

    public void bind(MessageItem message) {
        if (!this.hasSetMessage) {
            setMessage();
            if (this.time != null) {
                statusText(this.time.getElapsedTime(message.getContext()));
            }
        }
        super.bind(message);
        if (this.photoAdapter != null) {
            message.setPhotoAdapter(this.photoAdapter, true);
        }
        message.setMovementMethod(LinkMovementMethod.getInstance());
        message.setURLClickHandler(this.messageItemURLClickHandler);
    }

    private void setMessage() {
        this.hasSetMessage = true;
        CharSequence msg = "";
        for (final HelpThreadDisplayElement displayElement : this.displayElements) {
            CharSequence[] charSequenceArr = new CharSequence[2];
            charSequenceArr[0] = msg;
            charSequenceArr[1] = msg.length() > 0 ? "\n" : "";
            msg = TextUtils.concat(charSequenceArr);
            switch (displayElement.getType()) {
                case Message:
                    msg = TextUtils.concat(new CharSequence[]{msg, this.bypass.markdownToSpannable(displayElement.getMessage())});
                    break;
                case LinkAndDialog:
                    msg = TextUtils.concat(new CharSequence[]{msg, TextUtil.applySpan(new ClickableSpan() {
                        public void onClick(View view) {
                            DisplayElementGroupEpoxyModel.this.linkClickListener.onDialogLinkElementClicked(displayElement);
                        }
                    }, displayElement.getLinkLabel())});
                    break;
                case Map:
                    mapUrl(displayElement.getMapImageUrl(), displayElement.getMapUrl());
                    break;
                default:
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unsupported help thread display element type: " + displayElement.getType()));
                    break;
            }
        }
        messageText(msg);
    }

    public DisplayElementGroupEpoxyModel withTail(boolean withTail) {
        if (withTail) {
            receiverWithTail();
            profileImageRes(C0880R.C0881drawable.trip_assistant_icon);
        } else {
            receiverNoTail();
        }
        return this;
    }

    public DisplayElementGroupEpoxyModel linkClickListener(HelpThreadAdapterListener listener) {
        this.linkClickListener = listener;
        return this;
    }

    public DisplayElementGroupEpoxyModel messageItemURLClickHandler(MessageItemURLClickHandler messageItemURLClickHandler2) {
        this.messageItemURLClickHandler = messageItemURLClickHandler2;
        return this;
    }

    public DisplayElementGroupEpoxyModel time(AirDateTime time2) {
        this.time = time2;
        return this;
    }

    public DisplayElementGroupEpoxyModel mapUrl(String mapImageUrl, String mapUrl) {
        Attachment mapAttachment = new Attachment();
        mapAttachment.setUrl(mapImageUrl);
        this.photoAdapter = new MessagePhotosAdapter(PhotoType.Map, Collections.singletonList(mapAttachment), Collections.singletonList(DisplayElementGroupEpoxyModel$$Lambda$1.lambdaFactory$(mapUrl)));
        return this;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        DisplayElementGroupEpoxyModel that = (DisplayElementGroupEpoxyModel) o;
        if (this.time != null) {
            if (this.time.equals(that.time)) {
                return false;
            }
        } else if (that.time != null) {
            return false;
        }
        if (this.linkClickListener != null) {
            z = this.linkClickListener.equals(that.linkClickListener);
        } else if (that.linkClickListener != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = super.hashCode() * 31;
        if (this.linkClickListener != null) {
            i = this.linkClickListener.hashCode();
        } else {
            i = 0;
        }
        int i3 = (hashCode + i) * 31;
        if (this.time != null) {
            i2 = this.time.hashCode();
        }
        return i3 + i2;
    }
}
