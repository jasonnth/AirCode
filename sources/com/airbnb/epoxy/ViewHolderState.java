package com.airbnb.epoxy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.p001os.ParcelableCompat;
import android.support.p000v4.p001os.ParcelableCompatCreatorCallbacks;
import android.support.p000v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import com.airbnb.viewmodeladapter.R;
import java.util.Collection;

class ViewHolderState extends LongSparseArray<ViewState> implements Parcelable {
    public static final Creator<ViewHolderState> CREATOR = new Creator<ViewHolderState>() {
        public ViewHolderState[] newArray(int size) {
            return new ViewHolderState[size];
        }

        public ViewHolderState createFromParcel(Parcel source) {
            int size = source.readInt();
            ViewHolderState state = new ViewHolderState(size);
            for (int i = 0; i < size; i++) {
                state.put(source.readLong(), (ViewState) source.readParcelable(ViewState.class.getClassLoader()));
            }
            return state;
        }
    };

    public static class ViewState extends SparseArray<Parcelable> implements Parcelable {
        public static final Creator<ViewState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<ViewState>() {
            public ViewState createFromParcel(Parcel source, ClassLoader loader) {
                int size = source.readInt();
                int[] keys = new int[size];
                source.readIntArray(keys);
                return new ViewState(size, keys, source.readParcelableArray(loader));
            }

            public ViewState[] newArray(int size) {
                return new ViewState[size];
            }
        });

        public ViewState() {
        }

        private ViewState(int size, int[] keys, Parcelable[] values) {
            super(size);
            for (int i = 0; i < size; i++) {
                put(keys[i], values[i]);
            }
        }

        public void save(View view) {
            int originalId = view.getId();
            setIdIfNoneExists(view);
            view.saveHierarchyState(this);
            view.setId(originalId);
        }

        public void restore(View view) {
            int originalId = view.getId();
            setIdIfNoneExists(view);
            view.restoreHierarchyState(this);
            view.setId(originalId);
        }

        private void setIdIfNoneExists(View view) {
            if (view.getId() == -1) {
                view.setId(R.id.view_model_state_saving_id);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            int size = size();
            int[] keys = new int[size];
            Parcelable[] values = new Parcelable[size];
            for (int i = 0; i < size; i++) {
                keys[i] = keyAt(i);
                values[i] = (Parcelable) valueAt(i);
            }
            parcel.writeInt(size);
            parcel.writeIntArray(keys);
            parcel.writeParcelableArray(values, flags);
        }
    }

    public ViewHolderState() {
    }

    private ViewHolderState(int size) {
        super(size);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int size = size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            dest.writeLong(keyAt(i));
            dest.writeParcelable((Parcelable) valueAt(i), 0);
        }
    }

    public boolean hasStateForHolder(EpoxyViewHolder holder) {
        return get(holder.getItemId()) != null;
    }

    public void save(Collection<EpoxyViewHolder> holders) {
        for (EpoxyViewHolder holder : holders) {
            save(holder);
        }
    }

    public void save(EpoxyViewHolder holder) {
        if (holder.getModel().shouldSaveViewState()) {
            ViewState state = (ViewState) get(holder.getItemId());
            if (state == null) {
                state = new ViewState();
            }
            state.save(holder.itemView);
            put(holder.getItemId(), state);
        }
    }

    public void restore(EpoxyViewHolder holder) {
        if (holder.getModel().shouldSaveViewState()) {
            ViewState state = (ViewState) get(holder.getItemId());
            if (state != null) {
                state.restore(holder.itemView);
            }
        }
    }
}
