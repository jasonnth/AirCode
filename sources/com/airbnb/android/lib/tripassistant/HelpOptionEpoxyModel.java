package com.airbnb.android.lib.tripassistant;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.models.HelpThreadOption;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.models.MessageItemEpoxyModel_;
import com.airbnb.android.lib.tripassistant.HelpThreadAdapter.HelpThreadAdapterListener;
import com.airbnb.android.lib.tripassistant.MessagePhotosAdapter.PhotoType;
import com.airbnb.p027n2.primitives.messaging.MessageItem;
import java.util.List;
import p030in.uncod.android.bypass.Bypass;

public class HelpOptionEpoxyModel extends MessageItemEpoxyModel_ {
    private List<Attachment> attachments;
    private final Bypass bypass;
    private boolean hasSetMessage;
    private HelpThreadAdapterListener helpOptionClickListener;
    private final HelpThreadIssue issue;
    private final HelpThreadNode node;
    private final HelpThreadOption option;
    private MessagePhotosAdapter photoAdapter;
    private AirDateTime time;

    public HelpOptionEpoxyModel(Bypass bypass2, HelpThreadIssue issue2, HelpThreadNode node2, HelpThreadOption option2) {
        this.bypass = bypass2;
        this.issue = issue2;
        this.node = node2;
        this.option = option2;
        withTail(false);
        m5146id(generateModelId(issue2, node2, option2));
    }

    private long generateModelId(HelpThreadIssue issue2, HelpThreadNode node2, HelpThreadOption option2) {
        return node2.getId() + (option2.getId() * 486187739) + (issue2.getId() * 486187739) + (((long) option2.getClass().getName().hashCode()) * 486187739);
    }

    public void bind(MessageItem message) {
        if (!this.hasSetMessage) {
            messageText(this.bypass.markdownToSpannable(this.option.getLabel()));
            if (this.time != null) {
                statusText(this.time.getElapsedTime(message.getContext()));
            }
            this.hasSetMessage = true;
        }
        super.bind(message);
        if (this.helpOptionClickListener != null) {
            message.setOnClickListener(HelpOptionEpoxyModel$$Lambda$1.lambdaFactory$(this));
        } else {
            message.setClickable(false);
        }
        if (this.photoAdapter != null) {
            message.setPhotoAdapter(this.photoAdapter, false);
        }
    }

    public void unbind(MessageItem message) {
        super.unbind(message);
        message.clearPhotoAdapter();
    }

    public HelpOptionEpoxyModel withTail(boolean withTail) {
        if (withTail) {
            senderWithTail();
        } else {
            senderNoTail();
        }
        return this;
    }

    public HelpOptionEpoxyModel attachments(List<Attachment> attachments2) {
        Check.state(shouldHaveAttachments());
        this.attachments = attachments2;
        this.photoAdapter = new MessagePhotosAdapter(PhotoType.Uploaded, attachments2, null);
        return this;
    }

    public boolean hasSameAttachments(List<Attachment> attachments2) {
        return attachments2.equals(this.attachments);
    }

    public boolean shouldHaveAttachments() {
        return this.node.hasSelectedOption() && this.option.hasAttachments();
    }

    public HelpThreadIssue getIssue() {
        return this.issue;
    }

    public HelpOptionEpoxyModel optionClickListener(HelpThreadAdapterListener helpOptionClickListener2) {
        this.helpOptionClickListener = helpOptionClickListener2;
        return this;
    }

    public HelpOptionEpoxyModel time(AirDateTime time2) {
        this.time = time2;
        return this;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof HelpOptionEpoxyModel) || !super.equals(o)) {
            return false;
        }
        HelpOptionEpoxyModel that = (HelpOptionEpoxyModel) o;
        if (this.attachments != null) {
            if (!this.attachments.equals(that.attachments)) {
                return false;
            }
        } else if (that.attachments != null) {
            return false;
        }
        if (this.time != null) {
            if (this.time.equals(that.time)) {
                return false;
            }
        } else if (that.time != null) {
            return false;
        }
        if (this.helpOptionClickListener != null) {
            z = this.helpOptionClickListener.equals(that.helpOptionClickListener);
        } else if (that.helpOptionClickListener != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3 = 0;
        int hashCode = super.hashCode() * 31;
        if (this.attachments != null) {
            i = this.attachments.hashCode();
        } else {
            i = 0;
        }
        int i4 = (hashCode + i) * 31;
        if (this.helpOptionClickListener != null) {
            i2 = this.helpOptionClickListener.hashCode();
        } else {
            i2 = 0;
        }
        int i5 = (i4 + i2) * 31;
        if (this.time != null) {
            i3 = this.time.hashCode();
        }
        return i5 + i3;
    }
}
