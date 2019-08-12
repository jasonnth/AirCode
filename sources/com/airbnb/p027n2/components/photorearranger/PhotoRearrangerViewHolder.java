package com.airbnb.p027n2.components.photorearranger;

import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerViewHolder */
public class PhotoRearrangerViewHolder extends ViewHolder {
    private final RearrangablePhotoRow row = ((RearrangablePhotoRow) this.itemView);

    public PhotoRearrangerViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_view_holder_rearrangable_photo_cell, parent, false));
    }

    public void bind(PhotoRearrangerItem model, OnClickListener clickListener) {
        this.row.setImage(model.image);
        this.row.setLabelRes(model.firstItemLabelRes);
        this.row.setState(model.state);
        this.row.setOnClickListener(clickListener);
        updatePositionViews();
        this.row.endAnimations();
    }

    public void unbind() {
        this.row.setOnClickListener(null);
    }

    public void updatePositionViews() {
        this.row.setLabelVisible(getAdapterPosition() == 0);
    }

    public void onItemSelected() {
        this.row.onDrag();
    }

    public void onItemUnselected() {
        this.row.onDrop();
    }
}
