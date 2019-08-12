package com.android.p305ex.chips;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.text.util.Rfc822Tokenizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* renamed from: com.android.ex.chips.DropdownChipLayouter */
public class DropdownChipLayouter {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private Query mQuery;

    /* renamed from: com.android.ex.chips.DropdownChipLayouter$AdapterType */
    public enum AdapterType {
        BASE_RECIPIENT,
        RECIPIENT_ALTERNATES,
        SINGLE_RECIPIENT
    }

    /* renamed from: com.android.ex.chips.DropdownChipLayouter$ViewHolder */
    protected class ViewHolder {
        public final TextView destinationTypeView;
        public final TextView destinationView;
        public final TextView displayNameView;
        public final ImageView imageView;

        public ViewHolder(View view) {
            this.displayNameView = (TextView) view.findViewById(DropdownChipLayouter.this.getDisplayNameResId());
            this.destinationView = (TextView) view.findViewById(DropdownChipLayouter.this.getDestinationResId());
            this.destinationTypeView = (TextView) view.findViewById(DropdownChipLayouter.this.getDestinationTypeResId());
            this.imageView = (ImageView) view.findViewById(DropdownChipLayouter.this.getPhotoResId());
        }
    }

    public DropdownChipLayouter(LayoutInflater inflater, Context context) {
        this.mInflater = inflater;
        this.mContext = context;
    }

    public void setQuery(Query query) {
        this.mQuery = query;
    }

    public View bindView(View convertView, ViewGroup parent, RecipientEntry entry, int position, AdapterType type, String constraint) {
        String displayName = entry.getDisplayName();
        String destination = entry.getDestination();
        boolean showImage = true;
        CharSequence destinationType = getDestinationType(entry);
        View itemView = reuseOrInflateView(convertView, parent, type);
        ViewHolder viewHolder = new ViewHolder(itemView);
        switch (type) {
            case BASE_RECIPIENT:
                if (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, destination)) {
                    displayName = destination;
                    if (entry.isFirstLevel()) {
                        destination = null;
                    }
                }
                if (!entry.isFirstLevel()) {
                    displayName = null;
                    showImage = false;
                    break;
                }
                break;
            case RECIPIENT_ALTERNATES:
                if (position != 0) {
                    displayName = null;
                    showImage = false;
                    break;
                }
                break;
            case SINGLE_RECIPIENT:
                destination = Rfc822Tokenizer.tokenize(entry.getDestination())[0].getAddress();
                destinationType = null;
                break;
        }
        if (displayName != null || showImage) {
            viewHolder.destinationView.setPadding(0, 0, 0, 0);
        } else {
            viewHolder.destinationView.setPadding(this.mContext.getResources().getDimensionPixelSize(R.dimen.padding_no_picture), 0, 0, 0);
        }
        bindTextToView(displayName, viewHolder.displayNameView);
        bindTextToView(destination, viewHolder.destinationView);
        bindTextToView("(" + destinationType + ")", viewHolder.destinationTypeView);
        bindIconToView(showImage, entry, viewHolder.imageView, type);
        return itemView;
    }

    public View newView() {
        return this.mInflater.inflate(getItemLayoutResId(), null);
    }

    /* access modifiers changed from: protected */
    public View reuseOrInflateView(View convertView, ViewGroup parent, AdapterType type) {
        int itemLayout = getItemLayoutResId();
        switch (type) {
            case SINGLE_RECIPIENT:
                itemLayout = getAlternateItemLayoutResId();
                break;
        }
        if (convertView != null) {
            return convertView;
        }
        return this.mInflater.inflate(itemLayout, parent, false);
    }

    /* access modifiers changed from: protected */
    public void bindTextToView(CharSequence text, TextView view) {
        if (view != null) {
            if (text != null) {
                view.setText(text);
                view.setVisibility(0);
                return;
            }
            view.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void bindIconToView(boolean showImage, RecipientEntry entry, ImageView view, AdapterType type) {
        if (view != null) {
            if (showImage) {
                switch (type) {
                    case BASE_RECIPIENT:
                        byte[] photoBytes = entry.getPhotoBytes();
                        if (photoBytes != null && photoBytes.length > 0) {
                            view.setImageBitmap(ChipsUtil.getClip(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length)));
                            break;
                        } else {
                            BaseRecipientAdapter.tryFetchPhoto(entry, this.mContext.getContentResolver(), null, true, -1);
                            view.setImageResource(getDefaultPhotoResId());
                            break;
                        }
                        break;
                    case RECIPIENT_ALTERNATES:
                        Uri thumbnailUri = entry.getPhotoThumbnailUri();
                        if (thumbnailUri == null) {
                            view.setImageResource(getDefaultPhotoResId());
                            break;
                        } else {
                            view.setImageURI(thumbnailUri);
                            break;
                        }
                }
                view.setVisibility(0);
                return;
            }
            view.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public CharSequence getDestinationType(RecipientEntry entry) {
        return this.mQuery.getTypeLabel(this.mContext.getResources(), entry.getDestinationType(), entry.getDestinationLabel()).toString().toUpperCase();
    }

    /* access modifiers changed from: protected */
    public int getItemLayoutResId() {
        return R.layout.chips_recipient_dropdown_item;
    }

    /* access modifiers changed from: protected */
    public int getAlternateItemLayoutResId() {
        return R.layout.chips_alternate_item;
    }

    /* access modifiers changed from: protected */
    public int getDefaultPhotoResId() {
        return R.drawable.ic_contact_picture;
    }

    /* access modifiers changed from: protected */
    public int getDisplayNameResId() {
        return 16908310;
    }

    /* access modifiers changed from: protected */
    public int getDestinationResId() {
        return 16908308;
    }

    /* access modifiers changed from: protected */
    public int getDestinationTypeResId() {
        return 16908309;
    }

    /* access modifiers changed from: protected */
    public int getPhotoResId() {
        return 16908294;
    }
}
