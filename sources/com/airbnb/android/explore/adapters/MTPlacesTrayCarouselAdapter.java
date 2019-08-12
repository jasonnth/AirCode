package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.beta.models.guidebook.GuidebookPlace;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.GuidebookPlaceEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.GuidebookPlaceEpoxyModel_;
import com.airbnb.android.explore.C0857R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.PlaceCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

public class MTPlacesTrayCarouselAdapter extends AirEpoxyAdapter {
    private final CarouselItemClickListener carouselClickListener;
    private int selectedPosition = -1;

    static final class CardHolder extends AirViewHolder {
        @BindView
        PlaceCard placeCard;
        @BindView
        View selectionHighlight;

        CardHolder() {
        }
    }

    public final class CardHolder_ViewBinding implements Unbinder {
        private CardHolder target;

        public CardHolder_ViewBinding(CardHolder target2, View source) {
            this.target = target2;
            target2.selectionHighlight = Utils.findRequiredView(source, C0857R.C0859id.selection_highlight, "field 'selectionHighlight'");
            target2.placeCard = (PlaceCard) Utils.findRequiredViewAsType(source, C0857R.C0859id.place_card, "field 'placeCard'", PlaceCard.class);
        }

        public void unbind() {
            CardHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.selectionHighlight = null;
            target2.placeCard = null;
        }
    }

    public static abstract class CardModel extends AirEpoxyModelWithHolder<CardHolder> {
        GuidebookPlaceEpoxyModel placeCardModel;
        boolean selected;

        public void bind(CardHolder holder) {
            super.bind(holder);
            this.placeCardModel.bind(holder.placeCard);
            ViewLibUtils.setVisibleIf(holder.selectionHighlight, this.selected);
        }
    }

    public class CardModel_ extends CardModel implements GeneratedModel<CardHolder> {
        private OnModelBoundListener<CardModel_, CardHolder> onModelBoundListener_epoxyGeneratedModel;
        private OnModelUnboundListener<CardModel_, CardHolder> onModelUnboundListener_epoxyGeneratedModel;

        public void handlePreBind(EpoxyViewHolder holder, CardHolder object, int position) {
        }

        public void handlePostBind(CardHolder object, int position) {
            if (this.onModelBoundListener_epoxyGeneratedModel != null) {
                this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
            }
        }

        public CardModel_ onBind(OnModelBoundListener<CardModel_, CardHolder> listener) {
            onMutation();
            this.onModelBoundListener_epoxyGeneratedModel = listener;
            return this;
        }

        public void unbind(CardHolder object) {
            super.unbind(object);
            if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
                this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
            }
        }

        public CardModel_ onUnbind(OnModelUnboundListener<CardModel_, CardHolder> listener) {
            onMutation();
            this.onModelUnboundListener_epoxyGeneratedModel = listener;
            return this;
        }

        public CardModel_ selected(boolean selected) {
            onMutation();
            this.selected = selected;
            return this;
        }

        public boolean selected() {
            return this.selected;
        }

        public CardModel_ placeCardModel(GuidebookPlaceEpoxyModel placeCardModel) {
            onMutation();
            this.placeCardModel = placeCardModel;
            return this;
        }

        public GuidebookPlaceEpoxyModel placeCardModel() {
            return this.placeCardModel;
        }

        public CardModel_ showDivider(Boolean showDivider) {
            onMutation();
            this.showDivider = showDivider;
            return this;
        }

        public Boolean showDivider() {
            return this.showDivider;
        }

        public CardModel_ showDivider(boolean showDivider) {
            super.showDivider(showDivider);
            return this;
        }

        /* renamed from: id */
        public CardModel_ m5782id(long id) {
            super.mo11716id(id);
            return this;
        }

        /* renamed from: id */
        public CardModel_ m5787id(Number... ids) {
            super.mo11721id(ids);
            return this;
        }

        /* renamed from: id */
        public CardModel_ m5783id(long id1, long id2) {
            super.mo11717id(id1, id2);
            return this;
        }

        /* renamed from: id */
        public CardModel_ m5784id(CharSequence key) {
            super.mo11718id(key);
            return this;
        }

