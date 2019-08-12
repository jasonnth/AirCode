package com.airbnb.android.lib.enums;

import android.content.res.ColorStateList;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.ArrayList;

public class InstantBookGuestRequirementsAdapter extends Adapter<ViewHolder> {
    private final ArrayList<GuestRequirement> guestRequirements;
    private final InstantBookingAllowedCategory instantBookCategory;

    private enum GuestRequirement {
        ContactInfo(C0880R.string.booking_requirements_contact),
        ProfilePhoto(C0880R.string.profile_photo),
        PaymentInfo(C0880R.string.payment_information),
        TripPurpose(C0880R.string.booking_requirements_trip_purpose),
        HouseRules(C0880R.string.booking_requirements_house_rules, C0880R.string.booking_requirements_house_rules_desc),
        GovermentId(C0880R.string.booking_requirements_government_id),
        HighRating(C0880R.string.booking_requirements_high_rating),
        GoodReviews(C0880R.string.booking_requirements_good_reviews);
        
        /* access modifiers changed from: private */
        public final int subtitleId;
        /* access modifiers changed from: private */
        public final int titleId;

        private GuestRequirement(int titleId2) {
            this.titleId = titleId2;
            this.subtitleId = 0;
        }

        private GuestRequirement(int titleId2, int subtitleId2) {
            this.titleId = titleId2;
            this.subtitleId = subtitleId2;
        }

        public boolean isAlwaysIncluded() {
            switch (this) {
                case ContactInfo:
                case ProfilePhoto:
                case PaymentInfo:
                case TripPurpose:
                case HouseRules:
                    return true;
                case GovermentId:
                case HighRating:
                case GoodReviews:
                    return false;
                default:
                    throw new UnhandledStateException(this);
            }
        }
    }

    static class ViewHolder extends android.support.p002v7.widget.RecyclerView.ViewHolder {
        @BindView
        ColorizedIconView checkMark;
        @BindView
        AirTextView subtitle;
        private final ColorStateList textColor = this.title.getTextColors();
        @BindView
        AirTextView title;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.booking_requirement, parent, false));
            ButterKnife.bind(this, this.itemView);
        }

        public void bind(GuestRequirement requirement, InstantBookingAllowedCategory instantBookCategory) {
            boolean showDisabledRequirement;
            boolean z = true;
            this.title.setText(requirement.titleId);
            if (instantBookCategory.isInstantBookEnabled() || requirement.isAlwaysIncluded()) {
                showDisabledRequirement = false;
            } else {
                showDisabledRequirement = true;
            }
            TextUtil.showStrikeThrough(this.title, showDisabledRequirement);
            this.title.setTextColor(showDisabledRequirement ? this.subtitle.getTextColors() : this.textColor);
            this.checkMark.setDrawableId(showDisabledRequirement ? C0880R.C0881drawable.n2_ic_x_white : C0880R.C0881drawable.n2_ic_check_white);
            this.checkMark.setColorStateListRes(showDisabledRequirement ? C0880R.color.c_foggy : C0880R.color.c_rausch);
            if (requirement.subtitleId != 0) {
                this.subtitle.setText(requirement.subtitleId);
                this.subtitle.setVisibility(0);
                AirTextView airTextView = this.subtitle;
                if (instantBookCategory.isInstantBookEnabled()) {
                    z = false;
                }
                TextUtil.showStrikeThrough(airTextView, z);
                return;
            }
            this.subtitle.setVisibility(8);
        }

        public void bind(GuestRequirement requirement) {
            this.title.setText(requirement.titleId);
            this.subtitle.setVisibility(8);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target2, View source) {
            this.target = target2;
            target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.booking_requirement_desc, "field 'title'", AirTextView.class);
            target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.booking_requirement_subtitle, "field 'subtitle'", AirTextView.class);
            target2.checkMark = (ColorizedIconView) Utils.findRequiredViewAsType(source, C0880R.C0882id.booking_requirement_check_mark, "field 'checkMark'", ColorizedIconView.class);
        }

        public void unbind() {
            ViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.title = null;
            target2.subtitle = null;
            target2.checkMark = null;
        }
    }

    public InstantBookGuestRequirementsAdapter(InstantBookingAllowedCategory category) {
        GuestRequirement[] values;
        boolean alwaysShow;
        boolean isGovernmentId;
        boolean isRating;
        this.guestRequirements = new ArrayList<>();
        this.instantBookCategory = category;
        for (GuestRequirement requirement : GuestRequirement.values()) {
            if (requirement.isAlwaysIncluded() || !category.isInstantBookEnabled()) {
                alwaysShow = true;
            } else {
                alwaysShow = false;
            }
            if (requirement == GuestRequirement.GovermentId) {
                isGovernmentId = true;
            } else {
                isGovernmentId = false;
            }
            if (requirement == GuestRequirement.HighRating || requirement == GuestRequirement.GoodReviews) {
                isRating = true;
            } else {
                isRating = false;
            }
            if (alwaysShow || (category.isGovernmentIdNeeded() && isGovernmentId) || (isRating && category.isHighRatingNeeded())) {
                this.guestRequirements.add(requirement);
            }
        }
    }

    public InstantBookGuestRequirementsAdapter() {
        GuestRequirement[] values;
        this.guestRequirements = new ArrayList<>();
        this.instantBookCategory = null;
        for (GuestRequirement requirement : GuestRequirement.values()) {
            if (requirement.isAlwaysIncluded()) {
                this.guestRequirements.add(requirement);
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (this.instantBookCategory != null) {
            holder.bind((GuestRequirement) this.guestRequirements.get(position), this.instantBookCategory);
        } else {
            holder.bind((GuestRequirement) this.guestRequirements.get(position));
        }
    }

    public int getItemCount() {
        return this.guestRequirements.size();
    }
}
