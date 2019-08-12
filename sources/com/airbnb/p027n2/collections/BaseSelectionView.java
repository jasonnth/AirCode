package com.airbnb.p027n2.collections;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.p001os.ParcelableCompat;
import android.support.p000v4.p001os.ParcelableCompatCreatorCallbacks;
import android.support.p000v4.view.AbsSavedState;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.LayoutManager;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;
import com.airbnb.p027n2.components.KickerMarquee;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.airbnb.n2.collections.BaseSelectionView */
public abstract class BaseSelectionView<T extends SelectionViewItemPresenter> extends RecyclerView {
    private static final int BABU_STYLE_INDEX = 0;
    private static final int MULTI_SELECTION = 0;
    private static final int RADIO_BUTTON_SELECTION = 2;
    private static final int SINGLE_SELECTION = 1;
    /* access modifiers changed from: private */
    public final Adapter adapter = new Adapter<>();
    private boolean hasStableIds = true;
    /* access modifiers changed from: private */
    public ItemEnabledCallback itemEnabledCallback;
    /* access modifiers changed from: private */
    public final List<T> items = new ArrayList();
    protected String kicker;
    /* access modifiers changed from: private */
    public final HashSet<Integer> selectedItemPositions = new HashSet<>();
    /* access modifiers changed from: private */
    public int selectionMode;
    /* access modifiers changed from: private */
    public SelectionSheetOnItemClickedListener<T> selectionSheetOnItemClickedListener;
    /* access modifiers changed from: private */
    public Style style;
    protected String subtitle;
    protected String title;
    /* access modifiers changed from: private */
    public boolean withMarquee = true;

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$Adapter */
    private final class Adapter extends android.support.p002v7.widget.RecyclerView.Adapter<ViewHolder> {
        private Adapter() {
        }

