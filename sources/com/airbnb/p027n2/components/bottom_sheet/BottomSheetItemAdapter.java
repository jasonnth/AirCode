package com.airbnb.p027n2.components.bottom_sheet;

import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.n2.R;
import java.util.List;

/* renamed from: com.airbnb.n2.components.bottom_sheet.BottomSheetItemAdapter */
public class BottomSheetItemAdapter extends Adapter<ViewHolder> {
    private final List<BottomSheetItem> items;
    /* access modifiers changed from: private */
    public BottomSheetItemClickListener listener;

    /* renamed from: com.airbnb.n2.components.bottom_sheet.BottomSheetItemAdapter$HeaderViewHolder */
    static class HeaderViewHolder extends ViewHolder {
        private final TextView textView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void setData(BottomSheetHeader item) {
            super.setData(item);
            this.textView.setText(item.getTitle());
            int color = item.getTextColor();
            if (color != 0) {
                this.textView.setTextColor(ContextCompat.getColor(this.itemView.getContext(), color));
            }
        }
    }

    /* renamed from: com.airbnb.n2.components.bottom_sheet.BottomSheetItemAdapter$ItemViewHolder */
    class ItemViewHolder extends ViewHolder implements OnClickListener {
        private final ImageView imageView;
        private final TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void setData(BottomSheetMenuItem item) {
            super.setData(item);
            this.imageView.setImageDrawable(item.getDrawable());
            this.textView.setText(item.getTitle());
            int color = item.getTextColor();
            int background = item.getBackground();
            if (color != 0) {
                this.textView.setTextColor(ContextCompat.getColor(this.itemView.getContext(), color));
            }
            if (background != 0) {
                this.itemView.setBackgroundResource(background);
            }
        }

        public void onClick(View v) {
            if (BottomSheetItemAdapter.this.listener != null) {
                BottomSheetItemAdapter.this.listener.onBottomSheetItemClick((BottomSheetMenuItem) this.item);
            }
        }
    }

    /* renamed from: com.airbnb.n2.components.bottom_sheet.BottomSheetItemAdapter$ViewHolder */
    static class ViewHolder extends android.support.p002v7.widget.RecyclerView.ViewHolder {
        protected BottomSheetItem item;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(BottomSheetItem item2) {
            this.item = item2;
        }
    }

    public BottomSheetItemAdapter(List<BottomSheetItem> items2, BottomSheetItemClickListener listener2) {
        this.items = items2;
        this.listener = listener2;
    }

    public int getItemViewType(int position) {
        BottomSheetItem item = (BottomSheetItem) this.items.get(position);
        if (item instanceof BottomSheetMenuItem) {
            return 0;
        }
        if (item instanceof BottomSheetHeader) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_bottomsheetbuilder_list_header, parent, false));
        }
        if (viewType == 0) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_bottomsheetbuilder_list_item, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_bottomsheetbuilder_list_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        BottomSheetItem item = (BottomSheetItem) this.items.get(position);
        if (holder.getItemViewType() == 0) {
            ((ItemViewHolder) holder).setData((BottomSheetMenuItem) item);
        } else if (holder.getItemViewType() == 1) {
            ((HeaderViewHolder) holder).setData((BottomSheetHeader) item);
        }
    }

    public int getItemCount() {
        return this.items.size();
    }
}
