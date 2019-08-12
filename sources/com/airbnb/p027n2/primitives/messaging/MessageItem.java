package com.airbnb.p027n2.primitives.messaging;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.primitives.messaging.MessageItem */
public abstract class MessageItem extends MessagePost {
    private View failedIcon;
    private LoadingView loadingView;
    private AirTextView message;
    private LinearLayout messageBubble;
    private MessageItemURLClickHandler messageItemURLClickHandler = null;
    private RecyclerView photoRecyclerView;
    private int regularBackgroundResId;
    private AirTextView translateText;

    /* renamed from: com.airbnb.n2.primitives.messaging.MessageItem$MessageItemURLClickHandler */
    public interface MessageItemURLClickHandler {
        void onURLClick(Context context, String str);
    }

    /* renamed from: com.airbnb.n2.primitives.messaging.MessageItem$MessageItemURLSpan */
    public static class MessageItemURLSpan extends URLSpan {
        private final MessageItemURLClickHandler messageItemURLClickHandler;

        public MessageItemURLSpan(String url, MessageItemURLClickHandler messageItemURLClickHandler2) {
            super(url);
            this.messageItemURLClickHandler = messageItemURLClickHandler2;
        }

        public void onClick(View widget) {
            this.messageItemURLClickHandler.onURLClick(widget.getContext(), getURL());
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract int getBackgroundDrawableId(boolean z);

    /* access modifiers changed from: 0000 */
    public abstract int getLayoutId();

    public MessageItem(Context context) {
        super(context);
    }

    public MessageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs) {
        inflate(getContext(), getLayoutId(), this);
        bindViews();
        setupAttributes(attrs);
        setMessageState(false);
    }

    private void bindViews() {
        this.message = (AirTextView) ViewLibUtils.findById(this, R.id.text_message);
        this.translateText = (AirTextView) ViewLibUtils.findById(this, R.id.text_translate);
        this.status = (AirTextView) ViewLibUtils.findById(this, R.id.text_status);
        this.profileImage = (HaloImageView) ViewLibUtils.findById(this, R.id.image_thumbnail);
        this.messageBubble = (LinearLayout) ViewLibUtils.findById(this, R.id.message_bubble);
        this.failedIcon = ViewLibUtils.findById(this, R.id.error_icon);
        this.loadingView = (LoadingView) ViewLibUtils.findById(this, R.id.loading_view);
        this.photoRecyclerView = (RecyclerView) ViewLibUtils.findById(this, R.id.photo_recycler_view);
        this.reportTextView = (AirTextView) ViewLibUtils.findById(this, R.id.report_text);
        this.textProfilePhotoPlaceholder = (AirTextView) ViewLibUtils.findById(this, R.id.thumbnail_text_placeholder);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_MessageItem);
        this.message.setTextColor(a.getColor(R.styleable.n2_MessageItem_n2_messageTextColor, ContextCompat.getColor(getContext(), R.color.n2_text_color_main)));
        this.status.setTextColor(a.getColor(R.styleable.n2_MessageItem_n2_statusTextColor, ContextCompat.getColor(getContext(), R.color.n2_text_color_main)));
        setMessageText(a.getString(R.styleable.n2_MessageItem_n2_messageText));
        setStatusText(a.getString(R.styleable.n2_MessageItem_n2_statusText));
        this.regularBackgroundResId = getBackgroundDrawableId(a.getBoolean(R.styleable.n2_MessageItem_n2_withTail, true));
        if (a.hasValue(R.styleable.n2_MessageItem_n2_messageTextSize)) {
            this.message.setTextSize(0, (float) a.getDimensionPixelSize(R.styleable.n2_MessageItem_n2_messageTextSize, 0));
        }
        if (a.hasValue(R.styleable.n2_MessageItem_n2_messageMaxLines)) {
            this.message.setMaxLines(a.getInt(R.styleable.n2_MessageItem_n2_messageMaxLines, 0));
        }
        a.recycle();
    }

    private void updateUrlSpansForCustomClick(CharSequence s) {
        URLSpan[] spans;
        if (this.messageItemURLClickHandler != null) {
            SpannableString current = (SpannableString) s;
            for (URLSpan span : (URLSpan[]) current.getSpans(0, current.length(), URLSpan.class)) {
                int start = current.getSpanStart(span);
                int end = current.getSpanEnd(span);
                current.removeSpan(span);
                current.setSpan(new MessageItemURLSpan(span.getURL(), this.messageItemURLClickHandler), start, end, 0);
            }
        }
    }

    public void setMessageState(boolean sendFailed) {
        ViewLibUtils.setVisibleIf(this.failedIcon, sendFailed);
        this.messageBubble.setBackground(AppCompatResources.getDrawable(getContext(), sendFailed ? R.drawable.n2_message_item_orange_with_tail : this.regularBackgroundResId));
    }

    public void setLinksOnMessage() {
        Linkify.addLinks(this.message, 15);
    }

    public void setMessageText(CharSequence s) {
        if (!TextUtils.isEmpty(s)) {
            this.message.setText(s);
            updateUrlSpansForCustomClick(this.message.getText());
            this.message.setVisibility(0);
        } else {
            this.message.setVisibility(8);
        }
        this.photoRecyclerView.setVisibility(8);
    }

    public void setTranslateText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.translateText, text);
    }

    public void setTranslateClickListener(OnClickListener listener) {
        this.translateText.setOnClickListener(listener);
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public void setReportLinkText(CharSequence text) {
        this.reportTextView.setText(text);
    }

    public void showReportLink(boolean show) {
        ViewLibUtils.setVisibleIf(this.reportTextView, show);
    }

    public CharSequence getMessageText() {
        return this.message.getText();
    }

    public void showLoading(boolean show) {
        ViewLibUtils.setVisibleIf(this.loadingView, show);
        ViewLibUtils.setVisibleIf(this.message, !show);
    }

    public void setMovementMethod(MovementMethod method) {
        this.message.setMovementMethod(method);
    }

    public void setURLClickHandler(MessageItemURLClickHandler messageItemURLClickHandler2) {
        this.messageItemURLClickHandler = messageItemURLClickHandler2;
        updateUrlSpansForCustomClick(this.message.getText());
    }

    public void setPhotoAdapter(Adapter<? extends ViewHolder> photoAdapter, boolean singleColumn) {
        this.message.setVisibility(8);
        this.loadingView.setVisibility(8);
        this.photoRecyclerView.setVisibility(0);
        if (this.photoRecyclerView.getLayoutManager() == null) {
            if (singleColumn) {
                this.photoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                this.photoRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            }
        }
        this.photoRecyclerView.swapAdapter(photoAdapter, false);
    }

    public void clearPhotoAdapter() {
        this.photoRecyclerView.swapAdapter(null, true);
    }
}
