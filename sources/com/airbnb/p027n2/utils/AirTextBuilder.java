package com.airbnb.p027n2.utils;

import android.content.Context;
import android.support.p000v4.content.res.ResourcesCompat;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.utils.AirTextBuilder */
public class AirTextBuilder {
    private final Context context;
    private int linkIndex;
    private final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

    /* renamed from: com.airbnb.n2.utils.AirTextBuilder$OnLinkClickListener */
    public interface OnLinkClickListener {
        void onClick(View view, CharSequence charSequence);
    }

    public AirTextBuilder(Context context2) {
        this.context = context2;
    }

    public AirTextBuilder append(CharSequence text) {
        this.spannableStringBuilder.append(text);
        return this;
    }

    public AirTextBuilder appendBold(CharSequence text) {
        this.spannableStringBuilder.append(TextUtil.makeCircularBold(this.context, text));
        return this;
    }

    public AirTextBuilder appendItalic(CharSequence text) {
        int start = this.spannableStringBuilder.length();
        int end = start + text.length();
        this.spannableStringBuilder.append(text);
        this.spannableStringBuilder.setSpan(new StyleSpan(2), start, end, 33);
        return this;
    }

    public AirTextBuilder appendLink(CharSequence text, OnLinkClickListener listener) {
        int start = this.spannableStringBuilder.length();
        int end = start + text.length();
        this.spannableStringBuilder.append(text);
        final OnLinkClickListener onLinkClickListener = listener;
        final CharSequence charSequence = text;
        this.spannableStringBuilder.setSpan(new IndexedClickableSpan(this.context, this.linkIndex, Integer.valueOf(ResourcesCompat.getColor(this.context.getResources(), R.color.n2_babu, null)), false, R.color.n2_babu_pressed) {
            public void onClick(View view) {
                onLinkClickListener.onClick(view, charSequence);
            }
        }, start, end, 33);
        this.linkIndex++;
        return this;
    }

    public CharSequence build() {
        return this.spannableStringBuilder;
    }
}