        public int getHeaderCount() {
            if (BaseSelectionView.this.withMarquee) {
                return 1;
            }
            return 0;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case -2:
                    return new FooterViewHolder(parent);
                case -1:
                    return new KickerMarqueeViewHolder(parent);
                case 2:
                    return new ListItemViewHolder(parent);
                default:
                    throw new UnsupportedOperationException("Unknown view type: " + viewType);
            }
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position == 0 && BaseSelectionView.this.withMarquee) {
                ((KickerMarqueeViewHolder) holder).bind();
            } else if (position != getItemCount() - 1) {
                ((ListItemViewHolder) holder).bind(position - getHeaderCount());
            }
        }

        public int getItemViewType(int position) {
            if (position == 0 && BaseSelectionView.this.withMarquee) {
                return -1;
            }
            if (position == getItemCount() - 1) {
                return -2;
            }
            return 2;
        }

        public int getItemCount() {
            return BaseSelectionView.this.items.size() + getHeaderCount() + 1;
        }

        public long getItemId(int position) {
            if (position == 0) {
                return -1;
            }
            if (position == getItemCount() - 1) {
                return -2;
            }
            return (long) ((SelectionViewItemPresenter) BaseSelectionView.this.items.get(position - getHeaderCount())).hashCode();
        }
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$FooterViewHolder */
    private final class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_selection_sheet_footer, parent, false));
        }
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$ItemEnabledCallback */
    public interface ItemEnabledCallback<T> {
        boolean isItemEnabled(T t);
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$KickerMarqueeViewHolder */
    final class KickerMarqueeViewHolder extends ViewHolder {
        @BindView
        KickerMarquee kickerMarquee;

        public KickerMarqueeViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_selection_sheet_header, parent, false));
            ButterKnife.bind(this, this.itemView);
        }

        public void bind() {
            this.kickerMarquee.setTitle(BaseSelectionView.this.title);
            this.kickerMarquee.setSubtitle(BaseSelectionView.this.subtitle);
            this.kickerMarquee.setKicker((CharSequence) BaseSelectionView.this.kicker);
            BaseSelectionView.this.style.kickerMarqueeStyle.setStyle(this.kickerMarquee);
        }
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$KickerMarqueeViewHolder_ViewBinding */
    public final class KickerMarqueeViewHolder_ViewBinding implements Unbinder {
        private KickerMarqueeViewHolder target;

        public KickerMarqueeViewHolder_ViewBinding(KickerMarqueeViewHolder target2, View source) {
            this.target = target2;
            target2.kickerMarquee = (KickerMarquee) Utils.findRequiredViewAsType(source, R.id.kicker_marquee, "field 'kickerMarquee'", KickerMarquee.class);
        }

        public void unbind() {
            KickerMarqueeViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.kickerMarquee = null;
        }
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$ListItemViewHolder */
    final class ListItemViewHolder extends ViewHolder {
        @BindView
        TextView text;

        public ListItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_list_item_selection_sheet, parent, false));
            ButterKnife.bind(this, this.itemView);
            this.text.setTextAppearance(BaseSelectionView.this.getContext(), BaseSelectionView.this.style.titleStyleRes);
        }

        public void bind(int position) {
            T item = (SelectionViewItemPresenter) BaseSelectionView.this.items.get(position);
            this.text.setText(item.getDisplayText(BaseSelectionView.this.getContext()));
            this.itemView.setSelected(BaseSelectionView.this.selectedItemPositions.contains(Integer.valueOf(position)));
            if (BaseSelectionView.this.itemEnabledCallback != null) {
                this.itemView.setEnabled(BaseSelectionView.this.itemEnabledCallback.isItemEnabled(item));
            }
            this.itemView.setOnClickListener(BaseSelectionView$ListItemViewHolder$$Lambda$1.lambdaFactory$(this, position));
        }

        static /* synthetic */ void lambda$bind$0(ListItemViewHolder listItemViewHolder, int position, View v) {
            if (!(BaseSelectionView.this.selectionMode == 2 && !BaseSelectionView.this.selectedItemPositions.isEmpty() && position == ((Integer) BaseSelectionView.this.selectedItemPositions.iterator().next()).intValue())) {
                if (BaseSelectionView.this.selectionMode != 0 && !BaseSelectionView.this.selectedItemPositions.isEmpty()) {
                    int oldIndex = ((Integer) BaseSelectionView.this.selectedItemPositions.iterator().next()).intValue();
                    BaseSelectionView.this.selectedItemPositions.clear();
                    BaseSelectionView.this.adapter.notifyItemChanged(BaseSelectionView.this.adapter.getHeaderCount() + oldIndex);
                }
                listItemViewHolder.itemView.setSelected(!listItemViewHolder.itemView.isSelected());
                if (listItemViewHolder.itemView.isSelected()) {
                    BaseSelectionView.this.selectedItemPositions.add(Integer.valueOf(position));
                } else {
                    BaseSelectionView.this.selectedItemPositions.remove(Integer.valueOf(position));
                }
                BaseSelectionView.this.adapter.notifyItemChanged(BaseSelectionView.this.adapter.getHeaderCount() + position);
            }
            if (BaseSelectionView.this.selectionSheetOnItemClickedListener != null) {
                BaseSelectionView.this.selectionSheetOnItemClickedListener.onItemClicked(BaseSelectionView.this.items.get(position));
            }
        }
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$ListItemViewHolder_ViewBinding */
    public final class ListItemViewHolder_ViewBinding implements Unbinder {
        private ListItemViewHolder target;

        public ListItemViewHolder_ViewBinding(ListItemViewHolder target2, View source) {
            this.target = target2;
            target2.text = (TextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
        }

        public void unbind() {
            ListItemViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.text = null;
        }
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$SavedState */
    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() {
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        });
        HashSet<Integer> selectedPositions;

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeSerializable(this.selectedPositions);
        }

        private SavedState(Parcel in, ClassLoader loader) {
            if (loader == null) {
                loader = LayoutManager.class.getClassLoader();
            }
            super(in, loader);
            this.selectedPositions = (HashSet) in.readSerializable();
        }
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$SelectionSheetOnItemClickedListener */
    public interface SelectionSheetOnItemClickedListener<T> {
        void onItemClicked(T t);
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$SelectionViewItemPresenter */
    public interface SelectionViewItemPresenter {
        String getDisplayText(Context context);

        int hashCode();
    }

    /* renamed from: com.airbnb.n2.collections.BaseSelectionView$Style */
    public enum Style {
        BABU(R.style.n2_SelectionSheetItem, com.airbnb.p027n2.components.KickerMarquee.Style.BABU),
        WHITE(R.style.n2_SelectionItem, com.airbnb.p027n2.components.KickerMarquee.Style.WHITE);
        
        final com.airbnb.p027n2.components.KickerMarquee.Style kickerMarqueeStyle;
        final int titleStyleRes;

        private Style(int titleStyleRes2, com.airbnb.p027n2.components.KickerMarquee.Style kickerMarqueeStyle2) {
            this.titleStyleRes = titleStyleRes2;
            this.kickerMarqueeStyle = kickerMarqueeStyle2;
        }
    }

    public BaseSelectionView(Context context) {
        super(context);
        init(null);
    }

    public BaseSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BaseSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setupAttributes(attrs);
        setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter.setHasStableIds(this.hasStableIds);
        setAdapter(this.adapter);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_SelectionView);
        String titleText = a.getString(R.styleable.n2_SelectionView_n2_titleText);
        String subtitleText = a.getString(R.styleable.n2_SelectionView_n2_subtitleText);
        String kickerText = a.getString(R.styleable.n2_SelectionView_n2_kickerText);
        this.selectionMode = a.getInt(R.styleable.n2_SelectionView_n2_selectionMode, 0);
        this.hasStableIds = a.getBoolean(R.styleable.n2_SelectionView_n2_hasStableIds, true);
        this.withMarquee = a.getBoolean(R.styleable.n2_SelectionView_n2_withMarquee, true);
        setTitle(titleText);
        setSubtitle(subtitleText);
        setKicker(kickerText);
        this.style = Style.values()[a.getInt(R.styleable.n2_SelectionView_n2_selectionViewStyle, 0)];
        a.recycle();
    }

    public void setStyle(Style style2) {
        this.style = style2;
    }

    public void setSelectionSheetOnItemClickedListener(SelectionSheetOnItemClickedListener<T> listener) {
        this.selectionSheetOnItemClickedListener = listener;
    }

    public void setItemEnabledCallback(ItemEnabledCallback itemEnabledCallback2) {
        this.itemEnabledCallback = itemEnabledCallback2;
    }

    public void setTitle(String title2) {
        this.title = title2;
        this.adapter.notifyItemChanged(0);
    }

    public void setTitle(int titleRes) {
        setTitle(getContext().getString(titleRes));
    }

    public void setSubtitle(String subtitle2) {
        this.subtitle = subtitle2;
        this.adapter.notifyItemChanged(0);
    }

    public void setKicker(int kickerRes) {
        setKicker(getContext().getString(kickerRes));
    }

    public void setKicker(String kicker2) {
        this.kicker = kicker2;
        this.adapter.notifyItemChanged(0);
    }

    public void setSubtitle(int subtitleRes) {
        setSubtitle(getContext().getString(subtitleRes));
    }

    public int getItemCount() {
        return this.items.size();
    }

    /* access modifiers changed from: protected */
    public void setItems(T[] items2) {
        setItems(Arrays.asList(items2));
    }

    /* access modifiers changed from: protected */
    public void setItems(List<T> items2) {
        this.items.clear();
        this.selectedItemPositions.clear();
        addItems(items2);
    }

    /* access modifiers changed from: protected */
    public void addItem(T item) {
        this.items.add(item);
        this.adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void addItems(List<T> items2) {
        this.items.addAll(items2);
        this.adapter.notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return this.selectedItemPositions.size();
    }

    public void clearSelectedItems() {
        setSelectedItem((T) null);
    }

    public boolean hasSelectedItems() {
        return getSelectedItemCount() > 0;
    }

    /* access modifiers changed from: protected */
    public List<T> getItems() {
        return ImmutableList.copyOf((Collection<? extends E>) this.items);
    }

    /* access modifiers changed from: protected */
    public void setSelectedItem(T selectedItem) {
        if (this.selectionMode == 0) {
            throw new IllegalStateException("You may not call setSelectedItem() while in multi selection mode! Use setSelectedItems()!");
        } else if (this.items.isEmpty()) {
            throw new IllegalStateException("Did you set items yet?");
        } else if (selectedItem == null) {
            this.selectedItemPositions.clear();
        } else {
            int selectedItemIndex = this.items.indexOf(selectedItem);
            if (selectedItemIndex == -1) {
                throw new IllegalStateException(selectedItem + " does not exist in current list of items");
            }
            int oldIndex = -1;
            if (!this.selectedItemPositions.isEmpty()) {
                oldIndex = ((Integer) this.selectedItemPositions.iterator().next()).intValue();
            }
            this.selectedItemPositions.clear();
            this.selectedItemPositions.add(Integer.valueOf(selectedItemIndex));
            this.adapter.notifyItemChanged(this.adapter.getHeaderCount() + selectedItemIndex);
            if (oldIndex != -1) {
                this.adapter.notifyItemChanged(this.adapter.getHeaderCount() + oldIndex);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setSelectedItems(List<T> selectedItems) {
        if (this.selectionMode != 0) {
            throw new IllegalStateException("You may not call setSelectedItems() while not in multi selection mode! Use setSelectedItem()!");
        } else if (this.items.isEmpty()) {
            throw new IllegalStateException("Did you set items yet?");
        } else {
            this.selectedItemPositions.clear();
            for (T item : selectedItems) {
                int selectedItemIndex = this.items.indexOf(item);
                if (selectedItemIndex == -1) {
                    throw new IllegalStateException(item + " does not exist in current list of items");
                }
                this.selectedItemPositions.add(Integer.valueOf(selectedItemIndex));
                this.adapter.notifyItemChanged(this.adapter.getHeaderCount() + selectedItemIndex);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setSelectedItem(int index) {
        this.selectedItemPositions.clear();
        this.selectedItemPositions.add(Integer.valueOf(index));
        this.adapter.notifyItemChanged(this.adapter.getHeaderCount() + index);
    }

    /* access modifiers changed from: protected */
    public T getSelectedItem() {
        if (this.selectionMode == 0) {
            throw new IllegalStateException("You may not call getSelectedItem() while in multi selection mode! Use getSelectedItems()!");
        } else if (this.selectedItemPositions.isEmpty()) {
            return null;
        } else {
            if (this.selectedItemPositions.size() <= 1) {
                return (SelectionViewItemPresenter) this.items.get(((Integer) this.selectedItemPositions.iterator().next()).intValue());
            }
            throw new IllegalStateException("Single selection mode yet multiple items selected!");
        }
    }

    /* access modifiers changed from: protected */
    public List<T> getSelectedItems() {
        if (this.selectionMode != 0) {
            throw new IllegalStateException("You may not call getSelectedItems() while not in multi selection mode! Use getSelectedItem()!");
        }
        List<T> selectedItems = new ArrayList<>(this.selectedItemPositions.size());
        Iterator it = this.selectedItemPositions.iterator();
        while (it.hasNext()) {
            selectedItems.add(this.items.get(((Integer) it.next()).intValue()));
        }
        return selectedItems;
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.selectedPositions = this.selectedItemPositions;
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        if (ss.selectedPositions != null) {
            this.selectedItemPositions.clear();
            this.selectedItemPositions.addAll(ss.selectedPositions);
        }
    }
}