        /* renamed from: id */
        public CardModel_ m5786id(CharSequence key, CharSequence... otherKeys) {
            super.mo11720id(key, otherKeys);
            return this;
        }

        /* renamed from: id */
        public CardModel_ m5785id(CharSequence key, long id) {
            super.mo11719id(key, id);
            return this;
        }

        public CardModel_ layout(int arg0) {
            super.layout(arg0);
            return this;
        }

        public CardModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
            super.spanSizeOverride(arg0);
            return this;
        }

        public CardModel_ show() {
            super.show();
            return this;
        }

        public CardModel_ show(boolean show) {
            super.show(show);
            return this;
        }

        public CardModel_ hide() {
            super.hide();
            return this;
        }

        /* access modifiers changed from: protected */
        public CardHolder createNewHolder() {
            return new CardHolder();
        }

        /* access modifiers changed from: protected */
        public int getDefaultLayout() {
            return C0857R.layout.view_holder_places_tray;
        }

        public CardModel_ reset() {
            this.onModelBoundListener_epoxyGeneratedModel = null;
            this.onModelUnboundListener_epoxyGeneratedModel = null;
            this.selected = false;
            this.placeCardModel = null;
            this.showDivider = null;
            super.reset();
            return this;
        }

        public boolean equals(Object o) {
            boolean z;
            if (o == this) {
                return true;
            }
            if (!(o instanceof CardModel_) || !super.equals(o)) {
                return false;
            }
            CardModel_ that = (CardModel_) o;
            if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
                return false;
            }
            if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
                z = true;
            } else {
                z = false;
            }
            if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.selected != that.selected) {
                return false;
            }
            if (this.placeCardModel != null) {
                if (!this.placeCardModel.equals(that.placeCardModel)) {
                    return false;
                }
            } else if (that.placeCardModel != null) {
                return false;
            }
            if (this.showDivider != null) {
                if (!this.showDivider.equals(that.showDivider)) {
                    return false;
                }
            } else if (that.showDivider != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3 = 1;
            int i4 = 0;
            int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
            if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
                i = 1;
            } else {
                i = 0;
            }
            int i5 = (hashCode + i) * 31;
            if (!this.selected) {
                i3 = 0;
            }
            int i6 = (i5 + i3) * 31;
            if (this.placeCardModel != null) {
                i2 = this.placeCardModel.hashCode();
            } else {
                i2 = 0;
            }
            int i7 = (i6 + i2) * 31;
            if (this.showDivider != null) {
                i4 = this.showDivider.hashCode();
            }
            return i7 + i4;
        }

        public String toString() {
            return "MTPlacesTrayCarouselAdapter$CardModel_{selected=" + this.selected + ", placeCardModel=" + this.placeCardModel + ", showDivider=" + this.showDivider + "}" + super.toString();
        }
    }

    public interface CarouselItemClickListener {
        void onCarouselItemClicked(View view, GuidebookPlace guidebookPlace);
    }

    public MTPlacesTrayCarouselAdapter(CarouselItemClickListener carouselClickListener2) {
        this.carouselClickListener = carouselClickListener2;
    }

    public void setItems(List<GuidebookPlace> dataList) {
        this.models.clear();
        for (GuidebookPlace place : dataList) {
            this.models.add(buildGuidebookPlaceModel(place));
        }
        notifyDataSetChanged();
    }

    private EpoxyModel<?> buildGuidebookPlaceModel(GuidebookPlace place) {
        return new CardModel_().placeCardModel(new GuidebookPlaceEpoxyModel_().boldText(place.getBoldSubtitle()).regularText(place.getNonBoldSubtitle()).photo((Photo) place.getCoverPhotos().get(0)).cardClickListener((OnClickListener) null));
    }

    public void setSelectedPosition(int selectedPosition2) {
        setSelected(this.selectedPosition, false);
        this.selectedPosition = selectedPosition2;
        setSelected(selectedPosition2, true);
    }

    private void setSelected(int position, boolean selected) {
        if (position != -1 && position < getItemCount()) {
            ((CardModel_) ((EpoxyModel) this.models.get(this.selectedPosition))).selected(selected);
        }
    }
}
