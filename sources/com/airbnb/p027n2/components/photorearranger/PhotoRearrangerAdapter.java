package com.airbnb.p027n2.components.photorearranger;

import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerItem;
import com.airbnb.p027n2.components.photorearranger.RearrangablePhotoRow.State;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerAdapter */
public class PhotoRearrangerAdapter<TModel extends PhotoRearrangerItem> extends Adapter<PhotoRearrangerViewHolder> {
    private Listener<TModel> listener;
    private final ArrayList<TModel> models = Lists.newArrayList();

    /* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerAdapter$Listener */
    public interface Listener<TModel extends PhotoRearrangerItem> {
        void itemSelected(TModel tmodel);
    }

    public PhotoRearrangerAdapter() {
        setHasStableIds(true);
    }

    public void setListener(Listener<TModel> listener2) {
        this.listener = listener2;
    }

    public void setModels(List<TModel> newModels) {
        this.models.clear();
        this.models.addAll(newModels);
        notifyDataSetChanged();
    }

    public ImmutableList<TModel> getModels() {
        return ImmutableList.copyOf((Collection<? extends E>) this.models);
    }

    public void onItemMove(int fromPosition, int toPosition) {
        TModel model = (PhotoRearrangerItem) this.models.remove(fromPosition);
        if (!canRearrangeItem(model)) {
            throw new IllegalStateException("Item was moved but did not support moving");
        }
        this.models.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public PhotoRearrangerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoRearrangerViewHolder(parent);
    }

    public void onBindViewHolder(PhotoRearrangerViewHolder holder, int position) {
        TModel model = (PhotoRearrangerItem) this.models.get(position);
        holder.bind(model, PhotoRearrangerAdapter$$Lambda$1.lambdaFactory$(this, model));
    }

    public void onViewRecycled(PhotoRearrangerViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unbind();
    }

    public int getItemCount() {
        return this.models.size();
    }

    public long getItemId(int position) {
        return ((PhotoRearrangerItem) this.models.get(position)).f2705id;
    }

    public boolean canRearrangeItemAt(int position) {
        return canRearrangeItem((PhotoRearrangerItem) this.models.get(position));
    }

    public boolean canRearrangeItem(TModel model) {
        return model.state == State.Normal;
    }

    /* access modifiers changed from: private */
    public void modelSelected(TModel model) {
        if (this.listener != null) {
            this.listener.itemSelected(model);
        }
    }
}
